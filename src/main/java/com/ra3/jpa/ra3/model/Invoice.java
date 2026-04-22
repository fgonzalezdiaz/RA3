package com.ra3.jpa.ra3.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "orderId", unique = true)
    private Order order;

    private String invoiceNumber;
    private Date issueDate;
    private BigDecimal taxAmount;
    private BigDecimal totalWithTax;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
    public BigDecimal getTotalWithTax() {
        return totalWithTax;
    }
    public void setTotalWithTax(BigDecimal totalWithTax) {
        this.totalWithTax = totalWithTax;
    }
    
}
