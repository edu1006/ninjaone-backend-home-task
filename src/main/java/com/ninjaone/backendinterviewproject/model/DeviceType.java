package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.swing.*;

public enum DeviceType {
    WINDOWS_WORK_STATION("Windows Workstation"), WINDOWS_SERVER("Windows Server"),
    MAC("mac"), ETC("etc");
    private String desc;
    DeviceType(String desc){
        this.desc = desc;
    }

    @JsonCreator
    public static DeviceType fromString(String text) {
        if (text == null || text.isBlank()){
            throw new IllegalArgumentException("type can not be null or blank ");
        }
        for (DeviceType deviceType : DeviceType.values()) {
            if (deviceType.desc.equalsIgnoreCase(text)) {
                return deviceType;
            }
        }
        throw new IllegalArgumentException("No enum constant found for " + text);
    }

    public String getDesc(){
        return this.desc;
    }
}
