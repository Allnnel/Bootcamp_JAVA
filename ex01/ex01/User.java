package ex01;

public class User {
  private String name;
  private int id;
  private int balance;

  public User(String userName, int userBalance) {
    this.name = userName;
    this.balance = userBalance;
    this.id = UserIdsGenerator.getInstance().generateId();
  }

  public void setName(String userName) {
    this.name = userName;
  }

  public void setBalance(Integer userBalance) {
    if (userBalance != null && userBalance > 0) {
      this.balance = userBalance;
    } else {
      this.balance = 0;
    }
  }

  public String getName() {
    return this.name;
  }

  public Integer getIdentifier() {
    return this.id;
  }

  public Integer getBalance() {
    return this.balance;
  }
}
