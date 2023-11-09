package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final ServiceCatalogService serviceCatalogService;
    private final Logger logger = LoggerFactory.getLogger(DeviceService.class);


    public DeviceService(DeviceRepository deviceRepository, ServiceCatalogService serviceCatalogService) {
        this.deviceRepository = deviceRepository;
        this.serviceCatalogService = serviceCatalogService;
    }

    public Device addDevice(Device device) {
        validateDeviceDoesNotExist(device);
        logger.info("Adding device with system name " + device.getSystemName());
        return deviceRepository.save(device);
    }

    private void validateDeviceDoesNotExist(Device device){
        if(deviceRepository.existsBySystemName(device.getSystemName())) {
            logger.error("Device with system name " + device.getSystemName() + " already exists.");
            throw new IllegalArgumentException("Device already exists.");
        }
    }

    public void deleteDevice(Integer id) {
        if (!deviceRepository.existsById(id)) {
            logger.error("Device with ID: " + id + " does not exist.");
            throw new EntityNotFoundException("Device with ID: " + id + " does not exist.");
        }
        logger.info("Deleting device with ID: " + id);
        deviceRepository.deleteById(id);
    }

    @Cacheable("totalCost")
    public Double calculateTotalCost(Integer id) {
        return getDeviceById(id)
                .map(device -> calculateServiceCost(device))
                .orElseThrow(() -> {
                    logger.error("Device with ID " + id + " not found.");
                    return new EntityNotFoundException("Device not found");
                });
    }

    private Double calculateServiceCost(Device device) {
        return device.getServices().stream().mapToDouble(com.ninjaone.backendinterviewproject.model.Service::getCost).sum();
    }

    public Optional<Device> getDeviceById(Integer id) {
        return deviceRepository.findById(id);
    }

    public void saveDevice(Device device) {
        logger.info("Saving device with ID: " + device.getId());
        deviceRepository.save(device);
    }

    public Device addServiceToDevice(Integer id, com.ninjaone.backendinterviewproject.model.Service service) {
        return getDeviceById(id).map(device -> {
            addServiceToGivenDevice(device, service);
            return device;
        }).orElseThrow(() -> {
            logger.error("Device with ID " + id + " not found.");
            return new EntityNotFoundException( "Device not found");
        });
    }

    private void addServiceToGivenDevice(Device device, com.ninjaone.backendinterviewproject.model.Service service){
        serviceCatalogService.findServiceById(service.getId()).ifPresentOrElse(
                s -> {
                    device.getServices().add(s);
                    deviceRepository.save(device);
                    logger.info("Service added to device with ID: " + device.getId());
                },
                () -> {
                    logger.error("Service with ID " + service.getId() + " not found.");
                    throw new EntityNotFoundException("Service not found");
                }
        );
    }
}

