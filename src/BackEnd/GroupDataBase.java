package BackEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.ArrayList;

public class GroupDataBase {
    private static volatile GroupDataBase instance;//Singleton design pattern
    private ArrayList<Groups> group;

    private GroupDataBase() {
        group = new ArrayList<>();
        loadGroupsFromFile();
    }

    public static GroupDataBase getInstance() {
        if (instance == null) {
            synchronized (GroupDataBase.class) {
                if (instance == null) {
                    instance = new GroupDataBase();
                }
            }
        }
        return instance;
    }

    public void loadGroupsFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            group = objectMapper.readValue(new File("src/Groups.json"), new TypeReference<ArrayList<Groups>>() {
            });


        } catch (Exception e) {
            System.err.println("Error loading groups: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("src/Groups.json"), group);
        } catch (Exception e) {
            System.err.println("Error writing in JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Groups> getGroups() {
        return group;
    }

    public void addGroup(Groups newgroup) {
        group.add(newgroup);
    }

    public void removeGroup(Groups group) {
        this.group.remove(group);
    }

}

