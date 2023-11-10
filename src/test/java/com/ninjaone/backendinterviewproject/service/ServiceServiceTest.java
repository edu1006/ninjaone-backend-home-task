package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.repository.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceServiceTest {

    @InjectMocks
    private ServiceCatalogService serviceCatalogService;

    @Mock
    private ServiceRepository serviceRepository;



    @Test
    public void givenService_whenAddNewService_thenServiceIsSaved() {
        // given
        Service service = new Service();
        service.setName("Test Service");
        when(serviceRepository.existsByName(service.getName())).thenReturn(false);
        when(serviceRepository.save(service)).thenReturn(service);

        // when
        Service savedService = serviceCatalogService.addNewService(service);

        // then
        assertEquals(service.getName(), savedService.getName());
        verify(serviceRepository, times(1)).save(service);
    }

    @Test
    public void givenExistingService_whenValidateServiceDoesNotExist_thenIllegalArgumentExceptionIsThrown() {
        // given
        Service service = new Service();
        service.setName("Existing Service");
        when(serviceRepository.existsByName(service.getName())).thenReturn(true);

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceCatalogService.addNewService(service);
        });

        // then
        String expectedMessage = "Service already exists.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void givenServiceId_whenDeleteServiceById_thenServiceIsDeleted() {
        // given
        Integer id = 1;
        doNothing().when(serviceRepository).deleteById(id);

        // when
        serviceCatalogService.deleteServiceById(id);

        // then
        verify(serviceRepository, times(1)).deleteById(id);
    }

    @Test
    public void givenServiceId_whenFindServiceById_thenReturnService() {
        // given
        Integer id = 1;
        Service service = new Service();
        service.setName("Test Service");
        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));

        // when
        Optional<Service> foundService = serviceCatalogService.findServiceById(id);

        // then
        assertTrue(foundService.isPresent());
        assertEquals(service.getName(), foundService.get().getName());
    }

}