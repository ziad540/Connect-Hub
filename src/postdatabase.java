import java.io.File;
import java.util.ArrayList;

public class postdatabase {
    private String filename;

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

        try {
            // Read JSON file and map it to an ArrayList of Person objects
            ArrayList<Post> posts = objectMapper.readValue(new (filename), new TypeReference<ArrayList<Story>>() {});

            // Print the ArrayList
            System.out.println("People read from JSON file:");
            for (Post post : posts) {
                System.out.println(post);
            }

            return posts;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

