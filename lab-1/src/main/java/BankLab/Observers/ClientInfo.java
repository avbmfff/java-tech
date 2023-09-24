package BankLab.Observers;

import BankLab.Bank.Bank;

import java.util.Map;

/**
 * Информация о процентных ставках банка.
 * @see Bank
 */
public class ClientInfo {
    private static final int LIMIT_DEGREE = 0;

    private int transactionSumLimit;

    private int percentForDebit;

    private Map<Integer, Float> percentForDeposit;

    private float commissionForCredit;

    public ClientInfo(int transactionSumLimit, int percentForDebit, Map<Integer, Float> percentsForDeposit, float commissionForCredit){
    if (transactionSumLimit < LIMIT_DEGREE || percentForDebit < LIMIT_DEGREE || commissionForCredit < LIMIT_DEGREE){
        throw new IllegalArgumentException("invalid degree");
    }
    this.percentForDebit = percentForDebit;
    this.percentForDeposit = percentsForDeposit;
    this.commissionForCredit = commissionForCredit;
    this.transactionSumLimit = transactionSumLimit;
    }

    public void setTransactionSumLimit(int transactionSumLimit){
        if (transactionSumLimit < LIMIT_DEGREE){
            throw new IllegalArgumentException("invalid degree");
        }
        this.transactionSumLimit = transactionSumLimit;
    }

    public int getTransactionSumLimit(){
        return transactionSumLimit;
    }

    public void setPercentForDebit(int percentForDebit){
        if (percentForDebit < LIMIT_DEGREE){
            throw new IllegalArgumentException("invalid degree");
        }
        this.percentForDebit = percentForDebit;
    }

    public int getPercentForDebit(){
        return percentForDebit;
    }
    public float getCommissionForCredit(){
        return commissionForCredit;
    }

    public void setPercentForDeposit(int percentForDebit){
        if (transactionSumLimit < LIMIT_DEGREE){
            throw new IllegalArgumentException("invalid degree");
        }
        this.percentForDebit = percentForDebit;
    }


    public Map<Integer, Float> getDepositPercent(){
        return percentForDeposit;
    }
}
