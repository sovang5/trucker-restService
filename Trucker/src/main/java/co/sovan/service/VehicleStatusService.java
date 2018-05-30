package co.sovan.service;


import co.sovan.entity.Location;
import co.sovan.entity.VehicleStatus;
import org.apache.el.stream.Optional;

import java.util.List;

public interface VehicleStatusService {
    //method to store Vehicle Status
    VehicleStatus create(VehicleStatus vehicleStatus);
    //method to retrieve Vehicle Status
    List<VehicleStatus>findAll();
    //method to retrieve location of a specified vehicle
    List<Location> findLocation(String vin);

}
