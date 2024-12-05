import java.util.ArrayList;

public class FreindsSearch implements SearchStrategy {

    @Override
    public ArrayList<User> searchforusers(String name, User U)

    {
        ArrayList<User> Data=new ArrayList<>();
        GetFreinds getFreinds=new GetFreinds(U.getFirndesId());
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
