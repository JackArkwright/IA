import javax.swing.*;
import java.awt.*;

public class HomeScreenGUI {

    public static void homeScreen() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("SHLTC - Home");
        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        JLabel title = new JLabel("Welcome ");
        title.setBounds(25,25,400,50);
        title.setFont(new Font("Serif",Font.BOLD,30));
        panel.add(title);

        JButton showMembers = showMembersButton(frame);
        panel.add(showMembers);

        JButton showLessons = showLessonsButton(frame);
        panel.add(showLessons);

        frame.setVisible(true);
    }

    public static JButton showMembersButton(JFrame frame) {
        JButton showMembers = new JButton("Members");
        showMembers.setFocusable(false);
        showMembers.setBounds(25,90,200,75);
        showMembers.addActionListener(e -> {
            frame.dispose();
            MembersGUI.membersScreen();
        });

        return showMembers;
    }

    public static JButton showLessonsButton(JFrame frame) {
        JButton showLessons = new JButton("Lessons");
        showLessons.setFocusable(false);
        showLessons.setBounds(25,175,200,75);
        showLessons.addActionListener(e -> {
            frame.dispose();
            LessonsGUI.lessonScreen();
        });

        return showLessons;
    }

}
