import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Encrypter extends CryptCommon{
    
	public Encrypter() {
		
		start.setText("Start encryption");
		
		start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encryptFile();
            }
        });
		
	}
	
    private void encryptFile() {
        int shiftNumber = (Integer) shiftComboBox.getSelectedItem();
        String encryptedFileName = "";

        // Retrieve chosen file path from the label
        String chosenFilePath = info.getText().replace("File chosen: ", "");

        // Check if a file is selected
        if (chosenFilePath.isEmpty()) {
            System.out.println("No file selected.");
            info.setText("No file selected");
            return;
        }

        File inputFile = new File(chosenFilePath);
        String outputPath = inputFile.getParentFile().getAbsolutePath();

        try {
            Scanner scanner = new Scanner(inputFile);
            StringBuilder encryptedText = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                encryptedText.append(encrypt(line, shiftNumber)).append("\n");
            }

            scanner.close();

            // Write encrypted text to a new file
            encryptedFileName = inputFile.getName().replaceFirst("[.][^.]+$", "") + "_encrypted.txt";
            FileWriter writer = new FileWriter(outputPath + File.separator + encryptedFileName);
            writer.write(encryptedText.toString());
            writer.close();

            // Open the directory containing the encrypted file
            File encryptedFile = new File(outputPath + File.separator + encryptedFileName);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(encryptedFile.getParentFile());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            info.setText("Something went wrong");
        } catch (IOException e) {
            e.printStackTrace();
            info.setText("Something went wrong");
        }
        info.setText("Path: "+outputPath+"/"+encryptedFileName);
        System.out.println(outputPath);
        System.out.println(encryptedFileName);
    }

    private String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char encryptedChar = (char) (((ch - base + shift) % 26) + base);
                result.append(encryptedChar);
            } else if (Character.isDigit(ch)) { // Handle numbers
                char base = '0';
                char encryptedChar = (char) (((ch - base + shift) % 10 + 10) % 10 + base);
                result.append(encryptedChar);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        new Encrypter();
    }
}
