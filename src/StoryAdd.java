import java.util.ArrayList;

public class StoryAdd implements AddStory{
    @Override
    public void addStory(ArrayList<Story> stories, Story s) {
        stories.add(s);
        System.out.println("Story Added,,,VAAAAAMMMMMOOOOOOSSSSS");

    }
}
