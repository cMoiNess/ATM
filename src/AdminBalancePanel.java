import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminBalancePanel extends JPanel implements ActionListener{

    private boolean back = false;

    private boolean show = false;

    private JLabel user;
    private JLabel info;
    private JTextField username;

    private JButton goBackButton;

    private JButton showButton;
    private JLabel display;


    public AdminBalancePanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        info = new JLabel();
        display = new JLabel();
        user = new JLabel("User");
        username = new JTextField(15);
        goBackButton = new JButton("Return");
        showButton = new JButton("Show Balance");

        goBackButton.addActionListener(this);
        showButton.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(user,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(showButton,constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        add(goBackButton,constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(display,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(info,constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(username,constraints);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();

        if (buttonPressed.equals(goBackButton)){
            back = true;
        }
        if (buttonPressed.equals(showButton)){
            show = true;

        }
    }

    public void addToDisplay(String info){
        display.setText(info);
    }

    public String getUsername(){return username.getText();}

    public boolean getBack(){
        return back;
    }

    public void setBackFalse(){
        back = false;
    }

    public boolean getShow(){
        return show;
    }

    public void setShowFalse(){
        show = false;
    }

    public void setInfo(){info.setText("Wrong username!");}
    public void clearInfo(){
        info.setText("");
        username.setText("");
    }

}