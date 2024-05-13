package com.michel.station.Model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationMovement {
    private int movement_id;
    private int station_id;
}