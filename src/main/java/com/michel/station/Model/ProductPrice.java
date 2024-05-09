package com.michel.station.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {
    private int price_id;
    private int product_id;
    private BigDecimal price;
    private LocalDate date_added;
}