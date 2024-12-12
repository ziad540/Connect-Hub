package FrontEnd;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BackEnd.*;

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

    private JButton deleteCoverButton;
    private JButton deleteProfileButton;
    private JButton deleteBioButton;
    UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();
    User user;
    Search search = new Search();

    public ProfileDetails(JFrame frame, String userID) {
        user = search.getUser(userID);
        ProfileInformation tempProfile = new ProfileInformation(user.getProfileInformation().getProfilePicPath(),user.getProfileInformation().getCoverPicPath(),user.getProfileInformation().getBioData());
        setTitle("Profile Details");
        setSize(new Dimension(600,800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(profileDetailsWindow);
        setLocationRelativeTo(null);
        ImageIcon profile = new ImageIcon(tempProfile.getProfilePicPath());
        Image profileImage = profile.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
        profilePhoto.setIcon(new ImageIcon(profileImage));
        ImageIcon cover = new ImageIcon(tempProfile.getCoverPicPath());
        Image coverImage = cover.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
        coverPhoto.setIcon(new ImageIcon(coverImage));
        bioDetails.setText(tempProfile.getBioData());
        usernameDetails.setText(user.getUserName());
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Profile profile = new Profile(frame,userID);
            }
        });
        editCoverPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG,JPG Images", "png","jpg");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Load cover photo");
                int selected = fileChooser.showOpenDialog(profileDetailsWindow);
                if (selected == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    ImageIcon originalIcon = new ImageIcon(path);
                    Image coverImage = originalIcon.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
                    coverPhoto.setIcon(new ImageIcon(coverImage));
                    tempProfile.setCoverPicPath(path);
                }

            }
        });
        editProfilePhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG,JPG Images", "png","jpg");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Load profile photo");
                int selected = fileChooser.showOpenDialog(profileDetailsWindow);
                if (selected == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    ImageIcon originalIcon = new ImageIcon(path);
                    Image profileImage = originalIcon.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
                    profilePhoto.setIcon(new ImageIcon(profileImage));
                   tempProfile.setProfilePicPath(path);
                }

            }
        });
        editBioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new resetBio(bioDetails,tempProfile);
                // show new bio in section
            }
        });
        editUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new resetUsername(usernameDetails);
                // show new username in section
            }
        });
        editPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new resetPassword(user);
            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // save updated currentuser to file
                user.setUserName(usernameDetails.getText());
                user.setProfileInformation(tempProfile);

                userDatabaseManagement.saveToFile();
            }
        });
        deleteCoverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon originalIcon = new ImageIcon("src/Image/unknown cover.png");
                Image coverImage = originalIcon.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
                coverPhoto.setIcon(new ImageIcon(coverImage));
                tempProfile.setCoverPicPath("src/Image/unknown cover.png");
            }
        });
        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon originalIcon = new ImageIcon("src/Image/unknown user.png");
                Image profileImage = originalIcon.getImage().getScaledInstance(300,200,Image.SCALE_SMOOTH);
                profilePhoto.setIcon(new ImageIcon(profileImage));
                tempProfile.setProfilePicPath("src/Image/unknown user.png");
            }
        });
        deleteBioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bioDetails.setText("");
                tempProfile.setBioData("");

            }
        });
    }
}
