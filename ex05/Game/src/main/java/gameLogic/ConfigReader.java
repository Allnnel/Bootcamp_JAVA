package gameLogic;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
  private final Properties properties;

  public ConfigReader(boolean isProduction) {

    properties = new Properties();
    String path = isProduction ? "application-production.properties" : "application-dev.properties";
    try {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
      properties.load(inputStream);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public String getColor(String symbol) {
    String key = symbol + ".color";
    return properties.getProperty(key);
  }

  public String getSymbol(String symbol) {
    String key = symbol + ".char";
    return properties.getProperty(key);
  }
}
