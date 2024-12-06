package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BackEnd.*;

public class resetBio extends JFrame {
    private JPanel resetBioPanel;
    private JTextField newBioField;
    private JButton resetButton;
    public resetBio(JLabel label, ProfileInformation p) {
        JDialog dialog = new JDialog((Frame) null, "Reset Bio", true);//to make window always on top
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
        dialog.add(resetBioPanel);


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newBio = newBioField.getText();
                if(newBio.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please enter bio", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // add to array not file
                    label.setText(newBio);
                    p.setBioData(newBio);
                    dialog.dispose();
                }

            }
        });
        dialog.setVisible(true);
    }
}
