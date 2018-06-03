package co.sovan.controller;


import co.sovan.entity.Alert;
import co.sovan.entity.Vehicle;
import co.sovan.service.AlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody

@RequestMapping(value="alerts")
@Api(description="Vehicle's Alert Related Endpoints")
public class AlertController {
    @Autowired
    AlertService alertService;
//  mapping to get all Alerts specified to a vehicle
    @RequestMapping(method= RequestMethod.GET, value="{id}")
    @ApiOperation(value="Find Alerts to a specific vehicles",
            notes="Returns a single  vehicle's alerts  from the database based on ID" )
    public List<Alert> findAll(@PathVariable("id") String id){

        return alertService.getAllAlert(id);
    }
//  Mapping to get all Alerts with High priority
    @RequestMapping(method= RequestMethod.GET, value="high")
    @ApiOperation(value="Find High alerts ",
            notes="Returns all High alerts from the database" )
    public List<Alert> findAlertByType(){
        return alertService.getAlertByType("High");
    }
}
