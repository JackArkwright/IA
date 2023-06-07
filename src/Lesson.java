import java.util.ArrayList;
import java.util.Scanner;

public class Lesson {

    private String day; // the day of the lesson
    private char level; // the level of the class eg. 'r','o','g'
    private String time; // the time on the day
    private String coach;
    private Scanner lessonScanner = new Scanner(System.in);
    private static String filenameL = "lessonsFile.txt";
    private static String filenameM = "members.txt";
    private static String display;

    // Creating a new lesson that can have students and a coach assigned to it
    public Lesson (String day,char level,String time,String coach){
        this.day = day;
        this.level = level;
        this.time = time;
        this.coach = coach;
        display = day + ", " + time + ", " + coach + ", " + level;
    }

    public Lesson (String[] lessonData) {

    }


    public void createLesson (){
        // Array List that reads the members from the file
        // Array List that saves the students to add to the file
        ArrayList<String> members = new ArrayList<String>(FileHandling.wholeFileRead(filenameM));
        ArrayList<String> students = new ArrayList<>();
        Scanner lesson = new Scanner(System.in);
        // Continuing conditions
        boolean done = false;
        boolean found = false;
        String input;

        while (!done){
            System.out.println("Enter a Student's Full Name :");
            input = lessonScanner.nextLine();
            for (int i=0;i<members.size();i++){
                // Reads a line in the file and sees if the name matches the inputted name
                String[] splitString = members.get(i).split(", ");
                if (splitString[0].equals(input)){
                    students.add(members.get(i));
                    found = true;
                    break;
                    //ends if if it is found
                }
            }
            // makes sure student exists in the files
            if (found == false){
                System.out.println("That student is not logged as a member");
            }
            System.out.println("Would you like to add another student? Y or N ");
            input = lesson.nextLine();
            if (input.equals("N")){
                done = true;
            }
        }
        // Adds the line with the Details of the class followed by all students within and with
        // END to show the end of the lesson in the file to make it easier to display a class
        FileHandling.lineFileWriter(filenameL,true,"START");
        FileHandling.lineFileWriter(filenameL,true, display);
        FileHandling.arrayListWrite(filenameL,true, students);
        FileHandling.lineFileWriter(filenameL,true,"END");
    }


    public static void displayClass (String coach, String day, String time, String level){
        // Read the lesson file and split the lines that have the lesson details in them into pieces
        String format = day + ", " + time + ", " + coach + ", " + level;
        ArrayList<String> output = new ArrayList<>();
        output = FileHandling.wholeFileRead(filenameL);



        // Check if that's the lesson they want displayed
        // List the details and then a list of the students on screen, name and level.
    }
}
