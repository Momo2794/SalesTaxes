package com.example.saletaxes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long productID;

    @Column(name ="productname")
    private String productname;

    @Column(name ="producttype")
    private String producttype;

    @Column(name ="price")
    private Double productprice;

    @Column(name ="importtax")
    private Boolean importtax;

    @Column(name ="generaltax")
    private Boolean generaltax;

    public Product(Long productID, String productname, String producttype, Double productprice, Boolean importtax, Boolean generaltax) {
        this.productID = productID;
        this.productname = productname;
        this.producttype = producttype;
        this.productprice = productprice;
        this.importtax = importtax;
        this.generaltax = generaltax;
    }

    public Product() {}

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public Double getProductprice() {
        return productprice;
    }

    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }

    public Boolean getImporttax() {
        return importtax;
    }

    public void setImporttax(Boolean importtax) {
        this.importtax = importtax;
    }

    public Boolean getGeneraltax() {
        return generaltax;
    }

    public void setGeneraltax(Boolean generaltax) {
        this.generaltax = generaltax;
    }
}
