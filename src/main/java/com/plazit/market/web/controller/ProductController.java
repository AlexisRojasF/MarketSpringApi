package com.plazit.market.web.controller;

import com.plazit.market.domain.Product;
import com.plazit.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/all")
    public ResponseEntity< List<Product>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") int categoryId) {
        return service.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>( products ,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    public Optional<List<Product>> getScarseProducts(int quantity) {
        return service.getScarseProducts(quantity);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct( @PathVariable("id") int productId) {
    return service.getProduct(productId)
            .map(product -> new ResponseEntity<>(product,HttpStatus.ACCEPTED))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>( service.save(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id")  int productId) {

        if (service.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
}
