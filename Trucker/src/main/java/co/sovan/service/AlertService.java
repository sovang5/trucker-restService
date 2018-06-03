package co.sovan.service;


import co.sovan.entity.Alert;
import co.sovan.entity.VehicleStatus;

import java.util.List;
import java.util.Optional;

public interface AlertService {
    // method to generate alert for  specified vehicle
    void generateAlert(VehicleStatus vehicleStatus);
    //method to generate all alerts for a specified vehicle
    List<Alert> getAllAlert(String id);
    //method to generate HIGH alerts with in 2 hours
    List<Alert> getAlertByType(String S);

}
