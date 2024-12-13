package FrontEnd;


import BackEnd.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateGroupGui {
    Search search = new Search();

    public CreateGroupGui(String userId, JFrame parentFrame) {
        JFrame frame = new JFrame("Create New Group");
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Group Name:");
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(5, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane descScrollPane = new JScrollPane(descArea);

        JLabel photoLabel = new JLabel("Group Photo:");
        JButton choosePhotoButton = new JButton("Choose Photo");
        JLabel photoPreview = new JLabel("No photo selected", JLabel.CENTER);
        photoPreview.setPreferredSize(new Dimension(150, 150));
        photoPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        final String[] photoPath = {null}; // To store the selected photo path

        choosePhotoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                photoPath[0] = fileChooser.getSelectedFile().getAbsolutePath();

                ImageIcon icon = new ImageIcon(photoPath[0]);
                Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                photoPreview.setIcon(new ImageIcon(scaledImage));
                photoPreview.setText("");
            }
        });

        JButton createButton = new JButton("Create Group");
        createButton.setBackground(new Color(99, 190, 100));
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = nameField.getText().trim();
                String groupDesc = descArea.getText().trim();
                System.out.println(MemberShipDataBase.getInstance().getMemberShips().size() + "SIZEEE");
                if (groupName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Group name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                PrimaryAdmin newMember = new PrimaryAdmin(userId);
                System.out.println(newMember.getMemberShipID() + " id member");
                System.out.println(newMember.getUserID() + "sssdsd");
                User user = search.getUser(userId);
                System.out.println(user.getUserName());
                Groups newGroup = new Groups(groupName, groupDesc, userId, photoPath[0]);
                ArrayList<String> groupsId = user.getGroupId();
                ArrayList<String> memberId = new ArrayList<>();
                MemberShipDataBase.getInstance().addMemberShip(newMember);
                GroupDataBase.getInstance().addGroup(newGroup);
                memberId.add(newMember.getMemberShipID());
                newGroup.setMemberShipId(memberId);
                groupsId.add(newGroup.getGroupId());
                UserDatabaseManagement.getInstance().saveToFile();
                GroupDataBase.getInstance().saveToFile();
                MemberShipDataBase.getInstance().saveToFile();
                frame.dispose();
                parentFrame.setVisible(true);
            }
        });


        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(descLabel);
        mainPanel.add(descScrollPane);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(photoLabel);
        mainPanel.add(photoPreview);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(choosePhotoButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(createButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);


        JButton returnButton = new JButton("Return");
        returnButton.setBackground(new Color(190, 99, 100)); // لون مميز للزر
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);

        returnButton.addActionListener(e -> {
            frame.dispose();
            parentFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(returnButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(createButton);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
