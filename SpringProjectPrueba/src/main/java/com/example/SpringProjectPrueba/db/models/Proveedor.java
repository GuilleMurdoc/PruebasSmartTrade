package com.example.SpringProjectPrueba.db.models;

import jakarta.persistence.*;
        import com.example.SpringProjectPrueba.db.*;
        import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public Proveedor() {

    }

    public Proveedor(String name) {
        this.name = name;
    }
}

