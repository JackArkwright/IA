import java.util.ArrayList;
import java.util.Scanner;

public class Lesson {

    private String day; // the day of the lesson
    private char level; // the level of the class eg. 'r','o','g'
    private String time; // the time on the day
    private String coach;
    private Scanner lessonScanner = new Scanner(System.in);
    private static String filenameL = "lessonsFile.txt"; // TODO replace
    private static String filenameM = "members.txt";
    private ArrayList<Member> students;

    // Creating a new lesson that can have students and a coach assigned to it
    public Lesson (String day,char level,String time,String coach){
        this.day = day;
        this.level = level;
        this.time = time;
        this.coach = coach;
        this.students = new ArrayList<>();
    }

    public Lesson (String csvData) {
        String[] parsedData = csvData.split(", ");
        this.day = parsedData[0];
        this.time = parsedData[1];
        this.coach = parsedData[2];
        this.level = parsedData[3].toCharArray()[0];
        this.students = new ArrayList<>();
    }

    public void addStudent (Member student) {
        students.add(student);
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
        FileHandling.lineFileWriter(filenameL,true,day + ", " + time + ", " + coach + ", " + level);
        FileHandling.arrayListWrite(filenameL,true, students);
        FileHandling.lineFileWriter(filenameL,true,"END");
    }

    public void displayLesson () {
        // call the readLessonFile method
        // Loop through and get the lesson data from the list for each element,
        // Check if the time, date, coach and level match the one the user wants

        Club getLessons = new Club ("lessonsFile.txt");
        ArrayList<Lesson> allLessons = getLessons.readLessonFile();

        boolean found = false;
        int position = 0;

        while (found == false) {
            Lesson l = allLessons.get(position);
            if (toString().equals(l.toString())) {
                for (Member i : students) {
                    System.out.println(i);
                    System.out.println("test");
                }
            }

        }

    }

    public String toString() {
        return day + ", " + time + ", " + coach + ", " + level;
    }
}
