
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FreindGui {

    FreindGui() {
        JFrame frame = new JFrame("Freind list");
        frame.setSize(400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(102, 205, 170));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Freinds", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        JButton addPostButton = new JButton();
        ImageIcon image = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\avbfe351f753bcaa24ae2.png");
        addPostButton.setContentAreaFilled(false);
        addPostButton.setFont(new Font("Arial", Font.BOLD, 16));
        addPostButton.setPreferredSize(new Dimension(50, 50));
        addPostButton.setIcon(image);
        addPostButton.setBorderPainted(false);
        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new post");


            }
        });


        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align to the right
        eastPanel.setOpaque(false); // Make it blend with the parent panel

// Create the search field
        JTextField searchField = new JTextField(15); // Adjust column size as needed
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(150, 30)); // Adjust dimensions if needed

// Add the search field and button to the east panel
        eastPanel.add(searchField);
        eastPanel.add(addPostButton); // The button you've already created

// Add the east panel to the topPanel
        topPanel.add(eastPanel, BorderLayout.EAST);


        JButton refresh = new JButton();
        ImageIcon image2 = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\refresh.png");
        refresh.setContentAreaFilled(false);
        refresh.setFont(new Font("Arial", Font.BOLD, 16));
        refresh.setPreferredSize(new Dimension(50, 50));
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
        contentPanel.setLayout(new GridLayout(0, 1));


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
        ImageIcon image3 = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\profile.png");
        profile.setContentAreaFilled(false);

        profile.setPreferredSize(new Dimension(50, 50));
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
        ImageIcon image4 = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\stories.png");
        stories.setContentAreaFilled(false);

        stories.setPreferredSize(new Dimension(50, 50));
        stories.setIcon(image4);
        stories.setBorderPainted(false);
        stories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stories");


            }
        });


        bottomPanel.add(profile);
        bottomPanel.add(stories);
        bottomPanel.add(new JButton());
        bottomPanel.add(new JButton());

        frame.add(bottomPanel, BorderLayout.SOUTH);

        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());

        JPanel contentPanel2 = new JPanel();
        contentPanel2.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane2 = new JScrollPane(contentPanel2);
        right.add(scrollPane2, BorderLayout.CENTER);
        Object[][] freinds = {
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

        JLabel label = new JLabel("  Freinds requests");
        contentPanel2.add(label);
        populatefreinds(contentPanel2, freinds);
        JLabel label2 = new JLabel("  Freinds requests2");
        contentPanel2.add(label2);
        frame.add(right, BorderLayout.EAST);


//        JPanel right=new JPanel();
//
//        right.setLayout(new BorderLayout());
//
//        JPanel contentPanel2 = new JPanel();
//        contentPanel2.setLayout(new GridLayout(0, 1));
//        JScrollPane scrollPane2 = new JScrollPane(contentPanel2);
//
//        right.add(scrollPane2, BorderLayout.EAST);
//        JTextField search=new JTextField();
//        search.setPreferredSize(new Dimension(20, 30));
//        right.add(search,BorderLayout.CENTER);
//        frame.add(right,BorderLayout.EAST);


        frame.setVisible(true);
    }

    private void populatefreinds(JPanel panel, Object[][] posts) {
        for (Object[] post : posts) {
            panel.add(createfreindspanel((String) post[0], (String) post[1], (String) post[2], (String) post[4]));
        }
    }

    private static JPanel createfreindspanel(String name, String date, String status, String caption) {
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


    private static JPanel createPostPanel(String name, String date, String imagePath, String caption) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);


        JLabel userInfo = new JLabel(name);
        userInfo.setIcon(new ImageIcon("C:\\Users\\Abdallah\\Desktop\\868320_people_512x512.png"));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);


        JLabel space = new JLabel("     ");

        postPanel.add(space);


        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Abdallah\\Desktop\\online.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(imageLabel);
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(space);
        postPanel.add(space);
        JButton remove = new JButton();
        // ImageIcon image3=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\profile.png");
        // accept.setContentAreaFilled(false);

        remove.setPreferredSize(new Dimension(50, 50));
        //accept.setIcon(image3);
        //  accept.setBorderPainted(false);
        postPanel.add(space);
        postPanel.add(remove);

        JButton block = new JButton();
        // ImageIcon image3=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\profile.png");
        // accept.setContentAreaFilled(false);

        block.setPreferredSize(new Dimension(50, 50));
        //accept.setIcon(image3);
        //  accept.setBorderPainted(false);
        postPanel.add(space);
        postPanel.add(block);


        return postPanel;
    }


}