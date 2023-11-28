import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LessonsGUI {

    private static JList<String> lessonJList;
    private static JList<String> studentsList;
    private static DefaultListModel<String> model = new DefaultListModel<>();
    private static DefaultListModel<String> model2 = new DefaultListModel<>();
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

        ArrayList<Lesson> allLessons = new ArrayList<>(Club.readLessonFile());
        String[] lessons = new String[allLessons.size()];
        for (int i=0;i< allLessons.size();i++) {
            lessons[i] = allLessons.get(i).toString();
        }

        lessonJList = new JList<>(lessons);
        lessonJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lessonJList.setModel(model);

        for (String e : lessons) {
            model.addElement(e);
        }

        JLabel lessonDetails = new JLabel();
        panel.add(lessonDetails);
        lessonDetails.setBounds(270,30,180,25);

        lessonJList.addListSelectionListener(e -> {
            String selected = lessons[lessonJList.getSelectedIndex()];
            lessonDetails.setText(selected);
        });

        JScrollPane scrollPane = new JScrollPane(lessonJList);
        scrollPane.setBounds(10,10,150,280);
        panel.add(scrollPane);

        JButton viewStudents = viewSudentsButton(frame, lessons);
        viewStudents.setFocusable(false);
        panel.add(viewStudents);

        frame.setVisible(true);
    }

    public static void viewStudentsScreen(String lessonName) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("SHLTC - Lessons");
        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        ArrayList<Member> lessonStudents = new ArrayList<>(Club.displayLesson(lessonName));
        String[] students = new String[lessonStudents.size()];
        for (int i=0;i< lessonStudents.size();i++) {
            students[i] = lessonStudents.get(i).toString();
        }

        studentsList = new JList<>(students);
        studentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentsList.setModel(model2);

        for (String e : students) {
            model2.addElement(e);
        }

        JScrollPane scrollPane = new JScrollPane(studentsList);
        scrollPane.setBounds(10,10,150,280);
        panel.add(scrollPane);

        JButton addMember = addMemberButton(frame);
        addMember.setFocusable(false);
        panel.add(addMember);

        JButton removeMember = removeMemberButton(frame);
        removeMember.setFocusable(false);
        panel.add(removeMember);

        JButton back = backButton(frame);
        back.setFocusable(false);
        panel.add(back);


        frame.setVisible(true);
    }

    public static JButton viewSudentsButton(JFrame frame,String[] lessons){
        JButton viewStudents = new JButton("Students");
        viewStudents.setBounds(280,70,120,25);

        viewStudents.addActionListener(e -> {
            frame.dispose();
            int index = lessonJList.getSelectedIndex();
            viewStudentsScreen(lessons[index]);
        });

        return viewStudents;
    }

    public static JButton addMemberButton(JFrame frame) {
        JButton addMember = new JButton("New Member");
        addMember.setBounds(270,110,120,25);

        addMember.addActionListener(e -> {
            frame.dispose();
            addMemberScreen();
        });
        return addMember;
    }


}
