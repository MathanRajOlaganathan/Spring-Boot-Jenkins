package com.example.demo.controller;

import com.example.demo.service.GamingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GamingControllerTest {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;

    @MockBean
    private GamingService gamingService;

    private List<String> stub;

    @BeforeEach
    void init(){
        restTemplate = new RestTemplate();
       stub = Stream.of("CSGO", "NFS", "Prince Of Persia").collect(Collectors.toList());
        when(gamingService.getGames()).thenReturn(stub);
    }

    @Test
    void getGames() throws Exception {

      ResponseEntity<List<String>> responseEntity = restTemplate.exchange("http://localhost:8080/games"
              , HttpMethod.GET,null, new ParameterizedTypeReference<List<String>>() {
              });
        assertEquals(200,responseEntity.getStatusCode().value(),"get request for games failed");
        assertEquals(stub
                ,responseEntity.getBody(),"get request for games failed");
    }
}