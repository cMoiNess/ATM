public class BankAccount {
    String login;
    int pin;

    // ---- Constructor

    public BankAccount() {}

    public BankAccount(String login, int pin) {
        this.login = login;
        this.pin = pin;
    }

    // ---- Setters


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    // ---- Getters


    public String getLogin() {
        return this.login;
    }

    public int getPin() {
        return this.pin;
    }
}
