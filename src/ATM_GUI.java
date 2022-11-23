import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class ATM_GUI implements Serializable {


    public ATM_GUI(){}

    public static boolean isNumeric(String str) {
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

                    if (LoginPanel.getLogin()) {

                        if(isNumeric(loginFrame.getPassword())){

                            boolean userOkOrNok = fileGestion.verifAccountLoginAndPassword("user.txt", loginFrame.getName(), Integer.valueOf(loginFrame.getPassword())); //Boolean to check if user and password association matches user database file
                            boolean adminOkOrNok = fileGestion.verifAccountLoginAndPassword("admin.txt", loginFrame.getName(), Integer.valueOf(loginFrame.getPassword())); //Boolean to check if user and password association matches admin database file

                            if (userOkOrNok) {

                                nbIndex = fileGestion.findIndexAccount("user.txt", loginFrame.getName());
                                selectionFrame.setID(loginFrame.getName());
                                selectionFrame.setClear();
                                loginFrame.setIncorrectClear();
                                action = ACTION_SELECT;
                                LoginPanel.setLoginFalse();
                            } else if (adminOkOrNok) {
                                nbIndex = fileGestion.findIndexAccount("admin.txt", loginFrame.getName()); //Admin account information stored in index
                                adminSelectionFrame.setID(loginFrame.getName());
                                loginFrame.setIncorrectClear();
                                action = ACTION_ADMIN_SELECT;
                                LoginPanel.setLoginFalse();
                            } else {
                                loginFrame.setIncorrect();
                                LoginPanel.setLoginFalse();
                            }

                        } else {
                            loginFrame.setIncorrect();
                            selectionFrame.setClear();
                            LoginPanel.setLoginFalse();
                        }

                    }

                    if(LoginPanel.getRegister()) {

                        action = ACTION_REGISTER;
                    }

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

                    if (RegisterPanel.getAccept()) {

                        if(isNumeric(registerFrame.getPassword())){

                            boolean userOkOrNok = fileGestion.verifAccountExist("user.txt", registerFrame.getName());  //Boolean to check if user association matches user database file
                            boolean adminOkOrNok = fileGestion.verifAccountExist("admin.txt", registerFrame.getName());//Boolean to check if user association matches admin database file
                            if (userOkOrNok || adminOkOrNok) {

                                registerFrame.setIncorrectUsername();
                                registerFrame.setAcceptFalse();
                                break;

                            }else if(!(userOkOrNok) && !(adminOkOrNok)){

                                listUser.add(new User(registerFrame.getName(), Integer.valueOf(registerFrame.getPassword())));
                                fileGestion.writeToFileAnObject("user.txt", listUser.get(listUser.size() - 1));
                                nbIndex = fileGestion.findIndexAccount("user.txt", registerFrame.getName());
                                LoginPanel.setRegisterFalse();
                                registerFrame.setAcceptFalse();
                                loginFrame.setClear();
                                action = ACTION_LOGIN;
                                registerFrame.setClear();
                                registerFrame.setInfoClear();
                                break;

                            }else {

                                registerFrame.setIncorrect();
                                registerFrame.setClear();
                                registerFrame.setAcceptFalse();
                            }

                        }

                    }
                    //BACK BUTTON IS CLICKED
                    if (registerFrame.getBack()){
                        action = ACTION_LOGIN;
                        loginFrame.setClear();
                        registerFrame.setClear();
                        LoginPanel.setRegisterFalse();
                        registerFrame.setBackFalse();

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

                    selectionFrame.setName(loginFrame.getName());

                    selectionFrame.getBalance(loginFrame.getName(),listUser.get(nbIndex).getBalance());


                    //DEPOSIT BUTTON IS CLICKED
                    if (selectionFrame.getDeposit()){
                        selectionFrame.setDepositFalse();
                        action = ACTION_DEPOSIT;
                    }

                    //WITHDRAW BUTTON IS CLICKED
                    if (selectionFrame.getWithdraw()){
                        selectionFrame.setWithdrawFalse();
                        action = ACTION_WITHDRAW;
                    }

                    //TRANSFER BUTTON IS CLICKED
                    if (selectionFrame.getTransfer()){
                        selectionFrame.setTransferFalse();
                        action = ACTION_TRANSFER;
                    }

                    //HISTORY BUTTON IS CLICKED
                    if (selectionFrame.getHistory()){
                        selectionFrame.setHistoryFalse();
                        infoFrame.addToDisplay(listUser.get(nbIndex).historyOfTransaction());
                        action = ACTION_HISTORY;
                    }
                    //DISCONNECT BUTTON IS CLICKED
                    if (selectionFrame.getDisconnect()){
                        selectionFrame.setDisconnectFalse();
                        loginFrame.setClear();
                        fileGestion.writeToFileObjects("user.txt", listUser);
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



                    if(depositFrame.getDeposit()) {

                        String deposit_account_amount = depositFrame.getDepositAmount();
                        double amt = 0;

                        if (isNumeric(deposit_account_amount)){
                            amt = Double.parseDouble(deposit_account_amount);
                            if(amt <= 0){
                                depositFrame.setIncorrectAmount();
                                depositFrame.setDepositClear();
                                depositFrame.setDepositFalse();
                            } else {
                                depositFrame.setCorrectAmount();
                                depositFrame.setDepositClear();
                                listUser.get(nbIndex).deposit(amt);
                                depositFrame.setDepositFalse();
                                fileGestion.writeToFileObjects("user.txt", listUser);
                                break;
                            }
                        } else if (!(isNumeric(deposit_account_amount))) {

                            depositFrame.setIncorrectAmount();
                            depositFrame.setDepositClear();
                            depositFrame.setDepositFalse();
                        }

                    }

                    if (depositFrame.getBack()){
                        depositFrame.setDepositClear();
                        depositFrame.setInfoClear();
                        depositFrame.setBackFalse();
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



                    if(withdrawFrame.getWithdraw()){

                        String withdraw_account_amount = withdrawFrame.getWithdrawAmount();
                        double amt = 0;

                        if (isNumeric(withdraw_account_amount)){
                            amt = Double.parseDouble(withdraw_account_amount);
                            if((amt <= 0) || (listUser.get(nbIndex).getBalance() - amt< 0)){
                                withdrawFrame.setIncorrectAmount();
                                withdrawFrame.setWithdrawClear();
                                withdrawFrame.setWithdrawFalse();
                            } else {
                                withdrawFrame.setCorrectAmount();
                                withdrawFrame.setWithdrawClear();
                                listUser.get(nbIndex).withdrawal(amt);
                                withdrawFrame.setWithdrawFalse();
                                fileGestion.writeToFileObjects("user.txt", listUser);
                                break;
                            }
                        } else if (!(isNumeric(withdraw_account_amount))) {

                            withdrawFrame.setIncorrectAmount();
                            withdrawFrame.setWithdrawClear();
                            withdrawFrame.setWithdrawFalse();
                        }

                    }

                    //BACK BUTTON IS CLICKED
                    if (withdrawFrame.getBack()){
                        withdrawFrame.setWithdrawClear();
                        withdrawFrame.setInfoClear();
                        withdrawFrame.setBackFalse();
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

                    if(infoFrame.getBack()){
                        infoFrame.setBackFalse();
                        infoFrame.clearDisplay();
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

                    if(transferFrame.getTransfer()){

                        String account = transferFrame.getTransferAccount();
                        String amount = transferFrame.getTransferAmount();
                        double amt = 0;

                        if ((account.equals("")) && (amount.equals(""))) {

                            transferFrame.setIncorrect();
                            transferFrame.setTransferFalse();
                            transferFrame.setTransferClear();

                        } else if (amount.equals("")) {

                            transferFrame.setIncorrectAmount();
                            transferFrame.setTransferFalse();
                            transferFrame.setTransferClear();

                        } else if(account.equals("")){

                            transferFrame.setIncorrectAccount();
                            transferFrame.setTransferFalse();
                            transferFrame.setTransferClear();

                        } else {

                            boolean accountExist = fileGestion.verifAccountExist("user.txt", account);
                            if (isNumeric(amount) && accountExist) {

                                amt = Double.parseDouble(amount);

                                if ((amt <= 0) || (listUser.get(nbIndex).getBalance() - amt< 0)) {

                                    transferFrame.setIncorrectAmount();
                                    transferFrame.setTransferFalse();
                                    transferFrame.setTransferClear();

                                } else {

                                    int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);
                                    listUser.get(nbIndex).transfer(amt, listUser.get(nbIndexCredit));
                                    transferFrame.setCorrectAmount();
                                    transferFrame.setTransferFalse();
                                    transferFrame.setTransferClear();
                                    fileGestion.writeToFileObjects("user.txt", listUser);
                                    break;

                                }

                            } else if (isNumeric(amount) && !accountExist) {
                                transferFrame.setIncorrectAccount();
                                transferFrame.setTransferFalse();
                                transferFrame.setTransferClear();
                            } else if (!(isNumeric(amount)) && accountExist) {

                                transferFrame.setIncorrectAmount();
                                transferFrame.setTransferFalse();
                                transferFrame.setTransferClear();

                            }

                        }

                    }

                    if (transferFrame.getBack()){
                        transferFrame.setInfoClear();
                        transferFrame.setInfoClear();
                        transferFrame.setBackFalse();
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

                    adminSelectionFrame.setName(loginFrame.getName());

                    //ADMIN DEPOSIT BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminDeposit()){
                        adminSelectionFrame.setAdminDepositFalse();
                        action = ACTION_ADMIN_DEPOSIT;
                    }

                    //ADMIN WITHDRAW BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminWithdraw()){
                        adminSelectionFrame.setAdminWithdrawFalse();
                        action = ACTION_ADMIN_WITHDRAW;
                    }

                    //ADMIN TRANSFER BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminTransfer()){
                        adminSelectionFrame.setAdminTransferFalse();
                        action = ACTION_ADMIN_TRANSFER;
                    }

                    //ADMIN BALANCE BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminBalance()){
                        adminSelectionFrame.setAdminBalanceFalse();
                        action = ACTION_ADMIN_BALANCE;
                    }

                    //ADMIN HISTORY BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminHistory()){
                        adminSelectionFrame.setAdminHistoryFalse();
                        action = ACTION_ADMIN_HISTORY;
                        break;
                    }

                    //ADMIN DELETE BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminDelete()){
                        adminSelectionFrame.setAdminDeleteFalse();
                        action = ACTION_ADMIN_DELETE;
                    }

                    //ADMIN CREATE BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminCreate()){
                        adminSelectionFrame.setAdminCreateFalse();
                        action = ACTION_ADMIN_CREATE;
                    }

                    //ADMIN LIST BUTTON IS CLICKED
                    if (adminSelectionFrame.getAdminList()){
                        adminSelectionFrame.setAdminListFalse();
                        adminListFrame.addToDisplay(fileGestion.printUsersForAdmin("user.txt"));
                        action = ACTION_ADMIN_LIST;
                    }

                    //DISCONNECT BUTTON IS CLICKED
                    if (adminSelectionFrame.getDisconnect()){
                        adminSelectionFrame.setDisconnectFalse();
                        loginFrame.setClear();
                        loginFrame.setIncorrectClear();
                        fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin);
                        action = ACTION_LOGIN;
                    }

                    break;
                }

                case ACTION_ADMIN_DEPOSIT: {

                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(true);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    if(adminDepositFrame.getAdminDeposit()){

                        String account = adminDepositFrame.getDepositAccount();
                        String amount = adminDepositFrame.getDepositAmount();
                        double amt = 0;

                        if ((account.equals("")) && (amount.equals(""))) {

                            adminDepositFrame.setIncorrect();
                            adminDepositFrame.setAdminDepositFalse();
                            adminDepositFrame.setDepositClear();

                        } else if (amount.equals("")) {

                            adminDepositFrame.setIncorrectAmount();
                            adminDepositFrame.setAdminDepositFalse();
                            adminDepositFrame.setDepositClear();

                        } else if(account.equals("")){

                            adminDepositFrame.setIncorrectAccount();
                            adminDepositFrame.setAdminDepositFalse();
                            adminDepositFrame.setDepositClear();

                        } else {

                            boolean accountExist = fileGestion.verifAccountExist("user.txt", account);
                            if (isNumeric(amount) && accountExist) {

                                amt = Double.parseDouble(amount);

                                if (amt <= 0) {

                                    adminDepositFrame.setIncorrectAmount();
                                    adminDepositFrame.setAdminDepositFalse();
                                    adminDepositFrame.setDepositClear();

                                } else {

                                    int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);
                                    listAdmin.get(nbIndex).depositOtherAccount(amt, listUser.get(nbIndexCredit));
                                    adminDepositFrame.setCorrectAmount();
                                    adminDepositFrame.setAdminDepositFalse();
                                    adminDepositFrame.setDepositClear();
                                    fileGestion.writeToFileObjects("user.txt", listUser);
                                    fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin);
                                    break;

                                }

                            } else if (isNumeric(amount) && !accountExist) {
                                adminDepositFrame.setIncorrectAccount();
                                adminDepositFrame.setAdminDepositFalse();
                                adminDepositFrame.setDepositClear();
                            } else if (!(isNumeric(amount)) && accountExist) {

                                adminDepositFrame.setIncorrectAmount();
                                adminDepositFrame.setAdminDepositFalse();
                                adminDepositFrame.setDepositClear();

                            }

                        }

                    }

                    if (adminDepositFrame.getBack()){
                        adminDepositFrame.setInfoClear();
                        adminDepositFrame.setInfoClear();
                        adminDepositFrame.setBackFalse();
                        action = ACTION_ADMIN_SELECT;

                    }

                    break;

                }

                case ACTION_ADMIN_WITHDRAW: {

                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(true);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    if(adminWithdrawFrame.getAdminWithdraw()){

                        String account = adminWithdrawFrame.getWithdrawAccount();
                        String amount = adminWithdrawFrame.getWithdrawAmount();
                        double amt = 0;

                        if ((account.equals("")) && (amount.equals(""))) {

                            adminWithdrawFrame.setIncorrect();
                            adminWithdrawFrame.setAdminWithdrawFalse();
                            adminWithdrawFrame.setWithdrawClear();

                        } else if (amount.equals("")) {

                            adminWithdrawFrame.setIncorrectAmount();
                            adminWithdrawFrame.setAdminWithdrawFalse();
                            adminWithdrawFrame.setWithdrawClear();

                        } else if(account.equals("")){

                            adminWithdrawFrame.setIncorrectAccount();
                            adminWithdrawFrame.setAdminWithdrawFalse();
                            adminWithdrawFrame.setWithdrawClear();

                        } else {

                            boolean accountExist = fileGestion.verifAccountExist("user.txt", account);
                            if (isNumeric(amount) && accountExist) {

                                amt = Double.parseDouble(amount);
                                int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);

                                if ((amt <= 0) || listUser.get(nbIndexCredit).getBalance() - amt < 0) {

                                    adminWithdrawFrame.setIncorrectAmount();
                                    adminWithdrawFrame.setAdminWithdrawFalse();
                                    adminWithdrawFrame.setWithdrawClear();

                                } else {


                                    listAdmin.get(nbIndex).withdrawalOtherAccount(amt, listUser.get(nbIndexCredit));
                                    adminWithdrawFrame.setCorrectAmount();
                                    adminWithdrawFrame.setAdminWithdrawFalse();
                                    adminWithdrawFrame.setWithdrawClear();
                                    fileGestion.writeToFileObjects("user.txt", listUser);
                                    fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin);
                                    break;

                                }

                            } else if (isNumeric(amount) && !accountExist) {
                                adminWithdrawFrame.setIncorrectAccount();
                                adminWithdrawFrame.setAdminWithdrawFalse();
                                adminWithdrawFrame.setWithdrawClear();
                            } else if (!(isNumeric(amount)) && accountExist) {

                                adminWithdrawFrame.setIncorrectAmount();
                                adminWithdrawFrame.setAdminWithdrawFalse();
                                adminWithdrawFrame.setWithdrawClear();

                            }

                        }

                    }

                    if (adminWithdrawFrame.getBack()){
                        adminWithdrawFrame.setInfoClear();
                        adminWithdrawFrame.setInfoClear();
                        adminWithdrawFrame.setBackFalse();
                        action = ACTION_ADMIN_SELECT;

                    }

                    break;

                }

                case ACTION_ADMIN_TRANSFER: {

                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(true);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    if(adminTransferFrame.getAdminTransfer()){

                        String withdrawAccount = adminTransferFrame.getWithdrawAccount();
                        String depositAccount = adminTransferFrame.getDepositAccount();
                        String amount = adminTransferFrame.getTransferAmount();
                        double amt = 0;

                        if ((withdrawAccount.equals("")) || (depositAccount.equals("")) || (amount.equals(""))) {

                            adminTransferFrame.setIncorrect();
                            adminTransferFrame.setAdminTransferFalse();
                            adminTransferFrame.setAdminTransferClear();

                        }else {

                            boolean withdrawAccountExist = fileGestion.verifAccountExist("user.txt", withdrawAccount);
                            boolean depositAccountExist = fileGestion.verifAccountExist("user.txt", depositAccount);

                            if (isNumeric(amount) && withdrawAccountExist && depositAccountExist) {

                                amt = Double.parseDouble(amount);

                                int nbIndexCreditTransmitter = fileGestion.findIndexAccount("user.txt", withdrawAccount);
                                int nbIndexCreditBeneficiary = fileGestion.findIndexAccount("user.txt", depositAccount);

                                if ((amt <= 0) || (listUser.get(nbIndexCreditTransmitter).getBalance() - amt< 0)) {

                                    adminTransferFrame.setIncorrectAmount();
                                    adminTransferFrame.setAdminTransferFalse();
                                    adminTransferFrame.setAdminTransferClear();

                                } else {


                                    listAdmin.get(nbIndex).transferOtherAccount(amt, listUser.get(nbIndexCreditTransmitter), listUser.get(nbIndexCreditBeneficiary));

                                    adminTransferFrame.setCorrectAmount();
                                    adminTransferFrame.setAdminTransferFalse();
                                    adminTransferFrame.setAdminTransferClear();
                                    fileGestion.writeToFileObjects("user.txt", listUser);
                                    fileGestion.writeToFileObjectsAdmin("admin.txt", listAdmin);
                                    break;

                                }

                            } else if (isNumeric(amount) && !withdrawAccountExist && depositAccountExist) {
                                adminTransferFrame.setIncorrectWithdrawAccount();
                                adminTransferFrame.setAdminTransferFalse();
                                adminTransferFrame.setAdminTransferClear();
                            } else if (isNumeric(amount) && withdrawAccountExist && !depositAccountExist) {
                                adminTransferFrame.setIncorrectDepositAccount();
                                adminTransferFrame.setAdminTransferFalse();
                                adminTransferFrame.setAdminTransferClear();
                            } else if (!(isNumeric(amount))) {

                                adminTransferFrame.setIncorrectAmount();
                                adminTransferFrame.setAdminTransferFalse();
                                adminTransferFrame.setAdminTransferClear();

                            }

                        }

                    }

                    if (adminTransferFrame.getBack()){
                        adminTransferFrame.setInfoClear();
                        adminTransferFrame.setInfoClear();
                        adminTransferFrame.setBackFalse();
                        action = ACTION_ADMIN_SELECT;

                    }

                    break;

                }

                case ACTION_ADMIN_BALANCE: {

                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(true);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    if(adminBalanceFrame.getShow()){

                        String account = adminBalanceFrame.getUsername();
                        boolean withdrawAccountExist = fileGestion.verifAccountExist("user.txt", account);
                        if(withdrawAccountExist){

                            int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);
                            adminBalanceFrame.addToDisplay("Balance : "+listUser.get(nbIndexCredit).getBalance());
                            adminBalanceFrame.setShowFalse();
                            adminBalanceFrame.clearDisplay();
                            break;

                        }else {

                            adminBalanceFrame.setInfo();
                            adminBalanceFrame.setShowFalse();
                            break;

                        }

                    }
                    if(adminBalanceFrame.getBack()){

                        adminBalanceFrame.setBackFalse();
                        adminBalanceFrame.clearDisplay();
                        adminBalanceFrame.addToDisplay("");
                        action = ACTION_ADMIN_SELECT;

                    }
                    break;

                }

                case ACTION_ADMIN_HISTORY: {

                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(true);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    if(adminHistoryFrame.getShow()){

                        String account = adminHistoryFrame.getUsername();
                        boolean withdrawAccountExist = fileGestion.verifAccountExist("user.txt", account);
                        if(withdrawAccountExist){

                            int nbIndexCredit = fileGestion.findIndexAccount("user.txt", account);
                            adminHistoryFrame.addToDisplay(listAdmin.get(nbIndex).historyOfTransactionOtherAccount(listUser.get(nbIndexCredit)));
                            adminHistoryFrame.setShowFalse();
                            adminHistoryFrame.clearDisplay();
                            break;

                        }else {

                            adminHistoryFrame.setInfo();
                            adminHistoryFrame.setShowFalse();
                            break;

                        }

                    }
                    if(adminHistoryFrame.getBack()){

                        adminHistoryFrame.setBackFalse();
                        adminHistoryFrame.clearDisplay();
                        adminHistoryFrame.addToDisplay("");
                        action = ACTION_ADMIN_SELECT;

                    }
                    break;

                }

                case ACTION_ADMIN_CREATE: {

                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(true);

                    if (adminCreateFrame.getAccept()) {

                        if(isNumeric(adminCreateFrame.getPassword())){

                            String account = adminCreateFrame.getName();
                            boolean userOkOrNok = fileGestion.verifAccountExist("user.txt", account);
                            boolean adminOkOrNok = fileGestion.verifAccountExist("admin.txt", account);
                            if ((userOkOrNok) || (adminOkOrNok)) {

                                adminCreateFrame.setIncorrectUsername();
                                adminCreateFrame.setAcceptFalse();
                                break;

                            }else if(!(userOkOrNok) && !(adminOkOrNok)){

                                listAdmin.add(new Admin(adminCreateFrame.getName(), Integer.valueOf(adminCreateFrame.getPassword())));
                                fileGestion.writeToFileAnObjectAdmin("admin.txt", listAdmin.get(listAdmin.size() - 1));
                                adminCreateFrame.setSuccess();
                                adminCreateFrame.setAcceptFalse();
                                adminCreateFrame.setClear();
                                break;

                            }else {

                                adminCreateFrame.setIncorrect();
                                adminCreateFrame.setClear();
                                adminCreateFrame.setAcceptFalse();
                            }

                        }

                    }
                    //BACK BUTTON IS CLICKED
                    if (adminCreateFrame.getBack()){
                        action = ACTION_ADMIN_SELECT;
                        adminCreateFrame.setClear();
                        adminCreateFrame.setInfoClear();
                        adminCreateFrame.setBackFalse();

                    }

                    break;

                }

                case ACTION_ADMIN_DELETE: {

                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(false);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(true);
                    adminCreateFrame.setVisible(false);

                    if(adminDeleteFrame.getDelete()){
                        String account = adminDeleteFrame.getUsername();
                        boolean accountOkOrNokDelete = fileGestion.verifAccountExist("user.txt", account);

                        if(accountOkOrNokDelete){

                            listUser = fileGestion.eraseToFileAnObject("user.txt", listUser, account);
                            fileGestion.writeToFileObjects("user.txt", listUser);
                            adminDeleteFrame.setDeleteFalse();
                            adminDeleteFrame.setSuccess();

                        } else {

                            adminDeleteFrame.setInfo();
                            adminDeleteFrame.setDeleteFalse();

                        }
                    }
                    if(adminDeleteFrame.getBack()){
                        adminDeleteFrame.setBackFalse();
                        adminDeleteFrame.clearDisplay();
                        action = ACTION_ADMIN_SELECT;

                    }
                    break;

                }

                case ACTION_ADMIN_LIST: {

                    adminSelectionFrame.setVisible(false);
                    adminDepositFrame.setVisible(false);
                    adminWithdrawFrame.setVisible(false);
                    adminTransferFrame.setVisible(false);
                    adminListFrame.setVisible(true);
                    adminHistoryFrame.setVisible(false);
                    adminBalanceFrame.setVisible(false);
                    adminDeleteFrame.setVisible(false);
                    adminCreateFrame.setVisible(false);

                    if(adminListFrame.getBack()){
                        adminListFrame.setBackFalse();
                        adminListFrame.clearDisplay();
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



