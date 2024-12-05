public class uniqueId {
     static UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();
     public static int loadCounterId(){
        return userDatabaseManagement.getUsers().size()+1;
    }
}
