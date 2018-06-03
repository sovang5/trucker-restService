package co.sovan.controller;

import co.sovan.entity.Tires;
import co.sovan.entity.VehicleStatus;
import co.sovan.repository.VehicleStatusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK

)
@AutoConfigureMockMvc
@ActiveProfiles("integrationTest")
public class VehicleStatusControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VehicleStatusRepository vehicleStatusRepository;

    @Before
    public void setUp(){
        Tires tyre=new Tires();
        tyre.setId("101");
        tyre.setFrontLeft(35);
        tyre.setFrontRight(34);
        tyre.setRearLeft(33);
        tyre.setRearRight(34);
        VehicleStatus vStatus=new VehicleStatus();
        vStatus.setId("101");
        vStatus.setVin("1HGCR2F3XFA027534");
        vStatus.setLatitude(41.803194);
        vStatus.setLongitude(-88.144406);
        vStatus.setTimestamp(new Date());
        vStatus.setFuelVolume(25);;
        vStatus.setEngineRpm(5600);
        vStatus.setEngineHp(240);
        vStatus.setCheckEngineLightOn(true);
        vStatus.setEngineCoolantLow(true);
        vStatus.setCruiseControlOn(false);
        vStatus.setTires(tyre);
        vehicleStatusRepository.save(vStatus);


    }

    @After
    public void cleanup() {
        vehicleStatusRepository.deleteAll();
    }


    @Test
    public void create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Tires tyre=new Tires();
        tyre.setId("102");
        tyre.setFrontLeft(35);
        tyre.setFrontRight(34);
        tyre.setRearLeft(33);
        tyre.setRearRight(34);
        VehicleStatus vStatus=new VehicleStatus();
        vStatus.setId("102");
        vStatus.setVin("1HGCR2F3XFA027536");
        vStatus.setLatitude(41.803194);
        vStatus.setLongitude(-88.144406);
        vStatus.setTimestamp(new Date());
        vStatus.setFuelVolume(25);
        vStatus.setEngineRpm(5600);
        vStatus.setEngineHp(240);
        vStatus.setCheckEngineLightOn(true);
        vStatus.setEngineCoolantLow(true);
        vStatus.setCruiseControlOn(false);
        vStatus.setTires(tyre);

        mvc.perform(MockMvcRequestBuilders.post("/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(vStatus))
        )
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vin", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("102")));

        mvc.perform(MockMvcRequestBuilders.get("/readings"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));


    }

    @Test
    public void create400() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Tires tyre = new Tires();
        tyre.setId("102");
        tyre.setFrontLeft(35);
        tyre.setFrontRight(34);
        tyre.setRearLeft(33);
        tyre.setRearRight(34);
        VehicleStatus vStatus = new VehicleStatus();
        vStatus.setId("102");
        vStatus.setVin("1HGCR2F3XFA027536");
        vStatus.setLatitude(41.803194);
        vStatus.setLongitude(-88.144406);
        vStatus.setTimestamp(new Date());
        vStatus.setFuelVolume(25);
        vStatus.setEngineRpm(5600);
        vStatus.setEngineHp(240);
        vStatus.setCheckEngineLightOn(true);
        vStatus.setEngineCoolantLow(true);
        vStatus.setCruiseControlOn(false);
        vStatus.setTires(tyre);
        List<VehicleStatus> vs=new ArrayList<VehicleStatus>();
        vs.add(vStatus);

        mvc.perform(MockMvcRequestBuilders.post("/readings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(vs))
        )
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());
    }

    @Test
    public void findAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/readings"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin",Matchers.is("1HGCR2F3XFA027534")));

    }


    @Test
    public void findLocation() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/readings/1HGCR2F3XFA027534"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].latitude",Matchers.is(41.803194)));

    }
    @Test
    public void findLocation404() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/readings/1HGCR2F3XFA027535"))
                .andExpect(MockMvcResultMatchers.status()
                        .isNotFound());


    }

}