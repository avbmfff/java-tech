package BankLab.Observers;

import BankLab.Bank.Bank;
import BankLab.Clients.Client;

/**
 * Интерефей паттерна Налюдатель для получения оповещений.
 * @see Bank
 * @see Client
 */
public interface Observer {
    void update(Object obj);
}
