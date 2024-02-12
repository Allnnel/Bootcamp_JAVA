package ex04;

import static java.lang.System.out;

import ex00.Transaction;
import ex01.User;
import ex02.UserNotFoundException;
import ex03.TransactionNotFoundException;

public class Program {
  public static void main(String[] args)
      throws TransactionNotFoundException, UserNotFoundException {

    TransactionsService transactionsService = new TransactionsService();
    User oneUser = transactionsService.addUser("Elena", 100);
    User twoUser = transactionsService.addUser("Marina", 100);
    transactionsService.transaction(oneUser.getIdentifier(), twoUser.getIdentifier(), 1);
    transactionsService.transaction(oneUser.getIdentifier(), twoUser.getIdentifier(), 100);
    transactionsService.transaction(twoUser.getIdentifier(), oneUser.getIdentifier(), -7);
    transactionsService.transaction(twoUser.getIdentifier(), oneUser.getIdentifier(), 9);
    transactionsService.transaction(twoUser.getIdentifier(), oneUser.getIdentifier(), -2);
    Transaction[] transactionsArray1 = transactionsService.receiptOfTransfers(oneUser);
    Transaction[] transactionsArray2 = transactionsService.receiptOfTransfers(twoUser);

    for (int i = 0; i != transactionsArray1.length; i++) {
      out.println(" User 1 ");
      out.println("Status Transaction " + transactionsArray1[i].getStatusTransaction());
      out.println("Id Transaction " + transactionsArray1[i].getId());
      out.println("Sender Name " + transactionsArray1[i].getSender().getName());
      out.println("Recipient Name " + transactionsArray1[i].getRecipient().getName());
      out.println("Transfer Category " + transactionsArray1[i].getTransferCategory());
      out.println("Transfer Amount " + transactionsArray1[i].getTransferAmount());
    }
    for (int i = 0; i != transactionsArray1.length; i++) {
      out.println(" User 2 ");
      out.println("Status Transaction " + transactionsArray2[i].getStatusTransaction());
      out.println("Id Transaction " + transactionsArray2[i].getId());
      out.println("Sender Name " + transactionsArray2[i].getSender().getName());
      out.println("Recipient Name " + transactionsArray2[i].getRecipient().getName());
      out.println("Transfer Category " + transactionsArray2[i].getTransferCategory());
      out.println("Transfer Amount " + transactionsArray2[i].getTransferAmount());
    }

    try {
      transactionsService.deleteTransaction(
          transactionsArray1[0].getSender().getIdentifier(), transactionsArray1[0].getId());
      Transaction[] transactionsArrayDELETE = transactionsService.receiptOfTransfers(oneUser);
      out.println(
          " USER 1 " + transactionsArray1[0].getId() + " != " + transactionsArrayDELETE[0].getId());
      out.println(
          " USER 1 " + transactionsArray1[1].getId() + " == " + transactionsArrayDELETE[0].getId());

      out.println(
          " USER 2 " + transactionsArray2[0].getId() + " != " + transactionsArrayDELETE[0].getId());
      out.println(
          " USER 2 " + transactionsArray2[1].getId() + " == " + transactionsArrayDELETE[0].getId());

      Transaction[] transactions = transactionsService.checkTransactionValidity();

      for (int i = 0; i != transactions.length; i++) {
        out.println(
            "transactions "
                + transactions[i].getStatusTransaction()
                + " "
                + transactions[i].getId());
      }

    } catch (TransactionNotFoundException e) {
      out.println(e);
    }
  }
}
