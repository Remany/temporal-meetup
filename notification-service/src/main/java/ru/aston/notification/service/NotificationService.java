package ru.aston.notification.service;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface NotificationService {
    @ActivityMethod
    void notifyCustomer(String customerId);
}
