package com.michel.station.Repository;

import com.michel.station.DbConnection;
import com.michel.station.Model.ProductTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTemplateRepository {
    private final Connection connection;

    public ProductTemplateRepository() {
        this.connection = DbConnection.getConnection();
    }

    public void createProductTemplate(ProductTemplate productTemplate) {
        String query = "INSERT INTO ProductTemplate (product_id, name, product_price_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productTemplate.getProduct_id());
            statement.setString(2, productTemplate.getName());
            statement.setInt(3, productTemplate.getProduct_price_id());
            statement.executeUpdate();
            System.out.println("Product template created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating product template.");
            e.printStackTrace();
        }
    }

    public ProductTemplate getProductTemplateById(int productId) {
        ProductTemplate productTemplate = null;
        String query = "SELECT * FROM ProductTemplate WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                productTemplate = new ProductTemplate();
                productTemplate.setProduct_id(resultSet.getInt("product_id"));
                productTemplate.setName(resultSet.getString("name"));
                productTemplate.setProduct_price_id(resultSet.getInt("product_price_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting product template by ID.");
            e.printStackTrace();
        }
        return productTemplate;
    }

    public List<ProductTemplate> getAllProductTemplates() {
        List<ProductTemplate> productTemplates = new ArrayList<>();
        String query = "SELECT * FROM ProductTemplate";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductTemplate productTemplate = new ProductTemplate();
                productTemplate.setProduct_id(resultSet.getInt("product_id"));
                productTemplate.setName(resultSet.getString("name"));
                productTemplate.setProduct_price_id(resultSet.getInt("product_price_id"));
                productTemplates.add(productTemplate);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all product templates.");
            e.printStackTrace();
        }
        return productTemplates;
    }

    public void updateProductTemplate(ProductTemplate productTemplate) {
        String query = "UPDATE ProductTemplate SET name = ?, product_price_id = ? WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productTemplate.getName());
            statement.setInt(2, productTemplate.getProduct_price_id());
            statement.setInt(3, productTemplate.getProduct_id());
            statement.executeUpdate();
            System.out.println("Product template updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating product template.");
            e.printStackTrace();
        }
    }

    public void deleteProductTemplate(int productId) {
        String query = "DELETE FROM ProductTemplate WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
            System.out.println("Product template deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting product template.");
            e.printStackTrace();
        }
    }
}