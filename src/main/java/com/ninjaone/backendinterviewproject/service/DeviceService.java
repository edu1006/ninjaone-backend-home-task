package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public Device addDevice(Device device) {
        if(deviceRepository.existsBySystemName(device.getSystemName())) {
            throw new IllegalArgumentException("Device already exists.");
        }
        return deviceRepository.save(device);
    }

    public void deleteDevice(Integer id) {
        deviceRepository.deleteById(id);
    }

    @Cacheable("totalCost")
    public Double calculateTotalCost(Integer id) {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Device not found."));
        return device.getServices().stream().mapToDouble(com.ninjaone.backendinterviewproject.model.Service::getCost).sum();
    }

    public Optional<Device> getDeviceById(Integer id) {
        return deviceRepository.findById(id);
    }

    public void saveDevice(Device device) {
        this.deviceRepository.save(device); 
    }
}
