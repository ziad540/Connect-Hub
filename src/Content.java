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

    private void setContentId(String contentId) {
        this.contentId = contentId;
    }

    private void setAutherId(String autherId) {
        this.autherId = autherId;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
    public Content()
    {

    }


    @Override
    public String toString(){
    return "Content ID: " + contentId + ", Author ID: " + autherId + ", Content" + content + ", Image Path" + imagePath +  ", Timestamp: " + timestamp;
    }
}


