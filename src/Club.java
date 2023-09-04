import java.util.ArrayList;

public class Club {
    private static String memberFilename;
    private final String lessonFilename;
    private ArrayList<Lesson> lessonSchedule;

    public Club(String Lfilename, String MFilename) {
        this.lessonFilename = Lfilename;
        this.memberFilename = MFilename;
        lessonSchedule = new ArrayList<>();
        readLessonFile();
        readMemberFile();
    }

    public ArrayList<Lesson> getLessonSchedule () {
        return lessonSchedule;
    }

    // Initialise with data from the file
    public ArrayList<Lesson> readLessonFile () {
        // Get raw data from text file
        ArrayList<String> rawData = FileHandling.wholeFileRead(lessonFilename);

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
                //System.out.println(rawData.get(position)); // TODO DEBUG
                position++;
                while (position < rawData.size() && !rawData.get(position).equals("END") && !rawData.get(position).equals("")) {
                    Member m = new Member(rawData.get(position));
                    l.addStudent(m); // Assign this student to the lesson
                    position++;
                }
                //System.out.println(rawData.get(position));
            }
        }

        return lessonSchedule;

    }

    public static ArrayList<Member> readMemberFile() {
        // get the raw data
        ArrayList<String> rawData = FileHandling.wholeFileRead(memberFilename);
        ArrayList<Member> membersList = new ArrayList<>();

        for (String rawDatum : rawData) {
            Member m = new Member(rawDatum);
            membersList.add(m);
        }

        return membersList;
    }

    public static ArrayList<String> memberNames() {
        ArrayList<Member> membersList = readMemberFile();
        ArrayList<String> membersNames = new ArrayList<>();

        for (Member e : membersList) {
            String name = e.getName();
            membersNames.add(name);
        }

        return membersNames;
    }

    public void changeDetails (String nameToFind, String newName, char newLevel, int newAge) {
        String currentDetails = "";
        String[] details = new String[3];
        int index = -1;
        ArrayList<String> rawData = FileHandling.wholeFileRead(memberFilename);

        for (int i=0;i< rawData.size();i++) {
            details = rawData.get(i).split(", ");
            if (details[0].equals(nameToFind)) {
                currentDetails = rawData.get(i);
                index = i;
            }
        }
        if (currentDetails.equals("")){
            System.out.println("BrOkE HeLp");
            return;
        }

        String newDetails = newName + ", " + newAge + ", " + newLevel;
        rawData.remove(index);
        rawData.add(index,newDetails);

        FileHandling.arrayListWrite(memberFilename,false,rawData);


    }


    public void displayLesson (String search) {
        // call the readLessonFile method
        // Loop through and get the lesson data from the list for each element,
        // Check if the time, date, coach and level match the one the user wants
        ArrayList<Lesson> allLessons = readLessonFile();

        boolean found = false;
        int position = 0;
        Lesson l;
        l = allLessons.get(position);

        while (position < allLessons.size() && !found) {
            l = allLessons.get(position);
            if (l.toString().equals(search)) {
                found = true;
            }
            position++;
        }
        if(found) {
            l.display();
        } else {
            System.out.println("Lesson not found");
        }
    }
}