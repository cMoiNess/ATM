import java.util.Scanner;

public class CLI {

    public CLI() {}

    public void powerCLI() {
        User user = new User("user", 1234, 0);
        boolean condition = true;
        Scanner scanner = new Scanner(System.in);
        while (condition) {
            System.out.println("Choisir une action : \n " +
                    "- 1 : Dépôser de l'argent \n" +
                    "- 2 : Retirer de l'argent \n" +
                    "- 3 : Transférer de l'argent \n" +
                    "- 4 : Afficher le solde de votre compte bancaire");
            int choix = scanner.nextInt();

            switch(choix) {
                case 1 :
                    System.out.println("Entrer la valeur à dépôser");
                    int valueDeposit = scanner.nextInt();
                    user.deposit(valueDeposit);

                case 2 :
                    System.out.println("Entrer la valeur à retirér");
                    int valueWithdrawal = scanner.nextInt();
                    user.withdrawal(valueWithdrawal);

                case 3 :
                    /*
                    System.out.println("Entrer la valeur à transférer");
                    int valueTransfer = scanner.nextInt();
                    System.out.println("Entrer le nom du compte à créditer");
                    String valueCredit = scanner.next();
                    user.transfer(valueTransfer, valueCredit);
                     */

            }
        }
    }

}
