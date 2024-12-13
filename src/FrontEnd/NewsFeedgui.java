package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import BackEnd.*;

public class NewsFeedgui {


    JPanel contentPanel;
    JPanel contentPanel2;
    JPanel right;
    private JFrame frame;
    PostDatabaseManagement postDatabaseManagement = PostDatabaseManagement.getInstance();
    UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();
    ArrayList<User> friends;
    ArrayList<Post> posts;
    ArrayList<Groups> groups;

    User currentuser = new User();
    String currentUserId = null;
    Search search = new Search();

    NewsFeedgui(String userId) {
        currentUserId = userId;
        currentuser = search.getUser(userId);
        friends = search.getUsers(currentuser.getFirndesId());//(1)

        GetPosts getPosts = new GetPosts();
        posts = getPosts.data(friends);

       GroupOperation groupOperation=new GroupOperation();
        groups= groupOperation.getGroups(currentuser.getGroupId());








        frame = new JFrame("NewsFeed");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        JLabel backgroundLabel = new JLabel(new ImageIcon("C:\\Users\\Abdallah\\Desktop\\conncect-hub.jpg"));
        backgroundLabel.setBounds(-200, -200, 2100, 1400);



        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 255, 255));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Posts", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JButton addPostButton = new JButton();
        ImageIcon image = new ImageIcon("src/Image/new-post (2).png");
        addPostButton.setContentAreaFilled(false);

        addPostButton.setFont(new Font("Arial", Font.BOLD, 16));

        addPostButton.setPreferredSize(new Dimension(50, 50));
        addPostButton.setIcon(image);
        addPostButton.setBorderPainted(false);
        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Addpostgui(currentUserId,frame);//(5)
                frame.setVisible(false);


            }
        });

        topPanel.add(addPostButton, BorderLayout.EAST);
        JButton refresh = new JButton();
        ImageIcon image2 = new ImageIcon("src/Image/refresh.png");
        refresh.setContentAreaFilled(false);
        refresh.setFont(new Font("Arial", Font.BOLD, 16));
        refresh.setPreferredSize(new Dimension(50, 50));
        refresh.setIcon(image2);
        refresh.setBorderPainted(false);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        loadPosts.showPosts(contentPanel, NewsFeedgui.this, userDatabaseManagement, posts);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane, BorderLayout.CENTER);




        right = new JPanel();
        right.setLayout(new BorderLayout());

        contentPanel2 = new JPanel();
        contentPanel2.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane2 = new JScrollPane(contentPanel2);
        right.add(scrollPane2, BorderLayout.CENTER);


        populatefreinds(contentPanel2, friends);

//      JPanel  contentPanelGroups = new JPanel();
//        contentPanelGroups.setLayout(new BoxLayout(contentPanelGroups, BoxLayout.Y_AXIS));
//        JScrollPane scrollPaneGroups = new JScrollPane(contentPanelGroups);
//        right.add(scrollPaneGroups, BorderLayout.SOUTH);
//        populateGroups(contentPanelGroups, groups);

        frame.add(right, BorderLayout.EAST);


        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Refresh();

            }
        });

        topPanel.add(refresh, BorderLayout.WEST);

        frame.add(topPanel, BorderLayout.NORTH);


        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setBackground(new Color(240, 255, 255));
        bottomPanel.setPreferredSize(new Dimension(200, 50));

        JButton profile = createIconButton("src/Image/user.png");

        ImageIcon image3 = new ImageIcon("src/Image/user.png");
        profile.setContentAreaFilled(false);


        profile.setPreferredSize(new Dimension(50, 50));
        profile.setIcon(image3);
        profile.setBorderPainted(false);
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Profile(frame, currentUserId);//(2)
                frame.setVisible(false);
            }
        });


        JButton stories = new JButton();
        ImageIcon image4 = new ImageIcon("src/Image/story.png");
        stories.setContentAreaFilled(false);

        stories.setPreferredSize(new Dimension(50, 50));
        stories.setIcon(image4);
        stories.setBorderPainted(false);
        stories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new StoriesGui(currentUserId, frame);//(3)
                frame.setVisible(false);

            }
        });


        JButton logoutButton = new JButton();
        ImageIcon image5 = new ImageIcon("src/Image/logout.png");
        logoutButton.setContentAreaFilled(false);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setPreferredSize(new Dimension(50, 50));
        logoutButton.setIcon(image5);
        logoutButton.setBorderPainted(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                System.out.println(currentuser+"    4");
                userDatabaseManagement.loadUsersFromFile();
                search.getUser(userId).setStatus("offline");
                System.out.println(currentuser+"    5");
                userDatabaseManagement.saveToFile();
                new StartWindow();
                frame.dispose();

            }
        });


        JButton freind = new JButton();
        ImageIcon image6 = new ImageIcon("src/Image/team.png");
        freind.setContentAreaFilled(false);
        freind.setFont(new Font("Arial", Font.BOLD, 16));
        freind.setPreferredSize(new Dimension(50, 50));
        freind.setIcon(image6);
        freind.setBorderPainted(false);
        freind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FreindGui(currentUserId,frame);//(4)
                frame.setVisible(false);

            }
        });

        JButton notificaions = new JButton();
       ImageIcon image7 = new ImageIcon("src/Image/notificationspng.png");
        notificaions.setContentAreaFilled(false);
        notificaions.setFont(new Font("Arial", Font.BOLD, 16));
        notificaions.setPreferredSize(new Dimension(50, 50));
        notificaions.setIcon(image7);
        notificaions.setBorderPainted(false);
        notificaions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new notificationsGUI(currentUserId,frame);//(4)
                frame.setVisible(false);

            }
        });




        JButton groupsButton = new JButton();
        ImageIcon imageGroups = new ImageIcon("src/Image/group.png");
        groupsButton.setContentAreaFilled(false);
        groupsButton.setPreferredSize(new Dimension(50, 50));
        groupsButton.setIcon(imageGroups);
        groupsButton.setBorderPainted(false);

        groupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new GroupsGui(currentuser.getUserId(),frame);
                frame.setVisible(false);
            }
        });


        bottomPanel.add(profile);
        bottomPanel.add(stories);
        bottomPanel.add(groupsButton);
        bottomPanel.add(freind);
          bottomPanel.add(notificaions);

        bottomPanel.add(logoutButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setVisible(true);

//        contentPanel.revalidate();
//        contentPanel.repaint();
//        contentPanel2.revalidate();
//        contentPanel2.repaint();

    }


    private void populatefreinds(JPanel panel, ArrayList<User> users) {
        for (User user : users) {

            panel.add(createfreindspanel(user));
        }
    }

//    private void populateGroups(JPanel panel, ArrayList<Groups> groups) {
//        for (Groups group : groups) {
//
//            panel.add(createGroupPanel(group));
//        }
//    }


    public static JPanel createPostPanel(String name, String date, String caption, String imagePath, String profileimage) {

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



    private JPanel createfreindspanel(User friend) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);
        JLabel userInfo = new JLabel(friend.getUserName());
        userInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] options = {"View profile", "Remove", "Block"};
                int choice = JOptionPane.showOptionDialog(postPanel, "Please choose an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                switch (choice) {
                    case 0:
                        FriendProfile friendProfile = new FriendProfile(frame, friend);
                        break;
                    case 1:
                       UserRelationsManager.remove_freind(currentuser, friend);
                        JOptionPane.showMessageDialog(postPanel, "Removed Friend");
                        UserDatabaseManagement.getInstance().saveToFile();
                        Refresh();
                        break;
                    case 2:
                      UserRelationsManager.block_freind(currentuser, friend);
                        JOptionPane.showMessageDialog(postPanel, "Blocked Friend");
                        UserDatabaseManagement.getInstance().saveToFile();
                        Refresh();
                        break;
                }
            }
        });

        ImageIcon originalIcon2 = new ImageIcon(friend.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);



        userInfo.setIcon(new ImageIcon(scaledImage2));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        postPanel.add(SPACE);




if (friend.getStatus().equals("online"))
{ImageIcon originalIcon = new ImageIcon("src/Image/button (1).png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
        postPanel.add(statusLabel);}

else
{
    ImageIcon originalIcon = new ImageIcon("src/Image/offlinebutton.png");
    Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
    JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
    postPanel.add(statusLabel);
}


        return postPanel;
    }

    public void Refresh() {
        System.out.println(currentuser+"    3");
        userDatabaseManagement.loadUsersFromFile();
        StoryHandler storyHandler = new StoryHandler();
        storyHandler.deleteExpiredStories();
        userDatabaseManagement.loadUsersFromFile();
        postDatabaseManagement.loadPostsFromFile();
        currentuser = search.getUser(currentUserId);
        System.out.println(currentuser+"    4");
        friends = search.getUsers(currentuser.getFirndesId());//(1)
        GetPosts getPosts2 = new GetPosts();
        posts = getPosts2.data(friends);



        contentPanel.removeAll();

        loadPosts.showPosts(contentPanel, NewsFeedgui.this, userDatabaseManagement, posts);
        contentPanel2.removeAll();



        populatefreinds(contentPanel2, friends);
        contentPanel.revalidate();
        contentPanel.repaint();
        contentPanel2.revalidate();
        contentPanel2.repaint();
        frame.invalidate();
        frame.validate();
        frame.repaint();


    }


    public JFrame getFrame()

    {


        return frame;
    }
}
