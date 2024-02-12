package app;

import classes.User;
import managers.OrmManager;

public class Main {
  public static void main(String[] args) throws IllegalAccessException {
    OrmManager ormManager = new OrmManager(User.class);

    ormManager.createTable();
    ormManager.dropTable();

    User user = new User();
    user.setAge(10);
    user.setFirstName("a");

    ormManager.save(user);

    User foundUser = ormManager.findById(1L, User.class);
    System.out.println(foundUser);

    user.setFirstName("Новое имя");
    ormManager.update(user);
  }
}
