import java.time.Duration;
import java.time.LocalDateTime;


public class Story extends Content implements Expire{
    private Boolean expired;
        public Story(String contentId, String authorId, String content, String imagePath) {
            super(contentId, authorId, content, imagePath);
            expired = isExpired();
        }

        @Override
        public boolean isExpired() {
            Duration duration = Duration.between(getTimestamp(), LocalDateTime.now());
            return (duration.toHours() >= 24);
        }
        public Story()
        {}

}

