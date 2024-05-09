package com.michel.station.Repository;

import com.michel.station.DbConnection;
import com.michel.station.Model.Sales;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesRepository {
    private final Connection connection;

    public SalesRepository() {
        this.connection = DbConnection.getConnection();
    }

    public void createSale(Sales sale) {
        String query = "INSERT INTO Sales (sale_id, product_id, station_id, amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getSale_id());
            statement.setInt(2, sale.getProduct_id());
            statement.setInt(3, sale.getStation_id());
            statement.setBigDecimal(4, sale.getAmount());
            statement.executeUpdate();
            System.out.println("Sale created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating sale.");
            e.printStackTrace();
        }
    }

    public Sales getSaleById(int saleId) {
        Sales sale = null;
        String query = "SELECT * FROM Sales WHERE sale_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, saleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sale = new Sales();
                sale.setSale_id(resultSet.getInt("sale_id"));
                sale.setProduct_id(resultSet.getInt("product_id"));
                sale.setStation_id(resultSet.getInt("station_id"));
                sale.setAmount(resultSet.getBigDecimal("amount"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting sale by ID.");
            e.printStackTrace();
        }
        return sale;
    }

    public List<Sales> getAllSalesByStation(int stationId) {
        List<Sales> sales = new ArrayList<>();
        String query = "SELECT * FROM Sales WHERE station_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sales sale = new Sales();
                sale.setSale_id(resultSet.getInt("sale_id"));
                sale.setProduct_id(resultSet.getInt("product_id"));
                sale.setStation_id(resultSet.getInt("station_id"));
                sale.setAmount(resultSet.getBigDecimal("amount"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all sales by station.");
            e.printStackTrace();
        }
        return sales;
    }

    public void updateSale(Sales sale) {
        String query = "UPDATE Sales SET product_id = ?, station_id = ?, amount = ? WHERE sale_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getProduct_id());
            statement.setInt(2, sale.getStation_id());
            statement.setBigDecimal(3, sale.getAmount());
            statement.setInt(4, sale.getSale_id());
            statement.executeUpdate();
            System.out.println("Sale updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating sale.");
            e.printStackTrace();
        }
    }

    public void deleteSale(int saleId) {
        String query = "DELETE FROM Sales WHERE sale_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, saleId);
            statement.executeUpdate();
            System.out.println("Sale deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting sale.");
            e.printStackTrace();
        }
    }
}