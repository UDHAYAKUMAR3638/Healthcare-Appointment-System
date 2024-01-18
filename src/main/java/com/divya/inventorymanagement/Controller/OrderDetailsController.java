package com.divya.inventorymanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divya.inventorymanagement.Model.OrderDetails;
import com.divya.inventorymanagement.Service.OrderDetailsService;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    // Post request to add order details
    @PostMapping("/addorderdetails")
    public ResponseEntity<OrderDetails> addOrderDetails(@RequestBody OrderDetails orderDetails) {
        try {
            return ResponseEntity.ok(orderDetailsService.addOrderDetails(orderDetails));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Get request to get all order details
    @GetMapping("/getallorderdetails")
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        try {
            return ResponseEntity.ok(orderDetailsService.getAllOrderDetails());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Get request to get order details by id
    @GetMapping("/getorderdetailsbyid/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable int id){
        try{
            return ResponseEntity.ok(orderDetailsService.getOrderDetailsById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Put request to update order details by id
    @PutMapping("/updateorderdetailsbyid/{id}")
    public ResponseEntity<OrderDetails> updateOrderDetailsById(@PathVariable int id, @RequestBody OrderDetails orderDetails){
        try{
            return ResponseEntity.ok(orderDetailsService.updateOrderDetailsById(id, orderDetails));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Delete request to delete order details by id
    @PutMapping("/deleteorderdetailsbyid/{id}")
    public ResponseEntity<String> deleteOrderDetailsById(@PathVariable int id){
        try{
            return ResponseEntity.ok(orderDetailsService.deleteOrderDetailsById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    
}
