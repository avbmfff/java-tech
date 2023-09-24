package BankLab.Clients;

import BankLab.Accounts.Account;
import BankLab.Exceptions.BankException;
import BankLab.Observers.ClientInfo;
import BankLab.Observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий клиента банков.
 */
public class Client implements Observer {

    private Passport passport;

    private String name;

    private String lastName;

    private List<Account> accounts = new ArrayList<Account>();

    /**
     * Создание билдера для клиента.
     * @see ClientBuilder
     * @return
     */
    public static ClientBuilder createBuilder(){
        return new ClientBuilder();
    }

    /**
     * Оповещение клиента об изменении процентных ставок банка
     * @param obj
     * @see Observer
     */
    @Override
    public void update(Object obj) {
        Object info = (ClientInfo)obj;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getPassport(){
        return passport.getSeriesAndNumber();
    }

    public void setName(String name) throws IllegalArgumentException{
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name is null");
        }
        this.name = name;
    }

    public void setLastName(String lastName) throws IllegalArgumentException{
        if (lastName.isEmpty()){
            throw new IllegalArgumentException("last Name is null");
        }
        this.lastName = lastName;
    }

    public void setPassport(Passport passport){
        if (passport == null){
            throw new IllegalArgumentException("passport is null");
        }
        this.passport = passport;
    }

    /**
     * Добавление счета в список счетов клиента банка.
     * @param account - созданные счет
     * @throws IllegalArgumentException
     * @throws BankException
     */
    public void addNewAccount(Account account) throws IllegalArgumentException, BankException {
        if (account == null){
            throw new IllegalArgumentException("Null reference of account");
        }
        if (containsAccount(account)){
            throw new BankException("Account already exist");
        }
        accounts.add(account);
    }

    /**
     * Логическая проверка на существование счета у клиента
     * @param account
     * @return
     */
    private boolean containsAccount(Account account){
        for (Account value : accounts) {
            if (account.getId() == value.getId()){
                return true;
            }
        }
        return false;
    }

}
