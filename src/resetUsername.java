import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class resetUsername extends JFrame {
    private JPanel panel1;
    private JTextField userNameFiled;
    private JButton resetButton;

    public resetUsername(JLabel label) {
        setTitle("Reset Username");
        setSize(new Dimension(400,400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameFiled.getText();
                checkValid UserName= new checkValid( new TestUsername());
                Boolean checkUserName=UserName.test(username);
                if(username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                }

                else if(!checkUserName) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid username", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    label.setText(username);
                    dispose();
                    // add to array not file
                }
            }
        });
    }
}
