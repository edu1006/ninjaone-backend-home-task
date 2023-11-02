package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.repository.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceServiceTest {

    @Autowired
    private ServiceCatalogService serviceService;

    @MockBean
    private ServiceRepository serviceRepository;



    @Test
    public void givenValidService_whenAddService_thenServiceIsAdded() {
        Service service = new Service();
        service.setName("Service1");
        service.setCost(10.0);

        when(serviceRepository.existsByName(service.getName())).thenReturn(false);
        when(serviceRepository.save(any(Service.class))).thenReturn(service);

        Service addedService = serviceService.addService(service);

        assertEquals(service.getName(), addedService.getName());
        assertEquals(service.getCost(), addedService.getCost(), 0.0);
    }

    public void givenDuplicateService_whenAddService_thenThrowIllegalArgumentException() {
        Service service = new Service();
        service.setName("Service1");
        service.setCost(10.0);

        when(serviceRepository.existsByName(service.getName())).thenReturn(true);

        assertThrows(IllegalArgumentException.class , () -> serviceService.addService(service)) ;
    }

    @Test
    public void givenServiceId_whenDeleteService_thenServiceIsDeleted() {
        Integer id = 1;

        doNothing().when(serviceRepository).deleteById(id);

        serviceService.deleteService(id);

        verify(serviceRepository, times(1)).deleteById(id);
    }
}