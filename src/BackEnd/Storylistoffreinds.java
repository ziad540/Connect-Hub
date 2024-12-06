package BackEnd;

import java.util.ArrayList;

public class Storylistoffreinds
{

    public ArrayList<User> getlist(ArrayList<User> users)  //array of freinds bto3o
    {
        ArrayList<User> listofusers=new ArrayList<>();
        for (int i=0;i<users.size();i++)
        {
            if (!(users.get(i).getStoriesId().isEmpty()))
            {
                listofusers.add(users.get(i));
            }
        }

        return listofusers;  //array ely nazelo stories bs
    }
}
