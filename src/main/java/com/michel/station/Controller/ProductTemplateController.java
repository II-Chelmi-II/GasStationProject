package com.michel.station.Controller;

import com.michel.station.Model.ProductTemplate;
import com.michel.station.Repository.ProductTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-templates")
public class ProductTemplateController {
    private final ProductTemplateRepository productTemplateRepository;

    public ProductTemplateController(ProductTemplateRepository productTemplateRepository) {
        this.productTemplateRepository = productTemplateRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductTemplate>> getAllProductTemplates() {
        List<ProductTemplate> productTemplates = productTemplateRepository.getAllProductTemplates();
        return ResponseEntity.ok(productTemplates);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductTemplate> getProductTemplateById(@PathVariable int productId) {
        ProductTemplate productTemplate = productTemplateRepository.getProductTemplateById(productId);
        if (productTemplate != null) {
            return ResponseEntity.ok(productTemplate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createProductTemplate(@RequestBody ProductTemplate productTemplate) {
        productTemplateRepository.createProductTemplate(productTemplate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProductTemplate(@PathVariable int productId, @RequestBody ProductTemplate productTemplate) {
        productTemplate.setProduct_id(productId);
        productTemplateRepository.updateProductTemplate(productTemplate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductTemplate(@PathVariable int productId) {
        productTemplateRepository.deleteProductTemplate(productId);
        return ResponseEntity.noContent().build();
    }
}