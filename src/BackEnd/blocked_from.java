package BackEnd;

import java.util.ArrayList;

public class blocked_from {


    public static boolean blocked_From (User U,String id)
    {
        ArrayList<String> freinds=U.getBlockedfromID();
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
