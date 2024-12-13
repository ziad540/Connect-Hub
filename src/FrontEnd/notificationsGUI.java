package FrontEnd;

import BackEnd.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class notificationsGUI {
    private JFrame frame;
    private JPanel panel;
    private  ArrayList<Notification>  sampleNotifications;
    String userid;
    JFrame recentframe;


    public notificationsGUI(String userid,JFrame recentframe) {
        this.userid=userid;
        this.recentframe=recentframe;
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

        JLabel backgroundLabel = new JLabel(new ImageIcon("C:\\Users\\Abdallah\\Desktop\\conncect-hub.jpg"));
        backgroundLabel.setBounds(-200, -200, 2100, 1400);
        panel.add(backgroundLabel);

        frame.setVisible(true);
    }

    private  ArrayList<Notification>  createSampleNotifications() {
       ArrayList<Notification> not=new ArrayList<>();
       Search s=new Search();
       User u =s.getUser(userid);
       not=u.getNotificationManager().getNotifications();
        return not;
    }

    private void displayNotifications() {
        int height = 15;

        for (Notification n : sampleNotifications) {
            JPanel notificationPanel = new JPanel();
            notificationPanel.setBounds(15, height, 475, 80);
            notificationPanel.setBackground(Color.WHITE);
            notificationPanel.setLayout(null);
            panel.add(notificationPanel);

//            String imagePath = (String) notification.get("image");
//            if (!new java.io.File(imagePath).exists()) {
//                System.out.println("Image not found: " + imagePath);
//            }
//            JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
//            imageLabel.setBounds(10, 10, 60, 60);
//            notificationPanel.add(imageLabel);

            JLabel typeLabel = new JLabel((String) n.getMessage());
            typeLabel.setBounds(80, 10, 200, 20);
            typeLabel.setFont(new Font("Helvetica", Font.BOLD, 13));
            notificationPanel.add(typeLabel);

            JLabel messageLabel = new JLabel((String) n.getMessage());
            messageLabel.setBounds(80, 40, 200, 20);
            messageLabel.setFont(new Font("Helvetica", Font.PLAIN, 11));
            notificationPanel.add(messageLabel);

            JLabel dateLabel = new JLabel((String) n.getTimestamp());
            dateLabel.setBounds(300, 10, 150, 20);
            dateLabel.setFont(new Font("Helvetica", Font.PLAIN, 11));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            notificationPanel.add(dateLabel);

            notificationPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
             n.interact();
                    sampleNotifications.remove(n);
                    //panel.removeAll();
                    Refresh();
                    displayNotifications();

                }
            });


            height += 90;
        }
    }

    public void Refresh() {
        UserDatabaseManagement.getInstance().loadUsersFromFile();
        panel.removeAll();

        new notificationsGUI(userid,frame);

        panel.revalidate();
        panel.repaint();



    }

}
