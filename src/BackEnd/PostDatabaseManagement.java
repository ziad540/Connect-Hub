package BackEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.ArrayList;

public class PostDatabaseManagement {

    private static volatile PostDatabaseManagement instance;//Singleton design pattern
    private ArrayList<Post> posts;

    private PostDatabaseManagement(){
        posts = new ArrayList<>();
        loadStoriesFromFile();

    }

    public static PostDatabaseManagement getInstance() {
        if(instance==null){
            synchronized (PostDatabaseManagement.class){
                if(instance == null){
                    instance = new PostDatabaseManagement();
                }
            }
        }
        return instance;
    }

    private void loadStoriesFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            posts = objectMapper.readValue(new File("src/posts.json"), new TypeReference<ArrayList<Post>>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("src/posts.json"), posts);

        } catch (Exception e) {
            System.err.println("Error writing in JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Post> getPosts(){
        return posts;
    }

    public void addPost(Post post){
        posts.add(post);
    }



}
