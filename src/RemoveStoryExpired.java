import java.util.ArrayList;

public class RemoveStoryExpired implements RemoveExpiredStory{

    @Override
    public void removeExpiredStories(ArrayList<Story> stories) {
        for (Story story : stories) {
            if (story.isExpired()) {
                stories.remove(story);
            }
        }
    }
}
