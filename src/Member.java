public class Member {
    private int age;
    private String name;
    private char level; // red 'R', orange 'O', green 'G', yellow 'Y', adult 'A', coach 'C'
    private String filename = "members.txt";

    public Member (String name, int age, char level) {
        this.name = name;
        this.age = age;
        this.level = level;
        saveMember();

    }

    public void saveMember (){
        String memberDetails = name + ", " + age + ", " + level;
        FileHandling.lineFileWriter("members.txt",true,memberDetails);
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

}
