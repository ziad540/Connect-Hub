package BackEnd;

public interface PostManagement {
    void AddPosts(String groupId, String postId,String memberID);
    void RemovePosts(String groupId, String postId,String memberID);
}
