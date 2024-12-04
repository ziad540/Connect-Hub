import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class resetBio extends JFrame {
    private JPanel panel1;
    private JTextField newBioFiled;
    private JButton resetButton;
    public resetBio(JLabel label) {
        setTitle("Reset Bio");
        setSize(new Dimension(400,400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newBio = newBioFiled.getText();
                if(newBio.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter bio", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // add to array not file
                    label.setText(newBio);
                    dispose();
                }

            }
        });
    }
}
