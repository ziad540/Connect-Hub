package BackEnd;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"  // This is the property where the type info will be stored in JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FriendreqNotifiaction.class, name = "friendRequest"),
        @JsonSubTypes.Type(value = GroupNotification.class, name = "groupPost")
})


public abstract class Notification {
    private String recieverID;
    private String message;
    private String timestamp;

    public Notification(String message, String timestamp, String recieverID) {
        this.message = message;
        this.timestamp = timestamp;
        this.recieverID = recieverID;
    }


    public Notification() {
    }

    public String getMessage() {
        return message;
    }

    public String getRecieverID() {
        return recieverID;
    }

    public void setRecieverID(String recieverID) {
        this.recieverID = recieverID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public abstract void interact();


}
