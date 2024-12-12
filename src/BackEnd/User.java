package BackEnd;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String userId;
    private String email;
    private String userName;
    private String status;
    private LocalDate dateOfBirth;
    private String hashingPassword;
    private ProfileInformation profileInformation;
    private ArrayList<String> firndesId = new ArrayList<>();
    private ArrayList<String> postId = new ArrayList<>();
    private ArrayList<String> storiesId = new ArrayList<>();
    private ArrayList<String> freindrequestId = new ArrayList<>(); //ely gayin leya
    private ArrayList<String> sentfreindrequestId = new ArrayList<>(); //ely ana ba3thom
    private ArrayList<String> blockedID = new ArrayList<>(); //ely ana 3amlehom
    private ArrayList<String> blockedfromID = new ArrayList<>();// elyma3molymenhom
    private UserRelationsManager relationsManage = new UserRelationsManager();


    public User(String email, String userName, String password, String status, LocalDate dateOfBirth) {
        this.userId = String.valueOf(1000 + uniqueId.loadCounterId());
        this.email = email;
        this.userName = userName;
        this.status = status;
        this.dateOfBirth = dateOfBirth;
        profileInformation = new ProfileInformation("src/Image/unknown user.png", "src/Image/unknown cover.png", "");
        hashingPassword = passwordHashing.hashpassword(password);// hashing Password by class BackEnd.passwordHashing

    }

    public User() {
    }

    public ArrayList<String> getSentfreindrequestId() {
        return relationsManage.getSentfreindrequestId();
    }

    public void setSentfreindrequestId(ArrayList<String> sentfreindrequestId) {
        this.sentfreindrequestId = sentfreindrequestId;
    }

    public ArrayList<String> getBlockedfromID() {
        return relationsManage.getBlockedID();
    }

    public void setBlockedfromID(ArrayList<String> blockedfromID) {
        relationsManage.setBlockedID(blockedfromID);
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

    public void setPostId(ArrayList<String> postId) {
        this.postId = postId;
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
        hashingPassword = passwordHashing.hashpassword(password);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getStoriesId() {
        return storiesId;
    }

    public void setStoriesId(ArrayList<String> storiesId) {
        this.storiesId = storiesId;
    }

    public ArrayList<String> getFreindrequestId() {
        return relationsManage.getFreindrequestId();
    }

    public void setFreindrequestId(ArrayList<String> freindrequestId) {
        relationsManage.setFreindrequestId(freindrequestId);
    }

    public ArrayList<String> getBlockedID() {
        return relationsManage.getBlockedID();
    }

    public void setBlockedID(ArrayList<String> blockedID) {
        relationsManage.setBlockedID(blockedID);
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

    public ProfileInformation getProfileInformation() {
        return profileInformation;
    }

    public void setProfileInformation(ProfileInformation profileInformation) {
        this.profileInformation = profileInformation;
    }

}
