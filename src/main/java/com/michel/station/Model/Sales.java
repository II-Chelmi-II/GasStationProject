package com.michel.station.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    private Long stationId;
    private Long productId;
    private java.sql.Timestamp salesDateTime;
    private double quantitySold;
    private double amountSold;
}