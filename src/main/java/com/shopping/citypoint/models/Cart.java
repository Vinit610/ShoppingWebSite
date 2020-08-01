package com.shopping.citypoint.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {

    public Cart() {
    }

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    private @Id @GeneratedValue Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cartItems", referencedColumnName = "id")
    private List<CartItem> items;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
