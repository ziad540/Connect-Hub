package BackEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.ArrayList;

public class MemberShipDataBase {
    private static volatile MemberShipDataBase instance;//Singleton design pattern
    private ArrayList<MemberShip> memberShips;

    private MemberShipDataBase() {
        memberShips = new ArrayList<>();
        loadMemberFromFile();
    }

    public static MemberShipDataBase getInstance() {
        if (instance == null) {
            synchronized (MemberShipDataBase.class) {
                if (instance == null) {
                    instance = new MemberShipDataBase();
                }
            }
        }
        return instance;
    }

    public void loadMemberFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            memberShips = objectMapper.readValue(new File("src/MemberShip.json"), new TypeReference<ArrayList<MemberShip>>() {
            });

        } catch (Exception e) {
            System.err.println("Error loading : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("src/MemberShip.json"), memberShips);
        } catch (Exception e) {
            System.err.println("Error writing in JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<MemberShip> getMemberShips() {
        return memberShips;
    }

    public void addMemberShip(MemberShip memberShip) {
        memberShips.add(memberShip);
    }
}
