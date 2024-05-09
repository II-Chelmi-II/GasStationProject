package com.michel.station.Controller;

import com.michel.station.Model.Movement;
import com.michel.station.Repository.MovementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {
    private final MovementRepository movementRepository;

    public MovementController(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @GetMapping
    public ResponseEntity<List<Movement>> getAllMovements() {
        List<Movement> movements = movementRepository.getAllMovements();
        return ResponseEntity.ok(movements);
    }

    @GetMapping("/{movementId}")
    public ResponseEntity<Movement> getMovementById(@PathVariable int movementId) {
        Movement movement = movementRepository.getMovementById(movementId);
        if (movement != null) {
            return ResponseEntity.ok(movement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createMovement(@RequestBody Movement movement) {
        movementRepository.createMovement(movement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{movementId}")
    public ResponseEntity<Void> updateMovement(@PathVariable int movementId, @RequestBody Movement movement) {
        movement.setMovement_id(movementId);
        movementRepository.updateMovement(movement);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{movementId}")
    public ResponseEntity<Void> deleteMovement(@PathVariable int movementId) {
        movementRepository.deleteMovement(movementId);
        return ResponseEntity.noContent().build();
    }
}