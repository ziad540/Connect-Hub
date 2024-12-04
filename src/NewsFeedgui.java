import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NewsFeedgui {
    private loadPosts loadPosts=new loadPosts();

    NewsFeedgui(){



        JFrame frame = new JFrame("NewsFeed");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240,255,255));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Posts", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);


        JButton addPostButton = new JButton();
        ImageIcon image=new ImageIcon("src/new-post (2).png");
        addPostButton.setContentAreaFilled(false);
        addPostButton.setFont(new Font("Arial", Font.BOLD, 16));
        addPostButton.setPreferredSize(new Dimension(50,50));
        addPostButton.setIcon(image);
        addPostButton.setBorderPainted(false);
        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new post");



            }
        });







        topPanel.add(addPostButton, BorderLayout.EAST);



        JButton refresh = new JButton();
        ImageIcon image2=new ImageIcon("src/refresh.png");
        refresh.setContentAreaFilled(false);
        refresh.setFont(new Font("Arial", Font.BOLD, 16));
        refresh.setPreferredSize(new Dimension(50,50));
        refresh.setIcon(image2);
        refresh.setBorderPainted(false);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("refresh");



            }
        });



        topPanel.add(refresh, BorderLayout.WEST);


        frame.add(topPanel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));



        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        loadPosts.showPosts(contentPanel,NewsFeedgui.this);
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setBackground(new Color(240,255,255));
        bottomPanel.setPreferredSize(new Dimension(200, 50));
        JButton profile = new JButton();
        ImageIcon image3=new ImageIcon("src/user.png");
        profile.setContentAreaFilled(false);
        profile.setPreferredSize(new Dimension(50,50));
        profile.setIcon(image3);
        profile.setBorderPainted(false);
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Profile(NewsFeedgui.this);
             frame.setVisible(false);
            }
        });




        JButton stories = new JButton();
        ImageIcon image4=new ImageIcon("src/story.png");
        stories.setContentAreaFilled(false);

        stories.setPreferredSize(new Dimension(50,50));
        stories.setIcon(image4);
        stories.setBorderPainted(false);
        stories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stories");
            }
        });

        JButton back = new JButton();
        ImageIcon image5=new ImageIcon("src/logout.png");
        back.setContentAreaFilled(false);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setPreferredSize(new Dimension(50,50));
        back.setIcon(image5);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back post");



            }
        });


        JButton freinds = new JButton();
        ImageIcon image6=new ImageIcon("src/team.png");
        freinds.setContentAreaFilled(false);
        freinds.setFont(new Font("Arial", Font.BOLD, 16));
        freinds.setPreferredSize(new Dimension(50,50));
        freinds.setIcon(image6);
        freinds.setBorderPainted(false);
        freinds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back post");



            }
        });

        
        bottomPanel.add(profile);
        bottomPanel.add(stories);
        bottomPanel.add(freinds); bottomPanel.add(back);


        frame.add(bottomPanel, BorderLayout.SOUTH);

        FreindGui gui = new FreindGui();
        frame.add(gui.createFriendPanel(),BorderLayout.EAST);

        frame.setVisible(true);
    }

    public JPanel createPostPanel(String name, String date, String caption, String imagePath) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(name + " - " + date);
        userInfo.setIcon(new ImageIcon("C:\\Users\\Abdallah\\Desktop\\868320_people_512x512.png"));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);

        try {
            ImageIcon originalIcon = new ImageIcon(imagePath);
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

}





