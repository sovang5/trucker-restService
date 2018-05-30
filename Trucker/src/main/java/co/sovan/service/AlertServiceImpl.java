package co.sovan.service;


import co.sovan.Exception.ResourceNotFoundException;
import co.sovan.entity.Alert;
import co.sovan.entity.Vehicle;
import co.sovan.entity.VehicleStatus;
import co.sovan.repository.AlertRepsoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    @Async
//  Implementation to generate alert from the readings of a vehicle
    public void generateAlert(VehicleStatus vehicleStatus) {
        String vId=vehicleStatus.getVin();
        Vehicle vehicle=vehicleService.findOne(vId);

        if(vehicleStatus.getEngineRpm()>vehicle.getRedlineRpm()){
            createAlert(vehicle.getVin(),"Engine RPM is greater than Red Line RPM","High");

        }
        if(vehicleStatus.getFuelVolume()<(vehicle.getMaxFuelVolume()/10)){
            createAlert(vehicle.getVin(),"Fuel Volume is less than 10% of Max Volume","Medium");
        }
        if(checkTyrePressure(vehicleStatus)){
            createAlert(vehicle.getVin(),"Check the tyre Pressure","Low");
        }
        if(vehicleStatus.isCheckEngineLightOn()||vehicleStatus.isEngineCoolantLow()){
            createAlert(vehicle.getVin(),"Check the Engine Light or Coolant needs to be changed","Low");
        }




    }

//  Implementation to retrieve all the alerts for a specified vehicle
    public List<Alert> getAllAlert(String id) {
        List<Alert> existing=alertRepsoitory.findAllByVin(id);
        if(existing.isEmpty()){
            throw new ResourceNotFoundException("The vehicle Id "+id+"is not present in the Database");
        }
        return existing;
    }


//  Implementation to retrieve all the HIGH alerts with in 2 hours
    public List<Alert> getAlertByType() {
        List<Alert> newEntry=new ArrayList<Alert>();
        List<Alert> alerts=alertRepsoitory.findAllByType("High");
        if(alerts.isEmpty()){
            throw new ResourceNotFoundException("There is no High Alert in the Database");
        }
//  2 hours logic calculation
       for(int i=0;i<alerts.size();i++){

           if(((new Date().getTime()-alerts.get(i).getTimeStamp().getTime())/(1000*60))<200){
               newEntry.add(alerts.get(i));
           }
       }

    return newEntry;
    }
//  This method is to create and store the alert
    private void createAlert(String Id,String message, String type){
        Alert alert=new Alert();
        alert.setDetails(message);
        alert.setPriority(type);
        alert.setVin(Id);
        alertRepsoitory.save(alert);

    }
//  This is to check the tyre pressure of a vehicle
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
