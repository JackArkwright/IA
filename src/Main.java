import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner main = new Scanner(System.in);
        System.out.println("Username: ");
        String user = main.nextLine();
        System.out.println("Password: ");
        String pass = main.nextLine();
        if (FileHandling.login(user,pass) == true){
            System.out.println("Welcome");
        } else if (FileHandling.login(user,pass) == false){
            System.out.println("Incorrect data entered");
        }





    }

}
