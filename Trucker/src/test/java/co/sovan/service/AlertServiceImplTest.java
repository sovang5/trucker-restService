package co.sovan.service;

import co.sovan.Exception.ResourceNotFoundException;
import co.sovan.entity.Alert;
import co.sovan.entity.VehicleStatus;
import co.sovan.repository.AlertRepsoitory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class AlertServiceImplTest {

    @TestConfiguration
    static class AlertServiceImplTestConfiguration{
        @Bean
        public AlertService getService(){

            return new AlertServiceImpl();
        }

    }

    @Autowired
    private AlertService alertService;

    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private AlertRepsoitory alertRepsoitory;

    @MockBean
    private VehicleStatus vehicleStatus;

   private List<Alert> alertList=new ArrayList<Alert>();

    @Before
    public void setUp(){
        Alert alert=new Alert();
        alert.setVin("wb2014");
        alert.setDetails("tyre pressure is low");
        alert.setPriority("High");
        alertList.add(alert);
        /*Alert alert1=new Alert();
        alert1.setVin("wb2015");
        alert1.setDetails("Engine RPM is high");
        alert1.setPriority("High");*/

       /* Mockito.when(alertRepsoitory.save(alert1))
                .thenReturn(alert);*/
        Mockito.when(alertRepsoitory.findAllByVin(alert.getVin()))
                .thenReturn(alertList);
        Mockito.when(alertRepsoitory.findAllByType(alert.getPriority()))
                .thenReturn(alertList);




    }

    @Test
    public void generateAlert() throws Exception {

        //alertService.generateAlert(vehicleStatus);

        Alert alert=new Alert();
        alert.setId(null);
        alert.setVin("wb2014");
        alert.setDetails("tyre pressure is low");
        alert.setPriority("High");

        Mockito.when(alertRepsoitory.save(alert))
                .then(invocation->{
                alert.setId("new-Id");
                return null;
        });
        alertService.generateAlert(vehicleStatus);

        //verify that the id is not null
        Assert.assertNotNull("new alert id should exist", alert.getId());

        //verify that the id is same as the mock id
        Assert.assertEquals("Alert id should be same", "new-Id", alert.getId());

        //verify that the repository.save() was called 1 times
        Mockito.verify(alertRepsoitory, Mockito.times(1))
                .save(alert);


    }

    @Test
    public void getAllAlert() throws Exception {
        List<Alert> alert=alertService.getAllAlert("wb2014");
        Assert.assertEquals("vehicle's History Alerts are found",alert,alertList);

    }
    @Test(expected = ResourceNotFoundException.class)
    public void getAllAlertNotFound() throws Exception {
        List<Alert> alert=alertService.getAllAlert("wb20145");

    }

    @Test
    public void getAlertByType() throws Exception {
        List<Alert> alert=alertService.getAlertByType("High");
        Assert.assertEquals("vehicle's High Alerts are found",alert,alertList);
    }
    @Test(expected = ResourceNotFoundException.class)
    public void getAlertByTypeNotFound() throws Exception {
        List<Alert> alert=alertService.getAlertByType("Low");
        Assert.assertEquals("vehicle's High Alerts are  not found",alert,alertList);
    }

}