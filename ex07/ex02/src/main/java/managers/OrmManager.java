package managers;

import java.lang.reflect.Field;
import processors.OrmColumn;
import processors.OrmEntity;

public class OrmManager {
  private Class<?> entityClass;

  public OrmManager(Class<?> entityClass) {
    this.entityClass = entityClass;
  }

  public void createTable() {
    if (this.entityClass.isAnnotationPresent(OrmEntity.class)) {
      OrmEntity ormEntityAnnotation = this.entityClass.getAnnotation(OrmEntity.class);
      String tableName = ormEntityAnnotation.table();

      StringBuilder sb = new StringBuilder();
      sb.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");

      Field[] fields = this.entityClass.getDeclaredFields();
      for (Field field : fields) {
        if (field.isAnnotationPresent(OrmColumn.class)) {
          OrmColumn ormColumnAnnotation = field.getAnnotation(OrmColumn.class);

          String columnName = ormColumnAnnotation.name();
          int length = ormColumnAnnotation.length();
          String columnType = determineColumnType(field.getType());

          sb.append(columnName).append(" ").append(columnType);
          if (length > 0) {
            sb.append("(").append(length).append(")");
          }
          sb.append(",");
        }
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append(")");
      String createTableSql = sb.toString();

      System.out.println(createTableSql);

    } else {
      System.out.println(
          "The class is not marked with the @OrmEntity annotation, the table cannot be created.");
    }
  }

  public void save(Object entity) throws IllegalAccessException {
    if (this.entityClass != null) {
      OrmEntity ormEntityAnnotation = this.entityClass.getAnnotation(OrmEntity.class);
      String tableName = ormEntityAnnotation.table();
      StringBuilder sb = new StringBuilder();
      sb.append("INSERT INTO ").append(tableName).append(" (");

      Field[] fields = entity.getClass().getDeclaredFields();
      for (int i = 0; i != fields.length; i++) {
        if (i > 0) sb.append(", ");
        sb.append(fields[i].getName());
      }
      sb.append(") VALUES (");
      for (int i = 0; i != fields.length; i++) {
        fields[i].setAccessible(true);
        Object value = fields[i].get(entity);
        if (i > 0) sb.append(", ");
        sb.append("'").append(value).append("'");
      }
      sb.append(")");
      String sqlQuery = sb.toString();
      System.out.println(sqlQuery);

    } else {
      System.out.println(
          "The class is not marked with the @OrmEntity annotation, the table cannot be created.");
    }
  }

  public void update(Object entity) throws IllegalAccessException {
    if (this.entityClass != null) {
      OrmEntity ormEntityAnnotation = this.entityClass.getAnnotation(OrmEntity.class);
      String tableName = ormEntityAnnotation.table();
      StringBuilder sb = new StringBuilder();
      sb.append("UPDATE ").append(tableName).append(" SET ");

      Field[] fields = this.entityClass.getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
        fields[i].setAccessible(true);
        Object value = fields[i].get(entity);
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(fields[i].getName()).append("=").append("'").append(value).append("'");
      }

      String sqlQuery = sb.toString();
      System.out.println(sqlQuery);
    } else {
      System.out.println(
          "The class is not marked with the @OrmEntity annotation, the table cannot be created.");
    }
  }

  public <T> T findById(Long id, Class<T> aClass) {
    if (aClass.isAnnotationPresent(OrmEntity.class)) {
      OrmEntity ormEntityAnnotation = aClass.getAnnotation(OrmEntity.class);
      String tableName = ormEntityAnnotation.table();
      StringBuilder sb = new StringBuilder();
      sb.append("SELECT * FROM ").append(tableName).append(" WHERE id=").append(id);

      String sqlQuery = sb.toString();
      System.out.println(sqlQuery);
    } else {
      System.out.println(
          "The class is not marked with the @OrmEntity annotation, the table cannot be created.");
    }
    return null;
  }

  public void dropTable() {
    if (this.entityClass != null) {
      OrmEntity ormEntityAnnotation = entityClass.getAnnotation(OrmEntity.class);
      if (ormEntityAnnotation != null) {
        String tableName = ormEntityAnnotation.table();
        String dropTableSql = "DROP TABLE IF EXISTS " + tableName;
        System.out.println(dropTableSql);
      } else {
        System.out.println(
            "The class is not marked with the @OrmEntity annotation, the table cannot be dropped.");
      }
    }
  }

  private String determineColumnType(Class<?> fieldType) {
    if (String.class.equals(fieldType)) {
      return "VARCHAR";
    } else if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
      return "INT";
    } else if (long.class.equals(fieldType) || Long.class.equals(fieldType)) {
      return "BIGINT";
    } else if (boolean.class.equals(fieldType) || Boolean.class.equals(fieldType)) {
      return "BOOLEAN";
    } else {
      System.out.println("Failed to determine data type.Uses VARCHAR(255) by default.");
      return "VARCHAR(255)";
    }
  }
}
