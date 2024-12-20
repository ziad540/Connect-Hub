package BackEnd;

import java.util.ArrayList;

public class Groups implements Subject {
    private String groupName;
    private String groupDescription;
    private String groupOwnerId;
    private String groupId;
    private String groupPhoto;
    private ArrayList<String> memberShipId;
    private ArrayList<String> pendingRequestId;

    private ArrayList<Observer> observers = new ArrayList<>();

    public Groups() {

    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public Groups(String groupName, String groupDescription, String groupOwnerId, String groupPhoto) {
        groupId = "G" + uniqueId.loadcounterGroupsID();
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.groupOwnerId = groupOwnerId;
        if (groupPhoto != null) {
            this.groupPhoto = groupPhoto;
        }
        else{
            this.groupPhoto = "";
        }
        memberShipId = new ArrayList<>();
        pendingRequestId = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);

    }
//add post,add user
    @Override
    public void notifyObservers(String postMessage) {
        for (Observer observer : observers) {
            observer.update(postMessage);
        }

    }

    /**
     * Getter and setter
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(String groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public void setGroupPhoto(String groupPhoto) {
        this.groupPhoto = groupPhoto;
    }

    public ArrayList<String> getMemberShipId() {
        return memberShipId;
    }

    public void setMemberShipId(ArrayList<String> memberShipId) {
        this.memberShipId = memberShipId;
    }

    public ArrayList<String> getPendingRequestId() {
        return pendingRequestId;
    }

    public void setPendingRequestId(ArrayList<String> pendingRequestId) {
        this.pendingRequestId = pendingRequestId;
    }
}
