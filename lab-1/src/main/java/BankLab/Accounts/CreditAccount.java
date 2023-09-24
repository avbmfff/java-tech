package BankLab.Accounts;

import BankLab.Bank.Bank;
import BankLab.Clients.Client;
import BankLab.Exceptions.BankException;

import java.math.BigDecimal;

/**
 * Класс, наследуем от Account.
 * Кредитный счет, у которого есть комиссия на снятие со счета и пополнение, если клиент ушел в минус.
 * @see Account
 */
public class CreditAccount extends Account{
    public CreditAccount(Bank bank, Client client, float sum) throws IllegalArgumentException {
        super(bank, client);
        if (sum < LIMIT_MIN){
            throw new IllegalArgumentException("Sum can't be less than or equal zero");
        }
        this.sum = sum;
    }

    @Override
    public void addSum(float value) throws BankException {
        if (cancelTransaction(client)){
            throw new BankException("Operation not possible");
        }
        if (sum < LIMIT_MIN){
            sum += value - bank.clientInfo.getCommissionForCredit();
        } else {
            sum += value;
        }
    }

    @Override
    public void withdraw(float value) throws BankException {
        if (cancelTransaction(client)){
            throw new BankException("Operation not possible");
        }
        if (value > bank.clientInfo.getTransactionSumLimit() && checkClientsForDoubt(client)){
            throw new BankException("Operation not possible");
        }
        if (sum < LIMIT_MIN){
            sum -= value - bank.clientInfo.getCommissionForCredit();
        } else {
            sum -= value;
        }
    }
}
