package com.shopping.citypoint.Repository;

import com.shopping.citypoint.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    public Shop findShopByName(String name);
}
