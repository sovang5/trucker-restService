package co.sovan.service;

import co.sovan.Exception.ResourceNotFoundException;
import co.sovan.entity.Location;
import co.sovan.entity.VehicleStatus;
import co.sovan.repository.VehicleRepository;
import co.sovan.repository.VehicleStatusRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class VehicleStatusServiceImplTest {

    @TestConfiguration
    static class VehicleStatusServiceImplTestConfiguration{
        @Bean
        public VehicleStatusService getService(){
            return new VehicleStatusServiceImpl();
        }

    }

    @Autowired
    private VehicleStatusService vehicleStatusService;

    @MockBean
    private VehicleStatusRepository vehicleStatusRepository;

    List<VehicleStatus> vehicleStatus;
    private VehicleStatus vStatus=new VehicleStatus();

    @Before
    public void setUp(){
        vStatus.setId("123456789");
        vStatus.setVin("wb2014");
        vStatus.setEngineHp(250);
        vStatus.setEngineRpm(6500);
        vStatus.setFuelVolume(50);
        vStatus.setSpeed(60);
        vStatus.setLatitude(41.803194);
        vStatus.setLongitude(-88.144406);
        vStatus.setTimestamp(new Date());
        vehicleStatus= Collections.singletonList(vStatus);


        Mockito.when(vehicleStatusRepository.findAll())
                .thenReturn(vehicleStatus);
        Mockito.when(vehicleStatusRepository.findAllByVin(vStatus.getVin()))
                .thenReturn(vehicleStatus);

    }

    @Test
    public void create() throws Exception {
        VehicleStatus newStatus=new VehicleStatus();
        newStatus.setId("123456789");
        newStatus.setVin(null);
        newStatus.setEngineHp(250);
        newStatus.setEngineRpm(6500);
        newStatus.setFuelVolume(50);
        newStatus.setSpeed(60);
        newStatus.setLatitude(41.803194);
        newStatus.setLongitude(-88.144406);
        newStatus.setTimestamp(new Date());
        Mockito.when(vehicleStatusRepository.save(newStatus))
                .then(invocation->{
                    newStatus.setVin("wb2015");
                    return  newStatus;
                });

       VehicleStatus vss= vehicleStatusService.create(newStatus);
        Assert.assertEquals("Created vehicle Status",vss.getVin(),"wb2015");

        Mockito.verify(vehicleStatusRepository, Mockito.times(1))
                .save(newStatus);


    }

    @Test
    public void findAll() throws Exception {
        List<VehicleStatus> vService=vehicleStatusService.findAll();
        Assert.assertEquals("Vehicle Status retrieved Successsfully",vehicleStatus,vService);

    }


    @Test
    public void findLocation() throws Exception {

        List<Location> location=vehicleStatusService.findLocation("wb2014");
        Assert.assertEquals("Vehicle Location Found",vStatus.getLatitude(),location.get(0).getLatitude(),0.0);


    }
    @Test(expected = ResourceNotFoundException.class)
    public void LocationNotFount() throws Exception {

        List<Location> location=vehicleStatusService.findLocation("wb20145");

    }

}