package com.clinic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.dto.UserDTO;
import com.clinic.service.UserService;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController { 
    
    @Autowired
    UserService service;
    
    @GetMapping(value="/find/{name}")
    public ResponseEntity<List<UserDTO>> findByName(@PathVariable String name) {
        return service.findByName(name);
    }
    
    @PostMapping()
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        return service.create(userDTO);
    }
    
    @GetMapping()
    public ResponseEntity<List<UserDTO>> read() {
        return service.read();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable int id) {
        return service.getById(id);
    }
    
    @PutMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'CREATED')")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        return service.update(userDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable int id) {
        return service.delete(id);
    }   
}