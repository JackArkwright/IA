import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MembersGUI {

    private static JList<String> membersList;
    private static JTextField nameTextField;
    private static JComboBox levelSelect;
    private static JTextField ageTextField;
    private static ArrayList<String> members;
    private static DefaultListModel<String> model = new DefaultListModel<>();
    private static int index;

    public static void membersScreen() {

        //creating a new gui for the members screen. This screen has
        //a JList in it which looks at the text file containing all the members
        //and displays their names in the list so that the user can interact with them

        JFrame frame = new JFrame("SHLTC - Members");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,350);
        frame.setResizable(false);
        frame.add(panel);
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        //taking the list of members collected by the club class and sorting
        //them into alphabetical order using the Collections class
        members = Club.memberNames();
        String[] membersString = new String[members.size()];

        //creating the list and model to allow them to be displayed then
        //adding the list to the gui using the scroll pane method
        membersList = new JList<>(membersString);
        membersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        membersList.setModel(model);

        for (String e : members) {
            model.addElement(e);
        }

        JLabel memberDetails = new JLabel();
        panel.add(memberDetails);
        memberDetails.setBounds(270,30,180,25);

        membersList.addListSelectionListener(e -> {
            ArrayList<String> memberObjects = FileHandling.wholeFileRead("members.txt");
            String selected = memberObjects.get(membersList.getSelectedIndex());
            memberDetails.setText(selected);
        });

        JScrollPane scrollPane = new JScrollPane(membersList);
        scrollPane.setBounds(10,10,150,280);
        panel.add(scrollPane);

        JButton editDetails = editDetailsButton(frame);
        editDetails.setFocusable(false);
        panel.add(editDetails);

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

    public static void editMemberScreen(String member) {

        JFrame frame = new JFrame("SHLTC - Member Edit");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setResizable(false);
        frame.add(panel);
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        JLabel title = new JLabel("Edit Member Details: ");
        title.setBounds(10, 10, 400, 25);
        title.setFont(new Font(null, Font.BOLD, 14));
        panel.add(title);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(10, 40, 100, 25);
        panel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(80, 40, 165, 25);
        nameTextField.setText(member);
        panel.add(nameTextField);

        JLabel levelLabel = new JLabel("Level:");
        levelLabel.setBounds(10, 70, 100, 25);
        panel.add(levelLabel);

        String[] levels = {"Blue","Red","Orange","Green","Yellow"};
        levelSelect = new JComboBox(levels);
        levelSelect.setBounds(80,70,100,25);
        levelSelect.setSelectedIndex(4);
        panel.add(levelSelect);

        ArrayList<String> members = FileHandling.wholeFileRead("members.txt");
        String[] current = members.get(index).split(", ");
        String level = current[2];
        String age = current[1];

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setBounds(10, 100, 100, 25);
        panel.add(ageLabel);

        ageTextField = new JTextField();
        ageTextField.setBounds(80, 100, 165, 25);
        ageTextField.setText(age);
        panel.add(ageTextField);

        JButton confirmDetails = confirmDetailsButton(frame,members);
        panel.add(confirmDetails);

        frame.setVisible(true);

    }

    public static void addMemberScreen() {
        JFrame frame = new JFrame("SHLTC - New Member");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setResizable(false);
        frame.add(panel);
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        JLabel title = new JLabel("New Member Details: ");
        title.setBounds(10, 10, 400, 25);
        title.setFont(new Font(null, Font.BOLD, 14));
        panel.add(title);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(10, 40, 100, 25);
        panel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(80, 40, 165, 25);
        panel.add(nameTextField);

        JLabel levelLabel = new JLabel("Level:");
        levelLabel.setBounds(10, 70, 100, 25);
        panel.add(levelLabel);

        String[] levels = {"Blue","Red","Orange","Green","Yellow"};
        levelSelect = new JComboBox(levels);
        levelSelect.setBounds(80,70,100,25);
        panel.add(levelSelect);

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setBounds(10, 100, 100, 25);
        panel.add(ageLabel);

        ageTextField = new JTextField();
        ageTextField.setBounds(80, 100, 165, 25);
        panel.add(ageTextField);

        JButton confirmDetails = confirmNewDetailsButton(frame,members);
        panel.add(confirmDetails);

        frame.setVisible(true);

    }

    public static JButton editDetailsButton(JFrame frame) {
        JButton editDetails = new JButton("Edit Details");
        editDetails.setBounds(270,70,120,25);

        editDetails.addActionListener(e -> {
            frame.dispose();
            index = membersList.getSelectedIndex();
            editMemberScreen(members.get(index));
        });

        return editDetails;
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

    public static JButton removeMemberButton(JFrame frame){
        JButton removeMember = new JButton("Delete");
        removeMember.setBounds(270,150,120,25);

        removeMember.addActionListener(e -> {
            int index = membersList.getSelectedIndex();
            ArrayList<String> members = FileHandling.wholeFileRead("members.txt");
            members.remove(index);
            FileHandling.arrayListWrite("members.txt",false,members);
            frame.dispose();
            HomeScreenGUI.homeScreen();
        });
        return removeMember;
    }

    private static JButton backButton(JFrame frame) {
        JButton back = new JButton("Back");
        back.setBounds(270,190,120,25);

        back.addActionListener(e -> {
            frame.dispose();
            HomeScreenGUI.homeScreen();
        });
        return back;
    }


    public static JButton confirmDetailsButton(JFrame frame, ArrayList<String> members) {
        JButton confirmDetails = new JButton("Confirm");
        confirmDetails.setBounds(10,140,100,25);
        confirmDetails.setFocusable(false);

        confirmDetails.addActionListener(e -> {
            String name = nameTextField.getText();
            Object level = levelSelect.getSelectedItem();
            level = level.toString();
            String age = ageTextField.getText();

            if (level == "Yellow") {
                level = "Y";
            } else if (level == "Green") {
                level = "G";
            } else if (level == "Orange") {
                level = "O";
            } else if (level == "Red") {
                level = "R";
            } else if (level == "Blue") {
                level = "B";
            }

            members.set(index,(name + ", " + age + ", " + level));
            ArrayList<String> membersDetails = FileHandling.wholeFileRead("members.txt");
            membersDetails.set(index,(name + ", " + age + ", " + level));
            FileHandling.arrayListWrite("members.txt",false,membersDetails);
            frame.dispose();
            membersScreen();
        });

        return confirmDetails;
    }

    public static JButton confirmNewDetailsButton(JFrame frame, ArrayList<String> members) {
        JButton confirmDetails = new JButton("Confirm");
        confirmDetails.setBounds(10,140,100,25);
        confirmDetails.setFocusable(false);

        confirmDetails.addActionListener(e -> {
            String name = nameTextField.getText();
            Object level = levelSelect.getSelectedItem();
            level = level.toString();
            String age = ageTextField.getText();

            if (level == "Yellow") {
                level = "Y";
            } else if (level == "Green") {
                level = "G";
            } else if (level == "Orange") {
                level = "O";
            } else if (level == "Red") {
                level = "R";
            } else if (level == "Blue") {
                level = "B";
            }

            members.add(name);
            Collections.sort(members);
            ArrayList<String> membersDetails = FileHandling.wholeFileRead("members.txt");
            membersDetails.add(name + ", " + age + ", " + level);
            Collections.sort(membersDetails);
            FileHandling.arrayListWrite("members.txt",false,membersDetails);
            frame.dispose();
            membersScreen();
        });

        return confirmDetails;
    }

}
