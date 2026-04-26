package com.ra3.jpa.ra3.dto;

/*
 * DTO per gestionar errors no controlats des del controller.
 */
public class ErrorDto {

    private int status;
    private String description;

    public ErrorDto(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() { 
        return status;
    }
    
    public void setStatus(int status) { 
        this.status = status; 
    }

    public String getDescription() { 
        return description; 
    }
    
    public void setDescription(String description) { 
        this.description = description; 
    }
}
