import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

abstract class CryptCommon {
	
	JFrame frame = new JFrame();
    JButton chooseFileBtn = new JButton("Choose file");
    JButton start = new JButton("");
    JLabel shiftLabel = new JLabel("Type the shift number (1-25):");
    JComboBox<Integer> shiftComboBox = new JComboBox<>();
    JLabel info = new JLabel("");

    public CryptCommon() {
        for (int i = 1; i <= 25; i++) {
            shiftComboBox.addItem(i);
        }

        chooseFileBtn.setBounds(50, 50, 150, 30);
        shiftLabel.setBounds(50, 100, 250, 30);
        shiftComboBox.setBounds(250, 100, 150, 30);
        start.setBounds(50, 150, 150, 30);
        info.setBounds(50, 200, 450, 150);

        frame.setLayout(null);
        frame.add(chooseFileBtn);
        frame.add(shiftLabel);
        frame.add(shiftComboBox);
        frame.add(start);
        frame.add(info);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        chooseFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        });

    }

    private void chooseFile() {
        // Open file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            info.setText("File chosen: " + selectedFile.getAbsolutePath());
        }
    }

}
