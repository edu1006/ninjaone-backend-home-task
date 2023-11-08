package com.ninjaone.backendinterviewproject.controller;


import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServiceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceCatalogService serviceCatalogService;
    private final DeviceService deviceService;

    @Autowired
    public ServiceController(ServiceCatalogService serviceCatalogService, DeviceService deviceService) {
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
