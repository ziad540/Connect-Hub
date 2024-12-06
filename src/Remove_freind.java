import java.util.ArrayList;

public class Remove_freind {
    public static void  remove_freind (User remover,User removed)
    {
        ArrayList<String> ids=remover.getFirndesId();

        for(int i=0;i<ids.size();i++)
        {
            if (ids.get(i).equals(removed.getUserId()))
            {
                ids.remove(i);
                break;
            }
        }

        ids=removed.getFirndesId();

        for(int i=0;i<ids.size();i++)
        {
            if (ids.get(i).equals(remover.getUserId()))
            {
                ids.remove(i);
                break;
            }
        }




    }


}
