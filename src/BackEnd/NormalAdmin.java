package BackEnd;

import javax.swing.*;
import java.util.ArrayList;

public class NormalAdmin extends MemberShip implements NormalAdminOperation, MemberOperation {
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
                    if (memberToRemove.getStatus().equals("PrimaryAdmin")) {
                        JOptionPane.showMessageDialog(null, "Dont have a permeation", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    memberShipList.remove(memberToRemove);
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
    public void ApproveMembershipRequests(String groupId, String memberId) {
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
    public void LeaveGroup(String groupId) {
        ArrayList<Groups> groups = GroupDataBase.getInstance().getGroups();
        Groups group = null;
        for (Groups g : groups) {
            if (g.getGroupId().equals(groupId)) {
                group = g;
                break;
            }
        }
        if (group != null) {
            ArrayList<String> memberID = group.getMemberShipId();
            memberID.remove(this.memberShipID);
            GroupDataBase.getInstance().saveToFile();
            MemberShipDataBase.getInstance().saveToFile();
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
        return false;
    }

    @Override
    public boolean canRemoveMember() {
        return true;
    }
}

