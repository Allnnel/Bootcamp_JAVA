package edu.school21.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import edu.school21.exceptions.*;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsersServiceImplTest {

  private UsersRepository usersRepository;
  private UsersServiceImpl usersService;

  @BeforeEach
  void setUp() {
    usersRepository = Mockito.mock(UsersRepository.class);
    usersService = new UsersServiceImpl(usersRepository);
  }

  @Test
  void testAuthenticate_WithValidCredentials()
      throws EntityNotFoundException, AlwaysAuthenticatedException {
    String login = "testUser";
    String password = "testPassword";
    User user = new User(1L, login, password, false);
    when(usersRepository.findByLogin(login)).thenReturn(user);

    boolean result = usersService.authenticate(login, password);

    assertTrue(result);
    assertTrue(user.isStatus());
    Mockito.verify(usersRepository).update(user);
  }

  @Test
  void testAuthenticate_WithInvalidCredentials()
      throws EntityNotFoundException, AlwaysAuthenticatedException {
    String login = "testUser";
    String password = "invalidPassword";
    User user = new User(1L, login, "correctPassword", false);
    when(usersRepository.findByLogin(login)).thenReturn(user);

    boolean result = usersService.authenticate(login, password);

    assertFalse(result);
    assertFalse(user.isStatus());
    Mockito.verify(usersRepository, Mockito.never()).update(user);
  }

  @Test
  void testAuthenticate_AlwaysAuthenticatedException() throws EntityNotFoundException {
    String login = "testUser";
    String password = "testPassword";
    User user = new User(1L, login, password, true);
    when(usersRepository.findByLogin(login)).thenReturn(user);

    assertThrows(
        AlwaysAuthenticatedException.class,
        () -> {
          usersService.authenticate(login, password);
        });
    assertTrue(user.isStatus());
    Mockito.verify(usersRepository, Mockito.never()).update(user);
  }

  @Test
  void testAuthenticate() throws EntityNotFoundException {
    User user = new User(1L, "testUser", "testPassword", true);
    Long id = user.getId();
    String login = user.getLogin();
    String password = user.getPassword();
    user.setId(id);
    user.setLogin(login);
    user.setPassword(password);
    user.setStatus(true);

    when(usersRepository.findByLogin(login)).thenReturn(user);

    assertThrows(
        AlwaysAuthenticatedException.class,
        () -> {
          usersService.authenticate(login, password);
        });
    assertTrue(user.isStatus());
    Mockito.verify(usersRepository, Mockito.never()).update(user);
  }

  @Test
  void testAuthenticate_EntityNotFoundException()
      throws AlwaysAuthenticatedException, EntityNotFoundException {
    String login = "nonExistentUser";
    String password = "testPassword";

    when(usersRepository.findByLogin(login)).thenThrow(new EntityNotFoundException());

    assertThrows(
        EntityNotFoundException.class,
        () -> {
          usersService.authenticate(login, password);
        });


    Mockito.verify(usersRepository, Mockito.never()).update(Mockito.any());
  }
}
