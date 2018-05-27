package co.sovan.service;

import co.sovan.Exception.RunTimeErrorOccured;
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
    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }
    @Transactional
    public Vehicle findOne(String id) {
        Optional<Vehicle> existing = vehicleRepository.findById(id);
        if (!existing.isPresent()) {
            throw new RunTimeErrorOccured("Vehicle with id " + id + " doesn't exist.");
        }
        return existing.get();
    }
    @Transactional
    public Vehicle create(List<Vehicle> vehicle) {
        for (Vehicle v:vehicle
             ) {
            vehicleRepository.save(v);
        }
        return vehicle.get(0);
    }
}
