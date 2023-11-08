package com.ninjaone.backendinterviewproject.service;


import com.ninjaone.backendinterviewproject.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCatalogService {
    private final ServiceRepository serviceRepository;
    private final Logger logger = LoggerFactory.getLogger(ServiceCatalogService.class);

    @Autowired
    public ServiceCatalogService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public com.ninjaone.backendinterviewproject.model.Service addNewService(com.ninjaone.backendinterviewproject.model.Service service) {
        validateServiceDoesNotExist(service);
        logger.info("Adding service with name " + service.getName());
        return serviceRepository.save(service);
    }

    private void validateServiceDoesNotExist(com.ninjaone.backendinterviewproject.model.Service service){
        if(serviceRepository.existsByName(service.getName())) {
            logger.error("Service with name " + service.getName() + " already exists.");
            throw new IllegalArgumentException("Service already exists.");
        }
    }

    public void deleteServiceById(Integer id) {
        logger.info("Deleting service with ID: " + id);
        serviceRepository.deleteById(id);
    }

    public Optional<com.ninjaone.backendinterviewproject.model.Service> findServiceById(Integer id) {
        return serviceRepository.findById(id);
    }
}
