package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BackEnd.*;

public class LoginWindow extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginWindow;
    private JButton backButton;

    private checkValid checkValid;

    UserDatabaseManagement u = UserDatabaseManagement.getInstance();
    PostDatabaseManagement p = PostDatabaseManagement.getInstance();
    StoryDatabaseManagement s = StoryDatabaseManagement.getInstance();
    StoryHandler storyHandler = new StoryHandler();


    public LoginWindow(StartWindow sw) {
        storyHandler.deleteExpiredStories();
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
                for (int i = 0; i < u.getUsers().size(); i++) {
                    if (u.getUsers().get(i).getEmail().equals(Email)) {
                        String orignalPassword = u.getUsers().get(i).getHashingPassword();
                        emailIsExist = true;
                        if (orignalPassword.equals(CheckPasswordHashed)) {
                            User user = u.getUsers().get(i);
                            user.setStatus("online");
                            u.saveToFile();
                            new NewsFeedgui(user);
                            dispose();
                            setVisible(false);
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
