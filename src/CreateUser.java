public class CreateUser {
    private int userId;
    private String userName;


    public CreateUser(int userId,String userName) {
        this.userId = userId;
        this.userName=userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
