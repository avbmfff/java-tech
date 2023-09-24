package BankLab.Clients;

import java.util.Arrays;

/**
 * Реализация пасспорта клиента.
 */
public class Passport {
    private static final int LIMIT_DEGREE_SERIES = 4;

    private static final int LIMIT_DEGREE_NUMBER = 6;

    private String series;

    private String number;

    public Passport(String series, String number) throws IllegalArgumentException{
        if(series.length() != LIMIT_DEGREE_SERIES || number.length() != LIMIT_DEGREE_NUMBER){
            throw new IllegalArgumentException("Invalid series or passport data");
        }
        if(!checkForDigit(series) || !checkForDigit(number)){
            throw new IllegalArgumentException("Invalid series or passport data");
        }
        this.series = series;
        this.number = number;
    }

    /**
     * Получение и номера и серии пасспорта одновременно.
     * @return
     */
    public String getSeriesAndNumber(){
        return series + number;
    }

    /**
     * Проверка на то, что в поля "номер" и "серия" были введены только цифры.
     * @param digits - строка серии или номера
     * @return
     */
    private boolean checkForDigit(String digits){
        boolean isOnlyDigits = true;
        for(int i = 0; i < digits.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(digits.charAt(i))) {
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }

}
