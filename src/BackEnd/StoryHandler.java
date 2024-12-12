package BackEnd;

import java.util.ArrayList;

public class StoryHandler {
    UserDatabaseManagement u = UserDatabaseManagement.getInstance();
    StoryDatabaseManagement s = StoryDatabaseManagement.getInstance();
    ArrayList<User> users = u.getUsers();
    ArrayList<Story> stories;

    public void deleteExpiredStories(){
        s.loadStoriesFromFile();
        stories = s.getStories();
        for (User user:users){
            ArrayList<String> activeStories = new ArrayList<>();
                for(String id:user.getStoriesId()){
                    for(Story story:stories){
                        if(id.equals(story.getContentId())){
                            if (!story.getExpired()){
                                activeStories.add(id);
                                break;
                            }
                        }
                }
                user.setStoriesId(activeStories);
            }
        }
        u.saveToFile();
    }
}
