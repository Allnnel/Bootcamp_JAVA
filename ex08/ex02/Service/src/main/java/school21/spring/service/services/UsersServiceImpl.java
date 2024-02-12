package school21.spring.service.services;

import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

@Component("usersServiceImpl")
public class UsersServiceImpl implements UsersService {
  private final UsersRepository usersRepository;

  @Autowired
  public UsersServiceImpl(
      @Qualifier("usersRepositoryJdbcTemplate") UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public String SignUp(String email) {
    User user = new User(null, email, RandomPasswordGenerator.generatePassword(10));
    usersRepository.save(user);
    usersRepository.update(user);
    return user.getPassword();
  }

  private static class RandomPasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
      if (length < 4) {
        throw new IllegalArgumentException("The password length must be at least 4 characters");
      }
      StringBuilder sb = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
        int rndCharAt = random.nextInt(PASSWORD_ALLOW_BASE.length());
        char rndChar = PASSWORD_ALLOW_BASE.charAt(rndCharAt);
        sb.append(rndChar);
      }
      return sb.toString();
    }
  }
}
