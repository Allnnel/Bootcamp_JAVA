package school21.spring.service.services;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

@Component
public class UsersServiceImplTest {
  private DataSource dataSource;

  public UsersServiceImplTest() {
    TestApplicationConfig testApplicationConfig = new TestApplicationConfig();
    dataSource = (DataSource) testApplicationConfig.dataSource();
  }

  @Test
  void Testing() {
    String password;
    try {
      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement();
      String sql =
          "CREATE SCHEMA IF NOT EXISTS service;\n"
              + "CREATE TABLE IF NOT EXISTS service.users (\n"
              + "    id SERIAL PRIMARY KEY,\n"
              + "    email VARCHAR(40) UNIQUE,\n"
              + "    password VARCHAR(10) UNIQUE\n"
              + ");";
      Thread.sleep(1000); // Подождать 1 секунду

      statement.execute(sql);
      UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
      UsersService usersService = new UsersServiceImpl(usersRepository);
      password = usersService.SignUp("email");
      out.println("\nTESTING TESTING TESTING TESTING TESTING TESTING\n");
      out.println("password == " + password);
      out.println("\nTESTING TESTING TESTING TESTING TESTING TESTING\n");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    Assertions.assertEquals(password.length(), 10);
  }
}
