public class User extends BankAccount {

    double balance;

    public User() {}

    public User(String login, int pin, double balance) {
        super(login, pin);
        this.balance = balance;
    }

    // ---- Setter

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // ---- Getter

    public double getBalance() {
        return balance;
    }


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
        if (value < 0) {
            System.out.println("Vous ne pouvez pas transférer une valeur négative !");
        } else if (balance - value < 0) {
            System.out.println("Vous ne pouvez transférer autant d'argent car vous êtes pauvre !");
        } else {
            balance -= value;
            beneficiary.balance += value;
        }
    }


    // Méthode à voir plus tard, compléter les méthodes précédentes également
    public String historyOfTransaction() {return "toto";}
}



