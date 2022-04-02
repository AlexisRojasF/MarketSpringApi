package com.plazit.market.persistence;

import com.plazit.market.domain.Product;
import com.plazit.market.domain.repository.ProductRepository;
import com.plazit.market.persistence.crud.ProductoCrudRepository;
import com.plazit.market.persistence.entity.Producto;
import com.plazit.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {

        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {

        List<Producto> productos = productoRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {

        Optional<List<Producto>> productos = productoRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }


    @Override
    public Optional<Product> getProduct(int productId) {
        return productoRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct( productoRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoRepository.deleteById(productId);
    }
}
