package co.sovan.controller;


import co.sovan.entity.Location;
import co.sovan.entity.Vehicle;
import co.sovan.entity.VehicleStatus;
import co.sovan.service.VehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(value="readings")
@CrossOrigin(origins = {"http://mocker.egen.io"}, maxAge=4800)
public class VehicleStatusController {
    @Autowired
    VehicleStatusService vehicleStatusService;
//  Method to store Vehicle Status
    @RequestMapping(method = RequestMethod.POST)
    public VehicleStatus create(@RequestBody VehicleStatus vehicleStatus){
        return vehicleStatusService.create(vehicleStatus);
    }
//  Method to retrieve all Vehicle Status
    @RequestMapping(method= RequestMethod.GET)
    public List<VehicleStatus> findAll(){

        return vehicleStatusService.findAll();
    }
//  Method to retrieve a specified vehicle's location
    @RequestMapping(method = RequestMethod.GET,value = "{vin}")
    public List<Location> findLocation(@PathVariable("vin") String vin) {
        return vehicleStatusService.findLocation(vin);

    }

}
