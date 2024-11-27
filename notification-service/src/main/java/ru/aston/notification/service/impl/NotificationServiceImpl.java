package ru.aston.notification.service.impl;

import io.temporal.spring.boot.ActivityImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.aston.notification.service.NotificationService;

@Slf4j
@Service
@RequiredArgsConstructor
@ActivityImpl(taskQueues = "NotificationTaskQueue")
public class NotificationServiceImpl implements NotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void notifyCustomer(String customerId) {
        log.info("notify customer with id: {}", customerId);
        var topic = "customer-notifications";
        var message = "your order has been successfully placed";
        kafkaTemplate.send(topic, message);
    }
}
