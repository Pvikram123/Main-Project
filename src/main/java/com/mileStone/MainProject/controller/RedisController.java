package com.mileStone.MainProject.controller;

import com.mileStone.MainProject.models.Product;
import com.mileStone.MainProject.repository.redisRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@EnableCaching
public class RedisController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("getRedis")
     public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @PostMapping("postRedis")
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }
    @GetMapping("getRedis/{id}")
    @Cacheable(key = "#id",value = "Product",unless = "#result.price > 1000")
    public Product findProduct(@PathVariable int id) {
        return productRepository.findProductById(id);
    }
    @DeleteMapping("deleteRedis/{id}")
    public String delete(@PathVariable int id){
        return productRepository.deleteProduct(id);
    }
}
