package BackEnd;

import java.util.ArrayList;

public class RemoveStoryExpired implements RemoveExpiredStory {

    @Override
    public ArrayList<Story> removeExpiredStories(ArrayList<Story> allstories) {
        ArrayList<Story> validStories = new ArrayList<>();
        for (Story story : allstories) {
            if (!story.getExpired()) {
                validStories.add(story);
            }
        }
        return validStories;
    }
}
