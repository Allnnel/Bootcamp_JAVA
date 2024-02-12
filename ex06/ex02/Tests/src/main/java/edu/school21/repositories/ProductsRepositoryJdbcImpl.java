package edu.school21.repositories;

import edu.school21.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
  private final Connection connection;

  public ProductsRepositoryJdbcImpl(Connection connection) {
    this.connection = connection;
  }

  public List<Product> findAll() throws SQLException {
    final String sqlQuery = "SELECT * FROM products";
    List<Product> products = new LinkedList<>();
    PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      Integer id = resultSet.getInt("id");
      String name = resultSet.getString("name");
      Double price = resultSet.getDouble("price");
      Product product = new Product(id, name, price);
      products.add(product);
    }
    return products;
  }

  public Optional<Product> findById(Integer idProduct) throws SQLException {
    final String sqlQuery = "SELECT * FROM products WHERE id=?";
    ResultSet resultSet = null;
    Product product = null;
    PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
    preparedStatement.setInt(1, idProduct);
    resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      Integer id = resultSet.getInt("id");
      String name = resultSet.getString("name");
      Double price = resultSet.getDouble("price");
      product = new Product(id, name, price);
    }
    return Optional.ofNullable(product);
  }

  public void update(Product product) throws SQLException {
    final String sqlQuery = "UPDATE products SET name=?, price=? WHERE id=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
    preparedStatement.setString(1, product.getName());
    preparedStatement.setDouble(2, product.getPrice());
    preparedStatement.setInt(3, product.getId());
    preparedStatement.executeUpdate();
  }

  public void save(Product product) throws SQLException {
    final String sqlQuery = "INSERT INTO products (name, price) VALUES (?, ?)";
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
    preparedStatement.setString(1, product.getName());
    preparedStatement.setDouble(2, product.getPrice());
    preparedStatement.execute();
    resultSet = connection.createStatement().executeQuery("CALL IDENTITY()");
    if (resultSet.next()) {
      product.setId(resultSet.getInt(1));
    }
  }

  public void delete(Integer id) throws SQLException {
    final String sqlQuery = "DELETE FROM products WHERE id=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
    preparedStatement.setInt(1, id);
    preparedStatement.execute();
  }
}
