package BackEnd;

public interface PrimaryAdminOperation extends NormalAdminOperation {
    public void DeleteGroup(String groupID);
    public void PromoteNewAdmin(String groupId, String memberId);
    public void DemoteGroupAdmin(String groupId, String memberId);
}
