package com.example.SpringProjectPrueba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class testController {
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
}