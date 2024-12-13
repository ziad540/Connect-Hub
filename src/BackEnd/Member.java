package BackEnd;

import javax.swing.*;
import java.util.ArrayList;

public class Member extends MemberShip implements MemberOperation {

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
        return false;
    }

    @Override
    public boolean canDeleteGroups() {
        return false;
    }

    @Override
    public boolean canRemoveMember() {
        return false;
    }
}

