import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStoryGui {

    AddStoryGui() {
        JFrame frame = new JFrame("Story");
        frame.setSize(400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(102, 205, 170));
        topPanel.setPreferredSize(new Dimension(400, 60));
        JLabel titleLabel = new JLabel("Add Story", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea("Add Story...");
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        textArea.setLineWrap(true);


        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setPreferredSize(new Dimension(300, 100));
        contentPanel.add(textScrollPane, BorderLayout.NORTH);
        JPanel uploadPanel = new JPanel(new BorderLayout());
        uploadPanel.setPreferredSize(new Dimension(100, 100));





        JButton uploadButton = new JButton();
        ImageIcon imageIcon = new ImageIcon("src/UploadPhoto.jpeg");
        uploadButton.setContentAreaFilled(false);
        uploadButton.setFont(new Font("Arial", Font.BOLD, 16));
        uploadButton.setPreferredSize(new Dimension(300, 50));
        uploadButton.setIcon(imageIcon);
        uploadButton.setBorderPainted(false);

        JLabel photoLabel = new JLabel("No photo uploaded", SwingConstants.CENTER);
        photoLabel.setFont(new Font("Arial", Font.PLAIN, 12));


        photoLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    ImageIcon uploadedImage = new ImageIcon(fileChooser.getSelectedFile().getPath());

                    ImageIcon originalIcon = new ImageIcon(uploadedImage.getImage());
                    Image scaledImage = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
                    photoLabel.setIcon(new ImageIcon(scaledImage));
                    photoLabel.setText("");

                }
            }
        });


        uploadPanel.add(photoLabel, BorderLayout.CENTER);
        contentPanel.add(uploadPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center aligned buttons with spacing
        JButton addstoryButton = new JButton();
        ImageIcon addStoryIcon = new ImageIcon("src/AddStory.jpeg");
        addstoryButton.setContentAreaFilled(false);
        addstoryButton.setFont(new Font("Arial", Font.BOLD, 16));
        addstoryButton.setPreferredSize(new Dimension(50, 50));
        addstoryButton.setIcon(addStoryIcon);
        addstoryButton.setBorderPainted(false);
        addstoryButton.addActionListener(e -> System.out.println("Add Story"));

        // Add buttons to Bottom Panel
        bottomPanel.add(uploadButton);
        bottomPanel.add(addstoryButton);

        // Add Bottom Panel to Frame
        frame.add(bottomPanel, BorderLayout.SOUTH);






        frame.setVisible(true);
    }
}