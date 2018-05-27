package co.sovan.service;


import co.sovan.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle findOne(String id);
    Vehicle create(List<Vehicle> vehicle);


}
