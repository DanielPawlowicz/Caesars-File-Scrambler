import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

//Class for decrypting files
public class Decrypter extends CryptCommon{
	
	public Decrypter() {
		
		start.setText("Start decryption");
		frame.setTitle("Decrypt File");

		// Add action listener to the start button
		start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decryptFile();
            }
        });
		
	}
	
	// Decrypt the chosen file
	private void decryptFile() {
		int shiftNumber = (Integer) shiftComboBox.getSelectedItem();
        String decryptedFileName = "";

     // Retrieve chosen file path from the label
        String chosenFilePath = info.getText().replace("Chosen file: ", "");

     // Check if a file is selected
        if (chosenFilePath.isEmpty()) {
            System.out.println("No file selected.");
            return;
        }
        
        File inputFile = new File(chosenFilePath);
        String outputPath = inputFile.getParentFile().getAbsolutePath();
        
        
        try {
            Scanner scanner = new Scanner(inputFile);
            StringBuilder decryptedText = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                decryptedText.append(decrypt(line, shiftNumber)).append("\n");
            }

            scanner.close();

            decryptedFileName = inputFile.getName().replaceFirst("[.][^.]+$", "") + "_decrypted.txt";
            FileWriter writer = new FileWriter(outputPath + File.separator + decryptedFileName);
            writer.write(decryptedText.toString());
            writer.close();

            File decryptedFile = new File(outputPath + File.separator + decryptedFileName);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(decryptedFile.getParentFile());
            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            info.setText("Something went wrong");
	        } catch (IOException e) {
	            e.printStackTrace();
	            info.setText("Something went wrong");
	        }
	        info.setText("Path: "+outputPath+"/"+decryptedFileName);
    }
	
	// Decrypt a single line of text
	private String decrypt(String text, int shift) {
	    StringBuilder result = new StringBuilder();
	    for (int i = 0; i < text.length(); i++) {
	        char ch = text.charAt(i);
	        if (Character.isLetter(ch)) {
	            char base = Character.isUpperCase(ch) ? 'A' : 'a';
	            char decryptedChar = (char) (((ch - base - shift + 26) % 26) + base);
	            result.append(decryptedChar);
	        } else if (Character.isDigit(ch)) { // Handle numbers
	            char base = '0';
	            char decryptedChar = (char) (((ch - base - shift + 10) % 10 + 10) % 10 + base);
	            result.append(decryptedChar);
	        } else {
	            result.append(ch);
	        }
	    }
	    return result.toString();
	}

	
}