import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Week4 {

    public static List<List<Integer>> parseLine(String data) {
        //[@#$%^&*=\\+\\/-])
        List<Integer> winningNumbers = parsePattern(data, Pattern.compile(":\\s+(\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+)"));
        List<Integer> playingNumbers = parsePattern(data, Pattern.compile("\\|\\s+(\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+)"));

        return Arrays.asList(winningNumbers, playingNumbers);
    }

    public static List<Integer> parsePattern(String data, Pattern pattern) {
        List<Integer> numbers = new ArrayList<>();
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String element = matcher.group(1);
            System.out.println(element);
        }
        return numbers;
    }

    public static void main(String args[])  //static method  
    {  
        try {
            URL path = Week4.class.getResource("TestInput.txt");
            File inputFile = new File(path.getFile());
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                parseLine(data);
            }
            
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    } 
}
