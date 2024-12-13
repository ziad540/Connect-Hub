package FrontEnd;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import BackEnd.*;

public class FriendProfile extends JFrame {
    private JPanel friendProfile;
    private JLabel coverPhotoLabel;
    private JLabel bioLabel;
    private JLabel profilePhotoLabel;
    private JLabel bioDetails;
    private JButton backButton;
    private JLabel postsLabel;
    private JPanel posts;


    public FriendProfile(JFrame frame, User user) {
        JDialog dialog = new JDialog(frame, user.getUserName() + "'s Profile", true);//to make window always on top
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 800);
        dialog.setLocationRelativeTo(frame);
        dialog.setAlwaysOnTop(true);
        BufferedImage profile = null;
        try {
            profile = ImageIO.read(new File(user.getProfileInformation().getProfilePicPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image profileImage = profile.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        BufferedImage circularImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circularImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, 200, 200));
        g2d.drawImage(profileImage, 0, 0, 200, 200, null);
        g2d.setClip(null);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(new java.awt.geom.Ellipse2D.Float(0, 0, 200, 200));
        g2d.dispose();
        profilePhotoLabel.setIcon(new ImageIcon(circularImage));
        ImageIcon cover = new ImageIcon(user.getProfileInformation().getCoverPicPath());
        Image coverImage = cover.getImage().getScaledInstance(600, 150, Image.SCALE_SMOOTH);
        coverPhotoLabel.setIcon(new ImageIcon(coverImage));
        bioDetails.setText(user.getProfileInformation().getBioData());

        postsLabel.setText(user.getUserName() + "'s Posts");
        posts = new JPanel();
        posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
        GetuserPosts get = new GetuserPosts();
        ArrayList<Post> myposts = get.getuserposts(user);
        loadPosts.showMyPosts(posts, UserDatabaseManagement.getInstance(), myposts);
        JScrollPane scrollPane = new JScrollPane(posts);
        friendProfile.add(scrollPane, BorderLayout.CENTER);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                frame.setVisible(true);
            }
        });

        dialog.add(friendProfile);

        dialog.setVisible(true);
    }
}
