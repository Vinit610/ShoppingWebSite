package com.shopping.citypoint.models;

import javax.persistence.*;

@Entity
public class CartItem {

    private @Id @GeneratedValue Long id;
    private String name;
    private double price;
    private String catagory;
    private String subCatagory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemImage_id", referencedColumnName = "id")
    private ItemImage itemImage;

    public CartItem() {
    }

    public CartItem(Long id, String name, double price, String catagory, String subCatagory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.catagory = catagory;
        this.subCatagory = subCatagory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getSubCatagory() {
        return subCatagory;
    }

    public void setSubCatagory(String subCatagory) {
        this.subCatagory = subCatagory;
    }

    public ItemImage getItemImage() {
        return itemImage;
    }

    public void setItemImage(ItemImage itemImage) {
        this.itemImage = itemImage;
    }
}
