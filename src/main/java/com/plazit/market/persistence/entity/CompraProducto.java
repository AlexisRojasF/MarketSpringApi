package com.plazit.market.persistence.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "compras_productos")
@Getter
@Setter
public class CompraProducto {

    @EmbeddedId
    private CompraProductoPk id;

    private Integer cantidad;
    private Double total;
    private Boolean estado;

    @ManyToOne
    @MapsId("idCompra")
    @JoinColumn(name = "id_compra",insertable = false,updatable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_producto",insertable = false,updatable = false)
    private Producto producto;
}
