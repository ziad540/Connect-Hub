import java.io.File;
import java.util.ArrayList;

public class usersdatabase {

    private String filename;


    public void write(ArrayList<User> users)
    {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filename), users);
            System.out.println("JSON file created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<User>  load()

    {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file and map it to an ArrayList of Person objects
            ArrayList<User> Users = objectMapper.readValue(new (filename), new TypeReference<ArrayList<User>>() {});

            // Print the ArrayList
            System.out.println("People read from JSON file:");
            for (User user : users) {
                System.out.println(user);
            }
            return Users;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
