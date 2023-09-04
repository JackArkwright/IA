import java.util.Scanner;

public class Main {
    public static Scanner main = new Scanner(System.in);

    public static void main(String[] args) {

        LoginGUI.LoginScreen();

        Club testClub = new Club("lessonsFile.txt","members.txt");
        testClub.displayLesson("Mon, 7pm, Matt, Y");
        System.out.println("Finished");
        Member.displayMembers();


    }
}
