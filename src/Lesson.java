import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

public class Lesson {

    private String day; // the day of the lesson
    private char level; // the level of the class eg. 'r','o','g'
    private String time; // the time on the day
    private String coach;
    private Scanner lessonScanner = new Scanner(System.in);
    private String filenameL = "lessonsFile.txt";
    private String filenameM = "members.txt";
    private String display;

    // Creating a new lesson that can have students and a coach assigned to it
    public Lesson (String day,char level,String time,String coach){
        this.day = day;
        this.level = level;
        this.time = time;
        this.coach = coach;
        display = day + ", " + time + ", " + coach + "," + level;
    }


    public void addStudents (){
        ArrayList<String> members = new ArrayList<String>(FileHandling.wholeFileRead(filenameM));
        ArrayList<String> students = new ArrayList<>();
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
                    students.add(members.get(i));
                    found = true;
                    break;
                }
            }
            if (found == false){
                System.out.println("That student is not logged as a member");
            }
            System.out.println("Would you like to add another student? Y or N ");
            input = lesson.nextLine();
            if (input.equals("N")){
                done = true;
            }
        }
        FileHandling.lineFileWriter(filenameL,true, display);
        FileHandling.arrayListWrite(filenameL,true, students);
        FileHandling.lineFileWriter(filenameL,true,"END");
    }
}
