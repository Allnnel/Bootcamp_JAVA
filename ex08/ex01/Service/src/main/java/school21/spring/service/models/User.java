package school21.spring.service.models;

public class User {
  private Long id;
  private String email;

  public User(long id, String email) {
    this.email = email;
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
