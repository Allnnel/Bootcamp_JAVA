package edu.school21.repositories;

import static org.mockito.Mockito.*;

import edu.school21.models.User;

public class UsersRepositoryMock implements UsersRepository {
  @Override
  public User findByLogin(String login) {
    User user = mock(User.class);
    when(user.getLogin()).thenReturn(login);
    return user;
  }

  @Override
  public void update(User user) {

  }
}
