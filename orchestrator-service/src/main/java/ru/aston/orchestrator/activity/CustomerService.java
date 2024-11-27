package ru.aston.orchestrator.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.math.BigDecimal;

@ActivityInterface
public interface CustomerService {
    @ActivityMethod
    BigDecimal checkBalance(String customerId);

    @ActivityMethod
    void updateCustomerBalance(String customerId, BigDecimal newBalance);
}
