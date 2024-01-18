package com.divya.inventorymanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divya.inventorymanagement.Model.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer>{
    
}
