import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Profile extends JFrame{
    private JPanel profileWindow;
    private JLabel coverPhotoLabel;
    private JLabel bioLabel;
    private JButton viewProfileButton;
    private JLabel profilePhotoLabel;
    private JButton backButton;
    private JPanel posts;
    private JLabel bioDetails;

    public Profile(JFrame frame,User user) {
        setTitle("Profile");
        setSize(new Dimension(600,800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(profileWindow);
        setLocationRelativeTo(null);
        BufferedImage profile = null;
        try {
            profile = ImageIO.read(new File(user.getProfileInformation().getProfilePicPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image profileImage = profile.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        BufferedImage circularImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circularImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, 200, 200));
        g2d.drawImage(profileImage, 0, 0, 200, 200, null);
        g2d.setClip(null);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1 ));
        g2d.draw(new java.awt.geom.Ellipse2D.Float(0, 0, 200, 200));
        g2d.dispose();
        profilePhotoLabel.setIcon(new ImageIcon(circularImage));
        ImageIcon cover = new ImageIcon(user.getProfileInformation().getCoverPicPath());
        Image coverImage = cover.getImage().getScaledInstance(600,200,Image.SCALE_SMOOTH);
        coverPhotoLabel.setIcon(new ImageIcon(coverImage));
        bioDetails.setText(user.getProfileInformation().getBioData());
        setVisible(true);
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfileDetails profileDetails = new ProfileDetails(frame,user);
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                frame.setVisible(true);
            }
        });

        posts = new JPanel();
        posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
                GetuserPosts get=new GetuserPosts();
        ArrayList<Post> myposts= get.getuserposts(user);

        loadPosts.showMyPosts(posts, UserDatabaseManagement.getInstance(), myposts); // el moshkela hena han5od el code we 5alas

        JScrollPane scrollPane = new JScrollPane(posts);
        profileWindow.add(scrollPane, BorderLayout.WEST);
    }
}
