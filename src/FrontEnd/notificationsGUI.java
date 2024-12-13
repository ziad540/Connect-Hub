package FrontEnd;

import BackEnd.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class notificationsGUI {
    private JFrame frame;
    private JPanel panel;
    private ArrayList<Notification> sampleNotifications;
    String userid;
    JFrame recentframe;

    public notificationsGUI(String userid, JFrame recentframe) {
        this.userid = userid;
        this.recentframe = recentframe;
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

        // Create background label and add it initially
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/Image/conncect-hub.jpg"));
        backgroundLabel.setBounds(-200, -200, 2100, 1400);
        panel.add(backgroundLabel);

        JButton refresh = new JButton();
        ImageIcon image2 = new ImageIcon("src/Image/refresh.png");
        refresh.setContentAreaFilled(false);
        refresh.setFont(new Font("Arial", Font.BOLD, 16));
        refresh.setPreferredSize(new Dimension(50, 50));
        refresh.setBounds(1480, 0, 50, 50);
        refresh.setIcon(image2);
        refresh.setBorderPainted(false);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Refresh();
            }
        });
        panel.add(refresh);

        JButton back = new JButton();
        ImageIcon image3 = new ImageIcon("src/Image/return.png");
        back.setContentAreaFilled(false);
        back.setPreferredSize(new Dimension(50, 50));
        back.setIcon(image3);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                recentframe.setVisible(true);
            }
        });
        back.setBounds(1430, 0, 50, 50);

        JButton markasRead = new JButton();
        ImageIcon image4 = new ImageIcon("src/Image/markasread.png");
        markasRead.setContentAreaFilled(false);
        markasRead.setPreferredSize(new Dimension(50, 50));
        markasRead.setIcon(image4);
        markasRead.setBorderPainted(false);
        markasRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search s = new Search();
                User u = s.getUser(userid);
                u.getNotificationManager().setNotifications(new ArrayList<>());
                frame.dispose();
                recentframe.setVisible(true);
            }
        });
        markasRead.setBounds(1380, 0, 50, 50);
        panel.add(back);
        panel.add(markasRead);
        displayNotifications();
        panel.setComponentZOrder(backgroundLabel, panel.getComponentCount() - 1);
        frame.setVisible(true);
    }

    private ArrayList<Notification> createSampleNotifications() {
        ArrayList<Notification> not = new ArrayList<>();
        Search s = new Search();
        User u = s.getUser(userid);
        not = u.getNotificationManager().getNotifications();
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
                    UserDatabaseManagement.getInstance().saveToFile();
                    Refresh();
                }
            });

            height += 90;
        }
    }

    public void Refresh() {
        UserDatabaseManagement.getInstance().loadUsersFromFile();
        sampleNotifications = createSampleNotifications();
        panel.removeAll();



        JLabel backgroundLabel = new JLabel(new ImageIcon("src/Image/conncect-hub.jpg"));
        backgroundLabel.setBounds(-200, -200, 2100, 1400);
        panel.add(backgroundLabel);
        JButton refresh = new JButton();
        ImageIcon image2 = new ImageIcon("src/Image/refresh.png");
        refresh.setContentAreaFilled(false);
        refresh.setFont(new Font("Arial", Font.BOLD, 16));
        refresh.setPreferredSize(new Dimension(50, 50));
        refresh.setBounds(1480, 0, 50, 50);
        refresh.setIcon(image2);
        refresh.setBorderPainted(false);
        refresh.addActionListener(e -> Refresh());
        panel.add(refresh);

        JButton back = new JButton();
        ImageIcon image3 = new ImageIcon("src/Image/return.png");
        back.setContentAreaFilled(false);
        back.setPreferredSize(new Dimension(50, 50));
        back.setIcon(image3);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                recentframe.setVisible(true);
            }
        });
        back.setBounds(1430, 0, 50, 50);

        JButton markasRead = new JButton();
        ImageIcon image4 = new ImageIcon("src/Image/markasread.png");
        markasRead.setContentAreaFilled(false);
        markasRead.setPreferredSize(new Dimension(50, 50));
        markasRead.setIcon(image4);
        markasRead.setBorderPainted(false);
        markasRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search s = new Search();
                User u = s.getUser(userid);
                u.getNotificationManager().setNotifications(new ArrayList<>());
                frame.dispose();
                recentframe.setVisible(true);
            }
        });
        markasRead.setBounds(1380, 0, 50, 50);

        panel.add(markasRead);
        panel.add(back);
        displayNotifications();
        panel.setComponentZOrder(backgroundLabel, panel.getComponentCount() - 1);
        panel.revalidate();
        panel.repaint();
    }
}
