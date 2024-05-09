package com.michel.station.Controller;

import com.michel.station.Model.StationMovement;
import com.michel.station.Repository.StationMovementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station-movements")
public class StationMovementController {
    private final StationMovementRepository stationMovementRepository;

    public StationMovementController(StationMovementRepository stationMovementRepository) {
        this.stationMovementRepository = stationMovementRepository;
    }

    @GetMapping
    public ResponseEntity<List<StationMovement>> getAllStationMovements() {
        List<StationMovement> stationMovements = stationMovementRepository.getAllStationMovements();
        return ResponseEntity.ok(stationMovements);
    }

    @GetMapping("/{movementId}")
    public ResponseEntity<StationMovement> getStationMovementById(@PathVariable int movementId) {
        StationMovement stationMovement = stationMovementRepository.getStationMovementById(movementId);
        if (stationMovement != null) {
            return ResponseEntity.ok(stationMovement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createStationMovement(@RequestBody StationMovement stationMovement) {
        stationMovementRepository.createStationMovement(stationMovement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{movementId}")
    public ResponseEntity<Void> updateStationMovement(@PathVariable int movementId, @RequestBody StationMovement stationMovement) {
        stationMovement.setMovement_id(movementId);
        stationMovementRepository.updateStationMovement(stationMovement);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{movementId}")
    public ResponseEntity<Void> deleteStationMovement(@PathVariable int movementId) {
        stationMovementRepository.deleteStationMovement(movementId);
        return ResponseEntity.noContent().build();
    }
}