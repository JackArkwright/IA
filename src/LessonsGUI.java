import javax.swing.*;
import java.awt.*;

public class LessonsGUI {

    public static void lessonScreen() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("SHLTC - Lessons");
        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        frame.setVisible(true);
    }

}
