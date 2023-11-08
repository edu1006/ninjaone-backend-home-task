package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody @Valid Device device) {
        Device newDevice = deviceService.addDevice(device);
        return new ResponseEntity<>(newDevice, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Integer id) {
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/totalCost")
    public ResponseEntity<Double> getTotalCost(@PathVariable Integer id) {
        return ResponseEntity.ok(deviceService.calculateTotalCost(id));
    }

    @PostMapping("/devices/{id}")
    public ResponseEntity<Device> addServiceToDevice(@PathVariable Integer id, @RequestBody Service service) {
        Device device = deviceService.addServiceToDevice(id, service);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
}
