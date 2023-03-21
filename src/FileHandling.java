import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;

public class FileHandling {
    private static String line;

    //Allows for one line of the file to be accessed and returned to other methods for UI or the user
    public static String singleLine (String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Data not found");

        }
        return line;
    }

    public static ArrayList<String> wholeFileRead (String filename){
        ArrayList<String> fileOutput = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader (new FileReader (filename))){
            line = br.readLine();

            while (line != null){
                fileOutput.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("There was an error reading the file");
        } return fileOutput;
    }

    public static void fileWriter (String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))){

        } catch (IOException e){
            System.out.println("There was an unexpected error");
        }
    }
    // Searches through a file with a list of usernames and passwords
    // Returns true or false based on whether the login has been found
    public static boolean login (String username, String password) {
        line = singleLine("logins.txt");
        String[] splitString = line.split(", ");

    }
}
