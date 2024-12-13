package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import BackEnd.*;

//listso7abak ely 3andohom stories
public class StoriesGui {
    ArrayList<User> freindsstories;
    ArrayList<User> freinds;
    JFrame frame2;
    Search search = new Search();
    User user;

    public StoriesGui(String userID, JFrame frame) {
        StoryDatabaseManagement.getInstance().loadStoriesFromFile();
        UserDatabaseManagement.getInstance().loadUsersFromFile();
        user = search.getUser(userID);
        freinds = search.getUsers(user.getFirndesId());
        Storylistoffreinds st = new Storylistoffreinds();
        freindsstories = st.getlist(freinds);

        frame2 = new JFrame();
        frame2.setTitle("Stories");
        frame2.setSize(400, 800);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLayout(new BorderLayout());
        frame2.setVisible(true);
        frame2.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 255, 255));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Story", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);


        JButton addStoryButton = new JButton();
        ImageIcon image = new ImageIcon("src/Image/story (1).png");
        addStoryButton.setContentAreaFilled(false);
        addStoryButton.setFont(new Font("Arial", Font.BOLD, 16));
        addStoryButton.setPreferredSize(new Dimension(50, 50));
        addStoryButton.setIcon(image);
        addStoryButton.setBorderPainted(false);
        addStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStoryGui(user, frame2);
                frame2.setVisible(false);
            }
        });
        topPanel.add(addStoryButton, BorderLayout.EAST);


        JButton refreshButton = new JButton();
        ImageIcon image2 = new ImageIcon("src/Image/refresh.png");
        refreshButton.setContentAreaFilled(false);
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setPreferredSize(new Dimension(30, 30));
        refreshButton.setIcon(image2);
        refreshButton.setBorderPainted(false);

        topPanel.add(refreshButton, BorderLayout.WEST);

        mainPanel.add(topPanel, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoryDatabaseManagement.getInstance().loadStoriesFromFile();
                UserDatabaseManagement.getInstance().loadUsersFromFile();
                StoryHandler storyHandler = new StoryHandler();
                storyHandler.deleteExpiredStories();
                GetFreinds getFreinds2 = new GetFreinds(user.getFirndesId());
                freinds = getFreinds2.get();

                Storylistoffreinds st2 = new Storylistoffreinds();
                freindsstories = st2.getlist(freinds);

                contentPanel.removeAll();
                for (int i = 0; i < freindsstories.size(); i++) {
                    contentPanel.add(createStoryPanel(freindsstories.get(i).getUserId()));
                }

                contentPanel.revalidate();
                contentPanel.repaint();

            }
        });

        for (int i = 0; i < freindsstories.size(); i++) {

            contentPanel.add(createStoryPanel(freindsstories.get(i).getUserId()));
        }

        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setBackground(new Color(240, 255, 255));
        bottomPanel.setPreferredSize(new Dimension(200, 50));


        JButton backButton = new JButton();
        ImageIcon image3 = new ImageIcon("src/Image/return.png");
        backButton.setContentAreaFilled(false);
        backButton.setPreferredSize(new Dimension(50, 50));
        backButton.setIcon(image3);
        backButton.setBorderPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                frame2.dispose();
                frame.setVisible(true);


            }
        });
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        frame2.getContentPane().removeAll();
        frame2.getContentPane().add(mainPanel);
        frame2.revalidate();
        frame2.repaint();


    }

    private JPanel createStoryPanel(String friendID) {
        User currentuser = search.getUser(friendID);
        JPanel storyPanel = new JPanel();
        storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.X_AXIS));
        storyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        storyPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(currentuser.getUserName());

        ImageIcon originalIcon2 = new ImageIcon(currentuser.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);


        /// ///////
        userInfo.setIcon(new ImageIcon(scaledImage2));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        storyPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        storyPanel.add(SPACE);

        userInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame2.setVisible(false);
                personStoriesGui person = new personStoriesGui(friendID, frame2);


            }
        });


        if (currentuser.getStatus().equals("online")) {
            ImageIcon originalIcon = new ImageIcon("src/Image/button (1).png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
            storyPanel.add(statusLabel);
        } else {
            ImageIcon originalIcon = new ImageIcon("src/Image/offlinebutton.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
            storyPanel.add(statusLabel);
        }

        return storyPanel;


    }

    private JButton createIconButton(String imagePath) {   //fakrna nshil diii
        JButton button = new JButton(new ImageIcon(imagePath));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(50, 50));
        return button;
    }


}









