package ru.aston.orchestrator.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface NotificationService {
    @ActivityMethod
    void notifyCustomer(String customerId);
}
