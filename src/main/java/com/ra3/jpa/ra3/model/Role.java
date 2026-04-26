package com.ra3.jpa.ra3.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    private Roles name;
    private String description;
    
    @ManyToMany(mappedBy = "roles")
    private List<User> user = new ArrayList<>();

    public Role() {}

    public Role(Roles name, String description) {
        this.name = name;
        this.description = description;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Roles getName() {
        return name;
    }
    public void setName(Roles name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    } 

    
}
