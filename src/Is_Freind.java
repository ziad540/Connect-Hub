import java.util.ArrayList;

public class Is_Freind {

    public static boolean is_freind(User U,String id)
    {
        ArrayList<String> freinds=U.getFirndesId();
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
