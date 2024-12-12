package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class notificationsGUI {
    private JFrame frame;
    private JPanel panel;
    private List<Map<String, Object>> sampleNotifications;

    public notificationsGUI() {
        this.sampleNotifications = createSampleNotifications();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Notifications");
        frame.setSize(800, 750);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(227, 242, 253));
        frame.add(panel);

        displayNotifications();

        JLabel backgroundLabel = new JLabel(new ImageIcon("C:\\Users\\Abdallah\\Downloads\\Samsung-Innovation-Campus-1000x576.png"));
        backgroundLabel.setBounds(0, 0, 800, 576);
        panel.add(backgroundLabel);

        frame.setVisible(true); // Make sure this is called after adding all components
    }

    private List<Map<String, Object>> createSampleNotifications() {
        List<Map<String, Object>> notifications = new ArrayList<>();
        notifications.add(Map.of("type", "like", "message", "Someone liked your post", "date", "2024-12-12 10:00:00", "image", "C:\\Users\\Abdallah\\Desktop\\pngimg.com - like_PNG11.png"));
        notifications.add(Map.of("type", "comment", "message", "Someone commented on your post", "date", "2024-12-12 10:05:00", "image", "C:\\Users\\Abdallah\\Desktop\\pngtree-blue-rounded-comment-button-on-white-background-png-image_4891076.png"));
        notifications.add(Map.of("type", "message", "message", "You have a new message", "date", "2024-12-12 10:10:00", "image", "C:\\Users\\Abdallah\\Desktop\\png-transparent-white-and-blue-message-icon-illustration-email-computer-icons-symbol-message-inbox-by-gmail-envelope-miscellaneous-blue-angle-thumbnail.png"));
        return notifications;
    }

    private void displayNotifications() {
        int yOffset = 15;

        for (Map<String, Object> notification : sampleNotifications) {
            JPanel notificationPanel = new JPanel();
            notificationPanel.setBounds(15, yOffset, 450, 80);
            notificationPanel.setBackground(Color.WHITE);
            notificationPanel.setLayout(null);
            panel.add(notificationPanel);

            String imagePath = (String) notification.get("image");
            if (!new java.io.File(imagePath).exists()) {
                System.out.println("Image not found: " + imagePath); // Debugging missing images
            }
            JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
            imageLabel.setBounds(10, 10, 60, 60);
            notificationPanel.add(imageLabel);

            JLabel typeLabel = new JLabel((String) notification.get("type"));
            typeLabel.setBounds(80, 10, 200, 20);
            typeLabel.setFont(new Font("Helvetica", Font.BOLD, 13));
            notificationPanel.add(typeLabel);

            JLabel messageLabel = new JLabel((String) notification.get("message"));
            messageLabel.setBounds(80, 40, 200, 20);
            messageLabel.setFont(new Font("Helvetica", Font.PLAIN, 11));
            notificationPanel.add(messageLabel);

            JLabel dateLabel = new JLabel((String) notification.get("date"));
            dateLabel.setBounds(300, 10, 150, 20);
            dateLabel.setFont(new Font("Helvetica", Font.PLAIN, 11));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            notificationPanel.add(dateLabel);

            yOffset += 90;
        }
    }

    public static void main(String[] args) {
        new notificationsGUI();
    }
}
