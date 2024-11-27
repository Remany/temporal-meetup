package ru.aston.order.service;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import ru.aston.order.dto.OrderRequest;

@ActivityInterface
public interface WarehouseService {
    @ActivityMethod
    void stockCheck(OrderRequest orderReq);
}
