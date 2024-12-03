import java.time.LocalDate;

public class User {
    private static int CounterId=1;
    private final String userId;
    private String email;
    private String userName;
    private String status;
    private LocalDate dateOfBirth;
    private String hashingPassword;
    public User( String email, String userName, String password, String status, LocalDate dateOfBirth) {
        this.userId = String.valueOf(1000+CounterId++);
        this.email = email;
        this.userName = userName;
        this.status = status;
        this.dateOfBirth = dateOfBirth;
        hashingPassword=passwordHashing.hashpassword(password);// hashing Password by class passwordHashing
    }
    public String getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        hashingPassword=passwordHashing.hashpassword(password);
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
