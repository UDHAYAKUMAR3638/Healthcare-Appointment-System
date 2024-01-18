package com.divya.inventorymanagement.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private ProductOrder order;

    @ManyToOne
    @JoinColumn(name = "Contact_id", nullable = false)
    private Contact customer;

    private LocalDate orderDate;

    private BigDecimal totalAmount;

    
}
