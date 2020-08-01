package com.shopping.citypoint.Repository;

import com.shopping.citypoint.models.Item;
import com.shopping.citypoint.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
