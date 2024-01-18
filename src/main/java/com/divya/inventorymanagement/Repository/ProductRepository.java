package com.divya.inventorymanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divya.inventorymanagement.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    
}
