import java.util.ArrayList;

public class NewsFeedProcessor {

    private ArrayList<Post> posts;

    public NewsFeedProcessor(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Post> getFreindsposts(ArrayList<Post>posts,int id)
    {
        ShowFreindsPosts show=new ShowFreindsPosts();
        return show.getfreindsposts(posts,0);
    }


    public void AddPost(ArrayList<Post>posts,Post p)
    {  AddPost add=new AddPost();
        add.Addpost(posts,p);


    }

    public void Deletepost(ArrayList<Post>posts,Post p)

    {
        Deletepost delete=new Deletepost();
        delete.Deletepost(posts,p);

    }
}
