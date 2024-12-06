package BackEnd;

import java.util.ArrayList;

/**
 class manages user relationships and operations
  such as sending friend requests, blocking user
 */
public class UserRelationsManager {
    private ArrayList<String> freindrequestId = new ArrayList<>(); //ely gayin leya
    private ArrayList<String> sentfreindrequestId = new ArrayList<>(); //ely ana ba3thom
    private ArrayList<String> blockedID = new ArrayList<>(); //ely ana 3amlehom

    /**
     * Block user and remove this friend from friend list
     *
     *
     */
    public static void block_freind(User blocker, User blocked) {

        remove_freind(blocker, blocked);
        ArrayList<String> ids = blocker.getBlockedID();
        ids.add(blocked.getUserId());
        ids = blocked.getBlockedfromID();
        ids.add(blocker.getUserId());
    }

    /**
     * Declines a friend request
     *
     *
     */
    public static void decline_freind_req(User acceptor, User other) {
        ArrayList<String> ids = acceptor.getFreindrequestId();

        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(other.getUserId())) {
                ids.remove(i);
                break;
            }
        }
        ids = other.getSentfreindrequestId();
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(acceptor.getUserId())) {
                ids.remove(i);
                break;
            }
        }
    }

    /**
     * Remove a user from the friend list
     *
     *
     */
    public static void remove_freind(User remover, User removed) {
        ArrayList<String> ids = remover.getFirndesId();

        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(removed.getUserId())) {
                ids.remove(i);
                break;
            }
        }

        ids = removed.getFirndesId();

        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(remover.getUserId())) {
                ids.remove(i);
                break;
            }
        }
    }

    /**
     * Accept friend request and add this friend in friend list
     *
     *
     */
    public static void accept_freind_req(User acceptor, User other) {
        ArrayList<String> ids = new ArrayList<>();
        decline_freind_req(acceptor, other);

        ids = acceptor.getFirndesId();
        ids.add(other.getUserId());

        ids = other.getFirndesId();
        ids.add(acceptor.getUserId());

    }

    //Getter and setters
    public ArrayList<String> getBlockedID() {
        return blockedID;
    }

    public void setBlockedID(ArrayList<String> blockedID) {
        this.blockedID = blockedID;
    }

    public ArrayList<String> getSentfreindrequestId() {
        return sentfreindrequestId;
    }

    public void setSentfreindrequestId(ArrayList<String> sentfreindrequestId) {
        this.sentfreindrequestId = sentfreindrequestId;
    }

    public ArrayList<String> getFreindrequestId() {
        return freindrequestId;
    }

    public void setFreindrequestId(ArrayList<String> freindrequestId) {
        this.freindrequestId = freindrequestId;
    }

    public static void add_freind(User sender, User reciever) {
        ArrayList<String> sendersent = sender.getSentfreindrequestId();
        sendersent.add(reciever.getUserId());
        ArrayList<String> recieverrecieved = reciever.getFreindrequestId();
        recieverrecieved.add(sender.getUserId());
    }

}
