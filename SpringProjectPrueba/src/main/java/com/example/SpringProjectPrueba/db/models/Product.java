package com.example.SpringProjectPrueba.db.models;

import jakarta.persistence.*;
import com.example.SpringProjectPrueba.db.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Entity
@Table(name = "product")
public class Product extends ModelBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer price;

    @ManyToOne
    private Proveedor proveedor;

    public Product() {

    }

    public Product(String name, Integer price, Proveedor proveedor) {
        this.name = name;
        this.price = price;
        this.proveedor = proveedor;
    }
}


