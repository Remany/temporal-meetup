package ru.aston.order.service.impl;

import io.temporal.spring.boot.ActivityImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.order.repository.CustomerRepository;
import ru.aston.order.service.CustomerService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@ActivityImpl(taskQueues = "CustomerTaskQueue")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public BigDecimal checkBalance(String customerId) {
        var customer = customerRepository.findById(customerId).orElseThrow();
        return customer.getBalance();
    }

    @Override
    @Transactional
    public void updateCustomerBalance(String customerId, BigDecimal newBalance) {
        var customer = customerRepository.findById(customerId).orElseThrow();
        customer.setBalance(newBalance);
        customerRepository.save(customer);
    }
}
