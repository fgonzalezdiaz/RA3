package com.ra3.jpa.ra3.dto;

import com.ra3.jpa.ra3.model.Roles;

public class RoleDto {

    private Roles name;
    private String description;

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
