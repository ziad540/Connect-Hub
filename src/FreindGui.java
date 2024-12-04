import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FreindGui {

    public JPanel createFriendPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


//        JPanel topPanel = new JPanel(new BorderLayout());
//        topPanel.setBackground(new Color(102, 205, 170));
//        topPanel.setPreferredSize(new Dimension(400, 60));
//        JLabel titleLabel = new JLabel("Friends", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//        topPanel.add(titleLabel, BorderLayout.CENTER);

//        JButton addPostButton = new JButton();
//        ImageIcon image = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\avbfe351f753bcaa24ae2.png");
//        addPostButton.setContentAreaFilled(false);
//        addPostButton.setFont(new Font("Arial", Font.BOLD, 16));
//        addPostButton.setPreferredSize(new Dimension(50, 50));
//        addPostButton.setIcon(image);
//        addPostButton.setBorderPainted(false);
//          addPostButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("new post");
//
//
//
//            }
//        });
//        topPanel.add(addPostButton, BorderLayout.EAST);

//
//        JButton refreshButton = new JButton();
//        ImageIcon image2 = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\refresh.png");
//        refreshButton.setContentAreaFilled(false);
//        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
//        refreshButton.setPreferredSize(new Dimension(50, 50));
//        refreshButton.setIcon(image2);
//        refreshButton.setBorderPainted(false);
//       refresh.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("refresh");
//
//
//
//            }
//        });
//        topPanel.add(refreshButton, BorderLayout.WEST);
//
//        mainPanel.add(topPanel, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        Object[][] posts = {
                {"Dodo Yasser", "20/5", "of", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "zizooo"},
                {"Abdallah Yasser", "20/6", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "shika3333"},
                {"Nour Azab", "20/8", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Zamalek"},
                {"Ziad", "20/3", "off", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Ana Zeh2t"},
                {"Jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Goallll Oba"},
                {"Jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Goallll Oba"},
                {"Jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Goallll Oba"},
                {"Nour Azab", "20/8", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Zamalek"},
                {"Ziad", "20/3", "off", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Ana Zeh2t"},
                {"Jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Goallll Oba"},
                {"Jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Goallll Oba"},
                {"Jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "Goallll Oba"},


        };


        for (Object[] post : posts) {
            contentPanel.add(createPostPanel((String) post[0], (String) post[1], (String) post[2], (String) post[4]));
        }

//        // Bottom panel
//        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
//        bottomPanel.setPreferredSize(new Dimension(200, 50));
//
//        // Profile Button
//        JButton profileButton = new JButton();
//        ImageIcon image3 = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\profile.png");
//        profileButton.setContentAreaFilled(false);
//        profileButton.setPreferredSize(new Dimension(50, 50));
//        profileButton.setIcon(image3);
//        profileButton.setBorderPainted(false);
//         profile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("profile");
//
//
//
//            }
//        });
//        bottomPanel.add(profileButton);
//
//        // Stories Button
//        JButton storiesButton = new JButton();
//        ImageIcon image4 = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\stories.png");
//        storiesButton.setContentAreaFilled(false);
//        storiesButton.setPreferredSize(new Dimension(50, 50));
//        storiesButton.setIcon(image4);
//        storiesButton.setBorderPainted(false);
//        storiesButton.addActionListener(e -> System.out.println("Stories"));
//        bottomPanel.add(storiesButton);

        // Placeholder Buttons
//        bottomPanel.add(new JButton());
//        bottomPanel.add(new JButton());
//
//        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private static JPanel createPostPanel(String name, String date, String status, String caption) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(name);
        userInfo.setIcon(new ImageIcon("C:\\Users\\Abdallah\\Desktop\\868320_people_512x512.png"));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        postPanel.add(SPACE);



        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\online.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
        postPanel.add(statusLabel);

        return postPanel;
    }
}