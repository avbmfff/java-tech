package BankLab.Accounts;

import BankLab.Bank.Bank;
import BankLab.Clients.Client;
import BankLab.Exceptions.BankException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


/**
 * Класс, наследуем от Account.
 * Депозитный счет, у которого процент зависит от начального взноса, открывается на какой-то определенный период.
 * Преждевременно закрыть нельзя.
 * @see Account
 */
public class DepositAccount extends Account{
    private Date finishTime;

    private float startedSummary;

    private float percent;

    private  float percentSummary;

    private  int monthCounting;


    public DepositAccount(Bank bank, Client client, Date finishTime, float startedSummary) throws IllegalArgumentException {
        super(bank, client);
        if (sum <= 0){
            throw new IllegalArgumentException("Sum can't be less than or equal zero");
        }
        this.sum = sum;
        this.finishTime = finishTime;
        this.startedSummary = sum;
        getPercent();
    }

    @Override
    public void addSum(float value) throws BankException {
        if(cancelTransaction(client)){
            throw new BankException("Operation is not possible");
        }
        if (value < LIMIT_MIN){
            throw new IllegalArgumentException("Sum can't be less or equal zero");
        }
        if (LocalDate.now().equals(finishTime)){
            throw new BankException("Operation is not possible");
        }
        sum += value;
    }

    @Override
    public void withdraw(float value) throws BankException, IllegalArgumentException {
        if (cancelTransaction(client)){
            throw new BankException("Operation is not possible");
        }
        if (value > bank.clientInfo.getTransactionSumLimit() && checkClientsForDoubt(client)){
            throw new BankException("Operation is not possible");
        }
        if (LocalDate.now().equals(finishTime) || value < LIMIT_MIN){
            throw new BankException("Operation is not possible");
        } else {
            sum -= value;
        }
    }

    @Override
    public float getPercentSum(int days) throws IllegalArgumentException {
        float refSum = sum;
        if (days <= 0){
            throw new IllegalArgumentException("Value can't be less or equal zero");
        }
        while (days != 0 ){
            refSum += sum * (percent / DAYS_IN_YEAR);
            days--;
        }
        return refSum;
    }

    @Override
    public void addPercentSum() {
        sum += percentSummary;
        monthCounting +=1;
    }

    @Override
    public void countingPercentSum() {
        Time timeChange = new Time(0, 0, 0);
        Time now = Time.valueOf(LocalTime.now());
        if(now == timeChange){
            percentSummary += sum * (percent / DAYS_IN_YEAR);
        }
    }

    private void getPercent(){
        float prev = 0;
        Map<Integer, Float> percents = bank.clientInfo.getDepositPercent();
        ArrayList<Integer> keys = new ArrayList<>(percents.keySet());
        for (float key: keys) {
            if (key >= sum) {
                percent = percents.get(prev);
                break;
            }
            prev = key;
        }
    }
}
