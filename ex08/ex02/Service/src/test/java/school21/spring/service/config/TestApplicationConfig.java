package school21.spring.service.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.services.UsersServiceImplTest;

@SpringBootTest(classes = UsersServiceImplTest.class)
public class TestApplicationConfig {
  @Bean
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:mem:test");
    dataSource.setUsername("username");
    dataSource.setPassword("");
    return dataSource;
  }
}
