package com.michel.station.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    private Long stationId;
    private String stationName;
    private boolean isCentralized;
    private double gasolineEvaporationRate;
    private double dieselEvaporationRate;
    private double petrolEvaporationRate;
}