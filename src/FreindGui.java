
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class FreindGui {

    ArrayList<User> friends = null;
    ArrayList<User> freindrequests = null;
    ArrayList<User> friendssugg = null;
    User currnetus = null;
    JPanel contentPanel;
    JPanel contentPanel2;
    JFrame frame;

    FreindGui(User user, JFrame recentFrame) {
        currnetus = user;

        GetFreinds getFreinds = new GetFreinds(user.getFirndesId());
        friends = getFreinds.get();

        GetFreinds getFreinds2 = new GetFreinds(user.getFreindrequestId());
        freindrequests = getFreinds2.get();

        ListoffreindSuggestion getymysugg = new ListoffreindSuggestion();
        friendssugg = getymysugg.getfreindssugg(user, friends);

        System.out.println(friends.size() + "size     ");
        frame = new JFrame("Freind list");
        frame.setSize(400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 255, 255));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Freinds", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        JButton search = new JButton();
        ImageIcon image = new ImageIcon("src/search-interface-symbol.png");
        search.setContentAreaFilled(false);
        search.setFont(new Font("Arial", Font.BOLD, 16));
        search.setPreferredSize(new Dimension(50, 50));
        search.setIcon(image);
        search.setBorderPainted(false);


        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        eastPanel.setOpaque(false);


        JTextField searchField = new JTextField(15);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(150, 30));

        eastPanel.add(searchField);
        eastPanel.add(search);


        topPanel.add(eastPanel, BorderLayout.EAST);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            //searchhh


            {

                String name = searchField.getText();
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(frame, "enter a name please");
                    return;
                }

                searchProcessor search = new searchProcessor(new Allsearch());
                ArrayList<User> allusers = search.searchforusers(name, user);
                System.out.println(allusers.size());

                search = new searchProcessor(new FreindsSearch());
                ArrayList<User> myfreindssearch = search.searchforusers(name, user);
                System.out.println(myfreindssearch.size());

                search = new searchProcessor(new FreindRequestSearch());
                ArrayList<User> allrequests = search.searchforusers(name, user);
                System.out.println(allrequests.size());

                search = new searchProcessor(new sentFreindRequestssearch());
                ArrayList<User> allsent = search.searchforusers(name, user);
                System.out.println(allsent.size());

                if (allusers.isEmpty() && myfreindssearch.isEmpty() && allrequests.isEmpty() && allsent.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "no user found");
                    return;
                } else {
                    frame.setVisible(false);
                    new FreindListaftersearchGUI(frame, user, name);

                }


            }
        });
        JButton refresh = new JButton();
        ImageIcon image2 = new ImageIcon("src/refresh.png");
        refresh.setContentAreaFilled(false);
        refresh.setFont(new Font("Arial", Font.BOLD, 16));
        refresh.setPreferredSize(new Dimension(50, 50));
        refresh.setIcon(image2);
        refresh.setBorderPainted(false);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Refresh();

            }
        });


        topPanel.add(refresh, BorderLayout.WEST);


        frame.add(topPanel, BorderLayout.NORTH);
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));


        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane, BorderLayout.CENTER);


        for (User freind : friends) {
            contentPanel.add(createPostPanel(freind));


        }

        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setPreferredSize(new Dimension(200, 50));


        JButton back = new JButton();
        ImageIcon image3 = new ImageIcon("src/return.png");
        back.setContentAreaFilled(false);

        back.setPreferredSize(new Dimension(50, 50));
        back.setIcon(image3);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                recentFrame.setVisible(true);
            }
        });
//        JButton stories = new JButton();
//        ImageIcon image4 = new ImageIcon("src/block-user.png");
//        stories.setContentAreaFilled(false);
//
//        stories.setPreferredSize(new Dimension(50, 50));
//        stories.setIcon(image4);
//        stories.setBorderPainted(false);
//        stories.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("stories");
//            }
//        });

        bottomPanel.add(back);


        frame.add(bottomPanel, BorderLayout.SOUTH);

        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());

        contentPanel2 = new JPanel();
        contentPanel2.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane2 = new JScrollPane(contentPanel2);
        right.add(scrollPane2, BorderLayout.CENTER);


        JLabel label = new JLabel("  Freinds requests");
        if (freindrequests.size() > 0)
            contentPanel2.add(label);
        populatefreinds(contentPanel2);

        JLabel label2 = new JLabel("  Freinds sugg");

        if (friendssugg != null && friendssugg.size() > 0)
            contentPanel2.add(label2);
        populatefreindsSugg(contentPanel2);
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

    private void populatefreinds(JPanel panel) {
        for (User request : freindrequests) {
            panel.add(createfreindrequestsspanel(request));
        }
    }

    private void populatefreindsSugg(JPanel panel) {
        if (friendssugg == null)
            return;

        for (int i = 0; i < friendssugg.size(); i++) {
            panel.add(createfreindsuggpanel(friendssugg.get(i)));
        }

    }


    private JPanel createfreindrequestsspanel(User user) {  // accept or decline or block
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(user.getUserName());
        userInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] options = {"View profile", "Accept", "Decline"};
                int choice = JOptionPane.showOptionDialog(postPanel, "Please choose an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                switch (choice) {
                    case 0:
                        FriendProfile friendProfile = new FriendProfile(frame, user);

                        break;
                    case 1:
                        AcceptFreindRequest.accept_freind_req(currnetus, user);
                        JOptionPane.showMessageDialog(postPanel, "Accepted");
                        UserDatabaseManagement.getInstance().saveToFile();
                        Refresh();

                        break;
                    case 2:
                        decline_freind_request.decline_freind_req(currnetus, user);
                        JOptionPane.showMessageDialog(postPanel, "decline");
                        UserDatabaseManagement.getInstance().saveToFile();
                        Refresh();
                        break;
                }
            }
        });
        ImageIcon originalIcon2 = new ImageIcon(user.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userInfo.setIcon(new ImageIcon(scaledImage2));

        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        postPanel.add(SPACE);


        return postPanel;
    }


    private JPanel createfreindsuggpanel(User user) { // add we block
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(user.getUserName());
        userInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] options = {"View profile", "Add freind", "Block"};
                int choice = JOptionPane.showOptionDialog(postPanel, "Please choose an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                switch (choice) {
                    case 0:
                        FriendProfile friendProfile = new FriendProfile(frame, user);

                        break;
                    case 1:
                        AddFreind.add_freind(currnetus, user);
                        JOptionPane.showMessageDialog(postPanel, "Freind request sent");
                        UserDatabaseManagement.getInstance().saveToFile();
                        Refresh();

                        break;
                    case 2:
                        Block_freind.block_freind(currnetus, user);
                        JOptionPane.showMessageDialog(postPanel, "Blocked user");
                        UserDatabaseManagement.getInstance().saveToFile();
                        Refresh();
                        break;
                }
            }
        });
        ImageIcon originalIcon2 = new ImageIcon(user.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userInfo.setIcon(new ImageIcon(scaledImage2));

        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        postPanel.add(SPACE);


        return postPanel;
    }


    private JPanel createPostPanel(User user) { //sohaby  remove block
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);


        JLabel userInfo = new JLabel(user.getUserName());

        userInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] options = {"View profile", "Remove", "Block"};
                int choice = JOptionPane.showOptionDialog(postPanel, "Please choose an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                switch (choice) {
                    case 0:
                        FriendProfile friendProfile = new FriendProfile(frame, user);

                        break;
                    case 1:
                        Remove_freind.remove_freind(currnetus, user);
                        JOptionPane.showMessageDialog(postPanel, "Removed Friend");
                        UserDatabaseManagement.getInstance().saveToFile();
                        Refresh();

                        break;
                    case 2:
                        Block_freind.block_freind(currnetus, user);
                        JOptionPane.showMessageDialog(postPanel, "Blocked Friend");
                        UserDatabaseManagement.getInstance().saveToFile();
                        Refresh();
                        break;
                }
            }
        });
        ImageIcon originalIcon2 = new ImageIcon(user.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userInfo.setIcon(new ImageIcon(scaledImage2));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);


        postPanel.add(Box.createRigidArea(new Dimension(6, 0)));


        ImageIcon originalIcon = new ImageIcon("src/delete.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        postPanel.add(imageLabel);

        JButton remove = new JButton();
        // ImageIcon image3=new ImageIcon("C:\\Users\\Abdallah\\Desktop\\profile.png");
        // accept.setContentAreaFilled(false);

        remove.setPreferredSize(new Dimension(50, 50));
        //accept.setIcon(image3);
        //  accept.setBorderPainted(false);
        postPanel.add(Box.createRigidArea(new Dimension(6, 0)));
        postPanel.add(remove);
        postPanel.add(Box.createRigidArea(new Dimension(6, 0)));
        JButton block = new JButton();
        ImageIcon image3 = new ImageIcon("src/block-user.png");
        // accept.setContentAreaFilled(false);

        block.setPreferredSize(new Dimension(20, 20));
        //accept.setIcon(image3);
        block.setBorderPainted(false);
        postPanel.add(Box.createRigidArea(new Dimension(6, 0)));

        postPanel.add(block);


        return postPanel;
    }

    public void Refresh() {
        System.out.println("refresh");

        GetFreinds getFreinds = new GetFreinds(currnetus.getFirndesId());
        friends = getFreinds.get();
        contentPanel.removeAll();
        for (User freind : friends) {
            contentPanel.add(createPostPanel(freind));

        }


        contentPanel.revalidate();
        contentPanel.repaint();

        contentPanel2.removeAll();

        GetFreinds getFreinds2 = new GetFreinds(currnetus.getFreindrequestId());
        freindrequests = getFreinds2.get();

        ListoffreindSuggestion getymysugg = new ListoffreindSuggestion();
        friendssugg = getymysugg.getfreindssugg(currnetus, friends);
        JLabel label = new JLabel("  Freinds requests");
        if (freindrequests.size() > 0)
            contentPanel2.add(label);

        populatefreinds(contentPanel2);

        JLabel label2 = new JLabel("  Freinds sugg");
        if (friendssugg != null && friendssugg.size() > 0)
            contentPanel2.add(label2);
        populatefreindsSugg(contentPanel2);
        contentPanel2.revalidate();
        contentPanel2.repaint();


    }


}