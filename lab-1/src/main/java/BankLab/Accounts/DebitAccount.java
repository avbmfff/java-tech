package BankLab.Accounts;

import BankLab.Bank.Bank;
import BankLab.Clients.Client;
import BankLab.Exceptions.BankException;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;


/**
 * Класс, наследуем от Account.
 * Дебетовый счет со стабильным процентом, который можно закрыть в любой момент.
 * @see Account
 */
public class DebitAccount extends Account{

    private BigDecimal percentSum = BigDecimal.valueOf(0);

    private  int monthCounting = 0;
    public DebitAccount(Bank bank, Client client, float sum) throws IllegalArgumentException {
        super(bank, client);
        if (sum <= 0){
            throw new IllegalArgumentException("Sum can't be less than or equal zero");
        }
        this.sum = sum;
    }

    @Override
    public void addSum(float value) throws BankException, IllegalArgumentException {
        if(cancelTransaction(client)){
            throw new BankException("Operation is not possible");
        }
        if (value < LIMIT_MIN){
            throw new IllegalArgumentException("Sum can't be less or equal zero");
        }
        sum += value;
    }

    @Override
    public void withdraw(float value) throws BankException, IllegalArgumentException {
        if (cancelTransaction(client)){
            throw new BankException("Operation is not possible");
        }
        if (value > bank.clientInfo.getTransactionSumLimit() && checkClientsForDoubt(client)){
            throw new BankException("Operation not possible");
        }
        if (sum < LIMIT_MIN || sum - value < LIMIT_MIN){
            throw new IllegalArgumentException("Value can't be less or equal zero");
        } else {
            sum -= value;
        }
    }

    @Override
    public float getPercentSum(int days) throws IllegalArgumentException{
        float refSum = sum;
        if (days <= 0){
            throw new IllegalArgumentException("Value can't be less or equal zero");
        }
        while (days != 0 ){
            refSum += sum * (bank.clientInfo.getPercentForDebit() / DAYS_IN_YEAR);
            days--;
        }
        return refSum;
    }

    @Override
    public void addPercentSum() {
        sum += percentSum.floatValue();
        monthCounting +=1;
    }

    @Override
    public void countingPercentSum() {
        Time timeChange = new Time(0, 0, 0);
        Time now = Time.valueOf(LocalTime.now());
        if(now == timeChange){
            percentSum = BigDecimal.valueOf(sum * (bank.clientInfo.getPercentForDebit() / DAYS_IN_YEAR));
        }
    }
}
