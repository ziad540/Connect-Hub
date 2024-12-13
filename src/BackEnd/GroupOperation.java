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

    public ArrayList<Post> getObjPost(String groupId) {
        return search.getPosts(getPostId(groupId));
    }

    public MemberShip getMemberShip(String groupId, String userId) {
        ArrayList<MemberShip> memberShipList = search.getMemberShips(groupId);
        for (int i = 0; i < memberShipList.size(); i++) {
            if (memberShipList.get(i).getUserID().equals(userId)) {
                return memberShipList.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> getMemberShipUserIds(String groupId) {
        ArrayList<MemberShip> memberShipList = search.getMemberShips(groupId);
        ArrayList<String> memberShipUserIds = new ArrayList<>();
        for (int i = 0; i < memberShipList.size(); i++) {
            memberShipUserIds.add(memberShipList.get(i).getUserID());
        }
        return memberShipUserIds;
    }

    public MemberShip getMember(String id) {
        ArrayList<MemberShip> memberShipList = MemberShipDataBase.getInstance().getMemberShips();
        for (int i = 0; i < memberShipList.size(); i++) {
            if (memberShipList.get(i).getMemberShipID().equals(id)) {
                return memberShipList.get(i);
            }
        }
        return null;
    }


}
