package com.plazit.market.domain.service;

import com.plazit.market.domain.Product;
import com.plazit.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAll(){
        return repository.getAll();
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return repository.getByCategory(categoryId);
    }

    public Optional<List<Product>>  getScarseProducts(int quantity){
        return repository.getScarseProducts(quantity);
    }

    public Optional<Product> getProduct(int productId){
        return repository.getProduct(productId);
    }

    public  Product save(Product product){
        return repository.save(product);
    }

    public boolean delete(int productId){

        return getProduct(productId).map(product -> {
            repository.delete(productId);
            return true;
        }).orElse(false);
    }

}
