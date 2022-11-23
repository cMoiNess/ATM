import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminTransferPanel extends JPanel implements ActionListener{

    private boolean adminTransfer = false;

    private boolean back = false;

    private JLabel transfer_amount;
    private JLabel deposit_account;
    private JLabel withdraw_account;

    private JLabel info;



    private JButton makeTransferButton;

    private JButton backButton;

    private JTextField withdrawAccount;
    private JTextField depositAccount;
    private JTextField transferAmount;

    public AdminTransferPanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        transfer_amount = new JLabel ("Transfer Amount");
        deposit_account = new JLabel("Deposit Account");
        withdraw_account = new JLabel ("Withdraw Account");
        info = new JLabel();


        makeTransferButton = new JButton("Transfer");
        backButton = new JButton("Back");

        withdrawAccount = new JTextField(15);
        depositAccount = new JTextField(15);
        transferAmount = new JTextField(15);

        makeTransferButton.addActionListener(this);
        backButton.addActionListener(this);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(withdrawAccount,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(depositAccount,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(transferAmount,constraints);

        constraints.gridwidth = 1;

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(withdraw_account,constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(deposit_account,constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        add(transfer_amount,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(makeTransferButton,constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        add(backButton,constraints);

        constraints.gridwidth = 3;

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(info,constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();

        if (buttonPressed.equals(makeTransferButton)){
            adminTransfer = true;
        }
        if (buttonPressed.equals(backButton)){
            back = true;
        }
    }


    public boolean getBack(){return back;}

    public void setBackFalse(){
        back = false;
    }

    public boolean getAdminTransfer(){
        return adminTransfer;
    }

    public void setAdminTransferFalse(){
        adminTransfer = false;
    }

    public String getWithdrawAccount(){
        return withdrawAccount.getText();
    }

    public String getDepositAccount(){
        return depositAccount.getText();
    }

    public String getTransferAmount(){
        return transferAmount.getText();
    }

    public void setIncorrectAmount(){
        info.setText("Incorrect Amount");
    }

    public void setIncorrectWithdrawAccount(){
        info.setText("Incorrect Withdraw Account");
    }

    public void setIncorrectDepositAccount(){
        info.setText("Incorrect Deposit Account");
    }

    public void setIncorrect(){
        info.setText("Incorrect Amount or Account");
    }

    public void setCorrectAmount(){
        info.setText(getWithdrawAccount() + " transfered : " + getTransferAmount() + " to " + getDepositAccount());
    }

    public void setInfoClear(){ info.setText("");}

    public void setAdminTransferClear(){
        transferAmount.setText("");
        withdrawAccount.setText("");
        depositAccount.setText("");
    }

}