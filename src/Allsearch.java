import java.util.ArrayList;

public class Allsearch implements SearchStrategy
{  //bydawar fe kolo
    @Override
    public ArrayList<User> searchforusers(String name, User U)

            // el ba2y men sohabak

    {

        ArrayList<User> all=UserDatabaseManagement.getInstance().getUsers();
        ArrayList<User> Data=new ArrayList<>();


        for(int i=0;i<all.size();i++)
        {
            if ((!Is_Blocked.is_blocked(U,(all.get(i).getUserId()))&& (!Is_Freind.is_freind(U,(all.get(i).getUserId())) &&(!Is_Requested.is_requested(U,(all.get(i).getUserId()))))

            && (!blocked_from.blocked_From(U,(all.get(i).getUserId()))     &&  (!is_sent.is_Sent(U,(all.get(i).getUserId()))   )
            )))
                if (all.get(i).getUserName().startsWith(name) && (!all.get(i).getUserId().equals(U.getUserId()))) //mesh ana
                {
                    Data.add(all.get(i));

                }






        }

        return Data;

    }
}

