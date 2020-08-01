package com.shopping.citypoint.Repository;

import com.shopping.citypoint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserId(String id);
}
