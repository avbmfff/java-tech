package BankLab.Clients;

/**
 * Реализация fluent builder для клиента.
 * @see Client
 */
public class ClientBuilder {
    private Client client;

    public ClientBuilder(){
        client = new Client();
    }

    public ClientBuilder setPassport(String series, String number){
        Passport passport = new Passport(series, number);
        client.setPassport(passport);
        return this;
    }

    public ClientBuilder setName(String name){
        client.setName(name);
        return this;
    }

    public ClientBuilder setLastName(String lastName){
        client.setName(lastName);
        return this;
    }
}
