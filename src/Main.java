import java.util.Scanner;

public class Main {
    public static Scanner main = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Username: ");
        //Temporary login system
        String user = main.nextLine();
        System.out.println("Password: ");
        String pass = main.nextLine();
        if (FileHandling.login(user,pass) == true){
            System.out.println("Welcome");
        } else if (FileHandling.login(user,pass) == false){
            System.out.println("Incorrect data entered");
        }
        //Coaches Tom = new Coaches ("Tom Warburton",29);

        //Gives the user options. Will be replaced with buttons.
        System.out.println("What would you like to do? Create, View, Edit");
        String input = main.nextLine();

        //Collecting data to create a new lesson
        if (input.equals("Create")){
            System.out.println("What Day is the lesson? Mon, Tue, Wed etc.");
            String day = main.nextLine();
            System.out.println("What time is the lesson? 8pm, 5pm etc.");
            String time = main.nextLine();
            System.out.println("What Level is it? Y, R, G, O");
            String temp = main.nextLine();
            char level = temp.charAt(0);
            System.out.println("Which Coach will be taking the class?: ");
            String coach = main.next();

            //Creates the lesson with the data and calls the add students function straight away
            Lesson create = new Lesson(day, level, time, coach);
            create.createLesson();
        }






    }
}
