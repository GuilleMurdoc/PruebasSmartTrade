package com.example.SpringProjectPrueba.db.dtos;

import com.example.SpringProjectPrueba.db.models.Proveedor;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO extends DTO {
    private String name;

    private Integer price;

    private String proveedorName;
}
