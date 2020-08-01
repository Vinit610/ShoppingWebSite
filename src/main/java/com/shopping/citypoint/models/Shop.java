package com.shopping.citypoint.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Shop {

    private @Id @GeneratedValue Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_c", referencedColumnName = "id")
    private List<Catagory> catagories;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_i", referencedColumnName = "id")
    private List<Item> items;

    private String email;

    private String password;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopImage_id", referencedColumnName = "id")
    private ShopImage shopImage;

    public Shop() {
    }

    public Shop(String name, List<Catagory> catagories, List<Item> items, String email, String password, String description) {
        this.name = name;
        this.catagories = catagories;
        this.items = items;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Catagory> getCatagories() {
        return catagories;
    }

    public void setCatagories(List<Catagory> catagories) {
        this.catagories = catagories;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopImage getShopImage() {
        return shopImage;
    }

    public void setShopImage(ShopImage shopImage) {
        this.shopImage = shopImage;
    }
}
