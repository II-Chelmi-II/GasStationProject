package com.michel.station.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionLog {
    private Long stationId;
    private Long productId;
    private java.sql.Timestamp transactionDateTime;
    private String transactionType;
    private double quantity;
}