package com.michel.station.Controller;

import com.michel.station.Model.Station;
import com.michel.station.Repository.StationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {
    private final StationRepository stationRepository;

    public StationController(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @GetMapping
    public List<Station> getAllStations() {
        return stationRepository.getAllStations();
    }

    @GetMapping("/{stationId}")
    public Station getStationById(@PathVariable int stationId) {
        return stationRepository.getStationById(stationId);
    }

    @PostMapping
    public void createStation(@RequestBody Station station) {
        stationRepository.createStation(station);
    }

    @PutMapping("/{stationId}")
    public void updateStation(@PathVariable int stationId, @RequestBody Station station) {
        stationRepository.updateStation(station);
    }

    @DeleteMapping("/{stationId}")
    public void deleteStation(@PathVariable int stationId) {
        stationRepository.deleteStation(stationId);
    }
}