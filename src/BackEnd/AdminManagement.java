package BackEnd;

public interface AdminManagement {
    void PromoteNewAdmin(String groupId, String memberId);

    void DemoteGroupAdmin(String groupId, String memberId);
}
