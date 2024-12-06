import java.util.ArrayList;

public class GetPosts {
//BENB3AT ARRAY OF FREINDS LE

    public ArrayList<Post> data(ArrayList<User> users) {
        ArrayList<Post> posts=new ArrayList<>();
        ArrayList<Post> allposts = PostDatabaseManagement.getInstance().getPosts();

        for (int i=0;i<users.size();i++)
        {
            ArrayList<String> friendsposts = users.get(i).getPostId(); // kol wahed men so7aby

            for(int j=0;j<friendsposts.size();j++)
            {
                for(int z=0;z<allposts.size();z++)
                {
                    if (friendsposts.get(j).equals(allposts.get(z).getContentId()))
                    {
                        System.out.println("ana hena" + friendsposts.get(j));
                        posts.add(allposts.get(z));
                    }
                }

            }

        }
        return posts;

    }

}
