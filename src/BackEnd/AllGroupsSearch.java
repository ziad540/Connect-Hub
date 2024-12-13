package BackEnd;

import java.util.ArrayList;

public class AllGroupsSearch implements SearchGroupStrategy {
    @Override
    public ArrayList<Groups> searchforgroups(String name, String ID) {
        ArrayList<MemberShip> member = MemberShipDataBase.getInstance().getMemberShips();
        Search search = new Search();
        User user = search.getUser(ID);
        ArrayList<String> group = user.getGroupId();
        ArrayList<Groups> all = GroupDataBase.getInstance().getGroups();
        ArrayList<Groups> Data = new ArrayList<>();
        Boolean flag = true;
        for (int i = 0; i < all.size(); i++) {
            flag = true;
            for (int j = 0; j < group.size(); j++) {
                if ((all.get(i).getGroupId().equals(group.get(j)))) {
                    flag = false;
                }
            }
            if (flag) {
                Data.add(all.get(i));
            }
        }
        return Data;
    }
}