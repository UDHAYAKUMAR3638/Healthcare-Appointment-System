package com.divya.inventorymanagement.Service;

import java.util.List;

import com.divya.inventorymanagement.Model.SupplyRecord;

public interface SupplyRecordService {

    SupplyRecord addSupplyRecord(SupplyRecord supplyRecord);

    List<SupplyRecord> getAllSupplyRecords();

    SupplyRecord getSupplyRecordsBySupplierId(int supplierId);

    SupplyRecord updateSupplyRecordById(int id, SupplyRecord supplyRecord);

    String deleteSupplyRecordById(int id);
    
}
