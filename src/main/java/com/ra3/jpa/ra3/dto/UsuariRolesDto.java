package com.ra3.jpa.ra3.dto;

import java.util.List;

public class UsuariRolesDto {
    private String email;
    private List<RoleDto> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    
}
