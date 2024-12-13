package FrontEnd;

import BackEnd.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPostGroupGui {

    private ImageIcon lastimage = null;
    private String pathimage = null;
    GroupOperation groupOperation = new GroupOperation();
    Search search = new Search();

    AddPostGroupGui(String memberId, JFrame frame2) {
        MemberShip memberShip = groupOperation.getMember(memberId);
        JFrame frame = new JFrame("NewsFeed"); //new frame to Add post
        frame.setSize(400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        // Top Panel Contains the title label "Add BackEnd.Post"
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(102, 205, 170));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Add post", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        // contentPanel contians text area for post text and image uploaded
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea("Add post...");
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        textArea.setLineWrap(true);
        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setPreferredSize(new Dimension(300, 100));
        contentPanel.add(textScrollPane, BorderLayout.NORTH);

        //uploadPanel Contains the photo upload button
        JPanel uploadPanel = new JPanel(new BorderLayout());
        uploadPanel.setPreferredSize(new Dimension(100, 100));
        JButton uploadButton = new JButton();
        ImageIcon imageIcon = new ImageIcon("src/Image/upload.png");
        uploadButton.setContentAreaFilled(false);
        uploadButton.setFont(new Font("Arial", Font.BOLD, 16));
        uploadButton.setPreferredSize(new Dimension(300, 50));
        uploadButton.setIcon(imageIcon);
        uploadButton.setBorderPainted(false);
        // Label to show the uploaded photo
        JLabel photoLabel = new JLabel("No photo uploaded", SwingConstants.CENTER);
        photoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {

                    pathimage = fileChooser.getSelectedFile().getPath();
                    ImageIcon uploadedImage = new ImageIcon(pathimage);

                    ImageIcon originalIcon = new ImageIcon(uploadedImage.getImage());
                    Image scaledImage = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                    lastimage = new ImageIcon(scaledImage);
                    photoLabel.setIcon(lastimage);
                    photoLabel.setText("");

                }
            }
        });


        uploadPanel.add(photoLabel, BorderLayout.CENTER);
        contentPanel.add(uploadPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        bottomPanel.setPreferredSize(new Dimension(400, 80));
        JButton addPostButton = new JButton();
        ImageIcon i = new ImageIcon("src/Image/new-post.png");
        addPostButton.setContentAreaFilled(false);
        addPostButton.setFont(new Font("Arial", Font.BOLD, 16));
        addPostButton.setPreferredSize(new Dimension(50, 50));
        addPostButton.setIcon(i);
        addPostButton.setBorderPainted(false);
        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idint = uniqueId.loadCOUNTERPOSTID();
                String idCheck = String.valueOf(idint);
                ArrayList<Post> allposts = PostDatabaseManagement.getInstance().getPosts();
                for (int i = 0; i < PostDatabaseManagement.getInstance().getPosts().size(); i++) {
                    if (PostDatabaseManagement.getInstance().getPosts().get(i).getContentId().equals(idCheck)) {
                        idint++;
                        idCheck = String.valueOf(idint);
                    }
                }
                String id = String.valueOf(idint);
                Post post = new Post(id, memberShip.getUserID(), textArea.getText(), pathimage);
                allposts.add(post);
                PostDatabaseManagement.getInstance().saveToFile();
                ArrayList<String> posts = memberShip.getPostId();
                posts.add(id);
                JOptionPane.showMessageDialog(null, "Added post", "added", JOptionPane.INFORMATION_MESSAGE);
                UserDatabaseManagement.getInstance().saveToFile();
                MemberShipDataBase.getInstance().saveToFile();

            }
        });

        JButton backButton = new JButton();
        ImageIcon image3 = new ImageIcon("src/Image/return.png");
        backButton.setContentAreaFilled(false);
        backButton.setPreferredSize(new Dimension(50, 50));
        backButton.setIcon(image3);
        backButton.setBorderPainted(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(true);
                frame.setVisible(false);


                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                if (currentFrame != null) {
                    currentFrame.setVisible(false);
                }
            }
        });

        bottomPanel.add(uploadButton);
        bottomPanel.add(addPostButton);
        bottomPanel.add(backButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setVisible(true);
    }
}
