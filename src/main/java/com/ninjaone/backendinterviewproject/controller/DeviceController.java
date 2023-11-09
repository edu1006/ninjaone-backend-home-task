package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/devices")
public interface DeviceController {
    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody @Valid Device device);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Integer id) ;

    @GetMapping("/{id}/totalCost")
    public ResponseEntity<Double> getTotalCost(@PathVariable Integer id) ;
    @PostMapping("{id}/add-service-to-device/")
    public ResponseEntity<Device> addServiceToDevice(@PathVariable Integer id, @RequestBody Service service) ;

}
