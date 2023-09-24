package BankLab.Bank;

import BankLab.Accounts.Account;
import BankLab.Accounts.CreditAccount;
import BankLab.Accounts.DebitAccount;
import BankLab.Accounts.DepositAccount;
import BankLab.Clients.Client;
import BankLab.Exceptions.BankException;
import BankLab.Observers.BankInfo;
import BankLab.Observers.ClientInfo;
import BankLab.Observers.Observable;
import BankLab.Observers.Observer;

import java.time.LocalDate;
import java.util.*;

/**
 * Класс, описывающий работу Банка.
 */
public class Bank implements Observer, Observable {

    private static final int LIMIT_DEGREE = 0;
    private List<Client> clients;

    private List<Account> accounts;
    private List<Observer> observers;

    public Bank(String name, ClientInfo clientInfo) throws IllegalArgumentException{
        if (name.isEmpty() || clientInfo == null){
            throw new IllegalArgumentException("null reference of name or client information");
        }
        this.name = name;
        this.clientInfo = clientInfo;
        clients = new ArrayList<Client>();
        observers = new ArrayList<Observer>();
        accounts = new ArrayList<Account>();
    }

    public ClientInfo clientInfo;

    public String name;

    /**
     * Процедура для добавления клиента в базу данных банка.
     * @param client
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public void addClient(Client client) throws IllegalArgumentException, BankException {
        if (client == null){
            throw new IllegalArgumentException("null reference of client");
        }
        if (containsClient(client)){
            throw new BankException("client already exist");
        }
        clients.add(client);
    }

    /**
     * Процедура для удаления клиента из базы данных банка.
     * @param client
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public void removeClient(Client client) throws IllegalArgumentException, BankException {
        if (client == null){
            throw new IllegalArgumentException("null reference of client");
        }
        if (!containsClient(client)){
            throw new BankException("client already exist");
        }
        clients.remove(client);
    }

    public List<Client> getClients(){
        return clients;
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
     * @throws IllegalArgumentException
     * @see Observable
     */
    @Override
    public void removeObserver(Observer observer) throws IllegalArgumentException {
        if (observer == null){
            throw new IllegalArgumentException("null reference of observer");
        }
        observers.remove(observer);
    }

    /**
     * Реализация процедуры оповещения наблюдателей об изменений процентных ставок в банке
     * @see Observable
     */
    @Override
    public void notifyObservers() {
        for (Observer obs: observers) {
            obs.update(clientInfo);
        }
    }

    /**
     * Реализация добавления процентных сумм на счет, при получении данных от центрального банка
     * @see CentralBank
     * @param obj
     * @see Observer
     */
    @Override
    public void update(Object obj) {
        Object bInfo = (BankInfo)obj;
        for (Account account: accounts){
            account.addPercentSum();
        }
    }

    /**
     * Процедура для изменнения процентных ставок
     * @param clientInfo
     * @throws IllegalArgumentException
     */
    public void changeClientInfo  (ClientInfo clientInfo)throws IllegalArgumentException{
        if (clientInfo == null){
            throw new IllegalArgumentException("null reference of client information");
        }
        this.clientInfo = clientInfo;
    }

    /**
     * Функция создания банковского крединтного счета.
     * @param client
     * @param value желаемая сумма кредита
     * @return кредитный счет
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public Account createCreditAccount(Client client, int value) throws IllegalArgumentException, BankException {
        if (client == null || value < LIMIT_DEGREE || !containsClient(client)){
            throw new IllegalArgumentException("null reference of data");
        }
        CreditAccount account = new CreditAccount(this, client, value);
        client.addNewAccount(account);
        return account;
    }

    /**
     * Функция создания депозитного счета.
     * @param client
     * @param value - первоначальная сумма
     * @param month  - период существования счета
     * @return депозитный счет
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public Account createDepositAccount(Client client, int value, int month) throws IllegalArgumentException, BankException {
        if (client == null || value < LIMIT_DEGREE || month < LIMIT_DEGREE || !containsClient(client)){
            throw new IllegalArgumentException("null reference of data");
        }
        Calendar calendar = new GregorianCalendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        calendar.roll(Calendar.MONTH, +month);
        Date finishDate = calendar.getTime();
        DepositAccount account = new DepositAccount(this, client, finishDate, value);
        client.addNewAccount(account);
        return account;
    }

    /**
     * Функция создания дебетового счета
     * @param client
     * @param value  - начальная сумма
     * @return дебетовый счет
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public Account createDebitAccount(Client client, int value) throws IllegalArgumentException, BankException {
        if (client == null || value < LIMIT_DEGREE || !containsClient(client)){
            throw new IllegalArgumentException("null reference of data");
        }
        DebitAccount account = new DebitAccount(this, client, value);
        client.addNewAccount(account);
        return account;
    }

    /**
     * Процедура осуществеления переводов между счетами среди клиентов одного банка
     * @param toClient - клиент, которому переводят
     * @param fromClient - клиент, который переводит
     * @param toAccount - данные счета клиента на чей счет поступают средства
     * @param fromAccount - данные счета клиента, с чего счета будут списывать средства
     * @param sum - сумма для перевода
     * @throws IllegalArgumentException
     * @throws BankException
     */

    public void localTransfer(Client toClient, Client fromClient, Account toAccount, Account fromAccount, int sum) throws IllegalArgumentException, BankException{
        if(toClient == null || fromClient == null || toAccount == null || fromAccount == null || sum < LIMIT_DEGREE){
            throw new IllegalArgumentException("null reference of data");
        }
        if(!containsClient(fromClient) || fromAccount.getClient() != fromClient || !containsClient(toClient) || toAccount.getClient() != toClient){
            throw new BankException("data doesn't exist");
        }
        if(!fromAccount.checkForTransfer(sum)){
            throw new BankException("insufficient funds for the transaction");
        }

        fromAccount.withdraw(sum);
        toAccount.addSum(sum);
    }

    /**
     * Процедура перевода суммы денег с счета клиента данного банка, на счет клиента другого банка
     * @param bank - банк клиента, которому переводят на счет средства
     * @param toClient - клиент, которому переводят
     * @param fromClient - клиент, который переводит
     * @param toAccount - данные счета клиента на чей счет поступают средства
     * @param fromAccount - данные счета клиента, с чего счета будут списывать средства
     * @param sum - сумма для перевода
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public void betweenBanksTransfer(Bank bank, Client toClient, Client fromClient, Account toAccount, Account fromAccount, int sum) throws IllegalArgumentException, BankException{
        if(bank == null || toClient == null || fromClient == null || toAccount == null || fromAccount == null || sum < LIMIT_DEGREE){
            throw new IllegalArgumentException("null reference of data");
        }
        if(!CentralBank.getInstanceCentralBank().contains(bank) || bank.containsClient(toClient) || !containsClient(fromClient) || fromAccount.getClient() != fromClient || !containsClient(toClient) || toAccount.getClient() != toClient){
            throw new BankException("data doesn't exist");
        }
        if(!fromAccount.checkForTransfer(sum)){
            throw new BankException("insufficient funds for the transaction");
        }
        fromAccount.withdraw(sum);
        toAccount.addSum(sum);
    }

    /**
     * Логическая функция, которая проверяет существование клиента в БД банка.
     * @param client
     * @return
     */
    private boolean containsClient(Client client){
        boolean bool = false;
        for (Client value:clients) {
            if(value.getPassport().equals(client.getPassport())){
                bool = true;
            }
        }
        return bool;
    }
}
