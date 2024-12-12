package BackEnd;

import java.util.ArrayList;

public class GroupOperation {
    Search search = new Search();

    public ArrayList<String> getPostId(String groupId) {
        ArrayList<MemberShip> memberShipList = search.getMemberShips(groupId);
        ArrayList<String> postId = new ArrayList<>();
        for (int i = 0; i < memberShipList.size(); i++) {
            for (int j = 0; j < memberShipList.get(i).getPostId().size(); j++) {
                postId.add(memberShipList.get(i).getPostId().get(j));
            }
        }
        return postId;
    }

    public ArrayList<Groups> getGroups(ArrayList<String> groupsId) {
        return search.getGroups(groupsId);
    }
}
