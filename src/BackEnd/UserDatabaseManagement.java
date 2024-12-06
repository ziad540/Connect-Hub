package BackEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.ArrayList;

public class UserDatabaseManagement {

    private static volatile UserDatabaseManagement instance;//Singleton design pattern
    private ArrayList<User> users;

    private UserDatabaseManagement(){
       users = new ArrayList<>();
       loadUsersFromFile();

    }

    public static UserDatabaseManagement getInstance() {

        if(instance==null){
            synchronized (UserDatabaseManagement.class){
                if(instance == null){
                    instance = new UserDatabaseManagement();
                }
            }
        }
        return instance;
    }

    private void loadUsersFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
             users = objectMapper.readValue(new File("src/users.json"), new TypeReference<ArrayList<User>>() {});




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("src/users.json"), users);
        } catch (Exception e) {
            System.err.println("Error writing in JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }



}
