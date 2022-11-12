import java.io.Serializable;
import java.util.ArrayList;

public class User extends BankAccount implements Serializable {

    double balance;

    public User() {
    }


    public User(String login, int pin) {
        super(login, pin);
        this.balance = 0;
    }

    public User(String login, int pin, double balance) {
        super(login, pin);
        this.balance = balance;
    }

    // ---- Setter

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setLogin(String login) {super.login = login;}

    public void setPin(int pin) {super.pin = pin;}

    // ---- Getter

    public double getBalance() {
        return this.balance;
    }

    public String getLogin() {return super.login;}

    public int getPin() {return super.pin;}


    // ---- Methods

    public void withdrawal(double value) {
        if (value < 0) {
            System.out.println("Vous ne pouvez pas retirer une valeur négative !");
        } else if (balance - value < 0) {
            System.out.println("Vous ne pouvez retirer autant d'argent car vous êtes pauvre !");
        } else {
            balance -= value;
        }
    }

    public void deposit(double value) {
        // Checks if the deposited value is not negative
        if (value < 0) {
            System.out.println("Vous ne pouvez pas déposer une valeur négative");
        } else {
            balance += value;
        }
    }

    public void transfer(double value, User beneficiary) {

        if (beneficiary instanceof User) {
            if (value < 0) {
                System.out.println("Vous ne pouvez pas transférer une valeur négative !");
            } else if (balance - value < 0) {
                System.out.println("Vous ne pouvez transférer autant d'argent car vous êtes pauvre !");
            } else {
                balance -= value;
                beneficiary.balance += value;
            }
            // Implémente vérification si user existe
        }
    }

    public String toString() {
        return super.login + " : " + super.pin + " : " + this.balance;
    }


    // Méthode à voir plus tard, compléter les méthodes précédentes également
    public String historyOfTransaction() {
        return "toto";
    }

}



