package com.example.SpringProjectPrueba.db;

import com.example.SpringProjectPrueba.db.dtos.ProductDTO;
import com.example.SpringProjectPrueba.db.models.Product;
import com.example.SpringProjectPrueba.db.models.Proveedor;
import com.example.SpringProjectPrueba.db.repositories.ProductRepository;
import com.example.SpringProjectPrueba.db.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class dbService {
    private final ProductRepository productRepository;
    private final ProveedorRepository proveedorRepository;

    @Autowired
    public dbService(ProductRepository productRepository, ProveedorRepository proveedorRepository) {
        this.productRepository = productRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<ProductDTO> getProducts(){
        List<ProductDTO> list = new LinkedList<>();

        this.productRepository.findAll().forEach(el -> {
            list.add(new ProductDTO(el.getName(), el.getPrice(), el.getProveedor().getName()));
        });

        return list;
    }

    public void setInitialData(){
        this.proveedorRepository.save(new Proveedor("Joselu"));
        this.proveedorRepository.save(new Proveedor("Anonio"));

        this.proveedorRepository.findAll().forEach(proveedor -> {
            this.productRepository.save(new Product("Caracola", 5, proveedor));
            this.productRepository.save(new Product("Manzana", 100, proveedor));
        });
    }

    public ProductDTO getFirstProduct(){
        Optional<Product> product = this.productRepository.findById(1);

        if(!product.isPresent()) return null;

        return new ProductDTO(product.get().getName(), product.get().getPrice(), product.get().getProveedor().getName());


    }
}
