package BackEnd;

import java.util.ArrayList;

public class Search {
    UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();

    public User getUser(String userId){
        for (int i=0;i<userDatabaseManagement.getUsers().size();i++)
        {
            if (userId.equals(userDatabaseManagement.getUsers().get(i).getUserId()))
            {
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
}
