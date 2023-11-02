package com.ninjaone.backendinterviewproject.repository;

import com.ninjaone.backendinterviewproject.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    boolean existsBySystemName(String systemName);
}
