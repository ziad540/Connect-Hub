package BackEnd;

import java.util.ArrayList;

public class is_sent   // law ba3t le freind request

{
   public static boolean is_Sent (User U,String id)
    {
        ArrayList<String> freinds=U.getSentfreindrequestId();
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
