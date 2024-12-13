package BackEnd;

import javax.swing.*;

public class FriendreqNotifiaction extends Notification {
    String freindid;

    public FriendreqNotifiaction(String message, String timestamp, String recieverID, String freindid) {
        super(message, timestamp, recieverID);
        this.freindid = freindid;
    }

    public FriendreqNotifiaction() {
    }

    public String getFreindid() {
        return freindid;
    }

    public void setFreindid(String freindid) {
        this.freindid = freindid;
    }

    @Override
    public void interact() {
        String[] options = {"Accept ", "Decline", "View"};
        int choice = JOptionPane.showOptionDialog(null, "new friend request", "Group Post",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                Search s=new Search();
                UserRelationsManager.accept_freind_req(s.getUser(getRecieverID()),s.getUser(freindid));
                UserDatabaseManagement.getInstance().saveToFile();

                break;
            case 1:
               Search s2=new Search();
                UserRelationsManager.decline_freind_req(s2.getUser(getRecieverID()),s2.getUser(freindid));
                UserDatabaseManagement.getInstance().saveToFile();
                break;
            case 2:

                break;
        }
    }
}