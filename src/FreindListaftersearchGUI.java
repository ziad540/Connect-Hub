
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class FreindListaftersearchGUI {
    ArrayList<User> allusers;
    ArrayList<User> myfreindssearch;
    ArrayList<User> allrequests;
    ArrayList<User> allsent;
    User currnetus = null;
    JFrame frame;
    JPanel contentPanel;
    String name;

    JFrame recentframe;

    FreindListaftersearchGUI(JFrame recentframe, User user, String name) {
        this.recentframe = recentframe;
        this.name = name;
        currnetus = user;
        searchProcessor search = new searchProcessor(new Allsearch());
        allusers = search.searchforusers(name, user);
        System.out.println(allusers.size());

        search = new searchProcessor(new FreindsSearch());
        myfreindssearch = search.searchforusers(name, user);
        System.out.println(myfreindssearch.size());

        search = new searchProcessor(new FreindRequestSearch());
        allrequests = search.searchforusers(name, user);
        System.out.println(allrequests.size());

        search = new searchProcessor(new sentFreindRequestssearch());
        allsent = search.searchforusers(name, user);
        System.out.println(allsent.size());


        frame = new JFrame("Freind list");
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
        ImageIcon image = new ImageIcon("src/button (1).png");
        addPostButton.setContentAreaFilled(false);
        addPostButton.setFont(new Font("Arial", Font.BOLD, 16));
        addPostButton.setPreferredSize(new Dimension(50, 50));
        addPostButton.setIcon(image);
        addPostButton.setBorderPainted(false);
        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new post");  //


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


        JPanel panel = new JPanel();
        panel.setLayout(null);


        if (myfreindssearch.size() > 0) {
            JLabel sep = new JLabel("Freinds");

            panel.setBackground(new Color(240, 255, 255));
            sep.setFont(new Font("Arial", Font.BOLD, 20));


            sep.setBounds(20, 45, 310, 80);

            panel.setBounds(0, 0, 200, 600);
            panel.add(sep);

            contentPanel.add(panel);
        }


        populatefreinds(contentPanel);
        if (allrequests.size() > 0) {
            JLabel sep = new JLabel("Freind Requests");
            contentPanel.add(sep);
        }
        populatefreindrequests(contentPanel);
        if (allsent.size() > 0) {
            JLabel sep = new JLabel("sent freind rerquests");
            contentPanel.add(sep);
        }
        populatesentfreindrequests(contentPanel);
        if (allusers.size() > 0) {
            JLabel sep = new JLabel("ALL users");


            contentPanel.add(panel);
        }
        poulateAllusers(contentPanel);


        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.setPreferredSize(new Dimension(200, 50));


        JButton back = new JButton();
        ImageIcon image3 = new ImageIcon("src/return.pngg");
        back.setContentAreaFilled(false);

        back.setPreferredSize(new Dimension(50, 50));
        back.setIcon(image3);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back");

                frame.dispose();
                recentframe.setVisible(true);


            }
        });


        bottomPanel.add(back);
        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setVisible(true);
    }


    private void populatefreinds(JPanel panel) {
        for (User freind : myfreindssearch) {
            panel.add(createfreindspanel(freind));
        }
    }

    private void populatefreindrequests(JPanel panel) {
        for (User freind : allrequests) {
            panel.add(createfreindrequestsspanel(freind));
        }
    }


    private void populatesentfreindrequests(JPanel panel) {
        for (User freind : allsent) {
            panel.add(createGoingtfreindrequestsoPanel(freind));
        }


    }

    private void poulateAllusers(JPanel panel) {

        for (User freind : allusers) {
            panel.add(createALLusersPanel(freind));
        }

    }


    private JPanel createfreindspanel(User user) {  //sohaby view remove block
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(user.getUserName());


        ImageIcon originalIcon2 = new ImageIcon(user.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userInfo.setIcon(new ImageIcon(scaledImage2));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        postPanel.add(SPACE);


// hewar online wla
        if  (user.getStatus().equals("online"))
        {ImageIcon originalIcon = new ImageIcon("src/button (1).png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
            postPanel.add(statusLabel);}

else
        {
            ImageIcon originalIcon = new ImageIcon("src/offlinebutton.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            JLabel statusLabel = new JLabel(new ImageIcon(scaledImage));
            postPanel.add(statusLabel);
        }
        JButton view = new JButton("view");
        view.setContentAreaFilled(false);
        view.setFont(new Font("Arial", Font.BOLD, 16));
        view.setPreferredSize(new Dimension(50, 50));
        //  accept.setIcon(image2);
        view.setBorderPainted(false);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FriendProfile friendProfile = new FriendProfile(frame, user);

            }
        });

        postPanel.add(view);

        JButton remove = new JButton("remove");

        remove.setContentAreaFilled(false);
        remove.setFont(new Font("Arial", Font.BOLD, 16));
        remove.setPreferredSize(new Dimension(50, 50));
        remove.setBorderPainted(false);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserRelationsManager.remove_freind(currnetus, user);
                JOptionPane.showMessageDialog(postPanel, "Removed Friend");
                UserDatabaseManagement.getInstance().saveToFile();
                Refresh();
            }
        });

        postPanel.add(remove);

        JButton block = new JButton("block");
        block.setContentAreaFilled(false);
        block.setFont(new Font("Arial", Font.BOLD, 16));
        block.setPreferredSize(new Dimension(50, 50));
        //  accept.setIcon(image2);
        block.setBorderPainted(false);
        block.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRelationsManager.block_freind(currnetus, user);
                JOptionPane.showMessageDialog(postPanel, "Blocked Friend");
                UserDatabaseManagement.getInstance().saveToFile();
                Refresh();

            }
        });

        postPanel.add(block);


        return postPanel;
    }


    private JPanel createfreindrequestsspanel(User user) {  // accept or decline or view
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(user.getUserName());

        ImageIcon originalIcon2 = new ImageIcon(user.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userInfo.setIcon(new ImageIcon(scaledImage2));

        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        postPanel.add(SPACE);


        JButton view = new JButton("view");
        view.setContentAreaFilled(false);
        view.setFont(new Font("Arial", Font.BOLD, 16));
        view.setPreferredSize(new Dimension(50, 50));
        view.setBorderPainted(false);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FriendProfile friendProfile = new FriendProfile(frame, user);


            }
        });

        postPanel.add(view);

        JButton accept = new JButton("accept");

        accept.setContentAreaFilled(false);
        accept.setFont(new Font("Arial", Font.BOLD, 16));
        accept.setPreferredSize(new Dimension(50, 50));

        accept.setBorderPainted(false);
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRelationsManager.accept_freind_req(currnetus, user);
                JOptionPane.showMessageDialog(postPanel, "Accepted");
                UserDatabaseManagement.getInstance().saveToFile();
                Refresh();


            }
        });

        postPanel.add(accept);

        JButton decline = new JButton("decline");

        decline.setContentAreaFilled(false);
        decline.setFont(new Font("Arial", Font.BOLD, 16));
        decline.setPreferredSize(new Dimension(50, 50));

        decline.setBorderPainted(false);
        decline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRelationsManager.decline_freind_req(currnetus, user);
                JOptionPane.showMessageDialog(postPanel, "declined");
                UserDatabaseManagement.getInstance().saveToFile();
                Refresh();


            }
        });

        postPanel.add(decline);


        return postPanel;
    }


    private JPanel createGoingtfreindrequestsoPanel(User user) {  // button 3aleh pendinggg
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(user.getUserName());


        ImageIcon originalIcon2 = new ImageIcon(user.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userInfo.setIcon(new ImageIcon(scaledImage2));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        postPanel.add(SPACE);

        JButton view = new JButton("view");

        view.setContentAreaFilled(false);
        view.setFont(new Font("Arial", Font.BOLD, 16));
        view.setPreferredSize(new Dimension(50, 50));

        view.setBorderPainted(false);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FriendProfile friendProfile = new FriendProfile(frame, user);


            }
        });

        postPanel.add(view);


        return postPanel;
    }


    private JPanel createALLusersPanel(User user) {  // add block view
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.X_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        JLabel userInfo = new JLabel(user.getUserName());


        ImageIcon originalIcon2 = new ImageIcon(user.getProfileInformation().getProfilePicPath());
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userInfo.setIcon(new ImageIcon(scaledImage2));
        userInfo.setFont(new Font("Arial", Font.BOLD, 14));
        postPanel.add(userInfo);
        JLabel SPACE = new JLabel("  ");
        postPanel.add(SPACE);


        JButton view = new JButton("view");
        view.setContentAreaFilled(false);
        view.setFont(new Font("Arial", Font.BOLD, 16));
        view.setPreferredSize(new Dimension(50, 50));
        view.setBorderPainted(false);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                FriendProfile friendProfile = new FriendProfile(frame, user);

            }
        });

        postPanel.add(view);

        JButton add = new JButton("add");
        add.setContentAreaFilled(false);
        add.setFont(new Font("Arial", Font.BOLD, 16));
        add.setPreferredSize(new Dimension(50, 50));
        add.setBorderPainted(false);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserRelationsManager.add_freind(currnetus, user);
                JOptionPane.showMessageDialog(postPanel, "Freind request sent");
                UserDatabaseManagement.getInstance().saveToFile();
                Refresh();


            }
        });

        postPanel.add(add);

        JButton block = new JButton("block");

        block.setContentAreaFilled(false);
        block.setFont(new Font("Arial", Font.BOLD, 16));
        block.setPreferredSize(new Dimension(50, 50));
        block.setBorderPainted(false);
        block.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                UserRelationsManager.block_freind(currnetus, user);
                JOptionPane.showMessageDialog(postPanel, "Blocked user");
                UserDatabaseManagement.getInstance().saveToFile();
                Refresh();
            }
        });

        postPanel.add(block);


        return postPanel;
    }


    public void Refresh() {
        System.out.println("refresh");

        contentPanel.removeAll();
        searchProcessor search = new searchProcessor(new Allsearch());
        allusers = search.searchforusers(name, currnetus);
        System.out.println(allusers.size());

        search = new searchProcessor(new FreindsSearch());
        myfreindssearch = search.searchforusers(name, currnetus);
        System.out.println(myfreindssearch.size());

        search = new searchProcessor(new FreindRequestSearch());
        allrequests = search.searchforusers(name, currnetus);
        System.out.println(allrequests.size());

        search = new searchProcessor(new sentFreindRequestssearch());
        allsent = search.searchforusers(name, currnetus);
        System.out.println(allsent.size());

        if (allusers.isEmpty() && myfreindssearch.isEmpty() && allrequests.isEmpty() && allsent.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "no user found");
            frame.dispose();
            recentframe.setVisible(true);

            return;

        }

        if (myfreindssearch.size() > 0) {
            JLabel sep = new JLabel("Freinds");


            contentPanel.add(sep);
        }


        populatefreinds(contentPanel);
        if (allrequests.size() > 0) {
            JLabel sep = new JLabel("Freind Requests");


            contentPanel.add(sep);
        }
        populatefreindrequests(contentPanel);
        if (allsent.size() > 0) {
            JLabel sep = new JLabel("sent freind rerquests");


            contentPanel.add(sep);
        }
        populatesentfreindrequests(contentPanel);
        if (allusers.size() > 0) {
            JLabel sep = new JLabel("ALL users");


            contentPanel.add(sep);
        }
        poulateAllusers(contentPanel);


        contentPanel.revalidate();
        contentPanel.repaint();


    }


}