import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class resetPassword extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel oldPassword;
    private JLabel newPassword;
    private JLabel confirmPassword;
    private JButton Confirmbutton;
    private JPasswordField passwordField1;

    public resetPassword(User u) {
        setTitle("Reset Password");
        setSize(new Dimension(400,400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);

        Confirmbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = textField1.getText();
                String newPassword = textField2.getText();
                String confirmPassword = textField3.getText();
                if(oldPassword.isEmpty()||newPassword.isEmpty()||confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}
