import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class AdminSelectionPanel extends JPanel implements ActionListener{




    private boolean adminDeposit = false;
    private boolean adminWithdraw = false;
    private boolean adminTransfer = false;
    private boolean adminBalance = false;
    private boolean adminHistory = false;

    private boolean adminDelete = false;
    private boolean adminCreate = false;
    private boolean adminList = false;
    private boolean disconnect = false;
    private String name = "";


    private JLabel welcome;
    private JButton adminDepositButton;
    private JButton adminWithdrawButton;
    private JButton adminTransferButton;
    private JButton adminBalanceButton;
    private JButton adminHistoricButton;
    private JButton adminDeleteButton;
    private JButton adminCreateButton;
    private JButton adminListButton;
    private JButton disconnectButton;

    public AdminSelectionPanel(){

        setLayout(new GridBagLayout());

        welcome = new JLabel("Admin Selection Menu");

        adminDepositButton = new JButton("Deposit to user");
        adminWithdrawButton = new JButton("Withdraw from user");
        adminTransferButton = new JButton("Transfer between users");
        adminBalanceButton = new JButton("Show user balance");
        adminHistoricButton = new JButton("Show user historic");
        adminDeleteButton = new JButton("Delete user");
        adminCreateButton = new JButton("Create new admin");
        adminListButton = new JButton("Show all users");
        disconnectButton = new JButton("Disconnect");



        adminDepositButton.addActionListener(this);
        adminWithdrawButton.addActionListener(this);
        adminTransferButton.addActionListener(this);
        adminBalanceButton.addActionListener(this);
        adminHistoricButton.addActionListener(this);
        adminDeleteButton.addActionListener(this);
        adminCreateButton.addActionListener(this);
        adminListButton.addActionListener(this);
        disconnectButton.addActionListener(this);

        adminDepositButton.setPreferredSize(adminTransferButton.getPreferredSize());
        adminDepositButton.setMinimumSize(adminTransferButton.getMinimumSize());
        adminWithdrawButton.setPreferredSize(adminTransferButton.getPreferredSize());
        adminWithdrawButton.setMinimumSize(adminTransferButton.getMinimumSize());
        adminBalanceButton.setPreferredSize(adminTransferButton.getPreferredSize());
        adminBalanceButton.setMinimumSize(adminTransferButton.getMinimumSize());
        adminHistoricButton.setPreferredSize(adminTransferButton.getPreferredSize());
        adminHistoricButton.setMinimumSize(adminTransferButton.getMinimumSize());
        adminDeleteButton.setPreferredSize(adminTransferButton.getPreferredSize());
        adminDeleteButton.setMinimumSize(adminTransferButton.getMinimumSize());
        adminCreateButton.setPreferredSize(adminTransferButton.getPreferredSize());
        adminCreateButton.setMinimumSize(adminTransferButton.getMinimumSize());
        adminListButton.setPreferredSize(adminTransferButton.getPreferredSize());
        adminListButton.setMinimumSize(adminTransferButton.getMinimumSize());
        disconnectButton.setPreferredSize(adminTransferButton.getPreferredSize());
        disconnectButton.setMinimumSize(adminTransferButton.getMinimumSize());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0,5,5,5);

        constraints.weighty = 0;

        constraints.fill = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(welcome,constraints);

        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(adminDepositButton,constraints);


        constraints.gridx = 1;
        constraints.gridy = 1;
        add(adminWithdrawButton,constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(adminTransferButton,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(adminHistoricButton,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(adminBalanceButton,constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        add(adminCreateButton,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(adminDeleteButton,constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(adminListButton,constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        add(disconnectButton,constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();

        if (buttonPressed.equals(adminDepositButton)){
            adminDeposit = true;

        }

        else if (buttonPressed.equals(adminWithdrawButton)){
            adminWithdraw = true;
        }

        else if (buttonPressed.equals(adminTransferButton)){
            adminTransfer = true;
        }

        else if (buttonPressed.equals(adminBalanceButton)){
            adminBalance = true;
        }

        else if (buttonPressed.equals(adminHistoricButton)){
            adminHistory = true;
        }

        else if (buttonPressed.equals(adminDeleteButton)){
            adminDelete = true;
        }

        else if (buttonPressed.equals(adminCreateButton)){
            adminCreate = true;
        }

        else if (buttonPressed.equals(adminListButton)){
            adminList = true;
        }

        else if (buttonPressed.equals(disconnectButton)){
            disconnect = true;
        }

    }

    public boolean getAdminDeposit(){
        return adminDeposit;
    }

    public void setAdminDepositFalse(){
        adminDeposit = false;
    }

    public boolean getAdminWithdraw(){
        return adminWithdraw;
    }

    public void setAdminWithdrawFalse(){
        adminWithdraw = false;
    }

    public boolean getAdminTransfer(){
        return adminTransfer;
    }

    public void setAdminTransferFalse(){
        adminTransfer = false;
    }

    public boolean getAdminBalance(){
        return adminBalance;
    }

    public void setAdminBalanceFalse(){
        adminBalance = false;
    }
    public boolean getAdminHistory(){
        return adminHistory;
    }

    public void setAdminHistoryFalse(){
        adminHistory = false;
    }

    public boolean getAdminCreate(){
        return adminCreate;
    }

    public void setAdminCreateFalse(){
        adminCreate = false;
    }

    public boolean getAdminDelete(){
        return adminDelete;
    }

    public void setAdminDeleteFalse(){
        adminDelete = false;
    }

    public boolean getAdminList(){
        return adminList;
    }

    public void setAdminListFalse(){
        adminList = false;
    }

    public boolean getDisconnect(){
        return disconnect;
    }

    public void setDisconnectFalse(){
        disconnect = false;
    }


    public void setName(String n){
        name = n;
        welcome.setText("Welcome, " + name + " Please Make a Selection");

    }

    public String getName(){
        return name;
    }

}
