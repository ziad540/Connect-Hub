package BackEnd;

import java.util.ArrayList;

public class sentFreindRequestssearch implements SearchStrategy{
    @Override


    public ArrayList<User> searchforusers(String name, String ID)


    {
        Search search = new Search();
        User U = search.getUser(ID);
        System.out.println(U+" b3d el search");
        System.out.println(U.getSentfreindrequestId());
        ArrayList<User> Data=new ArrayList<>();
        GetFreinds getFreinds=new GetFreinds(U.getSentfreindrequestId());
        ArrayList<User> requests =getFreinds.get();

        for(int i=0;i<requests.size();i++)
        {
            if(requests.get(i).getUserName().startsWith(name))
            {
                Data.add(requests.get(i));

            }
        }

        return Data;

    }
}
