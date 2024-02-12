package edu.school21.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDataSourceTest {

  @BeforeEach
  public void init() throws SQLException {
    EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
    DataSource dataSource =
        embeddedDatabaseBuilder
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("schema.sql")
            .addScript("data.sql")
            .build();
    Connection connection = dataSource.getConnection();
    if (connection != null) {
      System.out.println("[INFO] Database created");
    }
    Assertions.assertNotNull(dataSource);
  }
}
