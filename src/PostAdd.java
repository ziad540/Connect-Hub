import java.util.ArrayList;

public class PostAdd implements AddPost{

    public void AddPost(ArrayList<Post> posts, Post p)
    {

        Addpostgui add=new Addpostgui();
         posts.add(p);

    }
}
