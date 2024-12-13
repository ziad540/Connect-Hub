package BackEnd;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.swing.*;
import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicMemberShip.class, name = "BasicMemberShip"),
})
public abstract class MemberShip {
    String type = "BasicMemberShip";
    String memberShipID;
    String userID;
    String status;
    ArrayList<String> postId;

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

    public abstract boolean canEditOrDeletePosts();

    public abstract boolean canDeleteGroups();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract boolean canRemoveMember();
}
