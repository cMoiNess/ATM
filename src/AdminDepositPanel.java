import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminDepositPanel extends JPanel implements ActionListener{

    private boolean adminDeposit = false;

    private boolean back = false;

    private JLabel deposit_amount;
    private JLabel deposit_account;

    private JLabel info;



    private JButton makeDepositButton;

    private JButton backButton;

    private JTextField depositAccount;
    private JTextField depositAmount;

    public AdminDepositPanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        deposit_amount = new JLabel ("Desired Amount");
        deposit_account = new JLabel ("Desired Account");
        info = new JLabel();


        makeDepositButton = new JButton("Deposit");
        backButton = new JButton("Back");

        depositAccount = new JTextField(15);
        depositAmount = new JTextField(15);

        makeDepositButton.addActionListener(this);
        backButton.addActionListener(this);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(depositAmount,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(depositAccount,constraints);

        constraints.gridwidth = 1;

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(deposit_amount,constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(deposit_account,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(makeDepositButton,constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        add(backButton,constraints);

        constraints.gridwidth = 3;

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(info,constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();

        if (buttonPressed.equals(makeDepositButton)){
            adminDeposit = true;
        }
        if (buttonPressed.equals(backButton)){
            back = true;
        }
    }


    public boolean getBack(){return back;}

    public void setBackFalse(){
        back = false;
    }
    public boolean getAdminDeposit(){
        return adminDeposit;
    }

    public void setAdminDepositFalse(){
        adminDeposit = false;
    }

    public String getDepositAccount(){
        return depositAccount.getText();
    }

    public String getDepositAmount(){
        return depositAmount.getText();
    }

    public void setIncorrectAmount(){
        info.setText("Incorrect Amount");
    }

    public void setIncorrectAccount(){
        info.setText("Incorrect Account");
    }

    public void setIncorrect(){
        info.setText("Incorrect Amount and Account");
    }

    public void setCorrectAmount(){
        info.setText("You have transfered : " + getDepositAmount() + " to " + getDepositAccount());
    }

    public void setInfoClear(){ info.setText("");}

    public void setDepositClear(){
        depositAmount.setText("");
        depositAccount.setText("");
    }

}