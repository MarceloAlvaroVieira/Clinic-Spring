package com.clinic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.dto.UserDTO;
import com.clinic.exception.ResourceNotFoundException;
import com.clinic.model.Perfil;
import com.clinic.model.User;
import com.clinic.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    BCryptPasswordEncoder encoder;

    @Value("${defaultPassword}")
    private String defaultPassword;

    UserService(){ encoder = new BCryptPasswordEncoder(); }
    
    public ResponseEntity<UserDTO> create(UserDTO userDTO){

        boolean exist = repository.findByLogin(userDTO.getLogin()) != null;

        User user = userDTO.toUser();

        if(exist){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }else{
            user.setPassword(encoder.encode(defaultPassword));

            if(user.getType()){
                user.setPerfil(Perfil.ADMIN);
            }else{
                user.setPerfil(Perfil.USER);
            }

            try {
                user = repository.save(user);    
                return ResponseEntity.ok().body(new UserDTO(user));
            } catch (Exception e) {}
            return ResponseEntity.ok().body(new UserDTO());
        }
    }

    public ResponseEntity<List<UserDTO>> findByName(String name) {
        List<User> users = new ArrayList<>();
        List<UserDTO> usersDTO = new ArrayList<>();

        if(!name.isEmpty()){
            users = repository.findByName(name);

            users.forEach(user ->{
                usersDTO.add(new UserDTO(user));
            });
        }

        return ResponseEntity.ok().body(usersDTO);
    }

    public ResponseEntity<List<UserDTO>> read() {
        List<User> users = repository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();

        users.forEach(user ->{
            usersDTO.add(new UserDTO(user));
        });

        return ResponseEntity.ok().body(usersDTO);
    }

    public ResponseEntity<UserDTO> getById(int id) {        
        User user = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        return ResponseEntity.ok().body(new UserDTO(user));
    }

    public ResponseEntity<UserDTO> update(UserDTO userDTO) {

        try{
            User database_user = repository.findByLogin(userDTO.getLogin());
            
            database_user.setPassword(encoder.encode(userDTO.getPassword()));        
            
            database_user.removePerfil(Perfil.CREATED);

            User user = repository.save(database_user);
    
            return ResponseEntity.ok().body(new UserDTO(user));
        }catch(Exception e){ System.out.println();}
    
        return ResponseEntity.ok().body(new UserDTO());
    }

    public ResponseEntity<Map<String, Boolean>> delete(int id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            repository.delete(user);

            Map<String,Boolean> response = new HashMap<>();
            
            response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
