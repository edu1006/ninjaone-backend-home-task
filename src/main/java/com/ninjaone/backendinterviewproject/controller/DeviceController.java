package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/devices")
    public Device addDevice(@RequestBody @Valid Device device) {
        return deviceService.addDevice(device);
    }

    @DeleteMapping("/devices/{id}")
    public void deleteDevice(@PathVariable Integer id) {
        deviceService.deleteDevice(id);
    }
    @GetMapping("/devices/{id}/totalCost")
    public ResponseEntity<Double> getTotalCost(@PathVariable Integer id) {
        return ResponseEntity.ok(deviceService.calculateTotalCost(id));
    }
}
