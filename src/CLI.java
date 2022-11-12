import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.*;
import java.lang.Class;
public class CLI implements Serializable {

    public CLI() {}

    public void powerCLI() throws FileNotFoundException {
        FileGestion fileGestion = new FileGestion();
        boolean conditionCreationCompte = true;
        boolean conditionUtilisationCompte = true;
        Scanner scanner = new Scanner(System.in);
        int nbIndex = 0;
        ArrayList<User> listUser = new ArrayList<User>();
        listUser = fileGestion.readUsersFromFile("user.txt");

        while (conditionCreationCompte) {
            System.out.println("Choisir une action : \n" +
                    "- 1 : Se connecter \n" +
                    "- 2 : Créer un compte bancaire");
            int choix = scanner.nextInt();

            switch(choix) {
                case 1 :
                    System.out.println("Entrer votre login :");
                    String valueLogin = scanner.next();
                    System.out.println("Entrer votre pin (nombre à 4 chiffres) :");
                    int valuePin = scanner.nextInt();
                    boolean accountOkOrNok = fileGestion.verifAccountLoginAndPassword("user.txt", valueLogin, valuePin);
                    if (accountOkOrNok) {
                        System.out.println("Bienvenue : " + valueLogin + " !");
                        nbIndex = fileGestion.findIndexAccount("user.txt", valueLogin);
                        conditionCreationCompte = false;
                        break;
                    } else {
                        System.out.println("Les identifiants saisies ne sont pas corrects !");
                        break;
                    }

                case 2:
                    System.out.println("Entrer votre nouveau login");
                    String valueNewLogin = scanner.next();

                    System.out.println("Entrer votre nouveau pin (nombre à 4 chiffres) :");
                    int valueNewPin = scanner.nextInt();

                    boolean accountExistOrNot = fileGestion.verifAccountExist("user.txt", valueNewLogin);
                    if (accountExistOrNot) {
                        System.out.println("Le compte existe déjà, merci de choisir un login différent ");
                    } else {
                        listUser.add(new User(valueNewLogin, valueNewPin));
                        fileGestion.writeToFileAnObject("user.txt", listUser.get(listUser.size() - 1));
                        nbIndex = fileGestion.findIndexAccount("user.txt", valueNewLogin);
                        System.out.println("Création du compte...");
                        System.out.println("Votre compte est ouvert !");
                        System.out.println("Bienvenue : " + valueNewLogin + " !");
                        conditionCreationCompte = false;
                        break;
                    }



                    /* Inscrire le nouvel utilisateur si le login n'est pas déjà existant + le connecte
                       si non, message d'erreur et repasse dans la boucle
                     */


                    break;
            }


        }

        while (conditionUtilisationCompte) {
            System.out.println("Choisir une action : \n" +
                    "- 1 : Dépôser de l'argent \n" +
                    "- 2 : Retirer de l'argent \n" +
                    "- 3 : Transférer de l'argent \n" +
                    "- 4 : Afficher le solde de votre compte bancaire \n" +
                    "- 5 : Afficher l'historique du compte" +
                    "- 6 : Se déconnecter \n" +
                    "- 7 : Sortir du système");
            int choix = scanner.nextInt();

            switch(choix) {
                case 1 :
                    System.out.println("Entrer la valeur à dépôser");
                    int valueDeposit = scanner.nextInt();
                    listUser.get(nbIndex).deposit(valueDeposit);
                    System.out.println("Vous avez dépôser : " + valueDeposit + "euro.s !");
                    break;

                case 2 :
                    System.out.println("Entrer la valeur à retirer");
                    int valueWithdrawal = scanner.nextInt();
                    listUser.get(nbIndex).withdrawal(valueWithdrawal);
                    System.out.println("Vous avez retirer : " + valueWithdrawal + "euro.s !");
                    break;

                case 3 :

                    System.out.println("Entrer la valeur à transférer");
                    Double valueTransfer = scanner.nextDouble();
                    System.out.println("Entrer le nom du compte à créditer");
                    String valueCredit = scanner.next();
                    boolean accountOkOrNok = fileGestion.verifAccountExist("user.txt", valueCredit);
                    if (accountOkOrNok) {
                        int nbIndexCredit = fileGestion.findIndexAccount("user.txt", valueCredit);
                        listUser.get(nbIndex).transfer(valueTransfer, listUser.get(nbIndexCredit));
                        break;
                    } else {
                        System.out.println("Le compte bénéficiare n'existe pas !");
                        break;
                    }

                case 4:
                    System.out.println("Voici le solde de votre compte : " + listUser.get(nbIndex).getBalance());
                    break;

                case 5 :
                    // historique du compte

                case 6:
                    System.out.println("Deconnexion !");
                    fileGestion.writeToFileObjects("user.txt", listUser);
                    powerCLI();
                    break;

                case 7:
                    System.out.println("Aurevoir !");
                    fileGestion.writeToFileObjects("user.txt", listUser);
                    System.exit(0);

            }
        }
    }

}
