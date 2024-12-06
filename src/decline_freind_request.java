//import java.util.ArrayList;
//
//public class decline_freind_request {
//
//    public static void decline_freind_req(User acceptor, User other) {
//        ArrayList<String> ids = acceptor.getFreindrequestId();
//
//        for (int i = 0; i < ids.size(); i++) {
//            if (ids.get(i).equals(other.getUserId())) {
//                ids.remove(i);
//                break;
//            }
//        }
//
//        ids = other.getSentfreindrequestId();
//
//        for (int i = 0; i < ids.size(); i++) {
//            if (ids.get(i).equals(acceptor.getUserId())) {
//                ids.remove(i);
//                break;
//            }
//        }
//
//
//    }
//
//}
