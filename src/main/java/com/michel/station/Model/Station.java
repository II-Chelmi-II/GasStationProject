package com.michel.station.Model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    private int station_id;
    private String location;
}