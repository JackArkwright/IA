import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HomeScreenGUI {

    public static void homeScreen(String name) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("SHLTC - Home");
        frame.setSize(700,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        JLabel title = new JLabel("Welcome " + name);
        title.setBounds(10,10,400,50);
        title.setFont(new Font("Serif",Font.BOLD,30));
        panel.add(title);

        frame.setVisible(true);

        JButton showMembers = showMembersButton();
    }

    public static JButton showMembersButton() {
        JButton showMembers = new JButton();
        return showMembers;
    }

}
