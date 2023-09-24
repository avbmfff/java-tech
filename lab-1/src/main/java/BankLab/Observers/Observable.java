package BankLab.Observers;

import BankLab.Bank.Bank;
import BankLab.Bank.CentralBank;

/**
 * Интерфейс паттерна Наблюдатель для раздачи оповещений.
 * @see Bank
 * @see CentralBank
 */
public interface Observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
