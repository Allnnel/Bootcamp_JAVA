package ex03;

import ex00.Transaction;
import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
  private LinkedList<Transaction> transactions;

  public TransactionsLinkedList() {
    this.transactions = new LinkedList<>();
  }

  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public void deleteTransaction(UUID id) throws TransactionNotFoundException {
    Transaction foundTransaction = null;
    for (Transaction transaction : transactions) {
      if (transaction.getId().equals(id)) {
        foundTransaction = transaction;
        break;
      }
    }

    if (foundTransaction == null) {
      throw new TransactionNotFoundException();
    }

    transactions.remove(foundTransaction);
  }

  public Transaction[] toArray() {
    return transactions.toArray(new Transaction[0]);
  }
}
