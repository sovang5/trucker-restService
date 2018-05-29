package co.sovan.service;


import co.sovan.entity.Alert;
import co.sovan.entity.VehicleStatus;

import java.util.List;
import java.util.Optional;

public interface AlertService {
    void generateAlert(VehicleStatus vehicleStatus);
    List<Alert> getAllAlert(String id);
    List<Alert> getAlertByType();

}
