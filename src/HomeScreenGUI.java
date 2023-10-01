import javax.swing.*;
import java.awt.*;

public class HomeScreenGUI {

    public static void homeScreen() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("SHLTC - Home");
        frame.setSize(800,650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        JLabel title = new JLabel("Welcome ");
        title.setBounds(50,50,400,50);
        title.setFont(new Font("Serif",Font.BOLD,30));
        panel.add(title);

        JButton showMembers = showMembersButton(frame);
        panel.add(showMembers);

        JButton showLessons = showLessonsButton();
        panel.add(showLessons);

        frame.setVisible(true);
    }

    public static JButton showMembersButton(JFrame frame) {
        JButton showMembers = new JButton("Members");
        showMembers.setFocusable(false);
        showMembers.setBounds(50,150,200,150);
        showMembers.addActionListener(e -> {
            frame.dispose();
            MembersGUI.membersScreen();
        });

        return showMembers;
    }

    public static JButton showLessonsButton() {
        JButton showLessons = new JButton("Lessons");
        showLessons.setFocusable(false);
        showLessons.setBounds(50,350,200,150);
        showLessons.addActionListener(e -> {

        });

        return showLessons;
    }

}
