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
        ImageIcon profile = new ImageIcon("src/wallpaper1.png");
        Image profileImage = profile.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
        profilePhoto.setIcon(new ImageIcon(profileImage));
        ImageIcon cover = new ImageIcon("src/wallpaper2.png");
        Image coverImage = cover.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
        coverPhoto.setIcon(new ImageIcon(coverImage));
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                p.setVisible(true);
            }
        });
        editCoverPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Load cover photo");
                int selected = fileChooser.showOpenDialog(profileDetailsWindow);
                if (selected == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    ImageIcon originalIcon = new ImageIcon(path);
                    Image coverImage = originalIcon.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
                    coverPhoto.setIcon(new ImageIcon(coverImage));
                }

            }
        });
        editProfilePhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Load profile photo");
                int selected = fileChooser.showOpenDialog(profileDetailsWindow);
                if (selected == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    ImageIcon originalIcon = new ImageIcon(path);
                    Image profileImage = originalIcon.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
                    profilePhoto.setIcon(new ImageIcon(profileImage));
                }

            }
        });
        editBioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new resetBio();
                // show new bio in section
            }
        });
        editUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new resetUsername();
                // show new username in section
            }
        });
        editPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new resetPassword(p);
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // save to file
            }
        });
    }
}
