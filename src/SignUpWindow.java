import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                String Email = emailField.getText();
                String Username = usernameField.getText();
                String Password = passwordField.getText();
                String ConfirmPassword = confirmPasswordField.getText();
                checkValid checkEmail =new checkValid(new TestEmail());
                Boolean emailCheck=checkEmail.test(Email);
                checkValid checkUsername =new checkValid(new TestUsername());
                Boolean usernameCheck=checkUsername.test(Username);
                checkValid checkPassword =new checkValid(new TestPassword());
                Boolean passwordCheck=checkPassword.test(Password);
                if (ConfirmPassword.isEmpty()||Password.isEmpty()||Email.isEmpty()||Username.isEmpty()) {
                    JOptionPane.showMessageDialog(signUpWindow, "Please enter all the fields correctly.","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(!emailCheck){
                    JOptionPane.showMessageDialog(dateWindow, "Invalid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!passwordCheck) {
                    JOptionPane.showMessageDialog(dateWindow, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!usernameCheck) {
                    JOptionPane.showMessageDialog(dateWindow, "Invalid Username", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if(!(ConfirmPassword.equals(checkPassword))){
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
