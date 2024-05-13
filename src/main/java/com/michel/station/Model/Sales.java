package com.michel.station.Model;

import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    private int sale_id;
    private int product_id;
    private int station_id;
    private BigDecimal quantity;
}