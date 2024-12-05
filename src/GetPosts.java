import java.util.ArrayList;

public class GetPosts {
//BENB3AT ARRAY OF FREINDS LE

    public ArrayList<Post> data(ArrayList<User> users) {
        ArrayList<Post> posts=new ArrayList<>();
        ArrayList<Post> allposts = PostDatabaseManagement.getInstance().getPosts();

        for (int i=0;i<users.size();i++)
        {
            ArrayList<String> friendsposts = users.get(i).getPostId();

            for(int j=0;j<friendsposts.size();j++)
            {
                for(int z=0;z<allposts.size();z++)
                {
                    if (friendsposts.get(j).equals(allposts.get(z)))
                    {
                        posts.add(allposts.get(z));
                    }
                }

            }

        }
        return posts;

    }

}
