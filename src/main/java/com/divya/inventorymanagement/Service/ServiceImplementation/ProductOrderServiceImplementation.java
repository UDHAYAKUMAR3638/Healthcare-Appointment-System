package com.divya.inventorymanagement.Service.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divya.inventorymanagement.Model.ProductOrder;
import com.divya.inventorymanagement.Repository.ProductOrderRepository;
import com.divya.inventorymanagement.Service.ProductOrderService;

@Service
public class ProductOrderServiceImplementation implements ProductOrderService{

    @Autowired
    private ProductOrderRepository productOrderRepository;
    
    @Override
    public String deleteProductOrderById(int id) {
        try{
            productOrderRepository.deleteById(id);
            return "Product order deleted successfully";
        }catch(Exception e){
            return "Product order not found";
        }
    }

    @Override
    public ProductOrder updateProductOrderById(int id, ProductOrder productOrder) {
       try{
           ProductOrder productOrderToUpdate = productOrderRepository.findById(id).get();
           productOrderRepository.save(productOrderToUpdate);
           return productOrderToUpdate;
       }
       catch(Exception e){
           return null;
       }
    }

    @Override
    public ProductOrder getProductOrderById(int id) {
        
        try{
            return productOrderRepository.findById(id).get();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<ProductOrder> getAllProductOrders() {
        try{
            return productOrderRepository.findAll();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public ProductOrder addProductOrder(ProductOrder productOrder) {
        try{
            return productOrderRepository.save(productOrder);
        }catch(Exception e){
            return null;
        }
    }
    
}
