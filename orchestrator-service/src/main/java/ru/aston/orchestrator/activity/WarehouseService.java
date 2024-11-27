package ru.aston.orchestrator.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import ru.aston.orchestrator.dto.OrderRequest;

@ActivityInterface
public interface WarehouseService {
    @ActivityMethod
    void stockCheck(OrderRequest orderReq);
}
