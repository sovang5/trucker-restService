package co.sovan.controller;


import co.sovan.entity.Location;
import co.sovan.entity.Vehicle;
import co.sovan.entity.VehicleStatus;
import co.sovan.service.VehicleStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(value="readings")
@CrossOrigin(origins = {"http://mocker.egen.io"}, maxAge=4800)
@Api(description="Vehicle's readings Related Endpoints")
public class VehicleStatusController {
    @Autowired
    VehicleStatusService vehicleStatusService;
//  Method to store Vehicle Status
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="Create Vehicle's Readings",
            notes="Save vehicle's readings in the database" )
    public VehicleStatus create(@RequestBody VehicleStatus vehicleStatus){
        return vehicleStatusService.create(vehicleStatus);
    }
//  Method to retrieve all Vehicle Status
    @ApiOperation(value="Retrieve Vehicle's Readings",
        notes="Retrieve vehicle's readings  from the database" )
    @RequestMapping(method= RequestMethod.GET)
    public List<VehicleStatus> findAll(){

        return vehicleStatusService.findAll();
    }
//  Method to retrieve a specified vehicle's location
    @ApiOperation(value="Retrieve Vehicle's Location",
             notes="Retrieve vehicle's Location  from the database in 30 minutes" )
    @RequestMapping(method = RequestMethod.GET,value = "{vin}")
    public List<Location> findLocation(@ApiParam(value = "Id of the vehicle", required = true)@PathVariable("vin") String vin) {
        return vehicleStatusService.findLocation(vin);

    }

}
