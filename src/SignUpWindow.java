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

    public SignUpWindow(StartWindow sw) {
        setTitle("Sign Up");
        setSize(new Dimension(500,600));
        setContentPane(signUpWindow);
        UtilDateModel model = new UtilDateModel();
        model.setValue(Calendar.getInstance().getTime());
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        dateWindow.add(datePicker);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        signUpWindow.revalidate();
        signUpWindow.repaint();
        setVisible(true);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
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
