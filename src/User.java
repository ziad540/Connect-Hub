import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private  String userId;
    private String email;
    private String userName;
    private String status;
    private LocalDate dateOfBirth;
    private String hashingPassword;
    private ProfileInformation profileInformation;
    //private UserDatabaseManagement userDatabaseManagement = UserDatabaseManagement.getInstance();
   // private usersdatabase userDatabase=new usersdatabase("src/users.json");
    private ArrayList<String>firndesId=new ArrayList<>();
    private ArrayList<String>postId=new ArrayList<>();
    public User() {
    }

    public ArrayList<String> getFirndesId() {
        return firndesId;
    }

    private void setFirndesId(ArrayList<String> firndesId) {
        this.firndesId = firndesId;
    }

    public ArrayList<String> getPostId() {
        return postId;
    }

    private void setPostId(ArrayList<String> postId) {
        this.postId = postId;
    }
    public User(String email, String userName, String password, String status, LocalDate dateOfBirth) {
        this.userId =String.valueOf(1000/*+loadCounterId()*/);
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
    // to give unique id to new user
    /*private int loadCounterId() {
        ArrayList<User>users=userDatabaseManagement.getUsers();
        return users.size()+1;
    }*/

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

    public ProfileInformation getProfileInformation() {
        return profileInformation;
    }

    public void setProfileInformation(ProfileInformation profileInformation) {
        this.profileInformation = profileInformation;
    }
}
