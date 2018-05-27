package co.sovan.controller;


import co.sovan.entity.Vehicle;
import co.sovan.service.VehicleService;
import co.sovan.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody

@RequestMapping(value="vehicles")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method= RequestMethod.GET)
    public List<Vehicle> findAll(){

        return vehicleService.findAll();
    }
    @RequestMapping(method= RequestMethod.GET, value="{id}")
    public Vehicle findOne(@PathVariable("id") String id){

        return vehicleService.findOne(id);
    }
    @CrossOrigin(origins = {"http://mocker.egen.io"}, maxAge=4800)
    @RequestMapping(method= RequestMethod.PUT)
    public Vehicle create(@RequestBody List<Vehicle> vehicle){

        return vehicleService.create(vehicle);
    }
}
