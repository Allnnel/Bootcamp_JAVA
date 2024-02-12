package school21.spring.service.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(
    basePackages = "school21.spring.service.services school21.spring.service.repositories")
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

  @Autowired private Environment environment;

  @Bean
  public DriverManagerDataSource driverManagerDataSource() {
    DriverManagerDataSource driverManager = new DriverManagerDataSource();
    driverManager.setDriverClassName(
        Objects.requireNonNull(environment.getProperty("db.driver.name")));
    driverManager.setUrl(environment.getProperty("db.url"));
    driverManager.setUsername(environment.getProperty("db.user"));
    driverManager.setPassword(environment.getProperty("db.password"));
    return driverManager;
  }

  @Bean
  public HikariDataSource hikariDataSource() {
    HikariDataSource hikariDataSource = new HikariDataSource();
    hikariDataSource.setDriverClassName(
        Objects.requireNonNull(environment.getProperty("db.driver.name")));
    hikariDataSource.setJdbcUrl(environment.getProperty("db.url"));
    hikariDataSource.setUsername(environment.getProperty("db.user"));
    hikariDataSource.setPassword(environment.getProperty("db.password"));
    return hikariDataSource;
  }
}
