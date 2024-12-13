package BackEnd;

import FrontEnd.GroupDetailsGui;

import javax.swing.*;

public class GroupNotification extends Notification{
    String groupid;

    public GroupNotification(String message, String timestamp, String recieverID, String groupid) {
        super(message, timestamp, recieverID);
        this.groupid = groupid;
    }

    public String getGroupid() {
        return groupid;
    }

    public GroupNotification() {
    }

    public void setGroupid(String groupid)
    {
        this.groupid = groupid;
    }

    @Override
    public void interact() {
        String[] options = {"View group", "mark as read"};
        int choice = JOptionPane.showOptionDialog(null, "New post in your group!", "Group Post",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                new GroupDetailsGui(getRecieverID(), groupid, null);



                break;
            case 1:

                break;
        }
    }
}
