import javax.swing.*;

// Panel to choose between encryption and decryption actions
public class ActionChooser extends JPanel {

    public ActionChooser() {
        // Create buttons for encryption and decryption
        JButton btnEncryption = new JButton("Encrypt file");
        JButton btnDecryption = new JButton("Decrypt file");

        // Add action listeners to the buttons
        btnEncryption.addActionListener(e -> new Encrypter());
        btnDecryption.addActionListener(e -> new Decrypter());

        // Add buttons to the panel
        add(btnEncryption);
        add(btnDecryption);
    }
}
