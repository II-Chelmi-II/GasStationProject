package com.michel.station.Model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTemplate {
    private int product_id;
    private String name;
    private int product_price_id;
}