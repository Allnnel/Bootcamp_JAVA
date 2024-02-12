package ex02;

import ex01.User;

public interface UsersList {
  int DEFAULT_SIZE = 10;

  void addUser(User userName);

  User getUserById(int id) throws UserNotFoundException;

  User getUserByIndex(int index) throws UserNotFoundException;

  int getUserCount();
}
