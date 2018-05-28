package co.sovan.service;


import co.sovan.entity.Alert;
import co.sovan.entity.VehicleStatus;

import java.util.List;

public interface AlertService {
    void generateAlert(VehicleStatus vehicleStatus);
    List<Alert>  getAllAlert(String id);
    List<Alert> getHighAlert(String id);

}
