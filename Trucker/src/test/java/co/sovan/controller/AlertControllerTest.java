package co.sovan.controller;

import co.sovan.entity.Alert;
import co.sovan.repository.AlertRepsoitory;
import co.sovan.service.AlertService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK

)
@AutoConfigureMockMvc
@ActiveProfiles("integrationTest")
public class AlertControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AlertRepsoitory alertRepsoitory;

    @Before
    public void setUp()  {
        Alert alert=new Alert();
        alert.setId("101");
        alert.setVin("wb2014");
        alert.setPriority("High");
        alert.setDetails("Rpm is high");
        alertRepsoitory.save(alert);

    }

    @After
    public void cleanUp()  {
        alertRepsoitory.deleteAll();

    }

    @Test
    public void findAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/alerts/wb2014"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin",Matchers.is("wb2014")));

    }
    @Test
    public void findAll404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/alerts/wb2015"))
                .andExpect(MockMvcResultMatchers.status()
                        .isNotFound());


    }

    @Test
    public void findAlertByType404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/alerts/low"))
                .andExpect(MockMvcResultMatchers.status()
                        .isNotFound());


    }



}