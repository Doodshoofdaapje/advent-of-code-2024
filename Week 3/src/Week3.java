import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Week3 {

    public static List<List<Vertex>> parseLine(String data, Integer rowIndex) {
        //[@#$%^&*=\\+\\/-])
        List<Vertex> machineNumbers = parsePattern(data, rowIndex, Pattern.compile("(\\d+)"));
        List<Vertex> specialCharacters = parsePattern(data, rowIndex, Pattern.compile("([@#$%^&*=\\+\\/-])"));

        return Arrays.asList(machineNumbers, specialCharacters);
    }

    public static List<Vertex> parsePattern(String data, Integer rowIndex, Pattern pattern) {
        List<Vertex> vertices = new ArrayList<>();
        Matcher matcher = pattern.matcher(data);
        Integer index = 0;
        while (matcher.find()) {
            String element = matcher.group(1);
            index = data.indexOf(element, index);
            //System.out.println(String.format("New vertex: x = %d, y = %d, width = %d, val = %s", index, rowIndex, element.length(), element));
            Vertex vertex = new Vertex(index, rowIndex, element.length(), element);
            vertices.add(vertex);
            index += + element.length();
        }
        return vertices;
    }

    public static Integer getMachineNumberSum(List<Vertex> numbers, List<Vertex> symbols) {
        Integer sum = 0;
        for (Vertex number : numbers) {
            for (Vertex symbol : symbols) {
                if (number.isAdjacentTo(symbol)) {
                    //System.out.println(String.format("Number %s matched with symbol %s", number.value, symbol.value));
                    sum += Integer.parseInt(number.value);
                    break;
                }
            }
        }

        return sum;
    }

    public static Integer getGearRatioSum(List<Vertex> numbers, List<Vertex> symbols) {
        Integer sum = 0;
        for (Vertex symbol : symbols) {
            if (!symbol.value.equals("*")) {
                continue;
            }
            Integer ratio = 1;
            Integer count = 0;
            for (Vertex number : numbers) {
                if (number.isAdjacentTo(symbol)) {
                    //System.out.println(String.format("Gear is adjacent to %d", Integer.parseInt(number.value)));
                    ratio *= Integer.parseInt(number.value);
                    count++;
                }
            }
            if (count > 1) {
                //System.out.println(String.format("Ratio = %d", ratio));
                sum += ratio;
            }
        }
        return sum;
    }

    public static void main(String args[])  //static method  
    {  
        try {
            URL path = Week3.class.getResource("Input.txt");
            File inputFile = new File(path.getFile());
            Scanner reader = new Scanner(inputFile);

            List<Vertex> machineNumbers = new ArrayList<>();
            List<Vertex> specialCharacters = new ArrayList<>();
            Integer index = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                List<List<Vertex>> parsedData = parseLine(data, index);
                machineNumbers.addAll(parsedData.get(0));
                specialCharacters.addAll(parsedData.get(1));
                index++;
            }
            Integer output = getMachineNumberSum(machineNumbers, specialCharacters);
            Integer output2 = getGearRatioSum(machineNumbers, specialCharacters);
            System.out.println(output);
            System.out.println(output2);
            
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    } 
}
