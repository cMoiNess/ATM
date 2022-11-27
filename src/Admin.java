public class Admin extends BankAccount{

    // ---- Constructors

    public Admin() {}

    public Admin(String login, int pin) {
        super(login, pin);
    }

    // ---- Setters

    public void setLogin(String login) {super.login = login;}

    public void setPin(int pin) {super.pin = pin;}

    // ---- Getters

    public String getLogin() {return super.login;}

    public int getPin() {return super.pin;}

    // ---- Methods

    // Method of withdrawing money to an account
    public void withdrawalOtherAccount(double value, User user) {
        Double oldBalance = user.balance;

        if (value < 0) { // Check for negative values
            System.out.println("Vous ne pouvez pas retirer une valeur négative !"); 
        } else if (user.balance - value < 0) { // Check for available money
            System.out.println("Vous ne pouvez retirer autant d'argent car vous êtes pauvre !"); 
        } else {
            user.balance -= value; // Withdrawal of requested value
            System.out.println("Vous avez retiré : " + value + "euro.s !");
            FileGestion fileGestion = new FileGestion(); // Object that will allow us to save the transaction
            String fileName = "res\\" + user.getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName); // Creation of a file of the form "[login].txt" if the file does not exist, otherwise nothing happens.
            fileGestion.writeToFileLineBreak(fileName, "Opération faite par l'admin : " + this.getLogin() + " | Retrait : " + value + " | " + "Solde avant retrait : "
                    + oldBalance + " | " + "Solde après retrait : " + user.getBalance()); // We save the transaction in the previous file
        }
    }

    // Method of withdrawing money to an account
    public void depositOtherAccount(double value, User user) {
        Double oldBalance = user.balance;

        // Checks if the deposited value is not negative
        if (value < 0) { // Check for negative values
            System.out.println("Vous ne pouvez pas déposer une valeur négative");
        } else {
            user.balance += value; 
            System.out.println("Vous avez déposé : " + value + "euro.s !");
            FileGestion fileGestion = new FileGestion(); // Object that will allow us to save the transaction
            String fileName = "res\\" + user.getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName); // Creation of a file of the form "[login].txt" if the file does not exist, otherwise nothing happens.
            fileGestion.writeToFileLineBreak(fileName, "Opération faite par l'admin : " + this.getLogin() + " | Dépôt : " + value + " | " + "Solde avant dépôt : " + oldBalance + " | " + "Solde après dépôt : " + user.getBalance()); // We save the transaction in the previous file
        }
    }

    // Method of transferring money from one account to another
    public void transferOtherAccount(double value, User transmitter, User beneficiary) {
        double oldBalanceTransmitter = transmitter.getBalance(); // variable that stores the balance of the transmitter before the transaction
        double oldBalanceBeneficiary = beneficiary.getBalance(); // variable that stores the balance of the beneficiary before the transaction
        if (value < 0) { // Check for negative values
            System.out.println("Vous ne pouvez pas transférer une valeur négative !");
        } else if (transmitter.balance - value < 0) { // Check for available money
            System.out.println("Vous ne pouvez transférer autant d'argent car vous êtes pauvre !");
        } else {
            transmitter.balance -= value; // Transfer of requested value
            beneficiary.balance += value; 
            System.out.println("Vous avez transféré : " + value + "euro.s !\n" +
                    "Vous avez transféré votre argent au compte suivant : " + beneficiary.getLogin());
            FileGestion fileGestion = new FileGestion(); // Object that will allow us to save the transaction
            String fileName = "res\\" + transmitter.getLogin() + ".txt";
            fileGestion.createFileWithVerif(fileName); // Creation of a file of the form "[login].txt" if the file does not exist, otherwise nothing happens. For the transmitter's account
            fileGestion.writeToFileLineBreak(fileName, "Opération faite par l'admin : " + this.getLogin() + " | Transfert : " + value + " | " + "Solde avant transfert : "
                    + oldBalanceTransmitter + " | " + "Solde après transfert : " + transmitter.getBalance() + " | " + "Bénéficiaire : " + beneficiary.getLogin()); // We save the transaction in the previous file
            fileGestion.createFileWithVerif("res\\" + beneficiary.getLogin() + ".txt"); // Creation of a file of the form "[login].txt" if the file does not exist, otherwise nothing happens. For the beneficiary's account
            fileGestion.writeToFileLineBreak("res\\" + beneficiary.getLogin() + ".txt", "Opération faite par un admin | Reçu : " + value + " | " + "Solde avant réception : "
                    + oldBalanceBeneficiary + " | " + "Solde après réception : " + beneficiary.getBalance() + " | " + "Transmetteur : " + transmitter.getLogin()); // We save the transaction in the previous file
        }
    }

    public String historyOfTransactionOtherAccount(User user) {
        FileGestion fileGestion = new FileGestion(); // Object that will allow us to save the transaction
        String fileName = "res\\" + user.getLogin() + ".txt";

        // We test if the user has already made transactions
        if (fileGestion.fileExist(fileName)) {
            return fileGestion.readFromFile(fileName); // We read the user's transaction file
        } else {
            return "Attention, vous n'avez pas encore effectué de transaction sur ce compte !";
        }

    }

}
