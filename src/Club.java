import java.util.ArrayList;

public class Club {
    private String filename;
    private ArrayList<Lesson> lessonSchedule;
    /*
    receive the filename
    read the file into an arraylist of strings
    Go through the array list and create a new string array when it reaches START including everything between START and END
    pass string array to the constructor for lesson
    add that lesson to an array list of lessons.
    */

    public Club(String filename) {
        this.filename = filename;
        lessonSchedule = new ArrayList<>();
        readLessonFile();
    }

    // Initialise with data from the file
    public void readLessonFile () {
        // Get raw data from text file
        ArrayList<String> rawData = FileHandling.wholeFileRead(filename);

        // Repeat until end of file
        int position = 0;
        while (position < rawData.size()) {
            // Search for START
            while (position < rawData.size() && !rawData.get(position).equals("START")) {
                position++;
            }
            if (position < rawData.size()) {
                // Skip past the START marker
                position = position + 1;
                Lesson l = new Lesson(rawData.get(position));
                lessonSchedule.add(l);
                // Parse each line until we reach END
                System.out.println(rawData.get(position)); // TODO DEBUG
                while (!rawData.get(position).equals("END")) {
                    position++;
                    Member m = new Member(rawData.get(position));
                    l.addStudent(m); // Assign this student to the lesson
                }
            }
        }

    }
}
