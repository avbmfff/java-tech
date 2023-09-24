package BankLab.Observers;

import BankLab.Bank.CentralBank;
import BankLab.Bank.Bank;

import java.util.Date;

/**
 * Класс даты, изменяемый ЦБ, чтобы банк начислил в этот день проценты по счетам своих пользователей.
 * @see CentralBank
 * @see Bank
 */

public class BankInfo {
    private Date date;

    public BankInfo(Date date){
        this.date = date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }
}
