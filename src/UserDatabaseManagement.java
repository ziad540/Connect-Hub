import java.util.ArrayList;

public class UserDatabaseManagement {

    private usersdatabase u;
    private ArrayList<User> users;

    public UserDatabaseManagement(){
        u = new usersdatabase("src/users.json");
        users = u.load();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
