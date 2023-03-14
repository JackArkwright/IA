public class Lesson {

    private String day; // the day of the lesson
    private char level; // the level of the class eg. 'r','o','g'
    private int time; // the time on the day

    // Creating a new lesson that can have students and a coach assigned to it
    public Lesson (String day,char level,int time,Staff coach ){
        this.day = day;
        this.level = level;
        this.time = time;
    }

}
