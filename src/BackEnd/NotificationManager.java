package BackEnd;

import java.util.ArrayList;

public class NotificationManager {
    private ArrayList<Notification> notifications = new ArrayList<>();

    public void addNotification(Notification notification)

    {
        notifications.add(notification);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }


    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public NotificationManager() {
    }
}
