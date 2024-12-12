package BackEnd;

public interface NormalAdminOperation extends AddPost {
    public void RemoveMember(String memberID, String groupID);
    public void RemovePosts(String groupId, String postId, String memberID);
    void ApproveMembershipRequests(String groupId, String memberId);
    void DeclineMembershipRequests(String groupId, String memberId);
}