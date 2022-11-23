import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminWithdrawPanel extends JPanel implements ActionListener{

    private boolean adminWithdraw = false;

    private boolean back = false;

    private JLabel withdraw_amount;
    private JLabel withdraw_account;

    private JLabel info;



    private JButton makeWithdrawButton;

    private JButton backButton;

    private JTextField withdrawAccount;
    private JTextField withdrawAmount;

    public AdminWithdrawPanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        withdraw_amount = new JLabel ("Desired Amount");
        withdraw_account = new JLabel ("Desired Account");
        info = new JLabel();


        makeWithdrawButton = new JButton("Deposit");
        backButton = new JButton("Back");

        withdrawAccount = new JTextField(15);
        withdrawAmount = new JTextField(15);

        makeWithdrawButton.addActionListener(this);
        backButton.addActionListener(this);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(withdrawAmount,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(withdrawAccount,constraints);

        constraints.gridwidth = 1;

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(withdraw_amount,constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(withdraw_account,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(makeWithdrawButton,constraints);

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

        if (buttonPressed.equals(makeWithdrawButton)){
            adminWithdraw = true;
        }
        if (buttonPressed.equals(backButton)){
            back = true;
        }
    }


    public boolean getBack(){return back;}

    public void setBackFalse(){
        back = false;
    }
    public boolean getAdminWithdraw(){
        return adminWithdraw;
    }

    public void setAdminWithdrawFalse(){
        adminWithdraw = false;
    }

    public String getWithdrawAccount(){
        return withdrawAccount.getText();
    }

    public String getWithdrawAmount(){
        return withdrawAmount.getText();
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
        info.setText("You have transfered : " + getWithdrawAmount() + " to " + getWithdrawAccount());
    }

    public void setInfoClear(){ info.setText("");}

    public void setWithdrawClear(){
        withdrawAmount.setText("");
        withdrawAccount.setText("");
    }

}