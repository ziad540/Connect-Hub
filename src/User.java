import java.time.LocalDate;

public class User {
    private static int CounterId=1;
    private  String userId;
    private String email;
    private String userName;
    private String status;
    private LocalDate dateOfBirth;
    private String hashingPassword;
    public User() {
    }

    public User( String email, String userName, String password, String status, LocalDate dateOfBirth) {
        this.userId = String.valueOf(1000+CounterId++);
        this.email = email;
        this.userName = userName;
        this.status = status;
        this.dateOfBirth = dateOfBirth;
        hashingPassword=passwordHashing.hashpassword(password);// hashing Password by class passwordHashing
    }



    public void setUserId(String userId) {
        this.userId = userId;
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

    public static int getCounterId() {
        return CounterId;
    }

    private static void setCounterId(int counterId) {
        CounterId = counterId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHashingPassword() {
        return hashingPassword;
    }

    public void setHashingPassword(String hashingPassword) {
        this.hashingPassword = hashingPassword;
    }
}
