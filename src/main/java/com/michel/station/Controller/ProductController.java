package com.michel.station.Controller;

import com.michel.station.Model.Product;
import com.michel.station.Repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam int name) {
        List<Product> products = productRepository.getAllProductsByStation(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product = productRepository.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productRepository.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{productId}/{stationId}")
    public ResponseEntity<Void> updateProduct(@PathVariable int productId, @PathVariable String name, @RequestBody Product product) {
        product.setProduct_id(productId);
        product.setName(name);
        productRepository.updateProduct(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}/{stationId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId, @PathVariable String name) {
        productRepository.deleteProduct(productId, name);
        return ResponseEntity.noContent().build();
    }
}