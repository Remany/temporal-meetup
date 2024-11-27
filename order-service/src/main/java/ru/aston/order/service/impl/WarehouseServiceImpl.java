package ru.aston.order.service.impl;

import io.temporal.spring.boot.ActivityImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.order.domain.Product;
import ru.aston.order.dto.OrderRequest;
import ru.aston.order.exception.InsufficientStockException;
import ru.aston.order.repository.ProductRepository;
import ru.aston.order.service.WarehouseService;

@Service
@RequiredArgsConstructor
@ActivityImpl(taskQueues = "WarehouseTaskQueue")
public class WarehouseServiceImpl implements WarehouseService {

    private final ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public void stockCheck(OrderRequest orderReq) {
        Product product = repository.findProductByProductName(orderReq.getName());
        if (orderReq.getAmount() > product.getAmount()) {
            throw new InsufficientStockException(
                    String.format("Продукт %s отсутствует на складе", orderReq));
        }
    }
}
