package com.michel.station.Repository;

import com.michel.station.DbConnection;
import com.michel.station.Model.Movement;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovementRepository {
    private final Connection connection;

    public MovementRepository() {
        this.connection = DbConnection.getConnection();
    }

    public void createMovement(Movement movement) {
        String query = "INSERT INTO Movement (movement_id, type, stock, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movement.getMovement_id());
            statement.setString(2, movement.getType());
            statement.setInt(3, movement.getStock());
            statement.setDate(4, java.sql.Date.valueOf(movement.getDate()));
            statement.executeUpdate();
            System.out.println("Movement created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating movement.");
            e.printStackTrace();
        }
    }

    public Movement getMovementById(int movementId) {
        Movement movement = null;
        String query = "SELECT * FROM Movement WHERE movement_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movementId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movement = new Movement();
                movement.setMovement_id(resultSet.getInt("movement_id"));
                movement.setType(resultSet.getString("type"));
                movement.setStock(resultSet.getInt("stock"));
                movement.setDate(resultSet.getDate("date").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("Error getting movement by ID.");
            e.printStackTrace();
        }
        return movement;
    }

    public List<Movement> getAllMovements() {
        List<Movement> movements = new ArrayList<>();
        String query = "SELECT * FROM Movement";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movement movement = new Movement();
                movement.setMovement_id(resultSet.getInt("movement_id"));
                movement.setType(resultSet.getString("type"));
                movement.setStock(resultSet.getInt("stock"));
                movement.setDate(resultSet.getDate("date").toLocalDate());
                movements.add(movement);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all movements.");
            e.printStackTrace();
        }
        return movements;
    }

    public void updateMovement(Movement movement) {
        String query = "UPDATE Movement SET type = ?, stock = ?, date = ? WHERE movement_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, movement.getType());
            statement.setInt(2, movement.getStock());
            statement.setDate(3, java.sql.Date.valueOf(movement.getDate()));
            statement.setInt(4, movement.getMovement_id());
            statement.executeUpdate();
            System.out.println("Movement updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating movement.");
            e.printStackTrace();
        }
    }

    public void deleteMovement(int movementId) {
        String query = "DELETE FROM Movement WHERE movement_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movementId);
            statement.executeUpdate();
            System.out.println("Movement deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting movement.");
            e.printStackTrace();
        }
    }
}