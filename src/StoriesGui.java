import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoriesGui {

    public StoriesGui(User user, JFrame frame) {
        JFrame frame2 = new JFrame();
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
        ImageIcon image = new ImageIcon("src/story (1).png");
        addStoryButton.setContentAreaFilled(false);
        addStoryButton.setFont(new Font("Arial", Font.BOLD, 16));
        addStoryButton.setPreferredSize(new Dimension(50, 50));
        addStoryButton.setIcon(image);
        addStoryButton.setBorderPainted(false);
        addStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStoryGui(user,frame2);
                frame2.dispose();
            }
        });
        topPanel.add(addStoryButton, BorderLayout.EAST);


        JButton refreshButton = new JButton();
        ImageIcon image2 = new ImageIcon("src/refresh.png");
        refreshButton.setContentAreaFilled(false);
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setPreferredSize(new Dimension(30, 30));
        refreshButton.setIcon(image2);
        refreshButton.setBorderPainted(false);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("refresh");
            }
        });
        topPanel.add(refreshButton, BorderLayout.WEST);

        mainPanel.add(topPanel, BorderLayout.NORTH);


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
            contentPanel.add(createStoryPanel((String) post[0], (String) post[1], (String) post[2], (String) post[4]));
        }

        //Bottom panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setBackground(new Color(240, 255, 255));
        bottomPanel.setPreferredSize(new Dimension(200, 50));

        // backButton
        JButton backButton = new JButton();
        ImageIcon image3 = new ImageIcon("src/return.png");
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


        frame2.getContentPane().removeAll(); // Remove current content
        frame2.getContentPane().add(mainPanel); // Add the new panel
        frame2.revalidate();
        frame2.repaint();


    }

    private static JPanel createStoryPanel(String name, String date, String status, String caption) {
        JPanel storyPanel = new JPanel();
        storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.X_AXIS));
        storyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        storyPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(name);
        userInfo.setIcon(new ImageIcon("C:\\Users\\Abdallah\\Desktop\\868320_people_512x512.png"));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        storyPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        storyPanel.add(SPACE);


        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\online.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
        storyPanel.add(statusLabel);

        return storyPanel;


    }

    private JButton createIconButton(String imagePath) {
        JButton button = new JButton(new ImageIcon(imagePath));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(50, 50));
        return button;
    }


}









