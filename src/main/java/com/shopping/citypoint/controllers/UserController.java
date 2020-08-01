package com.shopping.citypoint.controllers;

import com.shopping.citypoint.Repository.ShopRepository;
import com.shopping.citypoint.Repository.UserRepository;
import com.shopping.citypoint.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository repository){
        userRepository = repository;
    }

    @PostMapping(value = {"/addUser"})
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/loginAsUser")
    public ResponseEntity login(@RequestBody Credential credential){
        List<User> users = userRepository.findAll();
        for(User user : users){
            if(user.getEmail().equalsIgnoreCase(credential.getEmail()) && user.getPassword().equals(credential.getPassword())){
                return new ResponseEntity(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateCart/{userId}")
    public ResponseEntity updateCart(@PathVariable String userId, @RequestBody CartItem item){
        User user = userRepository.findByUserId(userId);
        if(user!=null){
            Cart cart = user.getCart() == null ? new Cart() : user.getCart();
            List<CartItem> items = cart.getItems() == null ? new ArrayList<>() : cart.getItems();
            items.add(item);
            cart.setItems(items);
            user.setCart(cart);
            userRepository.save(user);
            return new ResponseEntity(user, HttpStatus.OK);
        }else{
            return new ResponseEntity(user, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUserCart/{userId}")
    public Cart getUserCart(@PathVariable String userId){
        User user = userRepository.findByUserId(userId);
        System.out.println(user.getCart().getItems());
        System.out.println(user.getCart().toString());
        return user == null ? null : user.getCart();
    }
}
