import java.util.ArrayList;

public class GetStories {


    public ArrayList<Story> data(ArrayList<User> users) {
      ArrayList<Story> stories=new ArrayList<>();
      ArrayList<Story> allstories= StoryDatabaseManagement.getInstance().getUsers();

      for (int i=0;i<users.size();i++)
      {
          ArrayList<String> friendsstory = users.get(i).getStoriesId();

          for(int j=0;j<friendsstory.size();j++)
          {
              for(int z=0;z<allstories.size();z++)
              {
                  if (friendsstory.get(j).equals(allstories.get(z)))
                  {
                      stories.add(allstories.get(z));
                  }
              }

          }

      }
      return stories;

    }

}
