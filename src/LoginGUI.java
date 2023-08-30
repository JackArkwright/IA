import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class LoginGUI {

    private static JTextField userText;
    private static JTextField newUserText;
    private static JPasswordField cNewPass;
    private static JPasswordField passwordText;
    private static JPasswordField newPasswordtext;
    private static JLabel success;
    private static JLabel failure;
    private static int count = 0;

    public static void LoginScreen() {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame("SHLTC - Login");
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        JButton login = loginJButton(frame);
        login.setFocusable(false);
        panel.add(login);

        JButton newUser = newUserButton(frame);
        newUser.setFocusable(false);
        panel.add(newUser);

        success = new JLabel("");
        success.setBounds(100,85,300,25);
        panel.add(success);

        frame.setVisible(true);

    }

    public static void newUserScreen() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("SHLTC - New User");
        frame.setSize(350,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        panel.setLayout(null);

        JLabel title = new JLabel("Create a New User");
        title.setBounds(10,10,400,25);
        title.setFont(new Font("Comic Sans",Font.BOLD,14));
        panel.add(title);

        JLabel name = new JLabel("Full Name");
        name.setBounds(10,40,100,25);
        panel.add(name);

        newUserText = new JTextField();
        newUserText.setBounds(120,40,165,25);
        panel.add(newUserText);

        JLabel username = new JLabel("Username");
        username.setBounds(10,70,100,25);
        panel.add(username);

        newUserText = new JTextField();
        newUserText.setBounds(120,70,165,25);
        panel.add(newUserText);

        JLabel password = new JLabel("Password");
        password.setBounds(10,100,100,25);
        panel.add(password);

        newPasswordtext = new JPasswordField();
        newPasswordtext.setBounds(120,100,165,25);
        panel.add(newPasswordtext);

        JLabel cPassword = new JLabel("Verify Password");
        cPassword.setBounds(10,130,100,25);
        panel.add(cPassword);

        cNewPass = new JPasswordField();
        cNewPass.setBounds(120,130,165,25);
        panel.add(cNewPass);

        JButton confirmNewUser = confirmNewUser(frame);
        confirmNewUser.setFocusable(false);
        panel.add(confirmNewUser);

        failure = new JLabel();
        failure.setBounds(100,165,150,25);
        panel.add(failure);

        frame.setVisible(true);

    }

    private static JButton loginJButton(JFrame frame) {
        JButton login = new JButton("Login");
        login.setBounds(10,85,80,25);
        login.addActionListener(e -> {
            String user = userText.getText();
            String password = passwordText.getText();
            int index = 0;

            ArrayList<String> loginData = FileHandling.wholeFileRead("logins.txt");
            boolean confirmed = false;

            ArrayList<String> modifiedData = new ArrayList<>();

            for (String element : loginData) {
                int commaIndex = element.lastIndexOf(',');
                if (commaIndex != -1) {
                    String modifiedElement = element.substring(0, commaIndex);
                    modifiedData.add(modifiedElement);
                }
            }

            for (String s : modifiedData) {
                if (s.equals(user + ", " + password)) {
                    confirmed = true;
                    break;
                }
                index++;
            }
            if (!confirmed) {
                success.setText("Login Unsuccessful");
                count++;
            } else {
                success.setText("Login Successful");
                frame.dispose();
                String[] split = loginData.get(index).split(", ");
                HomeScreenGUI.homeScreen(split[2]);
            }

            if (count == 5) {
                System.exit(0);
            }
        });
        return login;
    }

    public static JButton newUserButton(JFrame frame) {
        JButton newUser = new JButton("New User");
        newUser.setBounds(10,115,90,25);
        newUser.addActionListener(e -> {
            frame.dispose();
            newUserScreen();
        });
        return newUser;
    }

    public static JButton confirmNewUser(JFrame frame) {
        JButton confirmNewUser = new JButton("Confirm");
        confirmNewUser.setBounds(10,165,80,25);
        confirmNewUser.addActionListener(e -> {
            String user = newUserText.getText();
            String password = newPasswordtext.getText();
            String cPassword = cNewPass.getText();

            ArrayList<String> loginData = FileHandling.wholeFileRead("logins.txt");
            boolean ready = false;

            if (password.equals(cPassword)) {
                for (String s : loginData) {
                    if (!s.equals(user + ", " + password)) {
                        ready = true;
                    } else {
                        ready = false;
                    }
                }
                if (ready) {
                    FileHandling.lineFileWriter("logins.txt",true,(user + ", " + password));
                    failure.setText("New User Created");
                    frame.dispose();
                    LoginScreen();

                }
            } else {
                failure.setText("Passwords Do Not Match");
            }
        });
        return confirmNewUser;
    }
}
