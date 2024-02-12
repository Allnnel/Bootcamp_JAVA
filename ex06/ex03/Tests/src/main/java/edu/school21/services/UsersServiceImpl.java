package edu.school21.services;

import edu.school21.exceptions.*;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
  UsersRepository usersRepository;

  public UsersServiceImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public boolean authenticate(String login, String password)
      throws AlwaysAuthenticatedException, EntityNotFoundException {
    User user = usersRepository.findByLogin(login);
    if (user == null || !user.getPassword().equals(password)) {
      return false;
    } else if (user.isStatus()) {
      throw new AlwaysAuthenticatedException();
    }
    user.setStatus(true);
    usersRepository.update(user);
    return true;
  }
}
