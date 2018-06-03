package co.sovan.service;

import co.sovan.Exception.ResourceNotFoundException;
import co.sovan.entity.Vehicle;
import co.sovan.repository.VehicleRepository;
import org.junit.After;
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

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {

    @TestConfiguration
    static class VehicleServiceImplTestConfiguration{
        @Bean
        public VehicleService getService(){
            return new VehicleServiceImpl();
        }

    }


    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    private List<Vehicle> vehicleList;
    private List<Vehicle> listVehicle=new ArrayList<Vehicle>();

    @Before
    public void setUp(){
        Vehicle vehicle=new Vehicle();
        vehicle.setVin("wb2011");
        vehicle.setModel("Bmw I3");
        vehicle.setMake("Bmw");
        vehicle.setLastServiceDate(new Date());
        vehicle.setRedlineRpm(6500);
        vehicle.setMaxFuelVolume(23);
        vehicleList=Collections.singletonList(vehicle);

        Mockito.when(vehicleRepository.findAll())
                .thenReturn(vehicleList);

        Mockito.when(vehicleRepository.findById(vehicle.getVin()))
                .thenReturn(Optional.of(vehicle));
        Mockito.when(vehicleRepository.saveAll(listVehicle))
                .thenReturn(listVehicle);





    }

    @After
    public void cleanUp(){
        vehicleRepository.deleteAll();

    }

   /* @Test
    public void findAllError() throws Exception {
        List<Vehicle> vehicles= vehicleService.findAll();
        Assert.assertEquals("Vehicles list is empty", Collections.emptyList(),vehicles);
    }*/
    @Test
    public void findAll() throws Exception {
        List<Vehicle> vehicles= vehicleService.findAll();
        Assert.assertEquals("Vehicles list has Value", vehicleList,vehicles);
    }

    @Test
    public void findOne() throws Exception {
        Vehicle vehicle= vehicleService.findOne("wb2011");
        Assert.assertEquals("Vehicle is Found",vehicleList.get(0),vehicle);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOneNotFound() throws Exception {
        Vehicle vehicle= vehicleService.findOne("wb2012");
        Assert.assertEquals("Vehicle not Found",vehicleList.get(0),vehicle);
    }

    @Test
    public void create() throws Exception {
        Vehicle vehicle=new Vehicle();
        vehicle.setVin("wb2014");
        vehicle.setModel("Bmw I8");
        vehicle.setMake("Bmw");
        vehicle.setLastServiceDate(new Date());
        vehicle.setRedlineRpm(6200);
        vehicle.setMaxFuelVolume(28);
        Vehicle vehicle1=new Vehicle();
        vehicle1.setVin("wb2015");
        vehicle1.setModel("Bmw I8");
        vehicle1.setMake("Bmw");
        vehicle1.setLastServiceDate(new Date());
        vehicle1.setRedlineRpm(6200);
        vehicle1.setMaxFuelVolume(28);

        listVehicle.add(vehicle);
        listVehicle.add(vehicle1);

        Mockito.when(vehicleRepository.saveAll(listVehicle))
                .then(invocation->{
                    listVehicle.get(0).setVin("wb2016");
                    return  listVehicle;
                });

        List<Vehicle> vehicleResult= vehicleService.create(listVehicle);
        Assert.assertEquals("Vehicle inserted in Database",vehicleResult.get(0).getVin(),"wb2016");
        Mockito.verify(vehicleRepository, Mockito.times(1))
                .saveAll(listVehicle);

    }

}