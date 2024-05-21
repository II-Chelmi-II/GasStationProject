package com.michel.station.Repository;

import com.michel.station.DbConnection;
import com.michel.station.Model.StationMovement;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StationMovementRepository {
    private final Connection connection;

    public StationMovementRepository() {
        this.connection = DbConnection.getConnection();
    }

    public void createStationMovement(StationMovement stationMovement) {
        String query = "INSERT INTO StationMovement (movement_id, station_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stationMovement.getMovement_id());
            statement.setInt(2, stationMovement.getStation_id());
            statement.executeUpdate();
            System.out.println("Station movement created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating station movement.");
            e.printStackTrace();
        }
    }

    public StationMovement getStationMovementById(int movementId) {
        StationMovement stationMovement = null;
        String query = "SELECT * FROM StationMovement WHERE movement_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movementId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                stationMovement = new StationMovement();
                stationMovement.setMovement_id(resultSet.getInt("movement_id"));
                stationMovement.setStation_id(resultSet.getInt("station_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting station movement by ID.");
            e.printStackTrace();
        }
        return stationMovement;
    }

    public List<StationMovement> getAllStationMovements() {
        List<StationMovement> stationMovements = new ArrayList<>();
        String query = "SELECT * FROM StationMovement";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StationMovement stationMovement = new StationMovement();
                stationMovement.setMovement_id(resultSet.getInt("movement_id"));
                stationMovement.setStation_id(resultSet.getInt("station_id"));
                stationMovements.add(stationMovement);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all station movements.");
            e.printStackTrace();
        }
        return stationMovements;
    }

    public void updateStationMovement(StationMovement stationMovement) {
        String query = "UPDATE StationMovement SET station_id = ? WHERE movement_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stationMovement.getStation_id());
            statement.setInt(2, stationMovement.getMovement_id());
            statement.executeUpdate();
            System.out.println("Station movement updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating station movement.");
            e.printStackTrace();
        }
    }

    public void deleteStationMovement(int movementId) {
        String query = "DELETE FROM StationMovement WHERE movement_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movementId);
            statement.executeUpdate();
            System.out.println("Station movement deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting station movement.");
            e.printStackTrace();
        }
    }
}