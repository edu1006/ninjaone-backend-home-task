package com.ninjaone.backendinterviewproject.controller.impl;

import com.ninjaone.backendinterviewproject.controller.DeviceController;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DeviceControllerImpl implements DeviceController {
    private final DeviceService deviceService;

    public DeviceControllerImpl(DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    public ResponseEntity<Device> addDevice(@RequestBody @Valid Device device) {
        Device newDevice = deviceService.addDevice(device);
        return new ResponseEntity<>(newDevice, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteDevice(@PathVariable Integer id) {
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Double> getTotalCost(@PathVariable Integer id) {
        return ResponseEntity.ok(deviceService.calculateTotalCost(id));
    }

    public ResponseEntity<Device> addServiceToDevice(@PathVariable Integer id, @RequestBody Service service) {
        Device device = deviceService.addServiceToDevice(id, service);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
}
