package com.ninjaone.backendinterviewproject.repository;

import com.ninjaone.backendinterviewproject.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    boolean existsByName(String name);
}