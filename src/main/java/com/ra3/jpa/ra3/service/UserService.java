package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.UserDto;
import com.ra3.jpa.ra3.model.User;
import com.ra3.jpa.ra3.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.ok().body("Este user no existe");
        }
        return ResponseEntity.ok().body(user.get());
    }

    public ResponseEntity<?> save(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setStatus(true);
        userRepository.save(user);
        return ResponseEntity.ok().body("User guardado correctamente");
    }

    public ResponseEntity<?> update(Long id, UserDto userDto) {
        Optional<User> existing = userRepository.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.ok().body("Este user no existe");
        }
        User user = existing.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok().body("User actualizado correctamente");
    }

    public ResponseEntity<?> delete(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body("Este user no existe");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("User eliminado correctamente");
    }

}
