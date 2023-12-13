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
        String winningNumbersString = getGroup(data, Pattern.compile(":*(\\s+(\\d+)){10}"));
        String playingNumbersString = getGroup(data, Pattern.compile("\\|*(\\s+(\\d+)){25}"));
        List<Integer> winningNumbers = parsePattern(winningNumbersString, Pattern.compile("(\\d+)"));
        List<Integer> playingNumbers = parsePattern(playingNumbersString, Pattern.compile("(\\d+)"));

        return Arrays.asList(winningNumbers, playingNumbers);
    }

    public static String getGroup(String data, Pattern pattern) {
        String output = "";
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            output = matcher.group(0);
        }
        return output;
    }

    public static List<Integer> parsePattern(String data, Pattern pattern) {
        List<Integer> numbers = new ArrayList<>();
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String element = matcher.group();
            numbers.add(Integer.parseInt(element));
        }
        System.out.println(numbers);
        return numbers;
    }

    public static Integer getGameScore(List<Integer> winningNumbers, List<Integer> playingNumbers) {
        Integer sum = 1;
        for (Integer playingNumber : playingNumbers) {
            if (winningNumbers.contains(playingNumber)) {
                sum *= 2;
            }
        }
        return sum/2;
    }

    public static void main(String args[])  //static method  
    {  
        try {
            URL path = Week4.class.getResource("Input.txt");
            File inputFile = new File(path.getFile());
            Scanner reader = new Scanner(inputFile);

            List<Integer> winningNumbers = new ArrayList<>();
            List<Integer> playingNumbers = new ArrayList<>();

            Integer output1 = 0;

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                List<List<Integer>> parsedInput = parseLine(data);
                winningNumbers= parsedInput.get(0);
                playingNumbers= parsedInput.get(1);
                output1 += getGameScore(winningNumbers, playingNumbers);
            }
            
            System.out.println(String.format("The total sum is %d", output1));

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    } 
}
