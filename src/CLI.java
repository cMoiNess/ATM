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
        boolean conditionUtilisationCompteAdmin = true;
        Scanner scanner = new Scanner(System.in);
        int nbIndex = 0;
        ArrayList<User> listUser = new ArrayList<User>();
        ArrayList<Admin> listAdmin = new ArrayList<Admin>();
        listUser = fileGestion.readUsersFromFile("user.txt");
        listAdmin = fileGestion.readAdminsFromFile("admin.txt");

        while (conditionCreationCompte) { // First choice from CLI (either connect to already existing account or create account)
            System.out.println("Choisir une action : \n" +
                    "- 1 : Se connecter \n" +
                    "- 2 : Créer un compte bancaire");
            int choix = scanner.nextInt();

            switch(choix) { //1=Connect | 2=Create account
                case 1 :
                    System.out.println("Entrer votre login :"); //Wait for login input 
                    String valueLogin = scanner.next();
                    System.out.println("Entrer votre pin (nombre à 4 chiffres) :"); //Wait for password/pin
                    int valuePin = scanner.nextInt();
                    boolean accountOkOrNok = fileGestion.verifAccountLoginAndPassword("user.txt", valueLogin, valuePin); //Boolean to check if user and password association matches user database file
                    boolean accountAdminOkOrNok = fileGestion.verifAccountLoginAndPassword("admin.txt", valueLogin, valuePin); //Boolean to check if user and password association matches admin database file
                    if (accountOkOrNok) { 
                        System.out.println("Bienvenue : " + valueLogin + " !"); //Welcome banner with login
                        nbIndex = fileGestion.findIndexAccount("user.txt", valueLogin); //User account information stored in index
                        conditionCreationCompte = false; //reset of variable
                        conditionUtilisationCompteAdmin = false; //reset of variable
                        break;
                    } else if (accountAdminOkOrNok) {
                        System.out.println("Bienvenue : " + valueLogin + " !"); //Welcome banner with login
                        nbIndex = fileGestion.findIndexAccount("admin.txt", valueLogin); //Admin account information stored in index
                        conditionCreationCompte = false; //reset of variable
                        conditionUtilisationCompte = false; //reset of variable
                        break;
                    }

                    else {
                        System.out.println("Les identifiants saisies ne sont pas corrects !"); //error of login/password association
                        break;
                    }

                case 2:
                    System.out.println("Entrer votre nouveau login :"); //Wait for login input 
                    String valueNewLogin = scanner.next();

                    System.out.println("Entrer votre nouveau pin (nombre à 4 chiffres) :"); //Wait for password/pin
                    int valueNewPin = scanner.nextInt();

                    boolean accountExistOrNot = fileGestion.verifAccountExist("user.txt", valueNewLogin); //Boolean to check whether the user exists or not
                    boolean accountExistOrNotAdmin = fileGestion.verifAccountExist("admin.txt", valueNewLogin); //Boolean to check whether the admin exists or not
                    if (accountExistOrNot || accountExistOrNotAdmin) {
                        System.out.println("Le compte existe déjà, merci de choisir un login différent ");
                    } else {
                        listUser.add(new User(valueNewLogin, valueNewPin)); //Creation of new user
                        fileGestion.writeToFileAnObject("user.txt", listUser.get(listUser.size() - 1)); //Add this user to database
                        nbIndex = fileGestion.findIndexAccount("user.txt", valueNewLogin); //User account information stored in index
                        System.out.println("Création du compte...");
                        System.out.println("Votre compte est ouvert !");
                        System.out.println("Bienvenue : " + valueNewLogin + " !"); //Welcome banner with login
                        conditionCreationCompte = false; //reset of variable
                        break;
                    }



                    /* Inscrire le nouvel utilisateur si le login n'est pas déjà existant + le connecte
                       si non, message d'erreur et repasse dans la boucle
                     */


                    break;
            }


        }

        while (conditionUtilisationCompte) { // Second choice from CLI (basically all the actions a user can do (deposit, withdraw, transfer, show different values, historic and disconnect)
            System.out.println("Choisir une action : \n" +
                    "- 1 : Déposer de l'argent \n" +
                    "- 2 : Retirer de l'argent \n" +
                    "- 3 : Transférer de l'argent \n" +
                    "- 4 : Afficher le solde de votre compte bancaire \n" +
                    "- 5 : Afficher l'historique du compte \n" +
                    "- 6 : Se déconnecter \n" +
                    "- 7 : Sortir du système");
            int choix = scanner.nextInt();

            switch(choix) {
                case 1 :
                    System.out.println("Entrer la valeur à dépôser");
                    int valueDeposit = scanner.nextInt();
                    listUser.get(nbIndex).deposit(valueDeposit); //Deposit to his account
                    break;

                case 2 :
                    System.out.println("Entrer la valeur à retirer");
                    Double valueWithdrawal = scanner.nextDouble();
                    listUser.get(nbIndex).withdrawal(valueWithdrawal); //Withdraw from his account
                    break;

                case 3 :

                    System.out.println("Entrer la valeur à transférer"); //Wait for value to transfer
                    Double valueTransfer = scanner.nextDouble();
                    System.out.println("Entrer le nom du compte à créditer"); //Wait for account login to transfer
                    String valueCredit = scanner.next();
                    boolean accountOkOrNok = fileGestion.verifAccountExist("user.txt", valueCredit); //Boolean to check if user and password association matches user database file
                    if (accountOkOrNok) {
                        int nbIndexCredit = fileGestion.findIndexAccount("user.txt", valueCredit); //User account information stored in index
                        listUser.get(nbIndex).transfer(valueTransfer, listUser.get(nbIndexCredit));
                        break;
                    } else {
                        System.out.println("Le compte bénéficiaire n'existe pas !");
                        break;
                    }

                case 4:
                    System.out.println("Voici le solde de votre compte : " + listUser.get(nbIndex).getBalance()); //Check account balance
                    break;

                case 5 :
                    listUser.get(nbIndex).historyOfTransaction(); //Check history of transaction
                    break;

                case 6:
                    System.out.println("Déconnexion !");
                    fileGestion.writeToFileObjects("user.txt", listUser); //Update database
                    powerCLI();
                    break;

                case 7:
                    System.out.println("Au revoir !");
                    fileGestion.writeToFileObjects("user.txt", listUser); //Update database
                    System.exit(0);

            }
        }

        while (conditionUtilisationCompteAdmin) {
            System.out.println("Choisir une action : \n" +
                    "- 1 : Déposer de l'argent sur un compte bancaire \n" +
                    "- 2 : Retirer de l'argent sur un compte bancaire \n" +
                    "- 3 : Transférer de l'argent d'un compte à un autre \n" +
                    "- 4 : Afficher le solde d'un compte bancaire \n" +
                    "- 5 : Afficher l'historique d'un compte bancaire \n" +
                    "- 6 : Supprimer un compte bancaire \n" +
                    "- 7 : Créer un nouvel administrateur \n" +
                    "- 8 : Afficher la liste des utilisateurs \n" +
                    "- 9 : Se déconnecter \n" +
                    "- 10 : Sortir du système");
            int choix = scanner.nextInt();

            switch(choix) {
                case 1 :
                    System.out.println("Entrer la valeur à dépôser");
                    int valueDeposit = scanner.nextInt();
                    System.out.println("Entrer le nom du compte à créditer");
                    String valueCreditDeposit = scanner.next();
                    boolean accountOkOrNokDeposit = fileGestion.verifAccountExist("user.txt", valueCreditDeposit);
                    if (accountOkOrNokDeposit) {
                        int nbIndexCredit = fileGestion.findIndexAccount("user.txt", valueCreditDeposit);
                        listAdmin.get(nbIndex).depositOtherAccount(valueDeposit, listUser.get(nbIndexCredit));
                        break;
                    } else {
                        System.out.println("Le compte bénéficiare n'existe pas !");
                        break;
                    }

                case 2 :
                    System.out.println("Entrer la valeur à retirer");
                    Double valueWithdrawal = scanner.nextDouble();
                    System.out.println("Entrer le nom du compte sur lequel vous voulez retirer de l'argent");
                    String valueCreditWithdrawal = scanner.next();

                    boolean accountOkOrNokWithdrawal = fileGestion.verifAccountExist("user.txt", valueCreditWithdrawal);
                    if (accountOkOrNokWithdrawal) {
                        int nbIndexCredit = fileGestion.findIndexAccount("user.txt", valueCreditWithdrawal);
                        listAdmin.get(nbIndex).withdrawalOtherAccount(valueWithdrawal, listUser.get(nbIndexCredit));
                        break;
                    } else {
                        System.out.println("Le compte bénéficiare n'existe pas !");
                        break;
                    }

                case 3 :
                    // A faire
                    System.out.println("Entrer la valeur à transférer");
                    Double valueTransfer = scanner.nextDouble();

                    System.out.println("Entrer le nom du compte transmetteur");
                    String valueCreditTransmitter = scanner.next();
                    System.out.println("Entrer le nom du compte bénéficiaire");
                    String valueCreditBeneficiary = scanner.next();


                    boolean accountOkOrNokTransmitter = fileGestion.verifAccountExist("user.txt", valueCreditTransmitter);
                    boolean accountOkOrNokBeneficiary = fileGestion.verifAccountExist("user.txt", valueCreditBeneficiary);

                    if (accountOkOrNokTransmitter && accountOkOrNokBeneficiary) {
                        int nbIndexCreditTransmitter = fileGestion.findIndexAccount("user.txt", valueCreditTransmitter);
                        int nbIndexCreditBeneficiary = fileGestion.findIndexAccount("user.txt", valueCreditBeneficiary);

                        listAdmin.get(nbIndex).transferOtherAccount(valueTransfer, listUser.get(nbIndexCreditTransmitter), listUser.get(nbIndexCreditBeneficiary));
                        System.out.println("Transfert effectué !");
                        break;
                    } else {
                        System.out.println("Le compte bénéficiaire ou transmetteur n'existe pas !");
                        break;
                    }

                case 4:
                    System.out.println("Entrer le nom du compte pour lequel vous voulez voir le solde");
                    String valueCreditSolde = scanner.next();

                    boolean accountOkOrNokSolde = fileGestion.verifAccountExist("user.txt", valueCreditSolde);
                    if (accountOkOrNokSolde) {
                        int nbIndexCredit = fileGestion.findIndexAccount("user.txt", valueCreditSolde);
                        System.out.println("Voici le solde du compte souhaité : " + listUser.get(nbIndexCredit).getBalance());
                        break;
                    } else {
                        System.out.println("Le compte bénéficiaire n'existe pas !");
                        break;
                    }

                case 5 :
                    System.out.println("Entrer le nom du compte pour lequel vous voulez voir l'historique");
                    String valueCreditHistory = scanner.next();
                    boolean accountOkOrNokHistory = fileGestion.verifAccountExist("user.txt", valueCreditHistory);
                    if (accountOkOrNokHistory) {
                        int nbIndexCredit = fileGestion.findIndexAccount("user.txt", valueCreditHistory);
                        listAdmin.get(nbIndex).historyOfTransactionOtherAccount(listUser.get(nbIndexCredit));
                        break;
                    } else {
                        System.out.println("Le compte bénéficiaire n'existe pas !");
                        break;
                    }

                case 6:
                    System.out.println("Entrer le nom du compte que vous voulez supprimer");
                    String valueCreditDelete = scanner.next();

                    boolean accountOkOrNokDelete = fileGestion.verifAccountExist("user.txt", valueCreditDelete);
                    if (accountOkOrNokDelete) {
                        listUser = fileGestion.eraseToFileAnObject("user.txt", listUser, valueCreditDelete);
                        fileGestion.writeToFileObjects("user.txt", listUser);

                        System.out.println("Supression du compte");
                        break;
                    } else {
                        System.out.println("Le compte n'existe pas !");
                        break;
                    }


                case 7:
                    System.out.println("Entrer le login du nouvel administrateur");
                    String valueNewLogin = scanner.next();

                    System.out.println("Entrer le pin du nouvel administrateur (nombre à 4 chiffres) :");
                    int valueNewPin = scanner.nextInt();

                    boolean accountExistOrNot = fileGestion.verifAccountExist("user.txt", valueNewLogin);
                    boolean accountExistOrNotAdmin = fileGestion.verifAccountExist("admin.txt", valueNewLogin);
                    if (accountExistOrNot || accountExistOrNotAdmin) {
                        System.out.println("Le compte existe déjà, merci de choisir un login différent ");
                        break;
                    } else {
                        listAdmin.add(new Admin(valueNewLogin, valueNewPin));
                        fileGestion.writeToFileAnObjectAdmin("admin.txt", listAdmin.get(listAdmin.size() - 1));
                        System.out.println("Création du compte...");
                        System.out.println("Le nouveau compte admin est ouvert !");
                        break;
                    }

                case 8:
                    fileGestion.printUsersForAdmin("user.txt");
                    break;

                case 9:
                    System.out.println("Déconnexion !");
                    fileGestion.writeToFileObjects("user.txt", listUser);
                    fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin);
                    powerCLI();
                    break;

                case 10:
                    System.out.println("Au revoir !");
                    fileGestion.writeToFileObjects("user.txt", listUser);
                    fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin);
                    System.exit(0);

            }
        }
    }
}
