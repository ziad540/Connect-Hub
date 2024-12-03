import java.io.File;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class STORIESDATABASE {
    private String filename;

    public void write(ArrayList<Story> stories)
    {  ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filename), stories);
            System.out.println("JSON file created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public ArrayList<Story>  load()

    {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file and map it to an ArrayList of Person objects
            ArrayList<Story> stories = objectMapper.readValue(new (filename), new TypeReference<ArrayList<Story>>() {});

            // Print the ArrayList
            System.out.println("People read from JSON file:");
            for (Story story : stories) {
                System.out.println(story);
            }

            return stories;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
