package com.shopping.citypoint.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.citypoint.Repository.ShopRepository;
import com.shopping.citypoint.util.ImageUtil;
import com.shopping.citypoint.models.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class ShopController {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopController(ShopRepository repository){
        shopRepository = repository;
    }

    @PostMapping(value = {"/addshop"})
    public Shop addShop(@RequestParam(value = "shop", required = true) @Valid String st, @RequestParam(value="file", required = false) @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
        ObjectMapper om=new ObjectMapper();
        Shop shop = om.readValue(st, Shop.class);
        if(file!=null) {
            ShopImage img = new ShopImage(file.getOriginalFilename(), file.getContentType(),
                    ImageUtil.compressBytes(file.getBytes()));
            shop.setShopImage(img);
        }
        return shopRepository.save(shop);
    }

    @GetMapping("/getshops")
    public List<Shop> getAll(){
        List<Shop> shops= shopRepository.findAll();
        for(Shop shop : shops){
            if(shop.getShopImage() == null) continue;
            ShopImage image = shop.getShopImage();
            image = new ShopImage(image.getName(), image.getType(),
                    ImageUtil.decompressBytes(image.getPicByte()));
            shop.setShopImage(image);
        }
        return shops;
    }

    @GetMapping("/getcatagories/{name}")
    public List<Catagory> getCatagoryForShop(@PathVariable String name){
        Shop shop =  shopRepository.findShopByName(name);
        List<Catagory> catagories = shop.getCatagories();
        return catagories;
    }

    @PostMapping("/additemtoshop/{name}")
    public Shop addItemToShop(@PathVariable String name, @RequestParam(value = "shop", required = true) @Valid String st, @RequestParam(value="file", required = false) @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
        Shop shop = shopRepository.findShopByName(name);
        List<Item> items = shop == null ? new ArrayList<>() : shop.getItems();
        ObjectMapper om=new ObjectMapper();
        Item item = om.readValue(st, Item.class);
        if(file!=null) {
            ItemImage img = new ItemImage(file.getOriginalFilename(), file.getContentType(),
                    ImageUtil.compressBytes(file.getBytes()));
            item.setItemImage(img);
        }
        items.add(item);
        shop.setItems(items);
        return shopRepository.save(shop);
    }

    @GetMapping("/getallitems/{name}")
    public List<Item> getAllItemsOfShop(@PathVariable String name){
        Shop shop = shopRepository.findShopByName(name);
        List<Item> items = shop.getItems();

        for(Item item : items){
            if(item.getItemImage() == null) continue;
            ItemImage image = item.getItemImage();
            image = new ItemImage(image.getName(), image.getType(),
                    ImageUtil.decompressBytes(image.getPicByte()));
            item.setItemImage(image);
        }
        return items;
    }

//    @GetMapping("/getallitems")
//    public List<Item> getAllItems(@PathVariable String name){
//        List<Shop>
//        Shop shop = shopRepository.findShopByName(name);
//        List<Item> items = shop.getItems();
//
//        for(Item item : items){
//            if(item.getItemImage() == null) continue;
//            ItemImage image = item.getItemImage();
//            image = new ItemImage(image.getName(), image.getType(),
//                    ImageUtil.decompressBytes(image.getPicByte()));
//            item.setItemImage(image);
//        }
//        return items;
//    }

    @GetMapping("/getallitems/{name}/{catagory}")
    public List<Item> getAllItemsByCatagory(@PathVariable String name, @PathVariable String catagory){
        Shop shop = shopRepository.findShopByName(name);
        List<Item> items = shop.getItems();
        List<Item> ans = items.stream().filter(item -> item.getCatagory().equals(catagory)).collect(Collectors.toList());
        for(Item item : ans){
            if(item.getItemImage() == null) continue;
            ItemImage image = item.getItemImage();
            image = new ItemImage(image.getName(), image.getType(),
                    ImageUtil.decompressBytes(image.getPicByte()));
            item.setItemImage(image);
        }
        return ans;
    }

    @PostMapping("/loginAsShop")
    public ResponseEntity checkCredenials(@RequestBody Credential credential){
        List<Shop> shops = shopRepository.findAll();
        for(Shop shop : shops){
            if(shop.getEmail().equalsIgnoreCase(credential.getEmail()) && shop.getPassword().equals(credential.getPassword())){
                return new ResponseEntity(shop, HttpStatus.OK);
            }
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
