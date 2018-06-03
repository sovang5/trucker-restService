package co.sovan.controller;


import co.sovan.entity.Vehicle;
import co.sovan.service.VehicleService;
import co.sovan.service.VehicleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody

@RequestMapping(value="vehicles")
@Api(description="Vehicle Related Endpoints")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
//  Mapping to retrieve all vehicles information

    @RequestMapping(method= RequestMethod.GET)
    @ApiOperation(value="Find All vehicles",
            notes="Returns all vehicles from the database" )
    public List<Vehicle> findAll(){

        return vehicleService.findAll();
    }
//  Mapping to return information of  a Specified Vehicle
    @RequestMapping(method= RequestMethod.GET, value="{id}")
    @ApiOperation(value="Find specific vehicles",
            notes="Returns a single  vehicle  from the database based on ID" )
    public Vehicle findOne(@ApiParam(value = "Id of the vehicle", required = true)@PathVariable("id") String id){

        return vehicleService.findOne(id);
    }
//  Mapping to Store  list of Vehicles
    @CrossOrigin(origins = {"http://mocker.egen.io"}, maxAge=4800)
    @ApiOperation(value="Save All vehicles",
            notes="Save all vehicles in the database" )
    @RequestMapping(method= RequestMethod.PUT)
    public List<Vehicle> create(@RequestBody List<Vehicle> vehicle){
        return vehicleService.create(vehicle);
    }
}
