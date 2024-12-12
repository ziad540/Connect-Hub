package BackEnd;

public interface MembershipManagement {
    void ApproveMembershipRequests(String groupId, String memberId);

    void DeclineMembershipRequests(String groupId, String memberId);
}
