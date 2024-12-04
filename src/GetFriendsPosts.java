import java.util.ArrayList;

public class GetFriendsPosts implements FreindsPosts{
    public ArrayList<Post> getFreindsposts(ArrayList<Post>posts, int id)
    {
        ShowFreindsPosts show=new ShowFreindsPosts();
        return show.getfreindsposts(posts,0);
    }
}
