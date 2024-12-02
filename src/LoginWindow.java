import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindow extends JFrame{
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginWindow;
    private JButton backButton;
    private TestEmail testEmail=new TestEmail();

    public LoginWindow(StartWindow sw) {
        setTitle("Login");
        setSize(new Dimension(500,400));
        setContentPane(loginWindow);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CheckEmail = emailField.getText();
                String CheckPassword = passwordField.getText();
                if(CheckPassword.isEmpty()||CheckEmail.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please enter email and password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Boolean checkEmail = testEmail.checkEmail(CheckEmail);
                    if (checkEmail == false) {
                        JOptionPane.showMessageDialog(loginWindow, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
