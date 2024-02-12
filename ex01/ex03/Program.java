package ex03;

import static java.lang.System.out;

import ex00.Transaction;
import ex01.User;

public class Program {
  public static void main(String[] args) {

    User oneUser = new User("Elena", 1200);
    User twoUser = new User("Marina", 0);
    out.println("oneUser balance == " + oneUser.getBalance());
    out.println("twoUser balance == " + twoUser.getBalance());
    Transaction transaction = new Transaction(twoUser, oneUser, 10);
    out.println(
        "transaction status == "
            + transaction.getStatusTransaction()
            + ", id: "
            + transaction.getId());
    out.println("oneUser balance == " + oneUser.getBalance());
    out.println("twoUser balance == " + twoUser.getBalance());

    Transaction transactionTwo = new Transaction(oneUser, twoUser, 10);
    out.println(
        "transaction status == "
            + transactionTwo.getStatusTransaction()
            + ", id: "
            + transactionTwo.getId());
    out.println("oneUser balance == " + oneUser.getBalance());
    out.println("twoUser balance == " + twoUser.getBalance());

    Transaction transactionThree = new Transaction(oneUser, twoUser, 10);
    out.println(
        "transaction status == "
            + transactionThree.getStatusTransaction()
            + ", id: "
            + transactionThree.getId());
    out.println("oneUser balance == " + oneUser.getBalance());
    out.println("twoUser balance == " + twoUser.getBalance());

    TransactionsLinkedList transactionsList = new TransactionsLinkedList();
  }
}
