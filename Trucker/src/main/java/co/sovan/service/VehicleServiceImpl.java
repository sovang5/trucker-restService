package co.sovan.service;

import co.sovan.Exception.ResourceNotFoundException;

import co.sovan.entity.Vehicle;
import co.sovan.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Transactional
//  Method to return all the vehicle's information
    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }
    @Transactional
//  Method to return a specific  vehicle information
    public Vehicle findOne(String id) {
        Optional<Vehicle> existing = vehicleRepository.findById(id);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("The vehicle Id "+id+"is not present in the Database");
        }
        return existing.get();
    }
    @Transactional
//  Method to Store Vehicle information
    public List<Vehicle> create(List<Vehicle> vehicle) {
        return (List<Vehicle>)vehicleRepository.saveAll(vehicle);
        /*for (Vehicle v:vehicle
             ) {
            vehicleRepository.save(v);
        }*/
        //return vehicle;
    }
}
