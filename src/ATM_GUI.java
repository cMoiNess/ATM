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

        listUser = fileGestion.readUsersFromFile("user.txt");



        final String ACTION_LOGIN = "1";                //LOGIN FRAME
        final String ACTION_REGISTER = "2";             //REGISTER FRAME
        final String ACTION_SELECT = "3";               //SELECTION FRAME
        final String ACTION_DEPOSIT = "4";              //DEPOSIT FRAME
        final String ACTION_WITHDRAW = "5";             //WITHDRAW FRAME
        final String ACTION_TRANSFER = "6";             //TRANSFER FRAME
        final String ACTION_HISTORY = "7";         // GET ACCOUNT INFO

        String action = "0";

        String cust_ID = "";

        // Create the frames and panels
        LoginFrame loginFrame = new LoginFrame();
        RegisterFrame registerFrame = new RegisterFrame();
        SelectionFrame selectionFrame = new SelectionFrame();
        DepositFrame depositFrame = new DepositFrame();
        WithdrawFrame withdrawFrame = new WithdrawFrame();
        TransferFrame transferFrame = new TransferFrame();
        InfoFrame infoFrame = new InfoFrame();

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

                    if (LoginPanel.getLogin()) {

                        if(isNumeric(loginFrame.getPassword())){

                            boolean accountOkOrNok = fileGestion.verifAccountLoginAndPassword("user.txt", loginFrame.getName(), Integer.valueOf(loginFrame.getPassword()));

                            if (accountOkOrNok) {

                                nbIndex = fileGestion.findIndexAccount("user.txt", loginFrame.getName());
                                selectionFrame.setID(loginFrame.getName());
                                selectionFrame.setClear();
                                loginFrame.setIncorrectClear();
                                action = ACTION_SELECT;
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

                            boolean accountExistOrNot = fileGestion.verifAccountExist("user.txt", registerFrame.getName());

                            if (accountExistOrNot) {

                                registerFrame.setIncorrectUsername();
                                registerFrame.setAcceptFalse();
                                break;

                            }else if(!(accountExistOrNot)){

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

                    //Display the WITHDRAW frame
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

            }

        }
        fileGestion.writeToFileObjects("user.txt", listUser);
        System.exit(0);

    }

}



