package edu.school21.repositories;

import edu.school21.models.Product;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class ProductsRepositoryJdbcImplTest {

  private Connection connection;

  @BeforeEach
  public void init() throws SQLException {
    this.connection =
        new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("schema.sql")
            .addScript("data.sql")
            .build()
            .getConnection();
  }

  @Test
  public void test1() throws SQLException {
    final Product EXPECTED_PRODUCT = new Product(6, "Name", 12D);
    ProductsRepositoryJdbcImpl productsRepository = new ProductsRepositoryJdbcImpl(connection);
    productsRepository.save(EXPECTED_PRODUCT);
    Assertions.assertNotNull(EXPECTED_PRODUCT.getId());
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4})
  public void test2(int id) throws SQLException {
    ProductsRepositoryJdbcImpl productsRepository = new ProductsRepositoryJdbcImpl(connection);
    Product EXPECTED_PRODUCT = productsRepository.findById(id).orElse(null);
    EXPECTED_PRODUCT.setPrice((double) id);
    EXPECTED_PRODUCT.setName(Integer.toString(id));
    productsRepository.update(EXPECTED_PRODUCT);
    Product result = productsRepository.findById(id).orElse(null);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(id, result.getPrice());
    Assertions.assertEquals(Integer.toString(id), result.getName());
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4})
  public void testDelete(int id) throws Exception {
    ProductsRepositoryJdbcImpl productsRepository = new ProductsRepositoryJdbcImpl(connection);
    productsRepository.delete(id);
    Assertions.assertFalse(productsRepository.findById(id).isPresent());
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4})
  public void testFindByIdTrue(int id) throws Exception {
    ProductsRepositoryJdbcImpl repo = new ProductsRepositoryJdbcImpl(connection);
    Assertions.assertTrue(repo.findById(id).isPresent());
  }

  @ParameterizedTest
  @ValueSource(ints = {99, 99, 15874, 41})
  public void testFindByIdFalse(int id) throws Exception {
    ProductsRepositoryJdbcImpl repo = new ProductsRepositoryJdbcImpl(connection);
    Assertions.assertFalse(repo.findById(id).isPresent());
  }

  @Test
  public void testFindAll() throws Exception {
    ProductsRepositoryJdbcImpl repo = new ProductsRepositoryJdbcImpl(connection);
    long EXPECTED_SIZE = 5;
    Assertions.assertEquals(EXPECTED_SIZE, repo.findAll().size());
  }
}
