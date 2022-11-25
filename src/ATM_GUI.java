import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class ATM_GUI implements Serializable {


    public ATM_GUI(){}

    public static boolean isNumeric(String str) { // Method to make sure a string is a double
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public void powerGUI() throws FileNotFoundException {

        FileGestion fileGestion = new FileGestion();
        boolean exit = false;
        int nbIndex = 0;
        ArrayList<User> listUser = new ArrayList<User>();
        ArrayList<Admin> listAdmin = new ArrayList<Admin>();

        listUser = fileGestion.readUsersFromFile("user.txt");
        listAdmin = fileGestion.readAdminsFromFile("admin.txt");



        final String ACTION_LOGIN = "1";                //LOGIN FRAME
        final String ACTION_REGISTER = "2";             //REGISTER FRAME
        final String ACTION_SELECT = "3";               //SELECTION FRAME
        final String ACTION_DEPOSIT = "4";              //DEPOSIT FRAME
        final String ACTION_WITHDRAW = "5";             //WITHDRAW FRAME
        final String ACTION_TRANSFER = "6";             //TRANSFER FRAME
        final String ACTION_HISTORY = "7";         // GET ACCOUNT INFO FRAME

        final String ACTION_ADMIN_SELECT = "8";         // ADMIN SELECTION FRAME
        final String ACTION_ADMIN_DEPOSIT = "9";         // ADMIN DEPOSIT FRAME
        final String ACTION_ADMIN_WITHDRAW = "10";         // ADMIN WITHDRAW FRAME
        final String ACTION_ADMIN_TRANSFER = "11";         // ADMIN TRANSFER FRAME
        final String ACTION_ADMIN_BALANCE = "12";         // ADMIN BALANCE FRAME
        final String ACTION_ADMIN_HISTORY = "13";         // ADMIN HISTORY FRAME
        final String ACTION_ADMIN_DELETE = "14";         // USER DELETE FRAME
        final String ACTION_ADMIN_CREATE = "15";         // ADMIN CREATION FRAME
        final String ACTION_ADMIN_LIST = "16";         // USER LIST FRAME


        String action = "0";

        // Create the frames and panels
        LoginFrame loginFrame = new LoginFrame();
        RegisterFrame registerFrame = new RegisterFrame();
        SelectionFrame selectionFrame = new SelectionFrame();
        DepositFrame depositFrame = new DepositFrame();
        WithdrawFrame withdrawFrame = new WithdrawFrame();
        TransferFrame transferFrame = new TransferFrame();
        InfoFrame infoFrame = new InfoFrame();

        AdminSelectionFrame adminSelectionFrame = new AdminSelectionFrame();
        AdminDepositFrame adminDepositFrame = new AdminDepositFrame();
        AdminWithdrawFrame adminWithdrawFrame = new AdminWithdrawFrame();
        AdminTransferFrame adminTransferFrame = new AdminTransferFrame();
        AdminListFrame adminListFrame = new AdminListFrame();
        AdminHistoryFrame adminHistoryFrame = new AdminHistoryFrame();
        AdminBalanceFrame adminBalanceFrame = new AdminBalanceFrame();
        AdminDeleteFrame adminDeleteFrame = new AdminDeleteFrame();
        AdminCreateFrame adminCreateFrame = new AdminCreateFrame();

        action = ACTION_LOGIN;

        while (exit == false) {

            switch (action) {

                case ACTION_LOGIN: {

                    //Display the LOGIN frame
                    loginFrame.setVisible(true);
                    registerFrame.setVisible(false);
                    selectionFrame.setVisible(false);
                    depositFrame.setVisible(false);
                    withdrawFrame.setVisible(false);
                    transferFrame.setVisible(false);
                    infoFrame.setVisible(false);

                    adminSelectionFrame.setVisible(false);

                    // Login button is pressed
                    if (LoginPanel.getLogin()) {

                        if(isNumeric(loginFrame.getPassword())){

                            boolean userOkOrNok = fileGestion.verifAccountLoginAndPassword("user.txt", loginFrame.getName(), Integer.valueOf(loginFrame.getPassword())); //Boolean to check if user and password association matches user database file
                            boolean adminOkOrNok = fileGestion.verifAccountLoginAndPassword("admin.txt", loginFrame.getName(), Integer.valueOf(loginFrame.getPassword())); //Boolean to check if user and password association matches admin database file

                            if (userOkOrNok) {

                                nbIndex = fileGestion.findIndexAccount("user.txt", loginFrame.getName()); //User account information stored in index
                                selectionFrame.setID(loginFrame.getName()); // Method used to display the user's name on the selection window
                                selectionFrame.setClear(); // Clears old informations displayed on the selection window
                                loginFrame.setIncorrectClear(); // Clears any errors
                                action = ACTION_SELECT; // Jump to the selection case
                                LoginPanel.setLoginFalse(); // sets login request to false
                            } else if (adminOkOrNok) {
                                nbIndex = fileGestion.findIndexAccount("admin.txt", loginFrame.getName()); //Admin account information stored in index
                                adminSelectionFrame.setID(loginFrame.getName()); // Method used to display the admin's name on the admin selection window
                                loginFrame.setIncorrectClear();
                                action = ACTION_ADMIN_SELECT; // Jump to the admin selection case
                                LoginPanel.setLoginFalse(); // Drops the flag
                            } else {
                                loginFrame.setIncorrect(); // Display an error
                                LoginPanel.setLoginFalse(); // Drops the flag
                            }

                        } else {
                            loginFrame.setIncorrect(); // Display an error
                            selectionFrame.setClear(); // Clears window
                            LoginPanel.setLoginFalse(); // Drops the flag
                        }

                    }

                    // Register button is pressed
                    if(LoginPanel.getRegister()) {

                        action = ACTION_REGISTER; // Jump to the register case
                    }

                    // Exit button is pressed
                    if(LoginPanel.getExit()) {
                        exit = true;
                    }
                    break;

                }

                case ACTION_REGISTER: {

                    //Display the REGISTER frame
                    loginFrame.setVisible(false);
                    registerFrame.setVisible(true);
                    selectionFrame.setVisible(false);
                    depositFrame.setVisible(false);
                    withdrawFrame.setVisible(false);
                    transferFrame.setVisible(false);
                    infoFrame.setVisible(false);

                    // accept button is pressed
                    if (RegisterPanel.getAccept()) {

                        if(isNumeric(registerFrame.getPassword())){ // If the password is a 4 digit number

                            boolean userOkOrNok = fileGestion.verifAccountExist("user.txt", registerFrame.getName());  //Boolean to check if user association matches user database file
                            boolean adminOkOrNok = fileGestion.verifAccountExist("admin.txt", registerFrame.getName());//Boolean to check if user association matches admin database file
                            if (userOkOrNok || adminOkOrNok) { // If any account has the same name

                                registerFrame.setIncorrectUsername(); // Display an error
                                registerFrame.setAcceptFalse(); // Drops the flag
                                break;

                            }else if(!(userOkOrNok) && !(adminOkOrNok)){ // If no account match

                                listUser.add(new User(registerFrame.getName(), Integer.valueOf(registerFrame.getPassword()))); //Creation of a new user
                                fileGestion.writeToFileAnObject("user.txt", listUser.get(listUser.size() - 1)); //Add this user the database
                                nbIndex = fileGestion.findIndexAccount("user.txt", registerFrame.getName()); //User account information stored in index
                                registerFrame.setAcceptFalse(); // Drops the flag
                                registerFrame.setClear(); // Clears the window
                                registerFrame.setInfoClear(); // Clears any error
                                break;

                            }else {

                                registerFrame.setIncorrect(); // Display an error
                                registerFrame.setClear(); // Clears Window
                                registerFrame.setAcceptFalse(); // Drops the flag
                            }

                        }

                    }
                    //BACK BUTTON IS CLICKED
                    if (registerFrame.getBack()){
                        action = ACTION_LOGIN; // Jump to the login case
                        loginFrame.setClear(); // Clears Window
                        registerFrame.setClear(); // Clears Window
                        LoginPanel.setRegisterFalse(); // Drops the flag
                        registerFrame.setBackFalse(); // Drops the back flag

                    }

                    break;

                }

                case ACTION_SELECT: {

                    //Display the SELECT frame
                    loginFrame.setVisible(false);
                    selectionFrame.setVisible(true);
                    registerFrame.setVisible(false);
                    depositFrame.setVisible(false);
                    withdrawFrame.setVisible(false);
                    transferFrame.setVisible(false);
                    infoFrame.setVisible(false);

                    selectionFrame.setName(loginFrame.getName()); // Method to display the user's name

                    selectionFrame.getBalance(loginFrame.getName(),listUser.get(nbIndex).getBalance()); // Method to get the user's balance


                    //DEPOSIT BUTTON IS CLICKED
                    if (selectionFrame.getDeposit()){
                        selectionFrame.setDepositFalse(); // Drops the flag
                        action = ACTION_DEPOSIT;
                    }

                    //WITHDRAW BUTTON IS CLICKED
                    if (selectionFrame.getWithdraw()){
                        selectionFrame.setWithdrawFalse(); // Drops the flag
                        action = ACTION_WITHDRAW;
                    }

                    //TRANSFER BUTTON IS CLICKED
                    if (selectionFrame.getTransfer()){
                        selectionFrame.setTransferFalse(); // Drops the flag
                        action = ACTION_TRANSFER;
                    }

                    //HISTORY BUTTON IS CLICKED
                    if (selectionFrame.getHistory()){
                        selectionFrame.setHistoryFalse(); // Drops the flag
                        infoFrame.addToDisplay(listUser.get(nbIndex).historyOfTransaction()); // Method to diplay the user's history
                        action = ACTION_HISTORY;
                    }
                    //DISCONNECT BUTTON IS CLICKED
                    if (selectionFrame.getDisconnect()){
                        selectionFrame.setDisconnectFalse(); // Drops the flag
                        loginFrame.setClear(); // Clears the login window
                        fileGestion.writeToFileObjects("user.txt", listUser); // Saves user info
                        action = ACTION_LOGIN;
                    }

                    break;

                }

                case ACTION_DEPOSIT:  {

                    //Display the DEPOSIT frame
                    loginFrame.setVisible(false);
                    selectionFrame.setVisible(false);
                    registerFrame.setVisible(false);
                    depositFrame.setVisible(true);
                    withdrawFrame.setVisible(false);
                    transferFrame.setVisible(false);
                    infoFrame.setVisible(false);

                    // Deposit button is pressed
                    if(depositFrame.getDeposit()) {

                        String deposit_account_amount = depositFrame.getDepositAmount();
                        double amt = 0;

                        if (isNumeric(deposit_account_amount)){ // If the deposit is a double
                            amt = Double.parseDouble(deposit_account_amount); // Assign the value of the deposit to amt
                            if(amt <= 0){
                                depositFrame.setIncorrectAmount(); // Display an error
                                depositFrame.setDepositClear(); // Clears window
                                depositFrame.setDepositFalse(); // Drops the flag
                            } else {
                                depositFrame.setCorrectAmount();
                                depositFrame.setDepositClear();
                                listUser.get(nbIndex).deposit(amt); // Method to add money to the user's account
                                depositFrame.setDepositFalse(); // Drops the flag
                                fileGestion.writeToFileObjects("user.txt", listUser); // Saves user info
                                break;
                            }
                        } else if (!(isNumeric(deposit_account_amount))) { // If the value isn't numeric

                            depositFrame.setIncorrectAmount(); // Display an error
                            depositFrame.setDepositClear(); // Clears window
                            depositFrame.setDepositFalse(); // Drops the flag
                        }

                    }

                    // Back button is pressed
                    if (depositFrame.getBack()){
                        depositFrame.setDepositClear(); // Clears Window
                        depositFrame.setInfoClear(); // Clears any informations on screen (errors/success message)
                        depositFrame.setBackFalse(); // Drops the flag
                        action = ACTION_SELECT;

                    }
                    break;

                }

                case ACTION_WITHDRAW:  {

                    //Display the WITHDRAW frame
                    loginFrame.setVisible(false);
                    selectionFrame.setVisible(false);
                    registerFrame.setVisible(false);
                    depositFrame.setVisible(false);
                    withdrawFrame.setVisible(true);
                    transferFrame.setVisible(false);
                    infoFrame.setVisible(false);

                    // Withdraw button is pressed
                    if(withdrawFrame.getWithdraw()){

                        String withdraw_account_amount = withdrawFrame.getWithdrawAmount();
                        double amt = 0;

                        if (isNumeric(withdraw_account_amount)){
                            amt = Double.parseDouble(withdraw_account_amount);
                            if((amt <= 0) || (listUser.get(nbIndex).getBalance() - amt< 0)){ // If the user's trying to withdraw more than he has
                                withdrawFrame.setIncorrectAmount(); // Display an error
                                withdrawFrame.setWithdrawClear(); // Clears window
                                withdrawFrame.setWithdrawFalse(); // Drops the flag
                            } else {
                                withdrawFrame.setCorrectAmount();
                                withdrawFrame.setWithdrawClear();
                                listUser.get(nbIndex).withdrawal(amt); // Method to withdraw money
                                withdrawFrame.setWithdrawFalse(); // Drops the flag
                                fileGestion.writeToFileObjects("user.txt", listUser); // Saves user info
                                break;
                            }
                        } else if (!(isNumeric(withdraw_account_amount))) {

                            withdrawFrame.setIncorrectAmount(); // Display an error
                            withdrawFrame.setWithdrawClear(); // Clears Window
                            withdrawFrame.setWithdrawFalse(); // Drops the flag
                        }

                    }

                    //BACK BUTTON IS CLICKED
                    if (withdrawFrame.getBack()){
                        withdrawFrame.setWithdrawClear(); // Clears Window
                        withdrawFrame.setInfoClear(); // Clears any informations on screen (errors/success message)
                        withdrawFrame.setBackFalse(); // Drops the flag
                        action = ACTION_SELECT;

                    }

                    break;

                }

                case ACTION_HISTORY: {

                    //Display the HISTORY frame
                    loginFrame.setVisible(false);
                    selectionFrame.setVisible(false);
                    registerFrame.setVisible(false);
                    depositFrame.setVisible(false);
                    withdrawFrame.setVisible(false);
                    transferFrame.setVisible(false);
                    infoFrame.setVisible(true);

                    // back button is pressed
                    if(infoFrame.getBack()){
                        infoFrame.setBackFalse(); // Drops the flag
                        infoFrame.clearDisplay(); // Clears Window
                        action = ACTION_SELECT;

                    }
                    break;

                }

                case ACTION_TRANSFER: {

                    //Display the TRANSFER frame
                    loginFrame.setVisible(false);
                    selectionFrame.setVisible(false);
                    registerFrame.setVisible(false);
                    depositFrame.setVisible(false);
                    withdrawFrame.setVisible(false);
                    transferFrame.setVisible(true);
                    infoFrame.setVisible(false);

                    // Transfer button is pressed
                    if(transferFrame.getTransfer()){

                        String account = transferFrame.getTransferAccount();
                        String amount = transferFrame.getTransferAmount();
                        double amt = 0;

                        if ((account.equals("")) && (amount.equals(""))) {

                            transferFrame.setIncorrect(); // Display an error
                            transferFrame.setTransferFalse(); // Drops the flag
                            transferFrame.setTransferClear(); // Clears Window

                        } else if (amount.equals("")) {

                            transferFrame.setIncorrectAmount(); // Display an error
                            transferFrame.setTransferFalse(); // Drops the flag
                            transferFrame.setTransferClear(); // Clears Window

                        } else if(account.equals("")){

                            transferFrame.setIncorrectAccount(); // Display an error
                            transferFrame.setTransferFalse(); // Drops the flag
                            transferFrame.setTransferClear(); // Clears Window

                        } else {

                            boolean accountExist = fileGestion.verifAccountExist("user.txt", account); // Check if account exists in user.txt
                            if (isNumeric(amount) && accountExist) {

                                amt = Double.parseDouble(amount);

                                if ((amt <= 0) || (listUser.get(nbIndex).getBalance() - amt< 0)) {

                                    transferFrame.setIncorrectAmount(); // Display an error
                                    transferFrame.setTransferFalse(); // Drops the flag
                                    transferFrame.setTransferClear(); // Clears Window

                                } else {

                                    int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);
                                    listUser.get(nbIndex).transfer(amt, listUser.get(nbIndexCredit)); // Method to transfer money to users
                                    transferFrame.setCorrectAmount();
                                    transferFrame.setTransferFalse(); // Drops the flag
                                    transferFrame.setTransferClear(); // Clears Window
                                    fileGestion.writeToFileObjects("user.txt", listUser);
                                    break;

                                }

                            } else if (isNumeric(amount) && !accountExist) {
                                transferFrame.setIncorrectAccount(); // Display an error
                                transferFrame.setTransferFalse(); // Drops the flag
                                transferFrame.setTransferClear(); // Clears Window
                            } else if (!(isNumeric(amount)) && accountExist) {

                                transferFrame.setIncorrectAmount(); // Display an error
                                transferFrame.setTransferFalse(); // Drops the flag
                                transferFrame.setTransferClear(); // Clears Window

                            }

                        }

                    }

                    // Back button is pressed
                    if (transferFrame.getBack()){
                        transferFrame.setInfoClear(); // Clears any informations on screen (errors/success message)
                        transferFrame.setTransferClear(); // Clears Window
                        transferFrame.setBackFalse(); // Drops the flag
                        action = ACTION_SELECT;

                    }

                    break;

                }

                case ACTION_ADMIN_SELECT:{

                    //Display the ADMIN SELECTION frame
                    loginFrame.setVisible(false);
                    adminSelectionFrame.setVisible(true);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    adminSelectionFrame.setName(loginFrame.getName()); // Method to display admin's name

                    //ADMIN DEPOSIT BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminDeposit()){
                        adminSelectionFrame.setAdminDepositFalse(); // Drops the flag
                        action = ACTION_ADMIN_DEPOSIT;
                    }

                    //ADMIN WITHDRAW BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminWithdraw()){
                        adminSelectionFrame.setAdminWithdrawFalse(); // Drops the flag
                        action = ACTION_ADMIN_WITHDRAW;
                    }

                    //ADMIN TRANSFER BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminTransfer()){
                        adminSelectionFrame.setAdminTransferFalse(); // Drops the flag
                        action = ACTION_ADMIN_TRANSFER;
                    }

                    //ADMIN BALANCE BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminBalance()){
                        adminSelectionFrame.setAdminBalanceFalse(); // Drops the flag
                        action = ACTION_ADMIN_BALANCE;
                    }

                    //ADMIN HISTORY BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminHistory()){
                        adminSelectionFrame.setAdminHistoryFalse(); // Drops the flag
                        action = ACTION_ADMIN_HISTORY;
                        break;
                    }

                    //ADMIN DELETE BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminDelete()){
                        adminSelectionFrame.setAdminDeleteFalse(); // Drops the flag
                        action = ACTION_ADMIN_DELETE;
                    }

                    //ADMIN CREATE BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminCreate()){
                        adminSelectionFrame.setAdminCreateFalse(); // Drops the flag
                        action = ACTION_ADMIN_CREATE;
                    }

                    //ADMIN LIST BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminList()){
                        adminSelectionFrame.setAdminListFalse(); // Drops the flag
                        adminListFrame.addToDisplay(fileGestion.printUsersForAdmin("user.txt")); // Method to display every users
                        action = ACTION_ADMIN_LIST;
                    }

                    //DISCONNECT BUTTON IS CLICKED
                    if (adminSelectionFrame.getDisconnect()){
                        adminSelectionFrame.setDisconnectFalse(); // Drops the flag
                        loginFrame.setClear(); // Clears window
                        loginFrame.setIncorrectClear(); // Clears any error
                        fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin); // Saves admin info
                        fileGestion.writeToFileObjects("user.txt", listUser); // Saves user info
                        action = ACTION_LOGIN;
                    }

                    break;
                }

                case ACTION_ADMIN_DEPOSIT: {

                    //Display the ADMIN DEPOSIT frame
                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(true);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    //ADMIN DEPOSIT BUTTON IS CLICKED
                    if(adminDepositFrame.getAdminDeposit()){

                        String account = adminDepositFrame.getDepositAccount();
                        String amount = adminDepositFrame.getDepositAmount();
                        double amt = 0;

                        if ((account.equals("")) && (amount.equals(""))) {

                            adminDepositFrame.setIncorrect(); // Display an error
                            adminDepositFrame.setAdminDepositFalse(); // Drops the flag
                            adminDepositFrame.setDepositClear(); // Clears Window

                        } else if (amount.equals("")) {

                            adminDepositFrame.setIncorrectAmount(); // Display an error
                            adminDepositFrame.setAdminDepositFalse(); // Drops the flag
                            adminDepositFrame.setDepositClear(); // Clears Window

                        } else if(account.equals("")){

                            adminDepositFrame.setIncorrectAccount(); // Display an error
                            adminDepositFrame.setAdminDepositFalse(); // Drops the flag
                            adminDepositFrame.setDepositClear(); // Clears Window

                        } else {

                            boolean accountExist = fileGestion.verifAccountExist("user.txt", account);
                            if (isNumeric(amount) && accountExist) {

                                amt = Double.parseDouble(amount);

                                if (amt <= 0) {

                                    adminDepositFrame.setIncorrectAmount(); // Display an error
                                    adminDepositFrame.setAdminDepositFalse(); // Drops the flag
                                    adminDepositFrame.setDepositClear(); // Clears Window

                                } else {

                                    int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);
                                    listAdmin.get(nbIndex).depositOtherAccount(amt, listUser.get(nbIndexCredit)); // Method that allows admin to send money to users
                                    adminDepositFrame.setCorrectAmount();
                                    adminDepositFrame.setAdminDepositFalse(); // Drops the flag
                                    adminDepositFrame.setDepositClear(); // Clears Window
                                    fileGestion.writeToFileObjects("user.txt", listUser); // Saves user info
                                    fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin); // Saves admin info
                                    break;

                                }

                            } else if (isNumeric(amount) && !accountExist) {
                                adminDepositFrame.setIncorrectAccount(); // Display an error
                                adminDepositFrame.setAdminDepositFalse(); // Drops the flag
                                adminDepositFrame.setDepositClear(); // Clears Window
                            } else if (!(isNumeric(amount)) && accountExist) {

                                adminDepositFrame.setIncorrectAmount(); // Display an error
                                adminDepositFrame.setAdminDepositFalse(); // Drops the flag
                                adminDepositFrame.setDepositClear(); // Clears Window

                            }

                        }

                    }

                    //BACK BUTTON IS CLICKED
                    if (adminDepositFrame.getBack()){
                        adminDepositFrame.setInfoClear(); // Clears any informations on screen (errors/success message)
                        adminDepositFrame.setDepositClear(); // Clears Window
                        adminDepositFrame.setBackFalse(); // Drops the flag
                        action = ACTION_ADMIN_SELECT;

                    }

                    break;

                }

                case ACTION_ADMIN_WITHDRAW: {

                    //Display the ADMIN WITHDRAW frame
                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(true);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    //WITHDRAW BUTTON IS CLICKED
                    if(adminWithdrawFrame.getAdminWithdraw()){

                        String account = adminWithdrawFrame.getWithdrawAccount();
                        String amount = adminWithdrawFrame.getWithdrawAmount();
                        double amt = 0;

                        if ((account.equals("")) && (amount.equals(""))) {

                            adminWithdrawFrame.setIncorrect(); // Display an error
                            adminWithdrawFrame.setAdminWithdrawFalse(); // Drops the flag
                            adminWithdrawFrame.setWithdrawClear(); // Clears Window

                        } else if (amount.equals("")) {

                            adminWithdrawFrame.setIncorrectAmount(); // Display an error
                            adminWithdrawFrame.setAdminWithdrawFalse(); // Drops the flag
                            adminWithdrawFrame.setWithdrawClear(); // Clears Window

                        } else if(account.equals("")){

                            adminWithdrawFrame.setIncorrectAccount(); // Display an error
                            adminWithdrawFrame.setAdminWithdrawFalse(); // Drops the flag
                            adminWithdrawFrame.setWithdrawClear(); // Clears Window

                        } else {

                            boolean accountExist = fileGestion.verifAccountExist("user.txt", account);
                            if (isNumeric(amount) && accountExist) {

                                amt = Double.parseDouble(amount);
                                int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);

                                if ((amt <= 0) || listUser.get(nbIndexCredit).getBalance() - amt < 0) {

                                    adminWithdrawFrame.setIncorrectAmount(); // Display an error
                                    adminWithdrawFrame.setAdminWithdrawFalse(); // Drops the flag
                                    adminWithdrawFrame.setWithdrawClear(); // Clears Window

                                } else {


                                    listAdmin.get(nbIndex).withdrawalOtherAccount(amt, listUser.get(nbIndexCredit)); // Method to withdraw money from an user
                                    adminWithdrawFrame.setCorrectAmount();
                                    adminWithdrawFrame.setAdminWithdrawFalse(); // Drops the flag
                                    adminWithdrawFrame.setWithdrawClear(); // Clears Window
                                    fileGestion.writeToFileObjects("user.txt", listUser); // Saves user info
                                    fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin); // Saves admin info
                                    break;

                                }

                            } else if (isNumeric(amount) && !accountExist) {
                                adminWithdrawFrame.setIncorrectAccount(); // Display an error
                                adminWithdrawFrame.setAdminWithdrawFalse(); // Drops the flag
                                adminWithdrawFrame.setWithdrawClear(); // Clears Window
                            } else if (!(isNumeric(amount)) && accountExist) {

                                adminWithdrawFrame.setIncorrectAmount(); // Display an error
                                adminWithdrawFrame.setAdminWithdrawFalse(); // Drops the flag
                                adminWithdrawFrame.setWithdrawClear(); // Clears Window

                            }

                        }

                    }

                    //BACK BUTTON IS CLICKED
                    if (adminWithdrawFrame.getBack()){
                        adminWithdrawFrame.setInfoClear(); // Clears any informations on screen (errors/success message)
                        adminWithdrawFrame.setWithdrawClear(); // Clears Window
                        adminWithdrawFrame.setBackFalse(); // Drops the flag
                        action = ACTION_ADMIN_SELECT;

                    }

                    break;

                }

                case ACTION_ADMIN_TRANSFER: {

                    //Display the ADMIN TRANSFER frame
                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(true);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    //TRANSFER BUTTON IS CLICKED
                    if(adminTransferFrame.getAdminTransfer()){

                        String withdrawAccount = adminTransferFrame.getWithdrawAccount();
                        String depositAccount = adminTransferFrame.getDepositAccount();
                        String amount = adminTransferFrame.getTransferAmount();
                        double amt = 0;

                        if ((withdrawAccount.equals("")) || (depositAccount.equals("")) || (amount.equals(""))) {

                            adminTransferFrame.setIncorrect(); // Display an error
                            adminTransferFrame.setAdminTransferFalse(); // Drops the flag
                            adminTransferFrame.setAdminTransferClear(); // Clears Window

                        }else {

                            boolean withdrawAccountExist = fileGestion.verifAccountExist("user.txt", withdrawAccount);
                            boolean depositAccountExist = fileGestion.verifAccountExist("user.txt", depositAccount);

                            if (isNumeric(amount) && withdrawAccountExist && depositAccountExist) {

                                amt = Double.parseDouble(amount);

                                int nbIndexCreditTransmitter = fileGestion.findIndexAccount("user.txt", withdrawAccount);
                                int nbIndexCreditBeneficiary = fileGestion.findIndexAccount("user.txt", depositAccount);

                                if ((amt <= 0) || (listUser.get(nbIndexCreditTransmitter).getBalance() - amt< 0)) {

                                    adminTransferFrame.setIncorrectAmount(); // Display an error
                                    adminTransferFrame.setAdminTransferFalse(); // Drops the flag
                                    adminTransferFrame.setAdminTransferClear(); // Clears Window

                                } else {


                                    listAdmin.get(nbIndex).transferOtherAccount(amt, listUser.get(nbIndexCreditTransmitter), listUser.get(nbIndexCreditBeneficiary)); // Method to transfer money between users

                                    adminTransferFrame.setCorrectAmount();
                                    adminTransferFrame.setAdminTransferFalse(); // Drops the flag
                                    adminTransferFrame.setAdminTransferClear(); // Clears Window
                                    fileGestion.writeToFileObjects("user.txt", listUser); // Saves user info
                                    fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin); // Saves admin info
                                    break;

                                }

                            } else if (isNumeric(amount) && !withdrawAccountExist && depositAccountExist) {
                                adminTransferFrame.setIncorrectWithdrawAccount(); // Display an error
                                adminTransferFrame.setAdminTransferFalse(); // Drops the flag
                                adminTransferFrame.setAdminTransferClear(); // Clears Window
                            } else if (isNumeric(amount) && withdrawAccountExist && !depositAccountExist) {
                                adminTransferFrame.setIncorrectDepositAccount(); // Display an error
                                adminTransferFrame.setAdminTransferFalse(); // Drops the flag
                                adminTransferFrame.setAdminTransferClear(); // Clears Window
                            } else if (!(isNumeric(amount))) {

                                adminTransferFrame.setIncorrectAmount(); // Display an error
                                adminTransferFrame.setAdminTransferFalse(); // Drops the flag
                                adminTransferFrame.setAdminTransferClear(); // Clears Window

                            }

                        }

                    }

                    //BACK BUTTON IS CLICKED
                    if (adminTransferFrame.getBack()){
                        adminTransferFrame.setInfoClear(); // Clears any informations on screen (errors/success message)
                        adminTransferFrame.setAdminTransferClear(); // Clears Window
                        adminTransferFrame.setBackFalse(); // Drops the flag
                        action = ACTION_ADMIN_SELECT;

                    }

                    break;

                }

                case ACTION_ADMIN_BALANCE: {

                    //Display the ADMIN BALANCE frame
                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(true);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    //SHOW BUTTON IS CLICKED
                    if(adminBalanceFrame.getShow()){

                        String account = adminBalanceFrame.getUsername();
                        boolean withdrawAccountExist = fileGestion.verifAccountExist("user.txt", account);
                        if(withdrawAccountExist){

                            int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);
                            adminBalanceFrame.addToDisplay("Balance : "+listUser.get(nbIndexCredit).getBalance()); // Method to display the user's balance
                            adminBalanceFrame.setShowFalse(); // Drops the flag
                            adminBalanceFrame.clearDisplay(); // Clears Window
                            break;

                        }else {

                            adminBalanceFrame.setInfo(); // Display an error
                            adminBalanceFrame.setShowFalse(); // Drops the flag
                            break;

                        }

                    }

                    //BACK BUTTON IS CLICKED
                    if(adminBalanceFrame.getBack()){

                        adminBalanceFrame.setBackFalse(); // Drops the flag
                        adminBalanceFrame.clearDisplay(); // Clears Window
                        adminBalanceFrame.addToDisplay(""); // Clears Window
                        action = ACTION_ADMIN_SELECT;

                    }
                    break;

                }

                case ACTION_ADMIN_HISTORY: {

                    //Display the ADMIN HISTORY frame
                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(true);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    //SHOW BUTTON IS CLICKED
                    if(adminHistoryFrame.getShow()){

                        String account = adminHistoryFrame.getUsername();
                        boolean withdrawAccountExist = fileGestion.verifAccountExist("user.txt", account);
                        if(withdrawAccountExist){

                            int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);
                            adminHistoryFrame.addToDisplay(listAdmin.get(nbIndex).historyOfTransactionOtherAccount(listUser.get(nbIndexCredit))); // Method to display user's history
                            adminHistoryFrame.setShowFalse(); // Drops the flag
                            adminHistoryFrame.clearDisplay(); // Clears Window
                            break;

                        }else {

                            adminHistoryFrame.setInfo(); // Display an error
                            adminHistoryFrame.setShowFalse(); // Drops the flag
                            break;

                        }

                    }

                    //BACK BUTTON IS CLICKED
                    if(adminHistoryFrame.getBack()){

                        adminHistoryFrame.setBackFalse(); // Drops the flag
                        adminHistoryFrame.clearDisplay(); // Clears Window
                        adminHistoryFrame.addToDisplay(""); // Clears Window
                        action = ACTION_ADMIN_SELECT;

                    }
                    break;

                }

                case ACTION_ADMIN_CREATE: {

                    //Display the ADMIN CREATE frame
                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(true);

                    //ACCEPT BUTTON IS CLICKED
                    if (adminCreateFrame.getAccept()) {

                        if(isNumeric(adminCreateFrame.getPassword())){

                            String account = adminCreateFrame.getName();
                            boolean userOkOrNok = fileGestion.verifAccountExist("user.txt", account);
                            boolean adminOkOrNok = fileGestion.verifAccountExist("admin.txt", account);
                            if ((userOkOrNok) || (adminOkOrNok)) {

                                adminCreateFrame.setIncorrectUsername(); // Display an error
                                adminCreateFrame.setAcceptFalse(); // Drops the flag
                                break;

                            }else if(!(userOkOrNok) && !(adminOkOrNok)){

                                listAdmin.add(new Admin(adminCreateFrame.getName(), Integer.valueOf(adminCreateFrame.getPassword()))); // Method to add an admin
                                fileGestion.writeToFileAnObjectAdmin("admin.txt", listAdmin.get(listAdmin.size() - 1)); // Add this admin to the database
                                adminCreateFrame.setSuccess();
                                adminCreateFrame.setAcceptFalse(); // Drops the flag
                                adminCreateFrame.setClear(); // Clears Window
                                break;

                            }else {

                                adminCreateFrame.setIncorrect(); // Display an error
                                adminCreateFrame.setClear(); // Clears Window
                                adminCreateFrame.setAcceptFalse(); // Drops the flag
                            }

                        }

                    }

                    //BACK BUTTON IS CLICKED
                    if (adminCreateFrame.getBack()){
                        action = ACTION_ADMIN_SELECT;
                        adminCreateFrame.setClear(); // Clears Window
                        adminCreateFrame.setInfoClear(); // Clears any informations on screen (errors/success message)
                        adminCreateFrame.setBackFalse(); // Drops the flag

                    }

                    break;

                }

                case ACTION_ADMIN_DELETE: {

                    //Display the ADMIN DELETE frame
                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(true);
                    adminCreateFrame.setVisible(false);

                    //DELETE BUTTON IS CLICKED
                    if(adminDeleteFrame.getDelete()){
                        String account = adminDeleteFrame.getUsername();
                        boolean accountOkOrNokDelete = fileGestion.verifAccountExist("user.txt", account);

                        if(accountOkOrNokDelete){

                            listUser = fileGestion.eraseToFileAnObject("user.txt", listUser, account); // Method to delete users
                            fileGestion.writeToFileObjects("user.txt", listUser); // Saves user info
                            adminDeleteFrame.setDeleteFalse(); // Drops the flag
                            adminDeleteFrame.setSuccess();

                        } else {

                            adminDeleteFrame.setInfo(); // Display an error
                            adminDeleteFrame.setDeleteFalse(); // Drops the flag

                        }
                    }

                    //BACK BUTTON IS CLICKED
                    if(adminDeleteFrame.getBack()){
                        adminDeleteFrame.setBackFalse(); // Drops the flag
                        adminDeleteFrame.clearDisplay(); // Clears Window
                        action = ACTION_ADMIN_SELECT;

                    }
                    break;

                }

                case ACTION_ADMIN_LIST: {

                    //Display the ADMIN LIST frame
                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(true);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    //BACK BUTTON IS CLICKED
                    if(adminListFrame.getBack()){
                        adminListFrame.setBackFalse(); // Drops the flag
                        adminListFrame.clearDisplay(); // Clears Window
                        action = ACTION_ADMIN_SELECT;

                    }
                    break;

                }

            }

        }
        fileGestion.writeToFileObjects("user.txt", listUser);
        fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin);
        System.exit(0);

    }

}



