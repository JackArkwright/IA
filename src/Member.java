import java.util.ArrayList;

public class Member {
    private int age;
    private String name;
    private char level; // red 'R', orange 'O', green 'G', yellow 'Y', adult 'A', coach 'C'
    private String filename = "members.txt";
    private String email;

    public Member (String name, int age, char level) {
        this.name = name;
        this.age = age;
        this.level = level;
        saveMember();
    }

    public Member (String csvData) {
        String[] parsedData = csvData.split(", ");
        this.name = parsedData[0];
        this.age = Integer.parseInt(parsedData[1]);
        this.level = parsedData[2].toCharArray()[0];
        saveMember();
    }

    public void saveMember (){
        String memberDetails = name + ", " + age + ", " + level;
        boolean found = false;
        ArrayList<String> check = new ArrayList<String>(FileHandling.wholeFileRead(filename));
        for (int i=0;i<check.size();i++) {
            if (check.get(i).equals(memberDetails)) {
                found = true;
                break;
            }
        }
        if(found = false){
            FileHandling.lineFileWriter(filename,true,memberDetails);
        }

    }

    public void displayMembers () {
        ArrayList<Member> members =
    }

    //Get the members name
    public String getName(){
        return name;
    }
    //Get the members age
    public int getAge(){
        return age;
    }
    //Get the members level
    public char getLevel(){
        return level;
    }

    public String toString () {
        return name + ", " + age + ", " + level;
    }

}
