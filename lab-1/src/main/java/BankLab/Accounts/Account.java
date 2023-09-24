package BankLab.Accounts;

import BankLab.Bank.Bank;
import BankLab.Bank.CentralBank;
import BankLab.Clients.Client;
import BankLab.Exceptions.BankException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Класс, описывающий основные принципы работы счетов.
 * @see CreditAccount
 * @see DepositAccount
 * @see DebitAccount
 */
public abstract class Account {
    protected static final int LIMIT_MIN = 0;

    protected static final int DAYS_IN_YEAR = 365;

    private UUID id;

    protected Bank bank;

    protected Client client;

    protected float sum;

    protected Date creatingTime;

    public Account(Bank bank, Client client) throws IllegalArgumentException {
        if (bank == null || client == null){
            throw new IllegalArgumentException("Null reference of bank or client");
        }

        id = UUID.randomUUID();
        sum = 0;
        creatingTime = new Date();
        this.bank = bank;
        this.client = client;
    }

    public float getSum() {
        return sum;
    }

    public UUID getId(){
        return id;
    }

    public Client getClient(){
        return client;
    }

    public Bank getBank(){
        return bank;
    }

    /**
     * Процедура для пополнения счёта.
     * @param value - сумма пополнения
     * @throws BankException
     */
    public void addSum(float value) throws BankException {}

    /**
     * Процедура для снятия денег со счета.
     * @param value - сумма снятия
     * @throws BankException
     */
    public void withdraw(float value) throws BankException {}

    /**
     * Функция перемотки и получения сведений о процентах, накопленныз за некий период, расчитанный в днях.
     * @param days - период в днях
     * @return сумма с процентным накоплением за период days
     * @throws IllegalArgumentException
     */
    public float getPercentSum(int days) throws IllegalArgumentException{
        return 0;
    }

    /**
     * Процедура, которая добавляет на счёт накопленные проценты.
     */
    public void addPercentSum() {}

    /**
     * Процедура, которая накапливает проценты каждый день.
     */
    public void countingPercentSum() {}

    /**
     * Логическая функция, которая показывает, сможет ли человек перевести некоторую сумму со своего счета.
     * @param sum - сумма для совершения транзакции
     * @return
     */
    public boolean checkForTransfer(float sum){
        if (this.sum - sum < 0) {
            return false;
        }
        return true;
    }

    /**
     * Логическая функция, которая показывает сомнителен ли клиент или нет.
     * @param client
     * @return
     * @throws IllegalArgumentException
     */
    protected boolean checkClientsForDoubt(Client client) throws IllegalArgumentException {
        if (client == null) {
            throw new IllegalArgumentException("null reference of client");
        }
        return client.getPassport() == null;
    }

    /**
     * Логическая функция отмены транзакции для сомнительных клиентов.
     * @param client
     * @return
     * @throws IllegalArgumentException
     */

    protected boolean cancelTransaction(Client client) throws IllegalArgumentException {
        if (client == null) {
            throw new IllegalArgumentException("Null reference of client");
        }
        return CentralBank.getInstanceCentralBank().containsBadClient(client);
    }
}
