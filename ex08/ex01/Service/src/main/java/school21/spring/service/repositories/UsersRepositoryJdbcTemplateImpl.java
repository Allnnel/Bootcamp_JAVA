package school21.spring.service.repositories;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import school21.spring.service.models.User;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
  private final Connection connection;

  public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) throws SQLException {
    this.connection = dataSource.getConnection();
  }

  @Override
  public Optional<User> findById(Long idUser) {
    String sqlQuery = "SELECT * FROM service.users WHERE id=?";
    ResultSet resultSet = null;
    User user = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      preparedStatement.setLong(1, idUser);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        long id = resultSet.getLong("id");
        String email = resultSet.getString("email");
        user = new User(id, email);
      }
    } catch (SQLException e) {
      out.println(e);
    }
    return Optional.ofNullable(user);
  }

  @Override
  public void save(User entity) {
    String sqlQuery = "INSERT INTO service.users (email) VALUES (?) RETURNING *";
    ResultSet resultSet = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      preparedStatement.setString(1, entity.getEmail());
      resultSet = preparedStatement.executeQuery();
      resultSet.next();
      entity.setId(resultSet.getLong("id"));
    } catch (SQLException e) {
      out.println(e);
    }
  }

  @Override
  public void update(User entity) {
    String sqlQuery = "UPDATE service.users SET email=? WHERE  id=?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      preparedStatement.setString(1, entity.getEmail());
      preparedStatement.setLong(2, entity.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      out.println(e);
    }
  }

  @Override
  public void delete(Long id) {
    String sqlQuery = "DROP service.users WHERE id=?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      preparedStatement.setLong(1, id);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      out.println(e);
    }
  }

  @Override
  public Optional<User> findByEmail(String emailUser) {
    String sqlQuery = "SELECT * FROM service.users WHERE email=?";
    ResultSet resultSet = null;
    User user = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      preparedStatement.setString(1, emailUser);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        String email = resultSet.getString("email");
        Long id = resultSet.getLong("id");
        user = new User(id, email);
      }
    } catch (SQLException e) {
      out.println(e);
    }
    return Optional.ofNullable(user);
  }

  @Override
  public List<User> findAll() {
    String sqlQuery = "SELECT * FROM service.users";
    ResultSet resultSet = null;
    List<User> list = new ArrayList<>();
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Long id = resultSet.getLong("id");
        String email = resultSet.getString("email");
        User user = new User(id, email);
        list.add(user);
      }
    } catch (SQLException e) {
      out.println(e);
    }
    return list;
  }
}
