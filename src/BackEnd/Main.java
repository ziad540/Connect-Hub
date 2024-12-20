package BackEnd;

import FrontEnd.GroupsGui;
import FrontEnd.StartWindow;

public class Main {

    public static void main(String[] args) {
//        PrimaryAdmin ziad = new PrimaryAdmin();
//        ziad.DeclineMembershipRequests("G1", "M1");
        Search S = new Search();
        User user = S.getUser("1002");
        Groups g = S.getgroup("G1");
        g.addObserver(user);
        new StartWindow();
        //new GroupsGui("1003");
        //new FrontEnd.FreindGui(new BackEnd.User(),new JFrame());

        //new FrontEnd.AddStoryGui();
        //new FrontEnd.FreindListaftersearchGUI();
    }

}