package com.plazit.market.persistence;

import com.plazit.market.domain.Purchase;
import com.plazit.market.domain.repository.PurchaseRepository;
import com.plazit.market.persistence.crud.CompraCrudRepository;
import com.plazit.market.persistence.entity.Compra;
import com.plazit.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository repository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) repository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return repository.findByIdCliente(clientId).map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.tCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(repository.save(compra));
    }
}
