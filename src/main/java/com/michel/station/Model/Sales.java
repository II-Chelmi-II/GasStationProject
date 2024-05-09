package com.michel.station.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    private int sale_id;
    private int product_id;
    private int station_id;
    private BigDecimal amount;
}