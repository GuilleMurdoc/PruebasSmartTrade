package com.example.SpringProjectPrueba.db.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer price;

    @ManyToOne
    private Proveedor proveedor;

    public Product(String name, Integer price, Proveedor proveedor) {
        this.name = name;
        this.price = price;
        this.proveedor = proveedor;
    }
}


