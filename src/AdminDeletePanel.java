import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminDeletePanel extends JPanel implements ActionListener{

    private boolean back = false;

    private boolean delete = false;

    private JLabel user;
    private JLabel info;
    private JTextField username;

    private JButton goBackButton;

    private JButton deleteButton;


    public AdminDeletePanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        info = new JLabel();
        user = new JLabel("User");
        username = new JTextField(15);
        goBackButton = new JButton("Return");
        deleteButton = new JButton("Delete User");

        goBackButton.addActionListener(this);
        deleteButton.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(user,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(deleteButton,constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(goBackButton,constraints);

        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
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
        if (buttonPressed.equals(deleteButton)){
            delete = true;

        }
    }

    public String getUsername(){return username.getText();}

    public boolean getBack(){
        return back;
    }

    public void setBackFalse(){
        back = false;
    }

    public boolean getDelete(){
        return delete;
    }

    public void setDeleteFalse(){
        delete = false;
    }

    public void setInfo(){info.setText("Wrong username!");}

    public void setSuccess(){info.setText(getUsername()+" has been deleted!");
        username.setText("");}

    public void clearInfo(){
        info.setText("");
        username.setText("");
    }

}