package com.divya.inventorymanagement.Service.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divya.inventorymanagement.Model.OrderDetails;
import com.divya.inventorymanagement.Repository.OrderDetailsRepository;
import com.divya.inventorymanagement.Service.OrderDetailsService;

@Service
public class OrderDetailsServiceImplementation implements OrderDetailsService{

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderDetails addOrderDetails(OrderDetails orderDetails) {
        try{
            return orderDetailsRepository.save(orderDetails);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        try{
            return orderDetailsRepository.findAll();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public OrderDetails getOrderDetailsById(int id) {
        try{
            return orderDetailsRepository.findById(id).get();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public OrderDetails updateOrderDetailsById(int id, OrderDetails orderDetails) {
        try {
            OrderDetails orderDetailsToUpdate = orderDetailsRepository.findById(id).get();
            orderDetailsRepository.save(orderDetailsToUpdate);
            return orderDetailsToUpdate;
        } catch (Exception e) {
            return null;
        } 
    }

    @Override
    public String deleteOrderDetailsById(int id) {
        try{
            orderDetailsRepository.deleteById(id);
            return "Order details deleted successfully";
        }catch(Exception e){
            return "Order details not found";
        }
    }
    
}
