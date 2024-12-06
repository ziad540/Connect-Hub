package FrontEnd;//import sun.security.util.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BackEnd.*;

public class resetPassword extends JFrame {
    private JPanel resetPasswordPanel;
    private JLabel oldPassword;
    private JLabel newPassword;
    private JLabel confirmPassword;
    private JButton Confirmbutton;
    private UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();

    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPasswordField passwordField3;

    public resetPassword(User user) {
        JDialog dialog = new JDialog((Frame) null, "Reset Password", true);//to make window always on top
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
        dialog.add(resetPasswordPanel);

        Confirmbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = passwordField1.getText();
                String newPassword = passwordField2.getText();
                String confirmPassword = passwordField3.getText();
                String orignalPassword = user.getHashingPassword();
                checkValid checkPassword = new checkValid(new TestPassword());
                boolean passwordCheck =checkPassword.test(newPassword);
                String CheckPasswordHashed = passwordHashing.hashpassword(oldPassword);
                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!orignalPassword.equals(CheckPasswordHashed)){
                    JOptionPane.showMessageDialog(dialog, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!(newPassword.equals(confirmPassword))) {
                    JOptionPane.showMessageDialog(dialog, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!passwordCheck) {
                    JOptionPane.showMessageDialog(dialog, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    user.setPassword(newPassword);
                    JOptionPane.showMessageDialog(dialog, "Password has been reset", "Success", JOptionPane.INFORMATION_MESSAGE);
                    userDatabaseManagement.saveToFile();
                    dialog.dispose();
                }
            }
        });
        dialog.setVisible(true);
    }
}
