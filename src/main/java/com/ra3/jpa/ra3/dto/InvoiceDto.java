package com.ra3.jpa.ra3.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.ra3.jpa.ra3.model.Invoice;
import com.ra3.jpa.ra3.model.Order;

public class InvoiceDto {

    private Long orderId;
    private String invoiceNumber;
    private Date issueDate;
    private BigDecimal taxAmount;
    private BigDecimal totalWithTax;

    public static InvoiceDto toDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();
        dto.setOrderId(invoice.getOrder().getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setIssueDate(invoice.getIssueDate());
        dto.setTaxAmount(invoice.getTaxAmount());
        dto.setTotalWithTax(invoice.getTotalWithTax());
        return dto;
    }

    public static Invoice toEntity(InvoiceDto invoiceDto, Order order) {
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setInvoiceNumber(invoiceDto.getInvoiceNumber());
        invoice.setIssueDate(invoiceDto.getIssueDate());
        invoice.setTaxAmount(invoiceDto.getTaxAmount());
        invoice.setTotalWithTax(invoiceDto.getTotalWithTax());
        return invoice;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
