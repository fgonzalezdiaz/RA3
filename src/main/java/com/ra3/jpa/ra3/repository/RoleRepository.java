package com.ra3.jpa.ra3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ra3.jpa.ra3.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
