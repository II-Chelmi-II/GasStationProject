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
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam int stationId) {
        List<Product> products = productRepository.getAllProductsByStation(stationId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}/{stationId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId, @PathVariable int stationId) {
        Product product = productRepository.getProductById(productId, stationId);
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
    public ResponseEntity<Void> updateProduct(@PathVariable int productId, @PathVariable int stationId, @RequestBody Product product) {
        product.setProduct_id(productId);
        product.setStation_id(stationId);
        productRepository.updateProduct(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}/{stationId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId, @PathVariable int stationId) {
        productRepository.deleteProduct(productId, stationId);
        return ResponseEntity.noContent().build();
    }
}