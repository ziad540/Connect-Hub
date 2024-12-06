import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class personStoriesGui {


    JPanel contentPanel;
    JPanel right;
    private JFrame frame;
    PostDatabaseManagement postDatabaseManagement = PostDatabaseManagement.getInstance();
    UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();
    ArrayList<Story> stories;
    User current=null;
    JFrame recentframe=null;


/////////////storihat sahbak
///
///  EL USER ELY HENA D2A SHABAK
    personStoriesGui(User user,JFrame recentf) {
recentframe=recentf;
        Getuserstories getstories=new Getuserstories();
       stories= getstories.getuserstories(user);

        frame = new JFrame("NewsFeed");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 255, 255));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Posts", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);


        JButton addPostButton = new JButton();
        ImageIcon image = new ImageIcon("src/new-post (2).png");
        addPostButton.setContentAreaFilled(false);

        addPostButton.setFont(new Font("Arial", Font.BOLD, 16));

        addPostButton.setPreferredSize(new Dimension(50, 50));
        addPostButton.setIcon(image);
        addPostButton.setBorderPainted(false);
        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Addpostgui(user, frame);
                frame.setVisible(false);


            }
        });
        System.out.println(stories.size());
        //topPanel.add(addPostButton, BorderLayout.EAST);
        JButton refresh = new JButton();
        ImageIcon image2 = new ImageIcon("src/refresh.png");
        refresh.setContentAreaFilled(false);
        refresh.setFont(new Font("Arial", Font.BOLD, 16));
        refresh.setPreferredSize(new Dimension(50, 50));
        refresh.setIcon(image2);
        refresh.setBorderPainted(false);


        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        loadStories.showPosts(contentPanel, personStoriesGui.this, userDatabaseManagement, stories);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

//         right=new JPanel();
//        FreindGui freind =new FreindGui();
//        right=freind.createFriendPanel();
//        frame.add(right,BorderLayout.EAST);







        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("refresh");

                Getuserstories getstories2=new Getuserstories();
                stories= getstories2.getuserstories(user);



                contentPanel.removeAll();

                loadStories.showPosts(contentPanel, personStoriesGui.this, userDatabaseManagement, stories);

                contentPanel.revalidate();
                contentPanel.repaint();



            }
        });

        topPanel.add(refresh, BorderLayout.WEST);

        frame.add(topPanel, BorderLayout.NORTH);




        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setBackground(new Color(240, 255, 255));
        bottomPanel.setPreferredSize(new Dimension(200, 50));

        JButton profile = createIconButton("src/user.png");

        ImageIcon image3 = new ImageIcon("src/user.png");
        profile.setContentAreaFilled(false);


        profile.setPreferredSize(new Dimension(50, 50));
        profile.setIcon(image3);
        profile.setBorderPainted(false);
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Profile(frame, user);

                frame.setVisible(false);
            }
        });


        JButton stories = new JButton();
        ImageIcon image4 = new ImageIcon("src/story.png");
        stories.setContentAreaFilled(false);

        stories.setPreferredSize(new Dimension(50, 50));
        stories.setIcon(image4);
        stories.setBorderPainted(false);
        stories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //             JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(stories); // Get the parent frame
//                if (frame != null) {
//                    frame.getContentPane().removeAll(); // Remove current content
//                    frame.getContentPane().add(new StoriesGui(user,frame).createFriendPanel()); // Add the new panel
//                    frame.revalidate();
//                    frame.repaint();
//                } else {
//                    System.err.println("Frame is null");
//                }
                new StoriesGui(user, frame);
                frame.setVisible(false);

            }
        });


        JButton back = new JButton();
        ImageIcon image5 = new ImageIcon("src/logout.png");
        back.setContentAreaFilled(false);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setPreferredSize(new Dimension(50, 50));
        back.setIcon(image5);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)

            {
                frame.dispose();

                recentf.setVisible(true);


            }
        });





//        bottomPanel.add(profile);
//        bottomPanel.add(stories);
//
//        bottomPanel.add(freind);
        bottomPanel.add(back);


        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setVisible(true);
    }


//    private void populatefreinds(JPanel panel, ArrayList<User> users) {
//        for (User user : users) {
//            System.out.println(user.getUserName());
//            panel.add( createfreindspanel(user));
//        }
//    }

    public JPanel createPostPanel(String name, String date, String caption, String imagePath,String profileimage) {

        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(name + " - " + date);

        ImageIcon originalIcon2 = new ImageIcon(profileimage);
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userInfo.setIcon(new ImageIcon(scaledImage2));
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

    private JButton createIconButton(String imagePath) {
        JButton button = new JButton();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(50, 50));
        button.setIcon(imageIcon);
        button.setBorderPainted(false);
        return button;
    }

//    private static JPanel createfreindspanel(User friend) {
//        JPanel postPanel = new JPanel();
//        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
//        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        postPanel.setBackground(Color.WHITE);
//
//        JLabel userInfo = new JLabel(friend.getUserName());
//        userInfo.setIcon(new ImageIcon(friend.getProfileInformation().getProfilePicPath()));
//        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
//        postPanel.add(userInfo);
//        JLabel SPACE = new JLabel("  ");
//        postPanel.add(SPACE);
//
//        if (friend.getStatus().equals("online")) {
//            ImageIcon originalIcon = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\online.png");
//
//            Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
//
//            JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
//            postPanel.add(statusLabel);
//        }
//        // offline
//        return postPanel;
//    }

//    private JPanel createfreindspanel(User friend) {
//        JPanel postPanel = new JPanel();
//        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
//        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        postPanel.setBackground(Color.WHITE);
//        JLabel userInfo = new JLabel(friend.getUserName());
//        userInfo.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                String[] options = {"View profile", "Remove", "Block"};
//                int choice = JOptionPane.showOptionDialog(postPanel, "Please choose an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//                switch (choice) {
//                    case 0:
//                        FriendProfile friendProfile = new FriendProfile(frame, friend);
//                        break;
//                    case 1:
//                        JOptionPane.showMessageDialog(postPanel, "Removed Friend");
//                        break;
//                    case 2:
//                        JOptionPane.showMessageDialog(postPanel, "Blocked Friend");
//                        break;
//                }
//            }
//        });
//
//        ImageIcon originalIcon2 = new ImageIcon(friend.getProfileInformation().getProfilePicPath());
//        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//
//
//        /// ///////
//        userInfo.setIcon(new ImageIcon(scaledImage2));
//        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
//        postPanel.add(userInfo);
//        JLabel SPACE = new JLabel("  ");
//        postPanel.add(SPACE);
//
//
//
//
//        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\online.png");
//        Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
//        JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
//        postPanel.add(statusLabel);
//
//        return postPanel;
//    }

    public JFrame getFrame() {


        return frame;
    }
}

