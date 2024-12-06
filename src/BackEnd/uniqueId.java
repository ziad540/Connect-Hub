package BackEnd;

public class uniqueId {
     static UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();
     public static int loadCounterId(){
        return userDatabaseManagement.getUsers().size()+1;
    }

    public static int loadCOUNTERPOSTID(){
        return  PostDatabaseManagement.getInstance().getPosts().size()+1;


    }

    public static int loadcounterstroiesID ()
    {
        return StoryDatabaseManagement.getInstance().getStories().size()+1;
    }
}
