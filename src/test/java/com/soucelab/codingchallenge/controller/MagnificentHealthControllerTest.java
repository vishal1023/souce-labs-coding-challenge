package com.soucelab.codingchallenge.controller;

import com.soucelab.codingchallenge.service.MagnificentHealthCheckService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MagnificentHealthController.class)
public class MagnificentHealthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MagnificentHealthCheckService personDataService;

    @Test
    public void shouldGetHealthOfMagnificentService() throws Exception {
        when(personDataService.getHealth()).thenReturn("Healthy");

        mockMvc.perform(get("/magnificent/health"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnHealthMatrix() throws Exception {
        when(personDataService.getHealth()).thenReturn("Healthy");

        mockMvc.perform(get("/magnificent/health-matrix"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
