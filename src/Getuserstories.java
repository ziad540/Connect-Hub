import java.util.ArrayList;

public class Getuserstories {

    public ArrayList<Story> getuserstories (User user)
    {
        ArrayList <String >stories=user.getStoriesId();

        ArrayList<Story> data=new ArrayList<>();

        ArrayList <Story> all=   StoryDatabaseManagement.getInstance().getStories();

        for(int i=0;i<stories.size();i++)
        {
            for (int j=0;j<all.size();j++) {
                if (stories.get(i).equals(all.get(j).getContentId()))
                {
                    data.add(all.get(j));
                }


            }

        }

return data;

    }
}
