import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Week3 {

    public static List<String> parseLine(String data) {
        List<String> row = new ArrayList<>();
        for (char c : data.toCharArray()) {
            row.add(".");
        }
        Pattern pattern = Pattern.compile("(\\d+|[@#$%^&*=\\+\\/-])");
        Matcher matcher = pattern.matcher(data);
        Integer index = -1;
        while (matcher.find()) {
            String element = matcher.group(1);
            index = data.indexOf(element, index + 1);
            for (int j = 0; j < element.length(); j++) {
                row.set(index + j, element + (element.length() - j -1));
            }
        }

        return row;
    }

    public static Integer checkSurroundingCells(List<List<String>> matrix, Integer i, Integer j) {
        List<String> specialSymbols = new ArrayList<>(List.of("@", "#", "$", "%", "^", "&", "*", "=", "+", "-", "/"));
        String element = matrix.get(i).get(j);
        if (element == ".") {
            return 0;
        }
        element = element.substring(0, element.length() - 1);
        System.out.print("Row " + i + " ");
        System.out.println(element);
        if (specialSymbols.contains(element)) {
            return 0;
        }
        for (int k = Math.max(i-1, 0); k <= Math.min(i+1, matrix.size() -1); k++) {
            for (int l = Math.max(j-1, 0); l <= Math.min(j+1, matrix.get(0).size() -1); l++) {
                if (k == i && l == j) {
                    continue;
                }
                String cell = matrix.get(k).get(l);
                cell = cell.substring(0, cell.length() - 1);
                //System.out.println(String.format("cell %d, %d = %s", k, l, cell));
                if (specialSymbols.contains(cell)) {
                    //System.out.println(element);
                    return Integer.parseInt(element);
                }
            }
        }
        return 0;
    }

    public static Integer getMachineNumberSum(List<List<String>> matrix) {
        Integer sum = 0;
        for (int k = 0; k < matrix.size(); k++) {
            for (int l = 0; l < matrix.get(k).size() -1; l++) {
                Integer cellValue = checkSurroundingCells(matrix, k, l);
                if (cellValue > 0) {
                    String cellAsString = matrix.get(k).get(l);
                    char lastCharacter = cellAsString.charAt(cellAsString.length() -1);
                    l += lastCharacter - '0'+1;
                }
                sum += cellValue;
            }
        }
        return sum;
    }

    public static void main(String args[])  //static method  
    {  
        try {
            URL path = Week3.class.getResource("GlassBoxInput.txt");
            File inputFile = new File(path.getFile());
            Scanner reader = new Scanner(inputFile);

            List<List<String>> matrix = new ArrayList<>();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                matrix.add(parseLine(data));
            }
            System.out.println(getMachineNumberSum(matrix));
            //printMatrix(matrix);
            
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    } 

    public static void printMatrix(List<List<String>> matrix) {
        for (List<String> row : matrix) {
            for (String elemnt : row) {
                System.out.print(elemnt);
            }
            System.out.println("");
        }
    }
}
