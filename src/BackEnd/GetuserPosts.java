package BackEnd;

import java.util.ArrayList;

public class GetuserPosts
{
    public ArrayList<Post> getuserposts (User user)
    {
        ArrayList <String >posts=user.getPostId();
        ArrayList<Post> data=new ArrayList<>();
        ArrayList <Post> all=PostDatabaseManagement.getInstance().getPosts();
        for(int i=0;i<posts.size();i++)
        {
            for (int j=0;j<all.size();j++) {
                if (posts.get(i).equals(all.get(j).getContentId()))
                {
                    data.add(all.get(j));
                }


            }

        }

        return data;

    }
}
