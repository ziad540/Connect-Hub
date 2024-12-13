package BackEnd;

public class uniqueId {
    ;

    public static int loadCounterId() {
        return UserDatabaseManagement.getInstance().getUsers().size() + 1;
    }

    public static int loadCOUNTERPOSTID() {
        return PostDatabaseManagement.getInstance().getPosts().size() + 1;
    }

    public static int loadcounterstroiesID() {
        return StoryDatabaseManagement.getInstance().getStories().size() + 1;
    }

    public static int loadcounterGroupsID() {
        return GroupDataBase.getInstance().getGroups().size() + 1;
    }

    public static int loadcounterMemberShipID() {
        return MemberShipDataBase.getInstance().getMemberShips().size()+1;
    }

}
