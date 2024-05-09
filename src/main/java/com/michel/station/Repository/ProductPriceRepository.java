package com.michel.station.Repository;

import com.michel.station.DbConnection;
import com.michel.station.Model.ProductPrice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductPriceRepository {
    private final Connection connection;

    public ProductPriceRepository() {
        this.connection = DbConnection.getConnection();
    }

    public void createProductPrice(ProductPrice productPrice) {
        String query = "INSERT INTO ProductPrice (price_id, product_id, price, date_added) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productPrice.getPrice_id());
            statement.setInt(2, productPrice.getProduct_id());
            statement.setBigDecimal(3, productPrice.getPrice());
            statement.setDate(4, java.sql.Date.valueOf(productPrice.getDate_added()));
            statement.executeUpdate();
            System.out.println("Product price created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating product price.");
            e.printStackTrace();
        }
    }

    public ProductPrice getProductPriceById(int priceId) {
        ProductPrice productPrice = null;
        String query = "SELECT * FROM ProductPrice WHERE price_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, priceId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                productPrice = new ProductPrice();
                productPrice.setPrice_id(resultSet.getInt("price_id"));
                productPrice.setProduct_id(resultSet.getInt("product_id"));
                productPrice.setPrice(resultSet.getBigDecimal("price"));
                productPrice.setDate_added(resultSet.getDate("date_added").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("Error getting product price by ID.");
            e.printStackTrace();
        }
        return productPrice;
    }

    public List<ProductPrice> getAllProductPrices() {
        List<ProductPrice> productPrices = new ArrayList<>();
        String query = "SELECT * FROM ProductPrice";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductPrice productPrice = new ProductPrice();
                productPrice.setPrice_id(resultSet.getInt("price_id"));
                productPrice.setProduct_id(resultSet.getInt("product_id"));
                productPrice.setPrice(resultSet.getBigDecimal("price"));
                productPrice.setDate_added(resultSet.getDate("date_added").toLocalDate());
                productPrices.add(productPrice);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all product prices.");
            e.printStackTrace();
        }
        return productPrices;
    }

    public List<ProductPrice> getProductPricesByProductId(int productId) {
        List<ProductPrice> productPrices = new ArrayList<>();
        String query = "SELECT * FROM ProductPrice WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductPrice productPrice = new ProductPrice();
                productPrice.setPrice_id(resultSet.getInt("price_id"));
                productPrice.setProduct_id(resultSet.getInt("product_id"));
                productPrice.setPrice(resultSet.getBigDecimal("price"));
                productPrice.setDate_added(resultSet.getDate("date_added").toLocalDate());
                productPrices.add(productPrice);
            }
        } catch (SQLException e) {
            System.out.println("Error getting product prices by product ID.");
            e.printStackTrace();
        }
        return productPrices;
    }

    public void updateProductPrice(ProductPrice productPrice) {
        String query = "UPDATE ProductPrice SET price = ?, date_added = ? WHERE price_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, productPrice.getPrice());
            statement.setDate(2, java.sql.Date.valueOf(productPrice.getDate_added()));
            statement.setInt(3, productPrice.getPrice_id());
            statement.executeUpdate();
            System.out.println("Product price updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating product price.");
            e.printStackTrace();
        }
    }

    public void deleteProductPrice(int priceId) {
        String query = "DELETE FROM ProductPrice WHERE price_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, priceId);
            statement.executeUpdate();
            System.out.println("Product price deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting product price.");
            e.printStackTrace();
        }
    }
}