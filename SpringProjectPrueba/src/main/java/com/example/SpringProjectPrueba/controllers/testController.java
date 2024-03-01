package com.example.SpringProjectPrueba.controllers;

import com.example.SpringProjectPrueba.db.dtos.ProductDTO;
import com.example.SpringProjectPrueba.db.models.Product;
import com.example.SpringProjectPrueba.db.models.Proveedor;
import com.example.SpringProjectPrueba.db.repositories.ProductRepository;
import com.example.SpringProjectPrueba.db.repositories.ProveedorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SpringProjectPrueba.db.dbService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController()
public class testController {
    private final dbService dbService;

    @Autowired
    public testController(dbService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<String>("Hello world", HttpStatus.OK);
    }

    @GetMapping("/prueba/{id}")
    public ResponseEntity<Integer> obtenerUsuarioPorId(@PathVariable int id) {

        if (id >= 0) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Void> setInitialData() {
        this.dbService.setInitialData();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/json")
    public ResponseEntity<String> processJson(@RequestBody String jsonRequest) {
        String jsonResponse = "{\"message\": \"JSON recibido correctamente\"}";

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<String> getProducts(){
        List<ProductDTO> listProduct = this.dbService.getProducts();

        listProduct.forEach(el -> {
            System.out.println(el.toString());
            System.out.println(el.toJSON());
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<String> getProduct(){
        ProductDTO product = this.dbService.getFirstProduct();

        if (product == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(product.toJSON(), HttpStatus.OK);
    }
}