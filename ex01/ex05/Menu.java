package ex05;

import static java.lang.System.out;

import ex00.Transaction;
import ex01.User;
import ex02.UserNotFoundException;
import ex03.TransactionNotFoundException;
import ex04.TransactionsService;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
  private int menuChoice;
  TransactionsService transactionsService;

  public Menu() {
    transactionsService = new TransactionsService();
    menuChoice = 0;
  }

  public void startDevelopmentMode() {
    Scanner scanner = new Scanner(System.in);
    while (menuChoice != 7) {
      printDevelopmentManu();
      menuChoice = scanner.nextInt();
      if (menuChoice == 1) {
        menuChoiceOne();
      } else if (menuChoice == 2) {
        menuChoiceTwo();
      } else if (menuChoice == 3) {
        menuChoiceThree();
      } else if (menuChoice == 4) {
        menuChoiceFour();
      } else if (menuChoice == 5) {
        menuChoiceFive();
      } else if (menuChoice == 6) {
        menuChoiceSix();
      }
    }
    scanner.close();
  }

  public void startProductionMode() {
    Scanner scanner = new Scanner(System.in);
    while (menuChoice != 5) {
      printProductionManu();
      menuChoice = scanner.nextInt();
      if (menuChoice == 1) {
        menuChoiceOne();
      } else if (menuChoice == 2) {
        menuChoiceTwo();
      } else if (menuChoice == 3) {
        menuChoiceThree();
      } else if (menuChoice == 4) {
        menuChoiceFour();
      }
    }
    scanner.close();
  }

  private void menuChoiceOne() {
    Scanner scanner = new Scanner(System.in);
    out.println("Enter a user name and a balance");
    String userName = scanner.next();
    int userBalance = scanner.nextInt();
    User newUser = transactionsService.addUser(userName, userBalance);
    out.println("User with id = " + newUser.getIdentifier() + " is added");
  }

  private void menuChoiceTwo() {
    Scanner scanner = new Scanner(System.in);
    out.println("Enter a user ID");
    int userId = scanner.nextInt();
    try {
      out.println(
          transactionsService.getUserById(userId).getName()
              + " - "
              + transactionsService.getBalance(userId));
      out.println("The transfer is completed");
    } catch (UserNotFoundException e) {
      out.println(e);
    }
  }

  private void menuChoiceThree() {
    Scanner scanner = new Scanner(System.in);
    out.println("Enter a sender ID, a recipient ID, and a transfer amount");
    int userIdOne = scanner.nextInt();
    int userIdTwo = scanner.nextInt();
    int amount = scanner.nextInt();
    try {
      Transaction transaction = transactionsService.transaction(userIdTwo, userIdOne, amount);
      if (transaction.getStatusTransaction() == Transaction.status.PASSED) {
        out.println("The transfer is completed");
      } else {
        out.println("The transfer is not completed");
      }

    } catch (UserNotFoundException e) {
      out.println(e);
    }
  }

  private void menuChoiceFour() {
    Scanner scanner = new Scanner(System.in);
    out.println("Enter a user ID");
    int userId = scanner.nextInt();
    try {

      Transaction[] transactions =
          transactionsService.receiptOfTransfers(transactionsService.getUserById(userId));
      for (int i = transactions.length - 1; i != -1; i--) {
        if (transactions[i].getRecipient().getIdentifier() == userId) {
          if (transactions[i].getStatusTransaction() == Transaction.status.FAILED)
            out.println(
                "To "
                    + transactions[i].getRecipient().getName()
                    + "(id = "
                    + transactions[i].getRecipient().getIdentifier()
                    + ") -"
                    + transactions[i].getTransferAmount()
                    + " with id = "
                    + transactions[i].getId()
                    + " FAILED");
          else
            out.println(
                "To "
                    + transactions[i].getRecipient().getName()
                    + "(id = "
                    + transactions[i].getRecipient().getIdentifier()
                    + ") -"
                    + transactions[i].getTransferAmount()
                    + " with id = "
                    + transactions[i].getId());

        } else {
          if (transactions[i].getStatusTransaction() == Transaction.status.FAILED)
            out.println(
                "From "
                    + transactions[i].getRecipient().getName()
                    + "(id = "
                    + transactions[i].getRecipient().getIdentifier()
                    + ") -"
                    + transactions[i].getTransferAmount()
                    + " with id = "
                    + transactions[i].getId()
                    + " FAILED");
          else
            out.println(
                "From "
                    + transactions[i].getRecipient().getName()
                    + "(id = "
                    + transactions[i].getRecipient().getIdentifier()
                    + ") -"
                    + transactions[i].getTransferAmount()
                    + " with id = "
                    + transactions[i].getId());
        }
      }
    } catch (TransactionNotFoundException | UserNotFoundException e) {
      out.println(e);
    }
  }

  private void menuChoiceFive() {
    Scanner scanner = new Scanner(System.in);
    out.println("Enter a user ID and a transfer ID");
    int userId = scanner.nextInt();
    String uuidString = scanner.next();
    UUID transactionId = UUID.fromString(uuidString);
    try {
      Transaction[] transactions =
          transactionsService.receiptOfTransfers(transactionsService.getUserById(userId));
      transactionsService.deleteTransaction(userId, transactionId);
      out.println(
          "Transfer To "
              + transactions[userId].getRecipient().getName()
              + "(id = "
              + transactions[userId].getRecipient().getIdentifier()
              + ") "
              + transactions[userId].getTransferAmount()
              + "removed");

    } catch (TransactionNotFoundException | UserNotFoundException e) {
      out.println(e);
    }
  }

  private void menuChoiceSix() {
    out.println("Check results:");
    try {
      Transaction[] transactions = transactionsService.checkTransactionValidity();
      for (int i = 0; i != transactions.length; i++) {
        out.println(
            transactions[i].getSender().getName()
                + "(id = "
                + transactions[i].getSender().getIdentifier()
                + ") has an unacknowledged transfer id = "
                + transactions[i].getId()
                + " from "
                + transactions[i].getRecipient().getName()
                + "(id = "
                + transactions[i].getRecipient().getIdentifier()
                + " for "
                + transactions[i].getTransferAmount());
      }
    } catch (TransactionNotFoundException e) {
      out.println(e);
    }
  }

  private void printProductionManu() {
    out.println("1. Add a user");
    out.println("2. View user balances");
    out.println("3. Perform a transfer");
    out.println("4. View all transactions for a specific user");
    out.println("5. Finish execution");
  }

  private void printDevelopmentManu() {
    out.println("1. Add a user");
    out.println("2. View user balances");
    out.println("3. Perform a transfer");
    out.println("4. View all transactions for a specific user");
    out.println("5. DEV – remove a transfer by ID");
    out.println("6. DEV – check transfer validity");
    out.println("7. Finish execution");
  }
}
