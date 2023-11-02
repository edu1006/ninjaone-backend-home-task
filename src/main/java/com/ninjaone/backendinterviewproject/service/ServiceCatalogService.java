package com.ninjaone.backendinterviewproject.service;


import com.ninjaone.backendinterviewproject.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCatalogService {
    @Autowired
    private ServiceRepository serviceRepository;

    public com.ninjaone.backendinterviewproject.model.Service addService(com.ninjaone.backendinterviewproject.model.Service service) {
        if(serviceRepository.existsByName(service.getName())) {
            throw new IllegalArgumentException("Service already exists.");
        }
        return serviceRepository.save(service);
    }

    public void deleteService(Integer id) {
        serviceRepository.deleteById(id);
    }

    public Optional<com.ninjaone.backendinterviewproject.model.Service> getServiceById(Integer id) {
        return serviceRepository.findById(id);
    }
}
