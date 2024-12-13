package BackEnd;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("BasicMemberShip")
public class BasicMemberShip extends MemberShip {
    public BasicMemberShip() {}

    public BasicMemberShip(String userID, String status) {
        super(userID, status);
    }

    @Override
    public boolean canEditOrDeletePosts() {
        return false;
    }

    @Override
    public boolean canDeleteGroups() {
        return false;
    }

    @Override
    public boolean canRemoveMember() {
        return false;
    }
}
