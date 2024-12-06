import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class loadStories {
    public static void showPosts(JPanel contentPanel, personStoriesGui newsFeedgui,UserDatabaseManagement userDatabaseManagement,ArrayList<Story> posts) {
        String userName ="";
        LocalDate date;
        String content;
        String image;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<User> users = userDatabaseManagement.getUsers();
        String profileimage=null;


        for (int i = 0; i < posts.size(); i++) {
            String userId = posts.get(i).getAutherId();

            for (int j = 0; j < users.size(); j++) {
                if (userId.equals(users.get(j).getUserId())) {
                    userName = users.get(j).getUserName();
                    profileimage=users.get(i).getProfileInformation().getProfilePicPath();
                    break;
                }
            }
            if (!userName.isEmpty()) {
                image = posts.get(i).getImagePath();
                date = LocalDate.from(posts.get(i).getTimestamp());
                String formattedDate = date.format(formatter);
                content = posts.get(i).getContent();



                contentPanel.add(newsFeedgui.createPostPanel(userName, formattedDate, content, image,profileimage));
            }


        }
        //contentPanel.revalidate();
        //contentPanel.repaint();
    }

}
