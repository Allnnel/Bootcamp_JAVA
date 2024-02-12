package ex03;

import ex00.Transaction;
import java.util.UUID;

public interface TransactionsList {
  void addTransaction(Transaction transaction);

  void deleteTransaction(UUID id) throws TransactionNotFoundException;

  Transaction[] toArray() throws TransactionNotFoundException;
}
