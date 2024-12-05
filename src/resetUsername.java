import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class resetUsername extends JFrame {
    private JPanel resetUsernamePanel;
    private JTextField userNameFiled;
    private JButton resetButton;

    public resetUsername(JLabel label) {
        JDialog dialog = new JDialog((Frame) null, "Reset Bio", true);//to make window always on top
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
        dialog.add(resetUsernamePanel);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameFiled.getText();
                checkValid UserName= new checkValid( new TestUsername());
                Boolean checkUserName=UserName.test(username);
                if(username.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                }

                else if(!checkUserName) {
                    JOptionPane.showMessageDialog(dialog, "Please enter a valid username", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    label.setText(username);
                    dialog.dispose();
                    // add to array not file
                }
            }
        });
        dialog.setVisible(true);
    }
}
