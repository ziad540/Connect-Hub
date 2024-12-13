package FrontEnd;

import BackEnd.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class GroupDetailsGui {
    GroupOperation operation = new GroupOperation();
    Search search = new Search();
    MemberFactoryImpl memberFactory = new MemberFactoryImpl();

    public GroupDetailsGui(String Id, Groups group, JFrame frame) {
        JFrame frame2 = new JFrame("Group Details - " + group.getGroupName());
        frame2.setSize(600, 800);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
        System.out.println(group.getGroupName());
        MemberShip member = operation.getMemberShip(group.getGroupId(), Id);
        System.out.println(member.getUserID());
        MemberShip memberType = memberFactory.createMember(member.getStatus());

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel groupDetailsPanel = new JPanel();
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

        // هنا يا زيزي عايز ال arraylist ديه يكون جواها كل بوستات الجروب
        ArrayList<String> postId = operation.getPostId(group.getGroupId());
        ArrayList<Post> posts = operation.getObjPost(group.getGroupId());
        if (posts.size() > 0) {
            JLabel postsTitleLabel = new JLabel("Posts", SwingConstants.CENTER);
            postsTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            groupDetailsPanel.add(postsTitleLabel);

            JPanel postsPanel = new JPanel();
            postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

            for (Post post : posts) {
                JPanel postPanel = new JPanel();
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                postPanel.setBackground(Color.WHITE);
/// //////////////////////////////////////////////////////////  بضيف ااسم صاحب البوست
                User postAuthor = search.getUser(Id);
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
                                    frame2.dispose();
                                    new GroupDetailsGui(Id, group, frame);
                                }
                            });

                            deletePost.addActionListener(ae -> {
                                // هنا يا زيزي عايز امسح البوست
                                if (memberType.canDeleteGroups()) {
                                    ((PrimaryAdmin) memberType).RemovePosts(group.getGroupId(), post.getContentId(), member.getMemberShipID());
                                } else {
                                    ((NormalAdmin) memberType).RemovePosts(group.getGroupId(), post.getContentId(), member.getMemberShipID());
                                }
                                JOptionPane.showMessageDialog(null, "Post deleted successfully!");
                                frame2.dispose();
                                new GroupDetailsGui(Id, group, frame);
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

            JScrollPane postsScrollPane = new JScrollPane(postsPanel);
            groupDetailsPanel.add(postsScrollPane);
        } else {
            JLabel noPostsLabel = new JLabel("No posts in this group.", SwingConstants.CENTER);
            groupDetailsPanel.add(noPostsLabel);
        }

        // هنا يا زيزي عايز ال arraylist ديه يكون جواها كل اعضاء الجروب
        ArrayList<String> memberShipUserId = operation.getMemberShipUserIds(group.getGroupId());
        ArrayList<User> members = search.getUsers(memberShipUserId);
        JPanel membersPanel = new JPanel();
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
                        // هنا يا زيزي عايز اتأكد هو صاحب الجروب ولا لأ
                        if (memberType.canDeleteGroups()) {
                            // هنا يا زيزي عايز اتأكد هو عضو عادي ولا لأ  ولا لأ
                            if (!(memberInGroup.canEditOrDeletePosts() || memberInGroup.canDeleteGroups())) {
                                JMenuItem promoteToAdmin = new JMenuItem("Promote to Admin");
                                promoteToAdmin.addActionListener(ae -> {
                                    // هنا عايز اخليه ادمن
                                    ((PrimaryAdmin) memberType).PromoteNewAdmin(group.getGroupId(), membership.getMemberShipID());
                                    JOptionPane.showMessageDialog(null, user.getUserName() + " promoted to Admin!");
                                    frame2.dispose();
                                    new GroupDetailsGui(Id, group, frame);
                                });
                                memberMenu.add(promoteToAdmin);
                                // هنا يا زيزي عايز اتأكد هو ادمن عادي و لا لأ
                            } else if (memberInGroup.canEditOrDeletePosts() && !(memberInGroup.canDeleteGroups())) {
                                JMenuItem demoteToUser = new JMenuItem("Demote to Normal User");
                                demoteToUser.addActionListener(ae -> {
                                    // هنا عايز اخلي العضو ده عضو عادي خالص
                                    ((PrimaryAdmin) memberType).DemoteGroupAdmin(group.getGroupId(), membership.getMemberShipID());
                                    JOptionPane.showMessageDialog(null, user.getUserName() + " demoted to Normal User!");
                                    frame2.dispose();
                                    new GroupDetailsGui(Id, group, frame);
                                });
                                memberMenu.add(demoteToUser);
                            }
                            JMenuItem removeMember = new JMenuItem("Remove Member");
                            removeMember.addActionListener(ae -> {
                                // هنا عايز اطرد العضو ده من المجموعه
                                if (memberInGroup.canDeleteGroups()) {
                                    JOptionPane.showMessageDialog(null, "Do not have permission");
                                } else {
                                    ((PrimaryAdmin) memberType).RemoveMember(membership.getMemberShipID(), group.getGroupId());
                                    JOptionPane.showMessageDialog(null, "Member removed!");
                                    frame2.dispose();
                                    new GroupDetailsGui(Id, group, frame);
                                }
                            });
                            memberMenu.add(removeMember);
                        }
                        //هنا يا زيزي عايز اتأكد هو ادمن
                        else if (memberType.canEditOrDeletePosts() && !(memberType.canDeleteGroups())) {
                            // هنا عايز اتأكد هو عضو عادي ولا لأ
                            if (!(memberInGroup.canEditOrDeletePosts())) {
                                JMenuItem removeMember = new JMenuItem("Remove Member");
                                removeMember.addActionListener(ae -> {
                                    // هنا عايز اطرد العضو ده من المجموعه
                                    ((NormalAdmin) memberType).RemoveMember(membership.getMemberShipID(), group.getGroupId());
                                    JOptionPane.showMessageDialog(null, "Member removed!");
                                    frame2.dispose();
                                    //refresh
                                    new GroupDetailsGui(Id, group, frame);
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

        JScrollPane membersScrollPane = new JScrollPane(membersPanel);
        membersScrollPane.setPreferredSize(new Dimension(200, 0));
        membersScrollPane.setBorder(BorderFactory.createTitledBorder("Group Members"));

        mainPanel.add(groupDetailsPanel, BorderLayout.CENTER);
        mainPanel.add(membersScrollPane, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        JButton returnButton = new JButton();
        ImageIcon icon = new ImageIcon("src/Image/return.png");
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        returnButton.setIcon(scaledIcon);
        returnButton.setPreferredSize(new Dimension(60, 60)); // Adjust button size
        returnButton.setContentAreaFilled(false); // Makes the button background transparent
        returnButton.setBorderPainted(false);    // Removes the border around the button
        returnButton.setFocusPainted(false);     // Removes focus outline

        returnButton.addActionListener(e -> {
            frame2.dispose();
            frame.setVisible(true);
        });
        bottomPanel.add(returnButton);


        JButton refresh = new JButton();
        ImageIcon icon2 = new ImageIcon("src/Image/refresh.png");
        Image scaledImage2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        returnButton.setIcon(scaledIcon2);
        returnButton.setPreferredSize(new Dimension(60, 60));
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
        returnButton.setFocusPainted(false);

        returnButton.addActionListener(e -> {
            Refresh();
        });
        bottomPanel.add(returnButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        frame2.add(mainPanel);
        frame2.setVisible(true);


    private void Refresh() {
        mainPanel.removeAll();
        JPanel updatedGroupDetailsPanel = new JPanel();
        updatedGroupDetailsPanel.setLayout(new BoxLayout(updatedGroupDetailsPanel, BoxLayout.Y_AXIS));

        try {
            ImageIcon groupIcon = new ImageIcon(group.getGroupPhoto());
            Image scaledImage = groupIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel groupImageLabel = new JLabel(new ImageIcon(scaledImage));
            updatedGroupDetailsPanel.add(groupImageLabel);
        } catch (Exception e) {
            updatedGroupDetailsPanel.add(new JLabel("No Group Image"));
        }

        JLabel updatedGroupNameLabel = new JLabel(group.getGroupName(), SwingConstants.CENTER);
        updatedGroupNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        updatedGroupDetailsPanel.add(updatedGroupNameLabel);

        if (!group.getGroupDescription().isEmpty()) {
            JLabel updatedGroupDescriptionLabel = new JLabel(group.getGroupDescription(), SwingConstants.CENTER);
            updatedGroupDescriptionLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            updatedGroupDescriptionLabel.setForeground(Color.DARK_GRAY);
            updatedGroupDetailsPanel.add(updatedGroupDescriptionLabel);
        }

        // Reload posts
        ArrayList<Post> updatedPosts = operation.getObjPost(group.getGroupId());
        if (updatedPosts.size() > 0) {
            JPanel updatedPostsPanel = new JPanel();
            updatedPostsPanel.setLayout(new BoxLayout(updatedPostsPanel, BoxLayout.Y_AXIS));

            for (Post post : updatedPosts) {
                JPanel postPanel = new JPanel();
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                postPanel.setBackground(Color.WHITE);

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

                updatedPostsPanel.add(postPanel);
                updatedPostsPanel.add(new JSeparator());
            }

            JScrollPane updatedPostsScrollPane = new JScrollPane(updatedPostsPanel);
            updatedGroupDetailsPanel.add(updatedPostsScrollPane);
        } else {
            updatedGroupDetailsPanel.add(new JLabel("No posts in this group.", SwingConstants.CENTER));
        }

        // Reload members
        ArrayList<String> updatedMemberShipUserId = operation.getMemberShipUserIds(group.getGroupId());
        ArrayList<User> updatedMembers = search.getUsers(updatedMemberShipUserId);
        JPanel updatedMembersPanel = new JPanel();
        updatedMembersPanel.setLayout(new BoxLayout(updatedMembersPanel, BoxLayout.Y_AXIS));

        for (User user : updatedMembers) {
            JLabel memberLabel = new JLabel(user.getUserName());
            memberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            updatedMembersPanel.add(memberLabel);
        }

        JScrollPane updatedMembersScrollPane = new JScrollPane(updatedMembersPanel);
        updatedMembersScrollPane.setPreferredSize(new Dimension(200, 0));
        updatedMembersScrollPane.setBorder(BorderFactory.createTitledBorder("Group Members"));

        // Add updated components to main panel
        mainPanel.add(updatedGroupDetailsPanel, BorderLayout.CENTER);
        mainPanel.add(updatedMembersScrollPane, BorderLayout.EAST);

        // Revalidate and repaint the frame
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}}


