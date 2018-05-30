package co.sovan.controller;


import co.sovan.entity.Alert;
import co.sovan.entity.Vehicle;
import co.sovan.service.AlertService;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody

@RequestMapping(value="alerts")
public class AlertController {
    @Autowired
    AlertService alertService;
//  mapping to get all Alerts specified to a vehicle
    @RequestMapping(method= RequestMethod.GET, value="{id}")
    public List<Alert> findAll(@PathVariable("id") String id){

        return alertService.getAllAlert(id);
    }
//  Mapping to get all Alerts with High priority
    @RequestMapping(method= RequestMethod.GET, value="high")
    public List<Alert> findAlertByType(){
        return alertService.getAlertByType();
    }
}
