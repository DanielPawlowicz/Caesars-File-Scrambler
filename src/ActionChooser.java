import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionChooser extends JPanel{

	public ActionChooser() {
		JButton encryptionButton = new JButton("Encrypt file");
		JButton decryptionButton = new JButton("Decrypt file");
		
		encryptionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("works e");
				new Encrypter();
			}
		});
		
		decryptionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("works d");
				new Decrypter();
			}
		});
		
		this.add(encryptionButton);
		this.add(decryptionButton);
	}
	
	
}
