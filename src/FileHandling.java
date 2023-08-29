import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

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
            e.printStackTrace();

        } return fileOutput;
    }

    public static void lineFileWriter (String filename, boolean append, String input) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename,append))){
            pw.println(input);
        } catch (IOException e){
            System.out.println("There was an unexpected error");
        }
    }

    public static void arrayListWrite (String filename, boolean append, ArrayList<String> input){
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename,append))){
            for (int i=0;i<input.size();i++){
                pw.println(input.get(i));
            }
        } catch (IOException e){
            System.out.println("An error has occurred.");
        }
    }
    // Searches through a file with a list of usernames and passwords
    // Returns true or false based on whether the login has been found

}
