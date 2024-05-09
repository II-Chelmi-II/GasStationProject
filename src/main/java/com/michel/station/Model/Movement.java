package com.michel.station.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private int movement_id;
    private String type;
    private int stock;
    private LocalDate date;
}