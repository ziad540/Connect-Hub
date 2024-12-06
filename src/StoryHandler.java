import java.util.ArrayList;

public class StoryHandler {
    UserDatabaseManagement u = UserDatabaseManagement.getInstance();
    StoryDatabaseManagement s = StoryDatabaseManagement.getInstance();
    ArrayList<User> users = UserDatabaseManagement.getInstance().getUsers();
    ArrayList<Story> stories = StoryDatabaseManagement.getInstance().getStories();

    public void deleteExpiredStories(){
        for (User user:users){
            ArrayList<String> activeStories = new ArrayList<>();
            for (int i =0;i<user.getStoriesId().size();i++){
                for(String id:user.getStoriesId()){
                    for(Story story:stories){
                        if(id.equals(story.getContentId())){
                            if (!story.getExpired()){
                                activeStories.add(id);
                                break;
                            }
                        }
                    }
                }
                user.setStoriesId(activeStories);
            }
        }
        u.saveToFile();
    }
}
