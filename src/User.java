import java.io.File;
import java.io.FileNotFoundException;
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
        double oldBalance = balance;
        if (value < 0) {
            System.out.println("Vous ne pouvez pas retirer une valeur négative !");
        } else if (balance - value < 0) {
            System.out.println("Vous ne pouvez retirer autant d'argent car vous êtes pauvre !");
        } else {
            balance -= value;
            System.out.println("Vous avez retiré : " + value + "euro.s !");
            FileGestion fileGestion = new FileGestion();
            String fileName = getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName);
            fileGestion.writeToFileLineBreak(fileName, "Retrait : " + value + " | " + "Solde avant retrait : " + oldBalance + " | " + "Solde après retrait : " + balance);
        }
    }

    public void deposit(double value) {
        double oldBalance = balance;
        // Checks if the deposited value is not negative
        if (value < 0) {
            System.out.println("Vous ne pouvez pas déposer une valeur négative");
        } else {
            balance += value;
            System.out.println("Vous avez dépôser : " + value + "euro.s !");
            FileGestion fileGestion = new FileGestion();
            String fileName = getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName);
            fileGestion.writeToFileLineBreak(fileName, "Dépôt : " + value + " | " + "Solde avant dépôt : " + oldBalance + " | " + "Solde après dépôt : " + balance);
        }
    }

    public void transfer(double value, User beneficiary) throws FileNotFoundException {
        double oldBalance = balance;
        double oldBalanceBeneficiary = beneficiary.balance;

        if (value < 0) {
            System.out.println("Vous ne pouvez pas transférer une valeur négative !");
        } else if (balance - value < 0) {
            System.out.println("Vous ne pouvez transférer autant d'argent car vous êtes pauvre !");
        } else {
            balance -= value;
            beneficiary.balance += value;
            System.out.println("Vous avez transféré : " + value + "euro.s !\n" +
                    "Vous avez transféré votre argent au compte suivant : " + beneficiary.getLogin());
            FileGestion fileGestion = new FileGestion();
            String fileName = getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName);
            fileGestion.writeToFileLineBreak(fileName, "Transfert : " + value + " | " + "Solde avant transfert : "
                    + oldBalance + " | " + "Solde après transfert : " + balance + " | " + "Bénéficiare : " + beneficiary.getLogin());
            fileGestion.createFileWithVerif(beneficiary.getLogin() + ".txt");
            fileGestion.writeToFileLineBreak(beneficiary.getLogin() + ".txt", "Reçu : " + value + " | " + "Solde avant réception : "
                    + oldBalanceBeneficiary + " | " + "Solde après réception : " + beneficiary.balance + " | " + "Transmetteur : " + this.login);
        }
    }

    public void historyOfTransaction() {
        // Penser à vérifier si le fichier existe et afficher s'il n'existe pas qu'il n'y pas encore eu de transaction
        FileGestion fileGestion = new FileGestion();
        String fileName = getLogin() + ".txt";
        if (fileGestion.fileExist(fileName)) {
            fileGestion.readFromFile(fileName);
        } else {
            System.out.println("Attention, vous n'avez pas encore effectué de transaction sur ce compte !");
        }

    }

    public String toString() {
        return super.login + " : " + super.pin + " : " + this.balance;
    }




}



