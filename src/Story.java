import java.time.Duration;
import java.time.LocalDateTime;


public class Story extends Content {
        public Story(String contentId, String authorId, String content, String imagePath) {
            super(contentId, authorId, content, imagePath);
        }

        public boolean isExpired() {
            Duration duration = Duration.between(getTimestamp(), LocalDateTime.now());
            return (duration.toHours() >= 24);
        }

}

