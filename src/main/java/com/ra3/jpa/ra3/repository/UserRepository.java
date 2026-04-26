package com.ra3.jpa.ra3.repository;

import org.springframework.stereotype.Repository;

import com.ra3.jpa.ra3.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
