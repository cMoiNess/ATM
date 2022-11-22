import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InfoPanel extends JPanel implements ActionListener{

	private boolean back = false;

	private JButton goBackButton;
	private JTextArea display = new JTextArea (10,42);
	private JScrollPane scroll = new JScrollPane (display);
	
	
	public InfoPanel(){

			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
		    constraints.anchor = GridBagConstraints.WEST;
		    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		    goBackButton = new JButton		("Return");
		    
		   
		    
		    goBackButton.addActionListener(this);
		    
			constraints.gridx = 0;
			constraints.gridy = 1;
			add(goBackButton,constraints);
			
			constraints.gridx = 0;
			constraints.gridy = 0;
			add(scroll,constraints);
			

			

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed.equals(goBackButton)){
			System.out.println("returning!");
			back = true;
			
		}
	}
	
	public void addToDisplay(String info){
		display.append(info);
	}
	
	public boolean getBack(){
		return back;
	}
	
	public void setBackFalse(){
		back = false;
	}
	
	public void clearDisplay(){
		display.setText("");
	}
	
}