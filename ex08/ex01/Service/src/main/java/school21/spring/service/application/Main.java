package school21.spring.service.application;

import static java.lang.System.out;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class Main {

  public static void main(String[] args) {
    Main main = new Main();
    main.testing();
  }

  private void testing() {

    ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);

    usersRepository.save(new User(5, "one@mail.ru"));
    usersRepository.save(new User(5, "two@mail.ru"));
    usersRepository.save(new User(5, "three@mail.ru"));

    List<User> list = usersRepository.findAll();
    for (User user1 : list) {
      out.println(user1.getId() + " " + user1.getEmail());
    }

    out.println(
        usersRepository.findByEmail("one@mail.ru").get().getId()
            + " "
            + usersRepository.findByEmail("one@mail.ru").get().getEmail());

    usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

    List<User> list2 = usersRepository.findAll();
    for (User user1 : list2) {
      out.println(user1.getId() + " . " + user1.getEmail());
    }

    out.println(
        usersRepository.findByEmail("one@mail.ru").get().getId()
            + ". "
            + usersRepository.findByEmail("one@mail.ru").get().getEmail());
  }
}
