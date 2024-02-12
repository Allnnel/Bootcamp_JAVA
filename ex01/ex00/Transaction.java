package ex00;

import ex01.User;
import java.util.UUID;

public class Transaction {
  private UUID id;
  private User recipient;
  private User sender;
  private int transferAmount;
  private category transferCategory;
  private status statusTransaction;

  private enum category {
    CREDITS,
    DEBITS
  }

  public enum status {
    PASSED,
    FAILED
  }

  public Transaction(User userRecipient, User userSender, int userTransferAmount) {
    setRecipient(userRecipient);
    setSender(userSender);
    setTransferAmount(userTransferAmount);
    setIdentifier(UUID.randomUUID());

    if (userTransferAmount > 0) {
      setTransferCategory(category.CREDITS);
    } else {
      setTransferCategory(category.DEBITS);
    }

    if (sender.getBalance() < 0 || sender.getBalance() < userTransferAmount) {
      setStatus(status.FAILED);
    } else {
      sender.setBalance(sender.getBalance() - userTransferAmount);
      recipient.setBalance(recipient.getBalance() + userTransferAmount);
      setStatus(status.PASSED);
    }
  }

  public void setIdentifier(UUID userIdentifier) {
    this.id = userIdentifier;
  }

  public UUID getId() {
    return this.id;
  }

  public void setRecipient(User userRecipient) {
    this.recipient = userRecipient;
  }

  public User getRecipient() {
    return this.recipient;
  }

  public void setSender(User userSender) {
    this.sender = userSender;
  }

  public User getSender() {
    return this.sender;
  }

  public void setStatus(status status) {
    this.statusTransaction = status;
  }

  public status getStatusTransaction() {
    return this.statusTransaction;
  }

  public void setTransferCategory(category transferCategory) {
    this.transferCategory = transferCategory;
  }

  public category getTransferCategory() {
    return this.transferCategory;
  }

  public void setTransferAmount(Integer transferAmount) {
    if (transferAmount < 0) transferAmount = 0;
    this.transferAmount = transferAmount;
  }

  public Integer getTransferAmount() {
    return this.transferAmount;
  }
}
