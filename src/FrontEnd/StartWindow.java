package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BackEnd.*;

public class StartWindow extends JFrame{
    private JPanel startWindow;
    private JButton signUpButton;
    private JButton loginButton;

    public StartWindow() {
        setTitle("Connect Hub");
        setSize(new Dimension(500,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(startWindow);
        setLocationRelativeTo(null);
        setVisible(true);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpWindow signUpWindow = new SignUpWindow(StartWindow.this);
                setVisible(false);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow loginWindow = new LoginWindow(StartWindow.this);
                setVisible(false);

            }
        });
    }

    public static void main(String[] args) {
        new StartWindow();

    }
}
