package BankLab.Bank;

import BankLab.Clients.Client;
import BankLab.Exceptions.BankException;
import BankLab.Observers.BankInfo;
import BankLab.Observers.Observable;
import BankLab.Observers.Observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Центральный банк, который оповещает банки о начислении процентов.
 * Написан с применением паттерна Одиночка.
 */
public class CentralBank implements Observable {

    private static CentralBank centralBank;

    private List<Bank> banks;

    private List<Client> badClients;

    private BankInfo bankInfo;

    private List<Observer> observers;


    private CentralBank(){
        name = "Central Bank";
        banks = new ArrayList<Bank>();
        badClients = new ArrayList<Client>();
        observers = new ArrayList<Observer>();
        bankInfo = null;

    }

    public String name;

    /**
     * Реализация паттерна Одиночка, для получения экземпляра ЦБ
     * @return ЦБ
     */
    public static CentralBank getInstanceCentralBank(){
        if(centralBank == null){
            return new CentralBank();
        }
        return centralBank;
    }

    /**
     * Изменение даты для начисления процентов
     * @param date
     */
    public void setDate(Date date){
        bankInfo.setDate(date);
    }

    /**
     * Добавление банка в БД ЦБ
     * @param bank
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public void addBank(Bank bank) throws IllegalArgumentException, BankException {
        if (bank == null){
            throw new IllegalArgumentException("null reference of bank");
        }
        if (contains(bank)){
            throw new BankException("bank already exist");
        }
        banks.add(bank);
    }

    /**
     * Процедура добавления Мошенников в БД ЦБ
     * @param client - данные мошенника
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public void addBadClient(Client client) throws IllegalArgumentException, BankException {
        if (client == null){
            throw new IllegalArgumentException("null reference of client");
        }
        if (containsBadClient(client)){
            throw new BankException("client already exist");
        }
        badClients.add(client);
    }

    /**
     * Реализация регистрации пользователей для получения уведомлений.
     * @param observer
     * @see Observable
     */
    @Override
    public void registerObserver(Observer observer) {
        if (observer == null){
            throw new IllegalArgumentException("null reference of observer");
        }
        observers.add(observer);
    }

    /**
     * Реализация удаление полизователь, получающих уведомления.
     * @param observer
     * @see Observable
     * @throws IllegalArgumentException
     */
    @Override
    public void removeObserver(Observer observer) {
        if (observer == null){
            throw new IllegalArgumentException("null reference of observer");
        }
        observers.remove(observer);
    }

    /**
     * Реализация процедуры оповещения банков о дате начисления процентов в банке
     * @see Observable
     */
    @Override
    public void notifyObservers() {
        for (Observer obs: observers) {
            obs.update(bankInfo);
        }
    }

    /**
     * Логическая проверка на сущестоввание банка в БД ЦБ
     * @param bank
     * @return
     */
    public boolean contains(Bank bank){
        for (Bank value: banks) {
            if (bank.name == value.name){
                return true;
            }
        }
        return false;
    }

    /**
     * Логическая проверка на существование мошенника в БД ЦБ
     * @param client - данные мошенника
     * @return
     */
    public boolean containsBadClient(Client client){
        for (Client value:badClients) {
            if(client.getPassport() == value.getPassport()){
                return true;
            }
        }
        return false;
    }
}
