package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.RoleDto;
import com.ra3.jpa.ra3.model.Role;
import com.ra3.jpa.ra3.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(roleRepository.findAll());
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            return ResponseEntity.ok().body("Este role no existe");
        }
        return ResponseEntity.ok().body(role.get());
    }

    public ResponseEntity<?> save(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        roleRepository.save(role);
        return ResponseEntity.ok().body("Role guardado correctamente");
    }

    public ResponseEntity<?> update(Long id, RoleDto roleDto) {
        Optional<Role> existing = roleRepository.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.ok().body("Este role no existe");
        }
        Role role = existing.get();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        roleRepository.save(role);
        return ResponseEntity.ok().body("Role actualizado correctamente");
    }

    public ResponseEntity<?> delete(Long id) {
        if (!roleRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body("Este role no existe");
        }
        roleRepository.deleteById(id);
        return ResponseEntity.ok().body("Role eliminado correctamente");
    }

}
