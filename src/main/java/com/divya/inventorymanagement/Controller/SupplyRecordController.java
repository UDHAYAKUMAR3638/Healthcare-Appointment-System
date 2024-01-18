package com.divya.inventorymanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.divya.inventorymanagement.Model.SupplyRecord;
import com.divya.inventorymanagement.Service.SupplyRecordService;

@RestController
@RequestMapping("api/supplyRecord")
public class SupplyRecordController {
    
    @Autowired
    private SupplyRecordService supplyRecordService;
    
    // Post request to add supply record
    @PostMapping("/addsupplyrecord")
    public ResponseEntity<SupplyRecord> addSupplyRecord(@RequestBody SupplyRecord supplyRecord){
        try{
            return ResponseEntity.ok(supplyRecordService.addSupplyRecord(supplyRecord));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
       
    }
    // Get request to get all supply records
    @GetMapping("/getallsupplyrecords")
    public ResponseEntity<List<SupplyRecord>> getAllSupplyRecords(){
        try{
            return ResponseEntity.ok(supplyRecordService.getAllSupplyRecords());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Get request to get supply records by supplier id
    @GetMapping("/getsupplyrecordsbysupplierid/{supplierId}")
    public ResponseEntity<SupplyRecord> getSupplyRecordsBySupplierId(@RequestParam int supplierId){
        try{
            return ResponseEntity.ok(supplyRecordService.getSupplyRecordsBySupplierId(supplierId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Put request to update supply record by id
    @PostMapping("/updatesupplyrecordbyid/{id}")
    public ResponseEntity<SupplyRecord> updateSupplyRecordById(@RequestParam int id, @RequestBody SupplyRecord supplyRecord){
        try{
            return ResponseEntity.ok(supplyRecordService.updateSupplyRecordById(id, supplyRecord));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Delete request to delete supply record by id
    @PostMapping("/deletesupplyrecordbyid/{id}")
    public ResponseEntity<String> deleteSupplyRecordById(@RequestParam int id){
        try{
            return ResponseEntity.ok(supplyRecordService.deleteSupplyRecordById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
