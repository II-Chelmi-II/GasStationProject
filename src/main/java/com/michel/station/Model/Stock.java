package com.michel.station.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Long stationId;
    private Long productId;
    private java.sql.Timestamp transactionDateTime;
    private double quantityAdded;
    private double quantitySold;
    private double remainingQuantity;
}