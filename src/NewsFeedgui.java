import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewsFeedgui {
    JPanel contentPanel;
    JPanel right;

    NewsFeedgui() {
        JFrame frame = new JFrame("NewsFeed");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 255, 255));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Posts", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Add Post Button
        JButton addPostButton = new JButton();
        ImageIcon image = new ImageIcon("src/new-post (2).png");
        addPostButton.setContentAreaFilled(false);
        addPostButton.setPreferredSize(new Dimension(50, 50));
        addPostButton.setIcon(image);
        addPostButton.setBorderPainted(false);
        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new post");
            }
        });
        topPanel.add(addPostButton, BorderLayout.EAST);

        // Refresh Button
        JButton refresh = new JButton();
        ImageIcon image2 = new ImageIcon("src/refresh.png");
        refresh.setContentAreaFilled(false);
        refresh.setPreferredSize(new Dimension(50, 50));
        refresh.setIcon(image2);
        refresh.setBorderPainted(false);

        // Content Panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Sample Posts
        Object[][] posts = {
                {"Dodo Yasser", "20/5", "of", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "zizooo"},
                {"Abdallah Yasser", "20/6", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "shika3333"},
                {"Nour Azab", "20/8", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "zamalek"},
                {"Ziad", "20/3", "off", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "ana zeh2t"},
                {"Jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "goallll oba"}
        };

        populatePosts(contentPanel, posts); // Populate posts initially

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

         right=new JPanel();
        FreindGui freind =new FreindGui();
        right=freind.createFriendPanel();
        frame.add(right,BorderLayout.EAST);

        // Refresh Button Logic
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("refresh");

                // Simulate new data
                Object[][] newPosts = {
                        {"Dodo Yasser", "20/5", "of", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "zizooo"},
                        {"Abdallah Yasser", "20/6", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "shika3333"},
                        {"Nour Azab", "20/8", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "zamalek"},
                        {"Ziad", "20/3", "off", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "ana zeh2t"},
                        {"Jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "goallll oba"},
                        {"New User", "21/12", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "new post added"},
                        {"Another User", "21/12", "off", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "hello world"}
                };

                // Clear and repopulate posts
                contentPanel.removeAll();
                populatePosts(contentPanel, newPosts);

                contentPanel.revalidate(); // Recalculate layout
                contentPanel.repaint();// Repaint the panel


                right.removeAll();
                right=freind.createFriendPanel();
               right.repaint();

            }
        });
        topPanel.add(refresh, BorderLayout.WEST);

        frame.add(topPanel, BorderLayout.NORTH);






        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setBackground(new Color(240, 255, 255));
        bottomPanel.setPreferredSize(new Dimension(200, 50));

        JButton profile = createIconButton("src/user.png");
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Profile(NewsFeedgui.this);
                frame.setVisible(false);
            }
        });

        JButton stories = createIconButton("src/story.png");
        stories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stories");
            }
        });

        JButton back = createIconButton("src/logout.png");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back post");
            }
        });

        JButton friends = createIconButton("src/team.png");
        friends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("friends");
            }
        });

        bottomPanel.add(profile);
        bottomPanel.add(stories);
        bottomPanel.add(friends);
        bottomPanel.add(back);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void populatePosts(JPanel panel, Object[][] posts) {
        for (Object[] post : posts) {
            contentPanel.add(createPostPanel(
                    (String) post[0], // Name
                    (String) post[1], // Date
                    (String) post[2], // Status
                    (String) post[4]  // Caption
            ));
        }
    }

    private JPanel createPostPanel(String name, String date, String imagePath, String caption) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(name + " - " + date);
        userInfo.setIcon(new ImageIcon("C:\\Users\\Abdallah\\Desktop\\868320_people_512x512.png"));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);

        try {
            ImageIcon originalIcon = new ImageIcon("src/licensed-image (2).jpeg");
            Image scaledImage = originalIcon.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            postPanel.add(imageLabel);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Image not found: " + imagePath);
            errorLabel.setForeground(Color.RED);
            postPanel.add(errorLabel);
        }

        if (!caption.isEmpty()) {
            JLabel captionLabel = new JLabel(caption);
            captionLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            captionLabel.setForeground(Color.DARK_GRAY);
            postPanel.add(captionLabel);
        }

        return postPanel;
    }

    private JButton createIconButton(String imagePath) {
        JButton button = new JButton();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(50, 50));
        button.setIcon(imageIcon);
        button.setBorderPainted(false);
        return button;
    }

    public static void main(String[] args) {
        new NewsFeedgui();
    }
}
