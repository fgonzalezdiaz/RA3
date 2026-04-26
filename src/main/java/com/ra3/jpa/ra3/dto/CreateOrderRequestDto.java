package com.ra3.jpa.ra3.dto;

import java.sql.Date;
import java.util.List;

public class CreateOrderRequestDto {

    private Long idCustomer;
    private Date orderDate;
    private List<Long> idProductes;

    public Long getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public List<Long> getIdProductes() {
        return idProductes;
    }
    public void setIdProductes(List<Long> idProductes) {
        this.idProductes = idProductes;
    }
}
