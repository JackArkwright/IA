import java.util.ArrayList;

public class Club {
    private String filename;
    private ArrayList<Lesson> lessonSchedule;

    public Club(String filename) {
        this.filename = filename;
        lessonSchedule = new ArrayList<>();
        readLessonFile();
    }

    // Initialise with data from the file
    public ArrayList<Lesson> readLessonFile () {
        // Get raw data from text file
        ArrayList<String> rawData = FileHandling.wholeFileRead(filename);

        // Repeat until end of file
        int position = 0;
        while (position < rawData.size()) {
            // Search for START
            while (position < rawData.size() && !rawData.get(position).equals("START") && !rawData.get(position).equals("")) {
                position++;
            }
            if (position < rawData.size()) {
                // Skip past the START marker
                position++;
                Lesson l = new Lesson(rawData.get(position));
                lessonSchedule.add(l);
                // Parse each line until we reach END
                System.out.println(rawData.get(position)); // TODO DEBUG
                position++;
                while (position < rawData.size() && !rawData.get(position).equals("END") && !rawData.get(position).equals("")) {
                    Member m = new Member(rawData.get(position));
                    l.addStudent(m); // Assign this student to the lesson
                    position++;
                }
                System.out.println(rawData.get(position));
            }
        }

        return lessonSchedule;

    }
}