package co.sovan.service;


import co.sovan.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    // Method to find all vehicles
    List<Vehicle> findAll();
    // Method to find vehicle by vehicle Id
    Vehicle findOne(String id);
    // Method to Store vehicle information in the database
    Vehicle create(List<Vehicle> vehicle);


}
