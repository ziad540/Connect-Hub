import java.util.ArrayList;

public class AcceptFreindRequest {


    public static void  accept_freind_req (User acceptor,User other)
    {
        ArrayList<String> ids=new ArrayList<>();
        decline_freind_request.decline_freind_req(acceptor,other);

        ids=acceptor.getFirndesId();
        ids.add(other.getUserId());

        ids=other.getFirndesId();
        ids.add(acceptor.getUserId());


    }
}
