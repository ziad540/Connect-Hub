//import java.io.File;
//import java.util.ArrayList;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//public class usersdatabase {
//
//    private String filename;
//
//    public usersdatabase(String filename) {
//        this.filename = "src/users.json";
//    }
//
//    public void write(ArrayList<User> users) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        try {
//            objectMapper.writeValue(new File("src/users.json"), users);
//            System.out.println("JSON file created successfully!");
//        } catch (Exception e) {
//            System.err.println("Error writing JSON file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<User> load() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//
//        try {
//            ArrayList<User> users = objectMapper.readValue(
//                    new File("src/users.json"),
//                    new TypeReference<ArrayList<User>>() {
//                    }
//            );
//
//
//            for (User user : users) {
//                System.out.println(user.getUserId() + " - " + user.getUserName() + " " + user.getEmail() + " " + user.getDateOfBirth());
//            }
//            return users;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
