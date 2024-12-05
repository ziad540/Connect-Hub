import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class loadPosts {
    public static void showPosts(JPanel contentPanel, NewsFeedgui newsFeedgui,UserDatabaseManagement userDatabaseManagement,PostDatabaseManagement postDatabaseManagement) {
        String userName ="";
        LocalDate date;
        String content;
        String image;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<User> users = userDatabaseManagement.getUsers();
        ArrayList<Post> posts = postDatabaseManagement.getPosts();

        for (int i = 0; i < posts.size(); i++) {
            String userId = posts.get(i).getAutherId();

            for (int j = 0; j < users.size(); j++) {
                if (userId.equals(users.get(j).getUserId())) {
                    userName = users.get(j).getUserName();
                    break;
                }
            }
            if (!userName.isEmpty()) {
                image = posts.get(i).getImagePath();
                date = LocalDate.from(posts.get(i).getTimestamp());
                String formattedDate = date.format(formatter);
                content = posts.get(i).getContent();


                contentPanel.add(newsFeedgui.createPostPanel(userName, formattedDate, content, image));
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
