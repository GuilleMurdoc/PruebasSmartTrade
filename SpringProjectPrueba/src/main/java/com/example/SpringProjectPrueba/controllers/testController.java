package com.example.SpringProjectPrueba.controllers;

import com.example.SpringProjectPrueba.db.models.Product;
import com.example.SpringProjectPrueba.db.repositories.ProductRepository;
import com.example.SpringProjectPrueba.db.repositories.ProveedorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class testController {
    private final ProductRepository productRepository;
    private final ProveedorRepository proveedorRepository;

    public testController(ProductRepository productRepository, ProveedorRepository proveedorRepository) {
        this.productRepository = productRepository;
        this.proveedorRepository = proveedorRepository;
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
    public ResponseEntity<Void> crearUsuario() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/json")
    public ResponseEntity<String> processJson(@RequestBody String jsonRequest) {
        String jsonResponse = "{\"message\": \"JSON recibido correctamente\"}";

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<String> postProduct(@RequestBody String jsonRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Product product = objectMapper.readValue(jsonRequest, Product.class);

        System.out.println(product.toString());

        System.out.println(this.productRepository.count());

        this.productRepository.save(product);

        System.out.println(this.productRepository.count());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<String> getProdcuts() throws JsonProcessingException {

        System.out.println(this.productRepository.count());

        this.productRepository.findAll().forEach(pdoruct -> System.out.println(pdoruct.toString()));

        return new ResponseEntity<>(HttpStatus.OK);
    }


}