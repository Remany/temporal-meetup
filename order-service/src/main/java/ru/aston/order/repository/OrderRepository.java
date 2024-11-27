package ru.aston.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    void deleteById(String orderId);
}
