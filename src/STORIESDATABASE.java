//import java.io.File;
//import java.util.ArrayList;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//public class STORIESDATABASE {
//    private String filename;
//
//    public void write(ArrayList<Story> stories)
//    {  ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            objectMapper.writeValue(new File(filename), stories);
//            System.out.println("JSON file created successfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public ArrayList<Story>  load()
//
//    {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//
//        try {
//
//            ArrayList<Story> stories = objectMapper.readValue(new File("C:\\Users\\Abdallah\\IdeaProjects\\Connect-Hub\\src\\stories.json"), new TypeReference<ArrayList<Story>>() {});
//
//
//            for (Story story : stories) {
//                System.out.println(story);
//            }
//
//            return stories;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//}
