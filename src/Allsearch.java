import java.util.ArrayList;

public class Allsearch implements SearchStrategy
{
    @Override
    public ArrayList<User> searchforusers(String name, User U)

    {

        ArrayList<User> all=UserDatabaseManagement.getInstance().getUsers();
        ArrayList<User> Data=new ArrayList<>();


        for(int i=0;i<all.size();i++)
        {
            if (!(Is_Blocked.is_blocked(U,(all.get(i).getUserId()))&& !(Is_Freind.is_freind(U,(all.get(i).getUserId())) &&!(Is_Requested.is_requested(U,(all.get(i).getUserId()))))))
                if (all.get(i).getUserName().startsWith(name))
                {
                    Data.add(all.get(i));
                }






        }

        return Data;

    }
}

