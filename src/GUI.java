import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GUI {

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

        JButton login = loginJButton();
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
        frame.setSize(350,210);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        panel.setLayout(null);

        JLabel title = new JLabel("Create a New User");
        title.setBounds(10,10,400,25);
        title.setFont(new Font("Comic Sans",Font.BOLD,14));
        panel.add(title);

        JLabel username = new JLabel("Username");
        username.setBounds(10,40,100,25);
        panel.add(username);

        newUserText = new JTextField();
        newUserText.setBounds(120,40,165,25);
        panel.add(newUserText);

        JLabel password = new JLabel("Password");
        password.setBounds(10,70,100,25);
        panel.add(password);

        newPasswordtext = new JPasswordField();
        newPasswordtext.setBounds(120,70,165,25);
        panel.add(newPasswordtext);

        JLabel cPassword = new JLabel("Verify Password");
        cPassword.setBounds(10,100,100,25);
        panel.add(cPassword);

        cNewPass = new JPasswordField();
        cNewPass.setBounds(120,100,165,25);
        panel.add(cNewPass);

        JButton confirmNewUser = confirmNewUser();
        confirmNewUser.setFocusable(false);
        panel.add(confirmNewUser);

        failure = new JLabel();
        failure.setBounds(100,135,150,25);
        panel.add(failure);

        frame.setVisible(true);

    }

    public static void homePage(){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("SHLTC");
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
    }

    private static JButton loginJButton() {
        JButton login = new JButton("Login");
        login.setBounds(10,85,80,25);
        login.addActionListener(e -> {
            String user = userText.getText();
            String password = passwordText.getText();

            ArrayList<String> loginData = FileHandling.wholeFileRead("logins.txt");
            boolean confirmed = false;

            for (String s : loginData) {
                if (s.equals(user + ", " + password)) {
                    confirmed = true;
                    break;
                }
            }
            if (!confirmed) {
                success.setText("Login Unsuccessful");
                count++;
            } else {
                success.setText("Login Successful");
                homePage();
            }

            if (count == 5) {
                loginJButton().setEnabled(false);
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

    public static JButton confirmNewUser() {
        JButton confirmNewUser = new JButton("Confirm");
        confirmNewUser.setBounds(10,135,80,25);
        confirmNewUser.addActionListener(e -> {
            String user = newUserText.getText();
            String password = newPasswordtext.getText();
            String cPassword = cNewPass.getText();

            ArrayList<String> loginData = FileHandling.wholeFileRead("logins.txt");
            boolean ready = false;

            if (password.equals(cPassword)) {
                for (String s : loginData) {
                    if (s.equals(user + ", " + password)) {

                    }
                }
                if (ready) {
                    FileHandling.lineFileWriter("logins.txt",true,null);
                }
            } else {
                failure.setText("Passwords Do Not Match");
            }





        });
        return confirmNewUser;
    }
}
