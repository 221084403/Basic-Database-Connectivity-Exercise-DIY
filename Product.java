/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.entity;

import java.sql.*;

/**
 *
 * @author MNCEDISI
 */
public class Product 
{
    private Integer idNumber;
    private String description;
    private Integer quantity;
    private Double unityPrice;
    private Timestamp timestamp;

    public Product() { }

    public Product(Integer idNumber, String description, Integer quantity, Double unityPrice, Timestamp timestamp) 
    {
        this.idNumber = idNumber;
        this.description = description;
        this.quantity = quantity;
        this.unityPrice = unityPrice;
        this.timestamp = timestamp;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(Double unityPrice) {
        this.unityPrice = unityPrice;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
}
