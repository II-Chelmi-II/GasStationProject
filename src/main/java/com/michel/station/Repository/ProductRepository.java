package com.michel.station.Repository;

import com.michel.station.DbConnection;
import com.michel.station.Model.Product;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final Connection connection;

    public ProductRepository() {
        this.connection = DbConnection.getConnection();
    }

    public void createProduct(Product product) {
        String query = "INSERT INTO Product (product_id, station_id, stock) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, product.getProduct_id());
            statement.setString(2, product.getName());
            statement.executeUpdate();
            System.out.println("Product created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating product.");
            e.printStackTrace();
        }
    }

    public Product getProductById(int productId) {
        Product product = null;
        String query = "SELECT * FROM Product WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting product by ID.");
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all products.");
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getAllProductsByStation(int stationId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE station_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all products by station.");
            e.printStackTrace();
        }
        return products;
    }

    public void updateProduct(Product product) {
        String query = "UPDATE Product SET name = ? WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setInt(3, product.getProduct_id());
            statement.executeUpdate();
            System.out.println("Product updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating product.");
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId, String name) {
        String query = "DELETE FROM Product WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting product.");
            e.printStackTrace();
        }
    }
}
