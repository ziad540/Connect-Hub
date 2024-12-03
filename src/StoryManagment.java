import java.util.ArrayList;

public class StoryManagment implements AddStory, DeleteStory, RemoveExpiredStory{


    @Override
    public void addStory(ArrayList<Story> stories, Story s) {
       stories.add(s);
        System.out.println("Story Added,,,VAAAAAMMMMMOOOOOOSSSSS");

    }

@Override
public void deleteStory(ArrayList<Story>stories,Story s){
    if (stories.contains(s)) {
        stories.remove(s);
        System.out.println("El Story 2tms7t ya 8aly,,VAAAAAAAAAAAAAAMMMMMMMMMMMOOOOOOOOOOSSSSSSSS");
    }
}

@Override
    public void removeExpiredStories(ArrayList<Story> stories) {
        for (Story story : stories) {
            if (story.isExpired()) {
                stories.remove(story);
            }
        }
    }

    }
