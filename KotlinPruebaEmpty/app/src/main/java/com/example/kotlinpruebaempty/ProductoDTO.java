package com.example.kotlinpruebaempty;

public class ProductoDTO {
    public String nombre;
    public int id;

    public ProductoDTO(int id){
        this.id = id;
        this.nombre = "Antonio";
    }

    public String getNombre() {
        return nombre;
    }
}
