package BackEnd;

import javax.swing.*;
import java.util.ArrayList;

public class PrimaryAdmin extends MemberShip implements PrimaryAdminOperation {
    public PrimaryAdmin(String userID) {
        super(userID, "PrimaryAdmin");
    }

    public PrimaryAdmin() {

    }

    @Override
    public void RemoveMember(String memberID, String groupID) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupID)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            ArrayList<String> memberShips = group.getMemberShipId();
            if (memberShips.contains(memberID)) {
                memberShips.remove(memberID);
                ArrayList<MemberShip> memberShipList = MemberShipDataBase.getInstance().getMemberShips();
                MemberShip memberToRemove = null;
                for (MemberShip member : memberShipList) {
                    if (member.getMemberShipID().equals(memberID)) {
                        memberToRemove = member;
                        break;
                    }
                }
                if (memberToRemove != null) {
                    memberShipList.remove(memberToRemove);

                    Search s =new Search();

                    GroupOperation OP=new GroupOperation();
                    MemberShip m=  OP.getMember(memberID);
                    User l=   s.getUser(m.getUserID());
                    group.removeObserver(l);
                    MemberShipDataBase.getInstance().saveToFile();
                    GroupDataBase.getInstance().saveToFile();
                } else {
                    JOptionPane.showMessageDialog(null, "Member not found in database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Member does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Group does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void DeleteGroup(String groupId) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            GroupDataBase.getInstance().removeGroup(group);
            GroupDataBase.getInstance().saveToFile();
        } else {
            JOptionPane.showMessageDialog(null, "Group does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void AddPosts(String groupId, String postId, String memberID) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            ArrayList<String> memberShips = group.getMemberShipId();
            if (memberShips.contains(memberID)) {
                ArrayList<MemberShip> memberShipList = MemberShipDataBase.getInstance().getMemberShips();
                MemberShip memberToAddPost = null;
                for (MemberShip member : memberShipList) {
                    if (member.getMemberShipID().equals(memberID)) {
                        memberToAddPost = member;
                        break;
                    }
                }
                if (memberToAddPost != null) {
                    ArrayList<String> memberPost = memberToAddPost.getPostId();
                    memberPost.add(postId);
                    MemberShipDataBase.getInstance().saveToFile();
                    group.notifyObservers("New posts aded in "+group.getGroupName());
                    GroupDataBase.getInstance().saveToFile();
                } else {
                    JOptionPane.showMessageDialog(null, "Member not found in database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Member does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Group does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void RemovePosts(String groupId, String postId, String memberID) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            ArrayList<String> memberShips = group.getMemberShipId();
            if (memberShips.contains(memberID)) {
                ArrayList<MemberShip> memberShipList = MemberShipDataBase.getInstance().getMemberShips();
                MemberShip memberToAddPost = null;
                for (MemberShip member : memberShipList) {
                    if (member.getMemberShipID().equals(memberID)) {
                        memberToAddPost = member;
                        break;
                    }
                }
                if (memberToAddPost != null) {
                    ArrayList<String> memberPost = memberToAddPost.getPostId();
                    if (memberPost.contains(postId)) {
                        memberPost.remove(postId);
                        MemberShipDataBase.getInstance().saveToFile();
                        GroupDataBase.getInstance().saveToFile();
                    } else {
                        JOptionPane.showMessageDialog(null, "post not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Member not found in database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Member does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Group does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void ApproveMembershipRequests(String groupId, String memberId)
    {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            ArrayList<String> pendingId = group.getPendingRequestId();
            if (pendingId.contains(memberId)) {
                pendingId.remove(memberId);
                ArrayList<String> memberShipId = group.getMemberShipId();
                memberShipId.add(memberId);
                Search s =new Search();

                GroupOperation OP=new GroupOperation();
                      MemberShip m=  OP.getMember(memberId);
                     User l=   s.getUser(m.getUserID());

               group.notifyObservers("new member to our group "+l.getUserName());

               group.addObserver(l);
                MemberShipDataBase.getInstance().saveToFile();
                GroupDataBase.getInstance().saveToFile();
            } else {
                JOptionPane.showMessageDialog(null, "Member does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Group does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void DeclineMembershipRequests(String groupId, String memberId) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            ArrayList<String> pendingId = group.getPendingRequestId();
            if (pendingId.contains(memberId)) {
                pendingId.remove(memberId);
                MemberShipDataBase.getInstance().saveToFile();
                GroupDataBase.getInstance().saveToFile();
            } else {
                JOptionPane.showMessageDialog(null, "Member does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Group does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void PromoteNewAdmin(String groupId, String memberId) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            if (group.getMemberShipId().contains(memberId)) {
                ArrayList<MemberShip> memberShipList = MemberShipDataBase.getInstance().getMemberShips();
                MemberShip Member = null;
                for (int i = 0; i < memberShipList.size(); i++) {
                    if (memberShipList.get(i).getMemberShipID().equals(memberId)) {
                        Member = memberShipList.get(i);
                        break;
                    }
                }
                if (Member.getStatus().equals("NormalAdmin")) {
                    JOptionPane.showMessageDialog(null, "member already admin", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Member.setStatus("NormalAdmin");
                    MemberShipDataBase.getInstance().saveToFile();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Member not found in group", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Group does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void DemoteGroupAdmin(String groupId, String memberId) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            if (group.getMemberShipId().contains(memberId)) {
                ArrayList<MemberShip> memberShipList = MemberShipDataBase.getInstance().getMemberShips();
                MemberShip Member = null;
                for (MemberShip member : memberShipList) {
                    if (member.getMemberShipID().equals(memberId)) {
                        Member = member;
                        break;
                    }
                }
                if (Member.getStatus().equals("Member")) {
                    JOptionPane.showMessageDialog(null, "member already not admin", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Member.setStatus("Member");
                    MemberShipDataBase.getInstance().saveToFile();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Member not found in group", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Group does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public boolean canEditOrDeletePosts() {
        return true;
    }

    @Override
    public boolean canDeleteGroups() {
        return true;
    }

    @Override
    public boolean canRemoveMember() {
        return true;
    }
}

