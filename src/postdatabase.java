import java.io.File;
import java.util.ArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class postdatabase {
    private String filename;
    public postdatabase() {
        this.filename = "src/posts.json";
    }

    public void write(ArrayList<Post> posts)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filename), posts);
            System.out.println("JSON file created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Post>  load()

    {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        try {

            ArrayList<Post> posts = objectMapper.readValue(new File("src/posts.json"), new TypeReference<ArrayList<Post>>() {});


            for (Post post : posts) {
                System.out.println(post);
            }

            return posts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}

