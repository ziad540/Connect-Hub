package BackEnd;

import java.util.ArrayList;

public class AllGroupsSearch implements SearchGroupStrategy{
    @Override
    public ArrayList<String> searchforgroups(String name, String ID) {

        Search search = new Search();
       User user=search.getUser(ID);
        ArrayList<Groups> all= GroupDataBase.getInstance().getGroups();
        ArrayList<Groups> Data=new ArrayList<>();

        for(int i=0;i<all.size();i++)  // groupat
        {
            if ((all.get(i).getMemberShipId().contains(ID)))










        }

        return Data;
    }
}
