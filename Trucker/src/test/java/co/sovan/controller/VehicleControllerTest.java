package co.sovan.controller;

import co.sovan.entity.Vehicle;
import co.sovan.repository.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK

)
@AutoConfigureMockMvc
@ActiveProfiles("integrationTest")
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Before
    public void setUp(){
        Vehicle vehicle=new Vehicle();
        vehicle.setVin("wb2014");
        vehicle.setMake("Bmw");
        vehicle.setModel("i8");
        vehicle.setMaxFuelVolume(25);
        vehicle.setRedlineRpm(6500);
        vehicle.setLastServiceDate(new Date());
        vehicle.setYear(2018);
        vehicleRepository.save(vehicle);

    }

    @After
    public void cleanup() {
        vehicleRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
            .andExpect(MockMvcResultMatchers.status()
                    .isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin",Matchers.is("wb2014")));

    }

    @Test
    public void findOne() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/vehicles/wb2014"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model",Matchers.is("i8")));
    }

    @Test
    public void findOne404() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/vehicles/wb2015"))
                .andExpect(MockMvcResultMatchers.status()
                      .isNotFound());

    }

    @Test
    public void create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Vehicle>  vehicleList=new ArrayList<Vehicle>();

        Vehicle vehicle=new Vehicle();
        vehicle.setVin("wb2016");
        vehicle.setMake("Bmw");
        vehicle.setModel("s3");
        vehicle.setMaxFuelVolume(20);
        vehicle.setRedlineRpm(6000);
        vehicle.setLastServiceDate(new Date());
        vehicle.setYear(2011);
        vehicleList.add(vehicle);
        mvc.perform(MockMvcRequestBuilders.put("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(vehicleList))
        )
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("s3")));

        mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void create404() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Vehicle vehicle=new Vehicle();
        vehicle.setVin("wb2016");
        vehicle.setMake("Bmw");
        vehicle.setModel("s3");
        vehicle.setMaxFuelVolume(20);
        vehicle.setRedlineRpm(6000);
        vehicle.setLastServiceDate(new Date());
        vehicle.setYear(2011);

        mvc.perform(MockMvcRequestBuilders.put("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(vehicle))
        )
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());



    }
}