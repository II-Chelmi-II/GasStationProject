package com.michel.station.Model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaporationRate {
    private int evaporation_rate_id;
    private double value;
    private int product_id;
}
