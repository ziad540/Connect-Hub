import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.ArrayList;

public class StoryDatabaseManagement {

    private static volatile StoryDatabaseManagement instance;
    //Singleton design pattern
    private ArrayList<Story> stories;

    private StoryDatabaseManagement(){
        stories = new ArrayList<>();
        loadStoriesFromFile();

    }

    public static StoryDatabaseManagement getInstance() {
        if(instance==null){
            synchronized (StoryDatabaseManagement.class){
                if(instance == null){
                    instance = new StoryDatabaseManagement();
                }
            }
        }
        return instance;
    }

    private void loadStoriesFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            stories = objectMapper.readValue(new File("src/stories.json"), new TypeReference<ArrayList<Story>>() {});


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("src/stories.json"), stories);
            System.out.println("JSON file saved successfully!");
        } catch (Exception e) {
            System.err.println("Error writing in JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Story> getUsers(){
        return stories;
    }

    public void addUser(Story story){
        stories.add(story);
    }



}

