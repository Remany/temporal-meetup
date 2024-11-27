package ru.aston.order.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String productName;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    private Integer amount;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
