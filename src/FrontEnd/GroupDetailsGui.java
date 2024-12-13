import BackEnd.Groups;
import BackEnd.Post;
import BackEnd.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GroupDetailsGui {

    public GroupDetailsGui(String Id, Groups group, JFrame frame) {
        JFrame frame2 = new JFrame("Group Details - " + group.getGroupName());
        frame2.setSize(800, 400);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setLocationRelativeTo(null);

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
        ArrayList<Post> posts = group.getPosts();
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
                        if (SwingUtilities.isRightMouseButton(e) && (group.isPrimaryAdmin(Id) || group.isOtherAdmin(Id))) {
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
                                group.removePost(post);
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
        ArrayList<User> members = group.getMembers();
        JPanel membersPanel = new JPanel();
        membersPanel.setLayout(new BoxLayout(membersPanel, BoxLayout.Y_AXIS));

        for (User member : members) {
            JLabel memberLabel = new JLabel(member.getUserName());
            memberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            memberLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            memberLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        JPopupMenu memberMenu = new JPopupMenu();
                        // هنا يا زيزي عايز اتأكد هو صاحب الجروب ولا لأ
                        if (group.isPrimaryAdmin(Id)) {
                            // هنا يا زيزي عايز اتأكد هو عضو عادي ولا لأ  ولا لأ
                            if (member.isNormalUser()) {
                                JMenuItem promoteToAdmin = new JMenuItem("Promote to Admin");
                                promoteToAdmin.addActionListener(ae -> {
                                    // هنا عايز اخليه ادمن
                                    member.setOtherAdmin();
                                    JOptionPane.showMessageDialog(null, member.getUserName() + " promoted to Admin!");
                                    frame2.dispose();
                                    new GroupDetailsGui(Id, group, frame);
                                });
                                memberMenu.add(promoteToAdmin);
                                // هنا يا زيزي عايز اتأكد هو ادمن عادي و لا لأ

                            } else if (member.isOtherAdmin()) {
                                JMenuItem demoteToUser = new JMenuItem("Demote to Normal User");
                                demoteToUser.addActionListener(ae -> {
                                    // هنا عايز اخلي العضو ده عضو عادي خالص
                                    member.setNormalUser();
                                    JOptionPane.showMessageDialog(null, member.getUserName() + " demoted to Normal User!");
                                    frame2.dispose();
                                    new GroupDetailsGui(Id, group, frame);
                                });
                                memberMenu.add(demoteToUser);
                            }
                            JMenuItem removeMember = new JMenuItem("Remove Member");
                            removeMember.addActionListener(ae -> {
                                // هنا عايز اطرد العضو ده من المجموعه
                                group.removeMember(member);
                                JOptionPane.showMessageDialog(null, "Member removed!");
                                frame2.dispose();
                                new GroupDetailsGui(Id, group, frame);
                            });
                            memberMenu.add(removeMember);
                            // هنا يا زيزي عايز اتأكد هو ادمن
                        } else if (group.isOtherAdmin(Id) && !group.isPrimaryAdmin(member.getUserId())) {
                            // هنا عايز اتأكد هو عضو عادي ولا لأ
                            if (member.isNormalUser()) {
                                JMenuItem removeMember = new JMenuItem("Remove Member");
                                removeMember.addActionListener(ae -> {
                                    // هنا عايز اطرد العضو ده من المجموعه
                                    group.removeMember(member);
                                    JOptionPane.showMessageDialog(null, "Member removed!");
                                    frame2.dispose();
                                    new GroupDetailsGui(Id, group, frame);
                                });
                                memberMenu.add(removeMember);
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
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> {
            frame2.dispose();
            frame.setVisible(true);
        });
        bottomPanel.add(returnButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame2.add(mainPanel);
        frame2.setVisible(true);
    }
}
