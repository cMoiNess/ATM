import javax.swing.*;


public class AdminDeleteFrame extends JFrame{

    private AdminDeletePanel adminDeletePanel;

    public AdminDeleteFrame(){
        super("Delete a user"); // Sets title
        setSize(530, 280);
        setResizable(false);
        setIconImage(LoginFrame.icon.getImage());
        adminDeletePanel = new AdminDeletePanel();
        this.setLocation(500, 500);
        add(adminDeletePanel);
    }


    public String getUsername(){return adminDeletePanel.getUsername();}

    public boolean getBack(){
        return adminDeletePanel.getBack();
    }

    public void setBackFalse(){
        adminDeletePanel.setBackFalse();
    }

    public boolean getDelete(){
        return adminDeletePanel.getDelete();
    }

    public void setDeleteFalse(){
        adminDeletePanel.setDeleteFalse();
    }

    public void setInfo(){adminDeletePanel.setInfo();}

    public void setSuccess(){adminDeletePanel.setSuccess();}

    public void clearDisplay(){
        adminDeletePanel.clearInfo();
    }

}