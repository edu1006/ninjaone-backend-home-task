package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenValidService_whenPostService_thenServiceIsAdded() {
        Service service = new Service();
        service.setName("Service1");
        service.setCost(10.0);

        ResponseEntity<Service> response = restTemplate.postForEntity("/services", service, Service.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(service.getName(), response.getBody().getName());
        assertEquals(service.getCost(), response.getBody().getCost());
    }

}