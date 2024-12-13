package BackEnd;

import java.util.ArrayList;

/**
 class manages user relationships and operations
  such as sending friend requests, blocking user
 */
public class UserRelationsManager {


    /**
     * Block user and remove this friend from friend list
     *
     *
     */
    public static void block_freind(User blocker, User blocked) {

       remove_freind(blocker, blocked);

        ArrayList<String> ids =new ArrayList<>();
           ids=     blocker.getBlockedID();
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



    public static void add_freind(User sender, User reciever) {
        System.out.println(sender+"ana sender");


        ArrayList<String> sendersent = new ArrayList<>();
            sendersent=    sender.getSentfreindrequestId();
        sendersent.add(reciever.getUserId());
        ArrayList<String> recieverrecieved = new ArrayList<>();
        recieverrecieved=reciever.getFreindrequestId();
        recieverrecieved.add(sender.getUserId());

    }

}
