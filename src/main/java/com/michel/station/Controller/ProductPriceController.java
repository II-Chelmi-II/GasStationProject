package com.michel.station.Controller;

import com.michel.station.Model.ProductPrice;
import com.michel.station.Repository.ProductPriceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-prices")
public class ProductPriceController {
    private final ProductPriceRepository productPriceRepository;

    public ProductPriceController(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductPrice>> getAllProductPrices() {
        List<ProductPrice> productPrices = productPriceRepository.getAllProductPrices();
        return ResponseEntity.ok(productPrices);
    }

    @GetMapping("/{priceId}")
    public ResponseEntity<ProductPrice> getProductPriceById(@PathVariable int priceId) {
        ProductPrice productPrice = productPriceRepository.getProductPriceById(priceId);
        if (productPrice != null) {
            return ResponseEntity.ok(productPrice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createProductPrice(@RequestBody ProductPrice productPrice) {
        productPriceRepository.createProductPrice(productPrice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{priceId}")
    public ResponseEntity<Void> updateProductPrice(@PathVariable int priceId, @RequestBody ProductPrice productPrice) {
        productPrice.setPrice_id(priceId);
        productPriceRepository.updateProductPrice(productPrice);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{priceId}")
    public ResponseEntity<Void> deleteProductPrice(@PathVariable int priceId) {
        productPriceRepository.deleteProductPrice(priceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductPrice>> getProductPricesByProductId(@PathVariable int productId) {
        List<ProductPrice> productPrices = productPriceRepository.getProductPricesByProductId(productId);
        return ResponseEntity.ok(productPrices);
    }
}