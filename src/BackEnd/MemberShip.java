package BackEnd;

import java.util.ArrayList;

public class MemberShip {
    String memberShipID;
    String userID;
    String status;
    ArrayList<String>postId;

    public MemberShip() {
    }

    public MemberShip(String userID, String status) {
        this.memberShipID = String.valueOf('M' + uniqueId.loadcounterMemberShipID());
        this.userID = userID;
        this.status = status;
    }

    /**
     * Getter and setter
     */
    public String getMemberShipID() {
        return memberShipID;
    }

    public void setMemberShipID(String memberShipID) {
        this.memberShipID = memberShipID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getPostId() {
        return postId;
    }

    public void setPostId(ArrayList<String> postId) {
        this.postId = postId;
    }
}
