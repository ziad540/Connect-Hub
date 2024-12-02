import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

public class Profile extends JFrame{
    private JPanel profileWindow;
    private JLabel coverPhotoLabel;
    private JList list1;
    private JTable table1;
    private JLabel bioLabel;
    private JButton viewProfileButton;
    private JLabel profilePhotoLabel;
    private JLabel bioDetails;

    public Profile() {
        setTitle("Profile");
        setSize(new Dimension(600,800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(profileWindow);
        setLocationRelativeTo(null);
        //ImageIcon profile = new ImageIcon("C:\\Users\\Nour_Azab\\Pictures\\Screenshots\\wallpaper1.png");
        BufferedImage profile = null;
        try {
            profile = ImageIO.read(new File("C:\\Users\\Nour_Azab\\Pictures\\Screenshots\\wallpaper1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image profileImage = profile.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        BufferedImage circularImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circularImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, 200, 200));
        g2d.drawImage(profileImage, 0, 0, 200, 200, null);
        g2d.setClip(null); // Remove clipping
        g2d.setColor(Color.BLACK); // Border color
        g2d.setStroke(new BasicStroke(1 )); // Border thickness
        g2d.draw(new java.awt.geom.Ellipse2D.Float(0, 0, 200, 200));
        g2d.dispose();
        profilePhotoLabel.setIcon(new ImageIcon(circularImage));
        ImageIcon cover = new ImageIcon("C:\\Users\\Nour_Azab\\Pictures\\Screenshots\\wallpaper2.png");
        Image coverImage = cover.getImage().getScaledInstance(600,200,Image.SCALE_SMOOTH);
        coverPhotoLabel.setIcon(new ImageIcon(coverImage));
        setVisible(true);
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfileDetails profileDetails = new ProfileDetails(Profile.this);
                setVisible(false);
            }
        });
    }
}
