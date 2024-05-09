package com.michel.station.Controller;

import com.michel.station.Model.Sales;
import com.michel.station.Repository.SalesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    private final SalesRepository salesRepository;

    public SalesController(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Sales> getSaleById(@PathVariable int saleId) {
        Sales sale = salesRepository.getSaleById(saleId);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/station/{stationId}")
    public ResponseEntity<List<Sales>> getAllSalesByStation(@PathVariable int stationId) {
        List<Sales> sales = salesRepository.getAllSalesByStation(stationId);
        return ResponseEntity.ok(sales);
    }

    @PostMapping
    public ResponseEntity<Void> createSale(@RequestBody Sales sale) {
        salesRepository.createSale(sale);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<Void> updateSale(@PathVariable int saleId, @RequestBody Sales sale) {
        sale.setSale_id(saleId);
        salesRepository.updateSale(sale);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable int saleId) {
        salesRepository.deleteSale(saleId);
        return ResponseEntity.noContent().build();
    }
}