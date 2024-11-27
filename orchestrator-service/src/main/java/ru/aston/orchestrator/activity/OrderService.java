package ru.aston.orchestrator.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import ru.aston.orchestrator.dto.OrderRequest;

@ActivityInterface
public interface OrderService {
    @ActivityMethod
    void cancelOrder(String orderId);

    @ActivityMethod
    String createOrder(OrderRequest orderReq);
}