package BackEnd;

import java.util.ArrayList;

public class Is_Blocked {
    public static boolean is_blocked (User U,String id)
    {
        ArrayList<String> blockedids=U.getBlockedID();
        for (int i=0;i<blockedids.size();i++)
        {
            if (id.equals(blockedids.get(i)))
            {
                return true;
            }
        }
        return false;
    }
}
