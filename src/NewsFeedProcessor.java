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



}
