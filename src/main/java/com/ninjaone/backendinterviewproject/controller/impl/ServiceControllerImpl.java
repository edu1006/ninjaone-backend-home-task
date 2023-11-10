package com.ninjaone.backendinterviewproject.controller.impl;


import com.ninjaone.backendinterviewproject.controller.ServiceController;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServiceCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class ServiceControllerImpl implements ServiceController {
    private final ServiceCatalogService serviceCatalogService;
    private final DeviceService deviceService;

    public ServiceControllerImpl(ServiceCatalogService serviceCatalogService, DeviceService deviceService) {
        this.serviceCatalogService = serviceCatalogService;
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<Service> addService(@RequestBody @Valid Service service) {
        Service newService = serviceCatalogService.addNewService(service);
        return new ResponseEntity<>(newService, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        serviceCatalogService.deleteServiceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
