package FrontEnd;

import BackEnd.*;
import FrontEnd.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GroupListAftersearchGUI {
    GroupOperation operation = new GroupOperation();
    Search search = new Search();

    public GroupListAftersearchGUI(String Id,JFrame f) {

        JFrame frame = new JFrame("Groups");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        User user = search.getUser(Id);
        frame.setVisible(true);


        // هنا يا زيزي انا عايز ال arrayList ديه يكون جواها groups اللي صاحب ال Id ده مشترك فيها
        ArrayList<Groups> groups = operation.getGroups(user.getGroupId()); // hena yegilo el array beta3t el search bto3 groupaty
        // ديه ال top panel يا زوز
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout()); // Use BorderLayout for more control
        topPanel.setBackground(new Color(99, 190, 100));
        topPanel.setPreferredSize(new Dimension(600, 150));
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);

        JTextField searchField = new JTextField(15);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(150, 30));

        JButton searchButton = new JButton();
        ImageIcon image = new ImageIcon("src/Image/search-interface-symbol.png");
        searchButton.setContentAreaFilled(false);
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.setPreferredSize(new Dimension(50, 50));
        searchButton.setIcon(image);
        searchButton.setBorderPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        topPanel.add(searchPanel, BorderLayout.SOUTH);
      //  frame.add(topPanel, BorderLayout.NORTH);
        searchButton.addActionListener(new ActionListener() {
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
                ArrayList<User> allusers = search.searchforusers(name, Id);


                search = new searchProcessor(new FreindsSearch());
                ArrayList<User> myfreindssearch = search.searchforusers(name, Id);


                search = new searchProcessor(new FreindRequestSearch());
                ArrayList<User> allrequests = search.searchforusers(name, Id);

                search = new searchProcessor(new sentFreindRequestssearch());
                ArrayList<User> allsent = search.searchforusers(name, Id);


                if (allusers.isEmpty() && myfreindssearch.isEmpty() && allrequests.isEmpty() && allsent.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "no user found");
                    return;
                } else {
                    frame.setVisible(false);
                    new FreindListaftersearchGUI(frame, Id, name);

                }


            }
        });



        // هنا بقا يا زوز هعرض كل ال جروبات اللي جبتهم فوق
        JPanel groupListPanel = new JPanel();
        groupListPanel.setLayout(new BoxLayout(groupListPanel, BoxLayout.Y_AXIS));
        groupListPanel.setBackground(Color.WHITE);
        for (Groups group : groups) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));
            groupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            groupPanel.setBackground(Color.WHITE);

            JLabel groupInfo = new JLabel(group.getGroupName());
            groupInfo.setFont(new Font("Arial", Font.BOLD, 14));

            try {
                ImageIcon originalIcon = new ImageIcon(group.getGroupPhoto());
                Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                groupInfo.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                groupInfo.setIcon(null);
            }

            // هنا بقا لما ادوس على اسم الجروب هفتحله شاشه جديده جواها بقا الجروب زات نفسه ب محتواياته
            groupInfo.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    new GroupDetailsGui(Id, group.getGroupId(), frame);
                    frame.setVisible(false);
                }
            });

            groupPanel.add(groupInfo);
            groupListPanel.add(groupPanel);
        }
/// ////////////////////////////////////////

        /// / 2ablha en dol ely msh fi ya join ya l2a
        for (Groups group : groups) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));
            groupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            groupPanel.setBackground(Color.WHITE);

            JLabel groupInfo = new JLabel(group.getGroupName());
            groupInfo.setFont(new Font("Arial", Font.BOLD, 14));

            try {
                ImageIcon originalIcon = new ImageIcon(group.getGroupPhoto());
                Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                groupInfo.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                groupInfo.setIcon(null);
            }

            // هنا بقا لما ادوس على اسم الجروب هفتحله شاشه جديده جواها بقا الجروب زات نفسه ب محتواياته
            groupInfo.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    String[] options = {"Send Request", "Cancel"};
                    int choice = JOptionPane.showOptionDialog(null, "Please choose an option:", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    switch (choice) {
                        case 0:
                           // send reqwuest
                            //Refresh();

                            break;
                        case 1:
                          return;




                    }
                }
            });

            groupPanel.add(groupInfo);
            groupListPanel.add(groupPanel);
        }

/// /////////////////// pending
        for (Groups group : groups) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));
            groupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            groupPanel.setBackground(Color.WHITE);

            JLabel groupInfo = new JLabel(group.getGroupName());
            groupInfo.setFont(new Font("Arial", Font.BOLD, 14));

            try {
                ImageIcon originalIcon = new ImageIcon(group.getGroupPhoto());
                Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                groupInfo.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                groupInfo.setIcon(null);
            }

            // هنا بقا لما ادوس على اسم الجروب هفتحله شاشه جديده جواها بقا الجروب زات نفسه ب محتواياته
            groupInfo.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "Pending request!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            });

            groupPanel.add(groupInfo);
            groupListPanel.add(groupPanel);
        }

        JScrollPane groupScrollPane = new JScrollPane(groupListPanel);
        frame.add(groupScrollPane, BorderLayout.CENTER);

        frame.setVisible(true);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Optional: Align the button in the center
        JButton returnButton = new JButton();



        JButton backButton = new JButton();
        ImageIcon image3 = new ImageIcon("src/Image/return.png");
        backButton.setContentAreaFilled(false);
        backButton.setPreferredSize(new Dimension(50, 50));
        backButton.setIcon(image3);
        backButton.setBorderPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                frame.dispose();
                f.setVisible(true);


            }
        });
        bottomPanel.add(backButton);




        JButton newGroupButton = new JButton("New Group");
        newGroupButton.setFont(new Font("Arial", Font.BOLD, 14));
        newGroupButton.setBackground(new Color(99, 190, 100));
        newGroupButton.setForeground(Color.WHITE);
        newGroupButton.setFocusPainted(false);
        newGroupButton.setPreferredSize(new Dimension(120, 40));

// Action listener for creating a new group
        newGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new window or dialog for creating a new group
                new CreateGroupGui(Id, frame); // You can implement CreateGroupGui class
                frame.setVisible(false); // Optional: Hide current frame while creating a new group
            }
        });

        bottomPanel.add(newGroupButton); // Add the button to the bottom panel











        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);













    }
}