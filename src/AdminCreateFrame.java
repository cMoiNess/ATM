import javax.swing.*;

public class AdminCreateFrame extends JFrame{

    private AdminCreatePanel adminCreatePanel;
    private boolean accept;



    public AdminCreateFrame(){

        super("Create an admin"); // Sets title
        setSize(350, 200); // Sets size
        setResizable(false);
        setIconImage(LoginFrame.icon.getImage()); // Sets Icon
        adminCreatePanel = new AdminCreatePanel();
        this.setLocation(500, 500);
        add(adminCreatePanel);
    }

    public String getName(){

        return adminCreatePanel.getName();

    }

    public String getPassword(){

        return adminCreatePanel.getPassword();

    }

    public boolean getAccept(){return adminCreatePanel.getAccept();}

    public void setAcceptFalse(){
        adminCreatePanel.setAcceptFalse();
    }

    public void setBackFalse(){
        adminCreatePanel.setBackFalse();
    }

    public void setSuccess(){adminCreatePanel.setSuccess();}

    public void setIncorrectPassword(){adminCreatePanel.setIncorrectPassword();}

    public void setIncorrectUsername(){adminCreatePanel.setIncorrectUsername();}

    public void setClear(){
        adminCreatePanel.setClear();
    }

    public void setInfoClear(){adminCreatePanel.setInfoClear();}

    public void setIncorrect(){adminCreatePanel.setIncorrect();}

    public boolean getBack(){
        return adminCreatePanel.getBack();
    }

}


