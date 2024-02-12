package edu.school21.models;

public class User {
  private Long id;
  private String login;
  private String password;
  private boolean status;

  public User(Long id, String login, String password, boolean status) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.status = status;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public boolean isStatus() {
    return status;
  }
}
