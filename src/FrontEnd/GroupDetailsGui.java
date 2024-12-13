package FrontEnd;

import BackEnd.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GroupDetailsGui {
    GroupOperation operation = new GroupOperation();
    Search search = new Search();
    MemberFactoryImpl memberFactory = new MemberFactoryImpl();
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel groupDetailsPanel = new JPanel();
    JPanel membersPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JButton returnButton = new JButton();
    JButton AddPostButton = new JButton();
    JButton refresh = new JButton();
    JPanel postsPanel = new JPanel();
    JScrollPane postsScrollPane;
    JScrollPane membersScrollPane;
    String userID;
    MemberShip member;


    public GroupDetailsGui(String Id, Groups group, JFrame frame) {
        userID = Id;
        JFrame frame2 = new JFrame("Group Details - " + group.getGroupName());
        frame2.setSize(600, 800);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println(group.getGroupName());
        member = operation.getMemberShip(group.getGroupId(), Id);
        System.out.println(member.getUserID());
        MemberShip memberType = memberFactory.createMember(member.getStatus());


        groupDetailsPanel.setLayout(new BoxLayout(groupDetailsPanel, BoxLayout.Y_AXIS));

        try {
            ImageIcon groupIcon = new ImageIcon(group.getGroupPhoto());
            Image scaledImage = groupIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel groupImageLabel = new JLabel(new ImageIcon(scaledImage));
            groupDetailsPanel.add(groupImageLabel);
        } catch (Exception e) {
            groupDetailsPanel.add(new JLabel("No Group Image"));
        }

        JLabel groupNameLabel = new JLabel(group.getGroupName(), SwingConstants.CENTER);
        groupNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        groupDetailsPanel.add(groupNameLabel);

        if (!group.getGroupDescription().isEmpty()) {
            JLabel groupDescriptionLabel = new JLabel(group.getGroupDescription(), SwingConstants.CENTER);
            groupDescriptionLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            groupDescriptionLabel.setForeground(Color.DARK_GRAY);
            groupDetailsPanel.add(groupDescriptionLabel);
        }
        JLabel postsTitleLabel = new JLabel("Posts", SwingConstants.CENTER);
        postsTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        groupDetailsPanel.add(postsTitleLabel);

        // هنا يا زيزي عايز ال arraylist ديه يكون جواها كل بوستات الجروب
        ArrayList<String> postId = operation.getPostId(group.getGroupId());
        ArrayList<Post> posts = operation.getObjPost(group.getGroupId());
        createPostPanel(posts, memberType, mainPanel, frame2, group);
        postsScrollPane = new JScrollPane(postsPanel);
        mainPanel.add(postsScrollPane, BorderLayout.CENTER);
        // هنا يا زيزي عايز ال arraylist ديه يكون جواها كل اعضاء الجروب
        ArrayList<String> memberShipUserId = operation.getMemberShipUserIds(group.getGroupId());
        ArrayList<User> members = search.getUsers(memberShipUserId);


        createMemberPanel(members, memberType, frame2, group, membersPanel);

        membersScrollPane = new JScrollPane(membersPanel);
        membersScrollPane.setPreferredSize(new Dimension(200, 0));
        membersScrollPane.setBorder(BorderFactory.createTitledBorder("Group Members"));

        mainPanel.add(groupDetailsPanel, BorderLayout.NORTH);
        mainPanel.add(membersScrollPane, BorderLayout.EAST);


        createBottomPanel(returnButton, refresh, AddPostButton, frame2, frame, group, Id);
        bottomPanel.add(returnButton);
        bottomPanel.add(refresh);
        bottomPanel.add(AddPostButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame2.add(mainPanel);
        frame2.setVisible(true);

    }

    private void Refresh(String iD, Groups group, JFrame frame2) {
        MemberShip member = operation.getMemberShip(group.getGroupId(), iD);
        MemberShip memberType = memberFactory.createMember(member.getStatus());
        ArrayList<String> postId = operation.getPostId(group.getGroupId());
        ArrayList<Post> posts = operation.getObjPost(group.getGroupId());
        System.out.println(postId.size() + "ADASD");
        ArrayList<String> memberShipUserId = operation.getMemberShipUserIds(group.getGroupId());
        ArrayList<User> members = search.getUsers(memberShipUserId);
        System.out.println(members.size() + "              aaaaa");
        postsPanel.removeAll();
        membersPanel.removeAll();
        createPostPanel(posts, memberType, mainPanel, frame2, group);
        createMemberPanel(members, memberType, frame2, group, membersPanel);
        postsScrollPane.revalidate();
        postsScrollPane.repaint();
        mainPanel.add(postsScrollPane, BorderLayout.CENTER);
        membersScrollPane.revalidate();
        membersScrollPane.repaint();
        mainPanel.add(membersScrollPane, BorderLayout.EAST);
        frame2.invalidate();
        frame2.validate();
        frame2.repaint();
    }

    public void createPostPanel(ArrayList<Post> posts, MemberShip memberType, JPanel mainPanel, JFrame frame2, Groups group) {

        if (posts.size() > 0) {

            postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

            for (Post post : posts) {
                JPanel postPanel = new JPanel();
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                postPanel.setBackground(Color.WHITE);
                /// //////////////////////////////////////////////////////////  بضيف ااسم صاحب البوست
                User postAuthor = search.getUser(post.getAutherId());
                JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                authorPanel.setBackground(Color.WHITE);
                try {
                    ImageIcon userIcon = new ImageIcon();         //عايز اضيف صوره ال user
                    Image scaledUserImage = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    JLabel userPhotoLabel = new JLabel(new ImageIcon(scaledUserImage));
                    authorPanel.add(userPhotoLabel);
                } catch (Exception e) {
                }

                JLabel userNameLabel = new JLabel(postAuthor.getUserName());
                userNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
                authorPanel.add(userNameLabel);

                postPanel.add(authorPanel);


                JLabel postContentLabel = new JLabel(post.getContent());
                postContentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                postPanel.add(postContentLabel);

                if (post.getImagePath() != null) {
                    try {
                        ImageIcon postIcon = new ImageIcon(post.getImagePath());
                        Image scaledPostImage = postIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        JLabel postImageLabel = new JLabel(new ImageIcon(scaledPostImage));
                        postPanel.add(postImageLabel);
                    } catch (Exception e) {
                        postPanel.add(new JLabel("No Post Image"));
                    }
                }

                postPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {

                        // هنا يا زيزي عايز اتأكد هل صاحب ال Id ده صاحب الجروب او ادمن ولا لأ
                        if (SwingUtilities.isRightMouseButton(e) && (memberType.canEditOrDeletePosts())) {
                            JPopupMenu postMenu = new JPopupMenu();
                            JMenuItem editPost = new JMenuItem("Edit Post");
                            JMenuItem deletePost = new JMenuItem("Delete Post");

                            editPost.addActionListener(ae -> {
                                String newContent = JOptionPane.showInputDialog("Edit Post Content:", post.getContent());
                                if (newContent != null && !newContent.trim().isEmpty()) {
                                    // هنا يا زيزي عايز اعدل البوست  و اغير محتواه
                                    post.setContent(newContent);
                                    JOptionPane.showMessageDialog(null, "Post updated successfully!");
//                                    new GroupDetailsGui(Id, group, frame);
                                    Refresh(userID, group, frame2);
                                }
                            });

                            deletePost.addActionListener(ae -> {
                                MemberShip member = operation.getMemberShip(group.getGroupId(), post.getAutherId());
                                // هنا يا زيزي عايز امسح البوست
                                if (memberType.canDeleteGroups()) {

                                    ((PrimaryAdmin) memberType).RemovePosts(group.getGroupId(), post.getContentId(), member.getMemberShipID());
                                } else {
                                    ((NormalAdmin) memberType).RemovePosts(group.getGroupId(), post.getContentId(), member.getMemberShipID());
                                }
                                JOptionPane.showMessageDialog(null, "Post deleted successfully!");
                                Refresh(userID, group, frame2);
                            });

                            postMenu.add(editPost);
                            postMenu.add(deletePost);
                            postMenu.show(postPanel, e.getX(), e.getY());
                        }
                    }
                });

                postsPanel.add(postPanel);
                postsPanel.add(new JSeparator());
            }
//            JScrollPane postsScrollPane = new JScrollPane(postsPanel);
//            mainPanel.add(postsScrollPane, BorderLayout.CENTER);
        } else {
            JLabel noPostsLabel = new JLabel("No posts in this group.", SwingConstants.CENTER);
            mainPanel.add(noPostsLabel, BorderLayout.CENTER);
        }
    }

    public void createMemberPanel(ArrayList<User> members, MemberShip memberType, JFrame frame2, Groups group, JPanel membersPanel) {
        membersPanel.setLayout(new BoxLayout(membersPanel, BoxLayout.Y_AXIS));
        for (User user : members) {
            MemberShip membership = operation.getMemberShip(group.getGroupId(), user.getUserId());
            MemberShip memberInGroup = memberFactory.createMember(membership.getStatus());
            JLabel memberLabel = new JLabel(user.getUserName());
            memberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            memberLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            memberLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        System.out.println("Mouse clicked!");
                        JPopupMenu memberMenu = new JPopupMenu();

                        // إضافة "View Profile"
                        JMenuItem viewProfile = new JMenuItem("View Profile");
                        viewProfile.addActionListener(ae -> {
                            frame2.setVisible(false);
                            FriendProfile friendProfile = new FriendProfile(frame2, user);

                        });
                        memberMenu.add(viewProfile);
                        // هنا يا زيزي عايز اتأكد هو صاحب الجروب ولا لأ
                        if (memberType.canDeleteGroups()) {
                            // هنا يا زيزي عايز اتأكد هو عضو عادي ولا لأ  ولا لأ
                            if (!(memberInGroup.canEditOrDeletePosts() || memberInGroup.canDeleteGroups())) {
                                JMenuItem promoteToAdmin = new JMenuItem("Promote to Admin");
                                promoteToAdmin.addActionListener(ae -> {
                                    // هنا عايز اخليه ادمن
                                    ((PrimaryAdmin) memberType).PromoteNewAdmin(group.getGroupId(), membership.getMemberShipID());
                                    JOptionPane.showMessageDialog(null, user.getUserName() + " promoted to Admin!");
                                    Refresh(userID, group, frame2);
//                                    new GroupDetailsGui(Id, group, frame);
                                });
                                memberMenu.add(promoteToAdmin);
                                // هنا يا زيزي عايز اتأكد هو ادمن عادي و لا لأ
                            } else if (memberInGroup.canEditOrDeletePosts() && !(memberInGroup.canDeleteGroups())) {
                                JMenuItem demoteToUser = new JMenuItem("Demote to Normal User");
                                demoteToUser.addActionListener(ae -> {
                                    // هنا عايز اخلي العضو ده عضو عادي خالص
                                    ((PrimaryAdmin) memberType).DemoteGroupAdmin(group.getGroupId(), membership.getMemberShipID());
                                    JOptionPane.showMessageDialog(null, user.getUserName() + " demoted to Normal User!");
//                                    new GroupDetailsGui(Id, group, frame);
                                    Refresh(userID, group, frame2);
                                });
                                memberMenu.add(demoteToUser);

                            } else if (!(user.getUserId().equals(member.getUserID()))) {
                                JMenuItem removeMember = new JMenuItem("Remove Member");
                                removeMember.addActionListener(ae -> {
                                    // هنا عايز اطرد العضو ده من المجموعه
                                    if (memberInGroup.canDeleteGroups()) {
                                        JOptionPane.showMessageDialog(null, "Do not have permission");
                                    } else {
                                        ((PrimaryAdmin) memberType).RemoveMember(membership.getMemberShipID(), group.getGroupId());
                                        JOptionPane.showMessageDialog(null, "Member removed!");
                                        Refresh(userID, group, frame2);
                                    }
                                });
                                memberMenu.add(removeMember);
                            }
                        }
                        //هنا يا زيزي عايز اتأكد هو ادمن
                        else if (memberType.canEditOrDeletePosts() && !(memberType.canDeleteGroups()) && !(user.getUserId().equals(member.getUserID()))) {
                            // هنا عايز اتأكد هو عضو عادي ولا لأ
                            if (!(memberInGroup.canEditOrDeletePosts())) {
                                JMenuItem removeMember = new JMenuItem("Remove Member");
                                removeMember.addActionListener(ae -> {
                                    // هنا عايز اطرد العضو ده من المجموعه
                                    ((NormalAdmin) memberType).RemoveMember(membership.getMemberShipID(), group.getGroupId());
                                    JOptionPane.showMessageDialog(null, "Member removed!");
                                    //refresh
                                    Refresh(userID, group, frame2);
                                });
                                memberMenu.add(removeMember);
                            } else {
                                JOptionPane.showMessageDialog(null, "Do not have permission");
                            }
                        }

                        memberMenu.show(memberLabel, e.getX(), e.getY());
                    }
                }
            });

            membersPanel.add(memberLabel);

        }
    }

    public void createBottomPanel(JButton returnButton, JButton refresh, JButton AddPostButton, JFrame frame2, JFrame frame, Groups group, String id) {
        ImageIcon icon = new ImageIcon("src/Image/return.png");
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        returnButton.setIcon(scaledIcon);
        returnButton.setPreferredSize(new Dimension(60, 60));
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
        returnButton.setFocusPainted(false);

        returnButton.addActionListener(e -> {
            frame2.dispose();
            frame.setVisible(true);
        });

        ImageIcon icon2 = new ImageIcon("src/Image/refresh.png");
        Image scaledImage2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        refresh.setIcon(scaledIcon2);
        refresh.setPreferredSize(new Dimension(60, 60));
        refresh.setContentAreaFilled(false);
        refresh.setBorderPainted(false);
        refresh.setFocusPainted(false);
        refresh.addActionListener(e -> {
            Refresh(userID, group, frame2);
        });



        ImageIcon icon3 = new ImageIcon("src/Image/new-post.png");
        Image scaledImage3 = icon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
        AddPostButton.setIcon(scaledIcon3);
        AddPostButton.setPreferredSize(new Dimension(60, 60));
        AddPostButton.setContentAreaFilled(false);
        AddPostButton.setBorderPainted(false);
        AddPostButton.setFocusPainted(false);
        AddPostButton.addActionListener(e -> {
            frame2.setVisible(false);
            new AddPostGroupGui(member.getMemberShipID(), frame2);
            Refresh(userID, group, frame2);
        });

        MemberShip member = operation.getMemberShip(group.getGroupId(), id);
        MemberShip memberType = memberFactory.createMember(member.getStatus());

        if (memberType.canDeleteGroups()) {
            JButton deleteGroupButton = new JButton("Delete Group");
            deleteGroupButton.setFont(new Font("Arial", Font.BOLD, 12));
            deleteGroupButton.setForeground(Color.RED);
            deleteGroupButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(frame2, "Are you sure you want to delete this group?", "Delete Group", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ((PrimaryAdmin) memberType).DeleteGroup(group.getGroupId());
                    JOptionPane.showMessageDialog(frame2, "Group deleted successfully!");
                    frame2.dispose();
                    frame.setVisible(true);
                }
            });
            bottomPanel.add(deleteGroupButton);
        } else if (!(memberType.canEditOrDeletePosts())) {
            JButton leaveGroupButton = new JButton("Leave Group");
            leaveGroupButton.setFont(new Font("Arial", Font.BOLD, 12));
            leaveGroupButton.setForeground(Color.RED);
            leaveGroupButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(frame2, "Are you sure you want to leave this group?", "Leave Group", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    /// هنا يا زيزي عايز الشخص ده عايز يطلع من المجموعه     مع العلم ان الشخص ده ممكن يكون Normal User او Other Admin
//                    memberType.leaveGroup(group.getGroupId(), member.getMemberShipID());
                    JOptionPane.showMessageDialog(frame2, "You have left the group.");
                    frame2.dispose();
                    frame.setVisible(true);
                }
            });
            bottomPanel.add(leaveGroupButton);
        }

        bottomPanel.add(returnButton);
        bottomPanel.add(refresh);
        bottomPanel.add(AddPostButton);
    }

}






