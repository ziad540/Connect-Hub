package FrontEnd;


import BackEnd.GroupOperation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGroupGui {
    public CreateGroupGui(String userId, JFrame parentFrame) {
        JFrame frame = new JFrame("Create New Group");
        frame.setSize(400, 500);
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

                if (groupName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Group name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                                                /// /////////// هنا يا زيزي عايز اضيف جروب جديد في الباك ايند
//                GroupOperation operation = new GroupOperation();
//                boolean isSuccess = operation.createGroup(userId, groupName, groupDesc, photoPath[0]);
//
//                if (isSuccess) {
//                    JOptionPane.showMessageDialog(frame, "Group created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                    frame.dispose();
//                    parentFrame.setVisible(true);
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Failed to create group. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
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
