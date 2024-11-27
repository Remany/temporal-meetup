package ru.aston.payment.service.impl;

import io.temporal.spring.boot.ActivityImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aston.payment.service.PaymentService;

import java.math.BigDecimal;

@Slf4j
@Service
@ActivityImpl(taskQueues = "PaymentTaskQueue")
public class PaymentServiceImpl implements PaymentService {

    @Override
    public BigDecimal processPayment(BigDecimal customerBalance, BigDecimal price) {
        if (customerBalance.compareTo(price) < 0) {
            log.error("Insufficient funds. Customer balance: {}, Price: {}", customerBalance, price);
            throw new IllegalArgumentException("Insufficient funds for payment");
        }
        BigDecimal newBalance = customerBalance.subtract(price);
        log.info("Payment processed successfully. Remaining balance: {}", newBalance);
        log.info("Order has been paid successfully");
        return newBalance;
    }
}
