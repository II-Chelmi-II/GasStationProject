package com.michel.station.Repository;

import com.michel.station.DbConnection;
import com.michel.station.Model.Station;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StationRepository {
    private final Connection connection;

    public StationRepository() {
        this.connection = DbConnection.getConnection();
    }

    public void createStation(Station station) {
        String query = "INSERT INTO Station (station_id, location) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, station.getStation_id());
            statement.setString(2, station.getLocation());
            statement.executeUpdate();
            System.out.println("Station created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating station.");
            e.printStackTrace();
        }
    }

    public Station getStationById(int stationId) {
        Station station = null;
        String query = "SELECT * FROM Station WHERE station_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                station = new Station();
                station.setStation_id(resultSet.getInt("station_id"));
                station.setLocation(resultSet.getString("location"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting station by ID.");
            e.printStackTrace();
        }
        return station;
    }

    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();
        String query = "SELECT * FROM Station";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Station station = new Station();
                station.setStation_id(resultSet.getInt("station_id"));
                station.setLocation(resultSet.getString("location"));
                stations.add(station);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all stations.");
            e.printStackTrace();
        }
        return stations;
    }

    public void updateStation(Station station) {
        String query = "UPDATE Station SET location = ? WHERE station_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, station.getLocation());
            statement.setInt(2, station.getStation_id());
            statement.executeUpdate();
            System.out.println("Station updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating station.");
            e.printStackTrace();
        }
    }

    public void deleteStation(int stationId) {
        String query = "DELETE FROM Station WHERE station_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stationId);
            statement.executeUpdate();
            System.out.println("Station deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting station.");
            e.printStackTrace();
        }
    }
}