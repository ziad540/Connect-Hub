import java.time.LocalDateTime;

public abstract class Content {
    private String contentId;
    private String autherId;
    private String content;
    private String imagePath;
    LocalDateTime timestamp = LocalDateTime.now();

    public Content(String contentId,String autherId,String content,String imagePath){
       this.contentId=contentId;
       this.autherId=autherId;
       this.content=content;
       this.imagePath=imagePath;
       this.timestamp = LocalDateTime.now();
    }

    public String getContentId() {
        return contentId;
    }

    public String getAutherId() {
        return autherId;
    }

    public String getContent() {
        return content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString(){
    return "Content ID: " + contentId + ", Author ID: " + autherId + ", Content" + content + ", Image Path" + imagePath +  ", Timestamp: " + timestamp;
    }
}


