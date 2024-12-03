import java.util.ArrayList;

public class Deletepost implements PostDelete{

    public void Deletepost(ArrayList<Post> posts, Post p)
    {
        posts.remove(p);


    }
}
