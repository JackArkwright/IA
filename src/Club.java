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
                boolean end = false;
                position++;

                while (end == false || !rawData.get(position).equals("END")) {
                    System.out.println(rawData.get(position));
                    Member m = new Member(rawData.get(position));
                    l.addStudent(m); // Assign this student to the lesson
                    position++;
                    if (rawData.get(position) == "END"){
                        end = true;
                        break;
                    }
                }
            }
        }

        return lessonSchedule;

    }
}