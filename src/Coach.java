import java.util.ArrayList;

public class Coach extends Member{
    private String filename = "coaches.txt";
    private int age;
    private String name;
    private char level = 'C';

    public Coach(String name, int age, String email){
        super(name,age,'C');
        saveCoach();
    }

    public void saveCoach(){
        String coachDetails = name + ", " + age + ", " + level;
        System.out.println(coachDetails);
        boolean found = false;
        ArrayList<String> check = new ArrayList<String>(FileHandling.wholeFileRead(filename));
        for (String s : check) {
            if (s.equals(coachDetails)) {
                found = true;
                break;
            }
        }
        if(!found){
            FileHandling.lineFileWriter(filename,true,coachDetails);
        }
    }
}
