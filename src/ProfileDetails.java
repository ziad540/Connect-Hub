import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileDetails extends JFrame{
    private JPanel profileDetailsWindow;
    private JButton saveChangesButton;
    private JButton editCoverPhotoButton;
    private JButton editProfilePhotoButton;
    private JButton backButton;
    private JLabel coverPhoto;
    private JLabel profilePhoto;
    private JButton editBioButton;
    private JButton editUsernameButton;
    private JButton editPasswordButton;
    private JLabel bioDetails;
    private JLabel usernameDetails;

    public ProfileDetails(Profile p) {
        setTitle("Profile Details");
        setSize(new Dimension(600,800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(profileDetailsWindow);
        setLocationRelativeTo(null);
        ImageIcon profile = new ImageIcon("C:\\Users\\Nour_Azab\\Pictures\\Screenshots\\wallpaper1.png");
        Image profileImage = profile.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
        profilePhoto.setIcon(new ImageIcon(profileImage));
        ImageIcon cover = new ImageIcon("C:\\Users\\Nour_Azab\\Pictures\\Screenshots\\wallpaper2.png");
        Image coverImage = cover.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
        coverPhoto.setIcon(new ImageIcon(coverImage));
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//                p.setVisible(true);
            }
        });
        editCoverPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editProfilePhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editBioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
