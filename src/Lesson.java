import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

public class Lesson {

    private String day; // the day of the lesson
    private char level; // the level of the class eg. 'r','o','g'
    private int time; // the time on the day
    private Staff coach;
    private Scanner lessonScanner = new Scanner(System.in);
    private String filenameL = "lessonsFile.txt";
    private String filenameM = "members.txt";
    private String display;
    private ArrayList<String> students;
    // Creating a new lesson that can have students and a coach assigned to it
    public Lesson (String day,char level,int time,Staff coach){
        this.day = day;
        this.level = level;
        this.time = time;
        this.coach = coach;
        display = day + ", " + time + ", " + coach + "," + level;
        ArrayList<String> students = new ArrayList<>();
    }


    public void addStudents (Member student){
        ArrayList<String> members = new ArrayList<>(FileHandling.wholeFileRead(filenameM));
        Scanner lesson = new Scanner(System.in);
        boolean done = false;
        boolean found = false;
        String input;

        while (done == false){
            System.out.println("Enter a Student's Full Name :");
            input = lessonScanner.nextLine();
            for (int i=0;i<members.size();i++){
                String[] splitString = members.get(i).split(", ");
                if (splitString[0].equals(input)){
                    students.add(input);
                    found = true;
                    break;
                }
            }
            if (found == false){
                System.out.println("That student is not logged as a member, would you like to add them?: ");
                input = lesson.nextLine();
                if (input == "yes"){

                }
            }
        }
    }
}
