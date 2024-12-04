public class Main {
    public static void main(String[] args) {
// add to gui
        new NewsFeedgui();
//        new FreindGui();
//        new Addpost();

        //  Profile profile =new Profile();
        usersdatabase user =new usersdatabase("src/users.json");
        user.load();

        postdatabase post= new postdatabase();
        post.load();

        STORIESDATABASE ST=new STORIESDATABASE();
        ST.load();


    }

}