package com.example.SpringProjectPrueba.db.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "proveedor")
    @ToString.Exclude
    private List<Product> products;

    public Proveedor(String name) {
        this.name = name;
    }
}

