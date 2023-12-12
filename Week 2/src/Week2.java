import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Week2 {

    public static Integer getGameId(String data) {
        Integer gameId = -1;
        
        // Game id pattern
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            gameId = Integer.parseInt(matcher.group());
        }
        return gameId;
    }

    public static List<Map<String, Integer>> getSubsets(String data) {
        List<Map<String, Integer>> subsets = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\s*(\\d+)\\s+([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            Map<String, Integer> subset = new HashMap<>();
            subset.put(matcher.group(2), Integer.parseInt(matcher.group(1)));
            subsets.add(subset);
        }
        return subsets;
    }

    public static Game parseLine(String data) {
        //Game 1: 12 blue, 15 red, 2 green; 17 red, 8 green, 5 blue; 8 red, 17 blue; 9 green, 1 blue, 4 red
        Integer gameId = getGameId(data);
        List<Map<String, Integer>> subsets = getSubsets(data);
        return new Game(gameId, subsets);
    }

    public static void main(String args[])  //static method  
    {  
        try {
            URL path = Week1.class.getResource("TestInput.txt");
            File inputFile = new File(path.getFile());
            Scanner reader = new Scanner(inputFile);

            List<Game> games = new ArrayList<>();
            while (reader.hasNextLine()) {
                Game game = parseLine(reader.nextLine());
                games.add(game);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    } 
}
