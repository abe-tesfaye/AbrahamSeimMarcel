package com.Providerapi.demo.Sales;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesId;

    private int productId;

    @Temporal(TemporalType.DATE)
    private Date date;
    private int quantity;

    // Getters and Setters
    public int getSalesId() { return salesId; }
    public void setSalesId(int salesId) { this.salesId = salesId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
