package co.sovan.service;


import co.sovan.entity.Alert;
import co.sovan.entity.Vehicle;
import co.sovan.entity.VehicleStatus;
import co.sovan.repository.AlertRepsoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService{
    @Autowired
    VehicleService vehicleService;
    @Autowired
    AlertRepsoitory alertRepsoitory;
    public void generateAlert(VehicleStatus vehicleStatus) {
        String vId=vehicleStatus.getVin();
        Vehicle vehicle=vehicleService.findOne(vId);
        if(vehicleStatus.getEngineRpm()>vehicle.getRedlineRpm()){
            createAlert(vehicle,"Engine RPM is greater than Red Line RPM","High");

        }
        if(vehicleStatus.getFuelVolume()<(vehicle.getMaxFuelVolume()/10)){
            createAlert(vehicle,"Fuel Volume is less than 10% of Max Volume","Medium");
        }
        if(checkTyrePressure(vehicleStatus)){
            createAlert(vehicle,"Check the tyre Pressure","Low");
        }
        if(vehicleStatus.isCheckEngineLightOn()||vehicleStatus.isEngineCoolantLow()){
            createAlert(vehicle,"Check the Engine Light or Coolant needs to be changed","Low");
        }




    }

    public List<Alert> getAllAlert(String id) {
        return alertRepsoitory.findAllByVin(id);
    }



    public List<Alert> getAlertByType() {
        List<Alert> newEntry=new ArrayList<Alert>();
        List<Alert> alerts=alertRepsoitory.findAllByVmake("High");

       for(int i=0;i<alerts.size();i++){
           if(((new Date().getTime()-alerts.get(i).getTimeStamp().getTime())/(1000*60))<200){
               newEntry.add(alerts.get(i));
           }
       }

    return newEntry;
    }

    private void createAlert(Vehicle vehicle,String message, String type){
        Alert alert=new Alert();
        alert.setDetails(message);
        alert.setPriority(type);
        alert.setVin(vehicle.getVin());
        alert.setVmake(vehicle.getMake());
        alertRepsoitory.save(alert);

    }

    private boolean checkTyrePressure(VehicleStatus vehicleStatus){
        return vehicleStatus.getTires().getFrontLeft()<32||
                vehicleStatus.getTires().getFrontLeft()>36||
                vehicleStatus.getTires().getFrontRight()<32||
                vehicleStatus.getTires().getFrontRight()>36||
                vehicleStatus.getTires().getRearLeft()<32||
                vehicleStatus.getTires().getRearLeft()>36||
                vehicleStatus.getTires().getRearRight()<32||
                vehicleStatus.getTires().getRearRight()>36;
    }
}
