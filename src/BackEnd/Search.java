package BackEnd;


import javax.swing.*;

import java.util.ArrayList;

public class Search {
    UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();

    MemberShipDataBase memberShipDataBase = MemberShipDataBase.getInstance();
    GroupDataBase groupDataBase = GroupDataBase.getInstance();
    PostDatabaseManagement postDatabaseManagement = PostDatabaseManagement.getInstance();

    public User getUser(String userId) {
        for (int i = 0; i < userDatabaseManagement.getUsers().size(); i++) {
            if (userId.equals(userDatabaseManagement.getUsers().get(i).getUserId())) {

                return userDatabaseManagement.getUsers().get(i);
            }
        }
        return null;
    }

    public ArrayList<User> getUsers(ArrayList<String> userIds) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            for (int j = 0; j < userDatabaseManagement.getUsers().size(); j++) {
                if (userIds.get(i).equals(userDatabaseManagement.getUsers().get(j).getUserId())) {
                    users.add(userDatabaseManagement.getUsers().get(j));
                }
            }
        }
        return users;
    }


    public ArrayList<Post> getPosts(ArrayList<String> postIds) {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 0; i < postIds.size(); i++) {
            for (int j = 0; j < postDatabaseManagement.getPosts().size(); j++) {
                if (postIds.get(i).equals(postDatabaseManagement.getPosts().get(j).getContentId())) {
                    posts.add(postDatabaseManagement.getPosts().get(j));
                }
            }
        }
        return posts;
    }


    public ArrayList<MemberShip> getMemberShips(String groupId) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        ArrayList<MemberShip> memberShipList = new ArrayList<>();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            ArrayList<String> memberShips = group.getMemberShipId();

            for (int i = 0; i < memberShips.size(); i++) {
                for (int j = 0; j < memberShipDataBase.getMemberShips().size(); j++) {
                    if (memberShips.get(i).equals(memberShipDataBase.getMemberShips().get(j).getMemberShipID())) {
                        memberShipList.add(memberShipDataBase.getMemberShips().get(j));
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No such group", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return memberShipList;
    }

    public ArrayList<Groups> getGroups(ArrayList<String>groupIds) {
        ArrayList<Groups> groups = new ArrayList<>();
        for (int i = 0; i < groupIds.size(); i++) {
            for (int j = 0; j < groupDataBase.getGroups().size(); j++) {
                if (groupIds.get(i).equals(groupDataBase.getGroups().get(j).getGroupId())) {
                    groups.add(groupDataBase.getGroups().get(j));
                }
            }
        }
        return groups;
    }



    public Groups getgroup( String groupID) {
        for (int i = 0; i < groupDataBase.getGroups().size(); i++) {
            if (groupID.equals(groupDataBase.getGroups().get(i).getGroupId())) {
                return groupDataBase.getGroups().get(i);
            }
        }
        return null;
    }

}
