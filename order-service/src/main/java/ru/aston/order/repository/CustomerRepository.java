package ru.aston.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.order.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
