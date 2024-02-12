package ex01;

public class UserIdsGenerator {
  private static final UserIdsGenerator ID_GENERATOR = new UserIdsGenerator();
  private int id;

  private UserIdsGenerator() {
    id = 0;
  }

  public static UserIdsGenerator getInstance() {
    return ID_GENERATOR;
  }

  public int generateId() {
    id++;
    return id;
  }
}
