package ex02;

import ex01.User;

public class UsersArrayList implements UsersList {
  private int countUser = 0;
  private User[] users = new User[DEFAULT_SIZE];

  public void addUser(User userName) {

    if (countUser > DEFAULT_SIZE) {
      increaseArraySize();
    }
    users[countUser] = userName;
    countUser++;
  }

  private void increaseArraySize() {
    int newSize = users.length * 2;
    User[] newArrayUsers = new User[newSize];
    System.arraycopy(users, 0, newArrayUsers, 0, users.length);
    users = newArrayUsers;
  }

  public User getUserById(int id) throws UserNotFoundException {
    if (id > 0)
      for (int i = 0; i != id && i != users.length; i++)
        if (users[i].getIdentifier().equals(id)) return users[i];

    throw new UserNotFoundException();
  }

  public User getUserByIndex(int index) throws UserNotFoundException {
    if (index < 0 || index > users.length) throw new UserNotFoundException();

    return users[index];
  }

  public int getUserCount() {
    return countUser;
  }
}
