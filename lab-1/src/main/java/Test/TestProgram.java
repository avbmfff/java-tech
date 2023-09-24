package Test;

import BankLab.Accounts.Account;
import BankLab.Bank.Bank;
import BankLab.Clients.Client;
import BankLab.Clients.Passport;
import BankLab.Exceptions.BankException;
import BankLab.Observers.ClientInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestProgram {
    @Test
    public void checkAccount() throws BankException {
        Map<Integer, Float> forBank = new HashMap<Integer, Float>();
        forBank.put(500, 5F);
        forBank.put(1000, 6F);
        ClientInfo clientInfo = new ClientInfo(150, 6, forBank, 150);
        Client client = new Client();
        Passport passport = new Passport("4565", "565789");
        client.setLastName("Gigi");
        client.setName("Gaga");
        client.setPassport(passport);
        Bank bank = new Bank("Bel", clientInfo);
        bank.addClient(client);
        Account account = bank.createDebitAccount(client, 29399);
        Assertions.assertEquals(account.getSum(), 29399);
    }

    @Test
    public void checkTransfer() throws BankException {
        Map<Integer, Float> forBank = new HashMap<Integer, Float>();
        forBank.put(500, 5F);
        forBank.put(1000, 6F);
        ClientInfo clientInfo = new ClientInfo(150, 6, forBank, 150);
        Client client1 = new Client();
        Client client = new Client();
        Passport passport1 = new Passport("2356", "487889");
        Passport passport = new Passport("8945", "654231");
        client1.setLastName("jfjjf");
        client.setLastName("jfjjf");
        client1.setName("fkfk");
        client.setName("fkfk");
        client1.setPassport(passport1);
        client.setPassport(passport);
        Bank bank = new Bank("Bel", clientInfo);
        bank.addClient(client1);
        bank.addClient(client);
        Account account1 = bank.createDebitAccount(client1, 4000);
        Account account = bank.createDebitAccount(client, 2000);
        bank.localTransfer(client, client1, account, account1, 500);
        Assertions.assertEquals(account1.getSum(), 3500);

    }

}
