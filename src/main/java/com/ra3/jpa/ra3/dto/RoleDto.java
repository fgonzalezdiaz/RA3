package com.ra3.jpa.ra3.dto;

import com.ra3.jpa.ra3.model.Role;
import com.ra3.jpa.ra3.model.Roles;

public class RoleDto {

    private Roles name;
    private String description;

    public static RoleDto toDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        return dto;
    }

    public static Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        return role;
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
