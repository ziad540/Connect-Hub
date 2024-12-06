package BackEnd;

import java.util.ArrayList;

public class Is_Requested {

    public static boolean is_requested(User U,String id)
    {
        ArrayList<String> freinds=U.getFreindrequestId();
        for (int i=0;i<freinds.size();i++)
        {
            if (id.equals(freinds.get(i)))
            {
                return true;
            }
        }
        return false;
    }
}
