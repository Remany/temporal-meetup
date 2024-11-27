package ru.aston.order.service.impl;

import io.temporal.spring.boot.ActivityImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.order.mapper.OrderMapper;
import ru.aston.order.dto.OrderRequest;
import ru.aston.order.repository.CustomerRepository;
import ru.aston.order.repository.OrderRepository;
import ru.aston.order.repository.ProductRepository;
import ru.aston.order.service.OrderService;

@Service
@RequiredArgsConstructor
@ActivityImpl(taskQueues = "OrderTaskQueue")
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    private final OrderMapper mapper;

    @Override
    @Transactional
    public void cancelOrder(String orderId) {
        var order = orderRepository.findById(orderId).orElseThrow();
        var product = productRepository.findProductByProductName(order.getProductName());
        product.setAmount(product.getAmount() + order.getAmount());
        orderRepository.deleteById(orderId);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public String createOrder(OrderRequest orderReq) {
        var order = mapper.toOrder(orderReq);
        var customer = customerRepository.findById(orderReq.getCustomerId()).orElseThrow();
        order.setCustomer(customer);
        var product = productRepository.findProductByProductName(order.getProductName());
        product.setAmount(product.getAmount() - orderReq.getAmount());
        productRepository.save(product);
        order.setProduct(product);
        var res = orderRepository.save(order);
        return res.getId();
    }
}
