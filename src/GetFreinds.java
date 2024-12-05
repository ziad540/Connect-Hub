import java.util.ArrayList;

public class GetFreinds {


    ArrayList<String> freinds2;

    public GetFreinds(ArrayList<String> freinds) {
        this.freinds2 = freinds;  //law b3t el freind requests aw b3t el freinds nafsohom
    }


    public ArrayList<User> get(User u) {
        ArrayList<User> freinds=new ArrayList<>();

        UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();
        ArrayList<User> users=UserDatabaseManagement.getInstance().getUsers();


        for(int i=0;i<freinds2.size();i++)
        {
            for (int j=0;j<users.size();j++)
            {
                if (freinds2.get(i).equals(users.get(j).getUserId()))
                {
                    freinds.add(users.get(j));
                    break;
                }

            }
        }
       return freinds;


    }
}
