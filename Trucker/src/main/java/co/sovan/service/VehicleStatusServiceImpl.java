package co.sovan.service;


import co.sovan.Exception.BadRequestException;
import co.sovan.Exception.ResourceNotFoundException;
import co.sovan.entity.Location;
import co.sovan.entity.VehicleStatus;
import co.sovan.repository.VehicleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class VehicleStatusServiceImpl implements  VehicleStatusService {
    @Autowired
    VehicleStatusRepository vehicleStatusRepository;
    @Autowired
    AlertService alertService;
    @Transactional
//  Method to Store Vehicle Status
    public VehicleStatus create(VehicleStatus vehicleStatus) {

         alertService.generateAlert(vehicleStatus);
         return vehicleStatusRepository.save(vehicleStatus);
    }
//  Method to return all vehicle's Status
    public List<VehicleStatus> findAll() {
        return (List<VehicleStatus>)vehicleStatusRepository.findAll();
    }


//  Method to return specified vehicle location
    @Transactional
    public List<Location> findLocation(String vin) {

        List<VehicleStatus> entry=vehicleStatusRepository.findAllByVin(vin);
        if(entry.isEmpty()){
            throw new ResourceNotFoundException("The vehicle Id "+vin+"is not present in the Database");
        }
        List<Location> location= new ArrayList<Location>();
        // Checking the time is less than 30 mins or not
        for(int i=0;i<entry.size();i++){
            if(((new Date().getTime()-entry.get(i).getTimestamp().getTime())/(1000*60))<30) {
                location.add(new Location(entry.get(i).getLatitude(), entry.get(i).getLongitude(), entry.get(i).getTimestamp()));
            }
        }
        return location;
    }
}
