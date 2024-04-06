import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		
		setTitle("Caesar Cipher Scrambler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		ActionChooser actionChooser = new ActionChooser();
		
		this.add(actionChooser);
		
		this.setVisible(true);
	}

}
