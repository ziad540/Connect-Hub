package BackEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class ListoffreindSuggestion {

    public ArrayList<User> getfreindssugg  (User U ,ArrayList<User> freinds)  //sohabak

    {
        HashSet<String> hashSet = new HashSet<>();

        for(int i=0;i<freinds.size();i++)
        {
            ArrayList<String> freindsid=freinds.get(i).getFirndesId();
            for(int j=0;j<freindsid.size();j++)
            {
                if ((!Is_Blocked.is_blocked(U,freindsid.get(j))&& (!Is_Freind.is_freind(U,freindsid.get(j)) &&(!Is_Requested.is_requested(U,freindsid.get(j)))))
                )

                { if ((!blocked_from.blocked_From(U,freindsid.get(j))) && !is_sent.is_Sent(U,freindsid.get(j)) && (!freindsid.get(j).equals(U.getUserId()) ))
                {
                    hashSet.add(freindsid.get(j));
                }}

            }
        }


        ArrayList<String> torandomize=new ArrayList<>(hashSet);

        Collections.shuffle(torandomize);
        if (torandomize.size()==0)
        {
            return new ArrayList<>();
        } else if (torandomize.size()<=3) {
            GetFreinds getFreinds=new GetFreinds(torandomize);
            ArrayList<User> finallist=getFreinds.get();
            return finallist;
        }
        else
        {
            ArrayList<String> subarray=new ArrayList<>();
            for (int i=0;i<3;i++)
            {
                subarray.add(torandomize.get(i));
            }
            GetFreinds getFreinds=new GetFreinds(subarray);
            ArrayList<User> finallist=getFreinds.get();
            return finallist;


        }






    }







}
