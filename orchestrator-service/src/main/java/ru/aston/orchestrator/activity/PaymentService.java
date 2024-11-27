package ru.aston.orchestrator.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.math.BigDecimal;

@ActivityInterface
public interface PaymentService {
    @ActivityMethod
    BigDecimal processPayment(BigDecimal customerBalance, BigDecimal price);
}
