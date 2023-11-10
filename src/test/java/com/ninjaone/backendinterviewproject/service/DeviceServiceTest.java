package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @MockBean
    private DeviceRepository deviceRepository;


    @Test
    public void givenValidDevice_whenAddDevice_thenDeviceIsAdded() {
        Device device = new Device();
        device.setId(1);
        device.setSystemName("System1");
        device.setType(DeviceType.MAC);

        when(deviceRepository.existsBySystemName(device.getSystemName())).thenReturn(false);
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device addedDevice = deviceService.addDevice(device);
        assertEquals(1, addedDevice.getId());
        assertEquals(device.getSystemName(), addedDevice.getSystemName());
        assertEquals(device.getType(), addedDevice.getType());
    }

    @Test
    public void givenDuplicateDevice_whenAddDevice_thenThrowIllegalArgumentException() {
        Device device = new Device();
        device.setSystemName("System1");
        device.setType(DeviceType.MAC);

        when(deviceRepository.existsBySystemName(device.getSystemName())).thenReturn(true);
        assertThrows(IllegalArgumentException.class, ()->deviceService.addDevice(device));
    }


}
