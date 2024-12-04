import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class LoginWindow extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginWindow;
    private JButton backButton;
    private checkValid checkValid;
    private usersdatabase userData = new usersdatabase("src/users.json");
    private ArrayList<User> usersOperation = userData.load();

    public LoginWindow(StartWindow sw) {
        setTitle("Login");
        setSize(new Dimension(500, 400));
        setContentPane(loginWindow);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Email = emailField.getText();
                String CheckPassword = passwordField.getText();
                String CheckPasswordHashed = passwordHashing.hashpassword(CheckPassword);
                if (CheckPassword.isEmpty() || Email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter email and password", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    checkValid checkEmail = new checkValid(new TestEmail());
                    Boolean emailCheck = checkEmail.test(Email);
                    if (emailCheck == false) {
                        JOptionPane.showMessageDialog(loginWindow, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                Boolean emailIsExist = false;// to check email in the database or not
                for (int i = 0; i < usersOperation.size(); i++) {
                    if (usersOperation.get(i).getEmail().equals(Email)) {
                        String orignalPassword = usersOperation.get(i).getHashingPassword();
                        emailIsExist = true;
                        if (orignalPassword.equals(CheckPasswordHashed)) {
                            new NewsFeedgui();
                        } else
                            JOptionPane.showMessageDialog(null, "Invalid email address and password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (emailIsExist == false) {
                    JOptionPane.showMessageDialog(null, "Invalid email address and password zz", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                sw.setVisible(true);
            }
        });
    }
}
