package ex04;

import ex00.Transaction;
import ex01.User;
import ex02.UserNotFoundException;
import ex02.UsersArrayList;
import ex03.TransactionNotFoundException;
import ex03.TransactionsLinkedList;
import java.util.UUID;

public class TransactionsService {
  private TransactionsLinkedList transactionsLinkedList;
  private UsersArrayList usersArrayList;

  public TransactionsService() {
    this.usersArrayList = new UsersArrayList();
    this.transactionsLinkedList = new TransactionsLinkedList();
  }

  public User addUser(String userName, int userBalance) {
    User user = new User(userName, userBalance);
    usersArrayList.addUser(user);
    return user;
  }

  public User getUserById(int id) throws UserNotFoundException {
    return usersArrayList.getUserById(id);
  }

  public Integer getBalance(int id) throws UserNotFoundException {
    User user = usersArrayList.getUserById(id);
    return user.getBalance();
  }

  public Transaction transaction(int idOneUser, int idTwoUser, int userBalance)
      throws UserNotFoundException {

    User oneUser = usersArrayList.getUserById(idOneUser);
    User twoUser = usersArrayList.getUserById(idTwoUser);
    Transaction transaction = new Transaction(oneUser, twoUser, userBalance);
    transactionsLinkedList.addTransaction(transaction);
    return transaction;
  }

  public Transaction[] receiptOfTransfers(User inputUser) throws TransactionNotFoundException {
    return transactionsLinkedList.toArray();
  }

  public void deleteTransaction(int idUser, UUID idTransaction)
      throws TransactionNotFoundException {

    Transaction[] transactions = transactionsLinkedList.toArray();
    for (int i = 0; i != transactions.length; i++) {
      if (transactions[i].getSender() != null
          && transactions[i].getId() != null
          && transactions[i].getRecipient() != null)
        if (transactions[i].getId().equals(idTransaction)
            && (transactions[i].getSender().getIdentifier() == idUser
                || transactions[i].getRecipient().getIdentifier() == idUser)) {
          transactionsLinkedList.deleteTransaction(idTransaction);
          return;
        }
    }
    throw new TransactionNotFoundException();
  }

  public Transaction[] checkTransactionValidity() throws TransactionNotFoundException {
    Transaction[] transactions = transactionsLinkedList.toArray();
    int countFailedTransactions = 0;

    for (Transaction transaction : transactions) {

      if ("FAILED".equals(String.valueOf(transaction.getStatusTransaction())))
        countFailedTransactions++;
    }

    Transaction[] failedTransactions = new Transaction[countFailedTransactions];
    int index = 0;
    for (Transaction transaction : transactions) {
      if ("FAILED".equals(String.valueOf(transaction.getStatusTransaction())))
        failedTransactions[index++] = transaction;
    }
    return failedTransactions;
  }
}
