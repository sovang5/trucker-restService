package co.sovan.service;


import co.sovan.entity.Location;
import co.sovan.entity.VehicleStatus;
import co.sovan.repository.VehicleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleStatusServiceImpl implements  VehicleStatusService {
    @Autowired
    VehicleStatusRepository vehicleStatusRepository;
    @Transactional
    public VehicleStatus create(VehicleStatus vehicleStatus) {
         vehicleStatusRepository.save(vehicleStatus);
         return vehicleStatus;
    }

    public List<VehicleStatus> findAll() {
        return (List<VehicleStatus>)vehicleStatusRepository.findAll();
    }

    @Transactional
    public List<Location> findLocation(String vin) {
        System.out.println("Come here");

        List<VehicleStatus> entry=vehicleStatusRepository.findAllByVin(vin);
        List<Location> location= new ArrayList<Location>();
        System.out.println(new Date());
        for(int i=0;i<entry.size();i++){
            if(((new Date().getTime()-entry.get(i).getTimestamp().getTime())/(1000*60))<30) {
                location.add(new Location(entry.get(i).getLatitude(), entry.get(i).getLongitude(), entry.get(i).getTimestamp()));
            }
        }
        return location;
    }
}
