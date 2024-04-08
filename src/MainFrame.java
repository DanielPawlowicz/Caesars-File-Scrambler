import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		
		setTitle("Caesar Cipher Scrambler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
        ActionChooser actionChooser = new ActionChooser();
        
        add(actionChooser);
        
        setVisible(true);
	}

}
