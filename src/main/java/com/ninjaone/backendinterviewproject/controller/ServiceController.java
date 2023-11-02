package com.ninjaone.backendinterviewproject.controller;


import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServiceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ServiceController {
    @Autowired
    private ServiceCatalogService serviceService;

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/services")
    public Service addService(@RequestBody @Valid Service service) {
        return serviceService.addService(service);
    }

    @DeleteMapping("/services/{id}")
    public void deleteService(@PathVariable Integer id) {
        serviceService.deleteService(id);
    }


    @PostMapping("/devices/{id}/services")
    public ResponseEntity<Device> addServiceToDevice(@PathVariable Integer id, @RequestBody Service service) {
        Optional<Device> deviceOptional = deviceService.getDeviceById(id);
        if (!deviceOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Device device = deviceOptional.get();

        Optional<Service> serviceOptional = serviceService.getServiceById(service.getId());
        if (!serviceOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        device.getServices().add(serviceOptional.get());
        deviceService.saveDevice(device);

        return ResponseEntity.ok(device);
    }}