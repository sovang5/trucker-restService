package co.sovan.service;


import co.sovan.entity.Location;
import co.sovan.entity.VehicleStatus;
import org.apache.el.stream.Optional;

import java.util.List;

public interface VehicleStatusService {
    VehicleStatus create(VehicleStatus vehicleStatus);
    List<VehicleStatus>findAll();
    List<Location> findLocation(String vin);

}
