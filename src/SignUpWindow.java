import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Properties;

public class SignUpWindow extends JFrame{
    private JTextField emailField;
    private JPanel signUpWindow;
    private JTextField usernameField;
    private JButton signUpButton;
    private JPasswordField confirmPasswordField;
    private JPanel dateWindow;
    private JPasswordField passwordField;
    private JButton backButton;
    private TestEmail testEmail=new TestEmail();
    private TestPassword testPassword=new TestPassword();
    private TestUsername testUsername=new TestUsername();

    public SignUpWindow(StartWindow sw) {
        setTitle("Sign Up");
        setSize(new Dimension(500,600));
        UtilDateModel model = new UtilDateModel();
        model.setValue(Calendar.getInstance().getTime());
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        dateWindow.add(datePicker);
        setLocationRelativeTo(null);
        setContentPane(signUpWindow);
        signUpWindow.revalidate();
        signUpWindow.repaint();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkEmail = emailField.getText();
                String checkUsername = usernameField.getText();
                String checkPassword = passwordField.getText();
                String checkConfirmPassword = confirmPasswordField.getText();
                Boolean validEmail = testEmail.checkEmail(checkEmail);
                Boolean validPassword =testPassword.testPassword(checkPassword);
                Boolean validUsername = testUsername.testUsername(checkUsername);
                if (checkConfirmPassword.isEmpty()||checkPassword.isEmpty()||checkEmail.isEmpty()||checkUsername.isEmpty()) {
                    JOptionPane.showMessageDialog(signUpWindow, "Please enter all the fields correctly.","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(!validEmail){
                    JOptionPane.showMessageDialog(dateWindow, "Invalid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!validPassword) {
                    JOptionPane.showMessageDialog(dateWindow, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!validUsername) {
                    JOptionPane.showMessageDialog(dateWindow, "Invalid Username", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if(!(checkConfirmPassword.equals(checkPassword))){
                    JOptionPane.showMessageDialog(dateWindow, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
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
