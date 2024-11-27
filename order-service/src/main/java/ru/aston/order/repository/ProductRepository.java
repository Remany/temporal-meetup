package ru.aston.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.order.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findProductByProductName(String productName);
}
