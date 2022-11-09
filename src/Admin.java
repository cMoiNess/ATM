public class Admin extends BankAccount{

    public Admin() {}

    public Admin(String login, int pin) {
        super(login, pin);
    }


    public void withdrawalOtherAccount(double value, User user) {
        if (value < 0) {
            System.out.println("Vous ne pouvez pas retirer une valeur négative !");
        } else if (user.balance - value < 0) {
            System.out.println("Vous ne pouvez retirer autant d'argent car vous êtes pauvre !");
        } else {
            user.balance -= value;
        }
    }

    public void depositOtherAccount(double value, User user) {
        // Checks if the deposited value is not negative
        if (value < 0) {
            System.out.println("Vous ne pouvez pas déposer une valeur négative");
        } else {
            user.balance += value;
        }
    }

    public void transferOtherAccount(double value, User transmitter, User beneficiary) {
        if (value < 0) {
            System.out.println("Vous ne pouvez pas transférer une valeur négative !");
        } else if (transmitter.balance - value < 0) {
            System.out.println("Vous ne pouvez transférer autant d'argent car vous êtes pauvre !");
        } else {
            transmitter.balance -= value;
            beneficiary.balance += value;
        }
    }

}
