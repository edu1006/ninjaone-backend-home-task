package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/services")
public interface ServiceController {

    @PostMapping
    public ResponseEntity<Service> addService(@RequestBody @Valid Service service);
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id);

}
