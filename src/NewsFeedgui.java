import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NewsFeedgui {

    NewsFeedgui(){



        JFrame frame = new JFrame("NewsFeed");
        frame.setSize(400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(102,205,170));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Posts", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);


        JButton addPostButton = new JButton();
        ImageIcon image=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\avbfe351f753bcaa24ae2.png");
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
        ImageIcon image2=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\refresh.png");
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


        Object[][] posts = {
                {"Dodo yasser", "20/5", "of", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "zizooo"},
                {"abdallah yasser", "20/6", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "shika3333"},
                {"nour azab", "20/8", "on", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "zamalek"},
                {"ziad", "20/3", "off", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "ana zeh2t"},
                {"jobeef", "20/3", "online", "C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg", "goallll oba"}
        };


        for (Object[] post : posts) {
            contentPanel.add(createPostPanel(
                    (String) post[0], // elesm
                    (String) post[1], // tari5
                    (String) post[2], // on wla off
                    (String) post[4]  // Caption
            ));
        }

        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setPreferredSize(new Dimension(200, 50));





        JButton profile = new JButton();
        ImageIcon image3=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\profile.png");
        profile.setContentAreaFilled(false);

        profile.setPreferredSize(new Dimension(50,50));
        profile.setIcon(image3);
        profile.setBorderPainted(false);
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("profile");



            }
        });


        // "C:\Users\Abdallah\Desktop\stories.png"


        JButton stories = new JButton();
        ImageIcon image4=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\stories.png");
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
        ImageIcon image5=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\back.png");
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
        ImageIcon image6=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\freinds.png");
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


       //
        bottomPanel.add(profile);
        bottomPanel.add(stories);
        bottomPanel.add(freinds); bottomPanel.add(back);


        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setVisible(true);
    }


    private static JPanel createPostPanel(String name, String date, String imagePath, String caption) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);


        JLabel userInfo = new JLabel(name + " - " + date);
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);


        try {
            ImageIcon originalIcon = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\licensed-image (2).jpeg");
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









        JPanel actionPanel = new JPanel();
        actionPanel.setPreferredSize(new Dimension(30,20));

        postPanel.add(actionPanel);

        return postPanel;
    }
}





