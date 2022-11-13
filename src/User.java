import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class User extends BankAccount implements Serializable {

    double balance;

    // ---- Constructors

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

    // ---- Setters

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setLogin(String login) {super.login = login;}

    public void setPin(int pin) {super.pin = pin;}

    // ---- Getters

    public double getBalance() {
        return this.balance;
    }

    public String getLogin() {return super.login;}

    public int getPin() {return super.pin;}

    // ---- To display an object

    public String toString() {
        return super.login + " : " + super.pin + " : " + this.balance;
    }

    // ---- Methods

    // Method of withdrawing money from the account
    public void withdrawal(double value) {
        double oldBalance = balance; // variable that stores the balance before the transaction
        // We test if it is possible to withdraw money, so if you have enough money. And we check if the deposited value is not negative.
        if (value < 0) {
            System.out.println("Vous ne pouvez pas retirer une valeur négative !");
        } else if (balance - value < 0) {
            System.out.println("Vous ne pouvez retirer autant d'argent car vous êtes pauvre !");
        } else {
            balance -= value; // We withdraw the money
            System.out.println("Vous avez retiré : " + value + "euro.s !");
            FileGestion fileGestion = new FileGestion(); // Object that will allow us to save the transaction
            String fileName = getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName); // Creation of a file of the form "[login].txt" if the file does not exist, otherwise nothing happens.
            fileGestion.writeToFileLineBreak(fileName, "Retrait : " + value + " | " + "Solde avant retrait : "
                    + oldBalance + " | " + "Solde après retrait : " + balance); // We save the transaction in the previous file
        }
    }

    // Method of depositing money into the account
    public void deposit(double value) {
        double oldBalance = balance; // variable that stores the balance before the transaction
        // Checks if the deposited value is not negative
        if (value < 0) {
            System.out.println("Vous ne pouvez pas déposer une valeur négative");
        } else {
            balance += value; // We deposit the money
            System.out.println("Vous avez dépôser : " + value + "euro.s !");
            FileGestion fileGestion = new FileGestion(); // Object that will allow us to save the transaction
            String fileName = getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName); // Creation of a file of the form "[login].txt" if the file does not exist, otherwise nothing happens.
            fileGestion.writeToFileLineBreak(fileName, "Dépôt : " + value + " | " + "Solde avant dépôt : " + oldBalance + " | " + "Solde après dépôt : " + balance); // We save the transaction in the previous file
        }
    }

    // Method of transferring money to another account
    public void transfer(double value, User beneficiary) throws FileNotFoundException {
        double oldBalance = balance; // variable that stores the balance of the transmitter before the transaction
        double oldBalanceBeneficiary = beneficiary.balance; // variable that stores the balance of the beneficiary before the transaction

        // We test if it is possible to withdraw money, so if you have enough money. And we check if the deposited value is not negative.
        if (value < 0) {
            System.out.println("Vous ne pouvez pas transférer une valeur négative !");
        } else if (balance - value < 0) {
            System.out.println("Vous ne pouvez transférer autant d'argent car vous êtes pauvre !");
        } else {
            balance -= value; // We withdraw the money in the transmitter's account
            beneficiary.balance += value; // We deposit the money in the beneficiary's account
            System.out.println("Vous avez transféré : " + value + "euro.s !\n" +
                    "Vous avez transféré votre argent au compte suivant : " + beneficiary.getLogin());
            FileGestion fileGestion = new FileGestion(); // Object that will allow us to save the transaction
            String fileName = getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName); // Creation of a file of the form "[login].txt" if the file does not exist, otherwise nothing happens. For the transmitter's account
            fileGestion.writeToFileLineBreak(fileName, "Transfert : " + value + " | " + "Solde avant transfert : "
                    + oldBalance + " | " + "Solde après transfert : " + balance + " | " + "Bénéficiare : " + beneficiary.getLogin()); // We save the transaction in the previous file
            fileGestion.createFileWithVerif(beneficiary.getLogin() + ".txt"); // Creation of a file of the form "[login].txt" if the file does not exist, otherwise nothing happens. For the beneficiary's account
            fileGestion.writeToFileLineBreak(beneficiary.getLogin() + ".txt", "Reçu : " + value + " | " + "Solde avant réception : "
                    + oldBalanceBeneficiary + " | " + "Solde après réception : " + beneficiary.balance + " | " + "Transmetteur : " + this.login); // We save the transaction in the previous file
        }
    }

    public void historyOfTransaction() {
        FileGestion fileGestion = new FileGestion(); // Object that will allow us to save the transaction
        String fileName = getLogin() + ".txt";

        // We test if the user has already made transactions
        if (fileGestion.fileExist(fileName)) {
            fileGestion.readFromFile(fileName); // We read the user's transaction file
        } else {
            System.out.println("Attention, vous n'avez pas encore effectué de transaction sur ce compte !");
        }

    }






}



