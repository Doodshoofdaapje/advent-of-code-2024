import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Week1{

    public static String preProcess(String data) {
        // Replacement dictionary 
        Map<String, String> replacementDict = new HashMap<>();
        replacementDict.put("one", "1");
        replacementDict.put("two", "2");
        replacementDict.put("three", "3");
        replacementDict.put("four", "4");
        replacementDict.put("five", "5");
        replacementDict.put("six", "6");
        replacementDict.put("seven", "7");
        replacementDict.put("eight", "8");
        replacementDict.put("nine", "9");

        String processedString = data;
        while (processedString != replaceFirstWord(processedString, replacementDict)) {
            processedString = replaceFirstWord(processedString, replacementDict);
        }

        return processedString;
    }

    public static String replaceFirstWord(String data, Map<String, String> replacements) {
        // Sort keys based on occurence in original string
        List<String> keys = new ArrayList<>(replacements.keySet());
        List<String> indexedKeys = getIndexesOfKeys(data, keys);

        String processedString = data;
        if (indexedKeys.size() > 0) {
            indexedKeys.sort(Comparator.comparingInt(data::indexOf));
            String firstKey = indexedKeys.get(0);
            processedString = data.replaceFirst(firstKey, replacements.get(firstKey));
        }
        
        return processedString;
    }

    public static Integer getCalibrationValue(String data) {
        String preProcessedString = preProcess(data);
        String filteredString = preProcessedString.replaceAll("([a-z])", "");
        String outputAsString = "" + filteredString.charAt(0) + filteredString.charAt(filteredString.length() - 1);
        Integer returnValue = Integer.parseInt(outputAsString);
        return returnValue;
    } 

    public static List<String> getIndexesOfKeys(String data, List<String> keys) {
        List<String> filteredKeys = new ArrayList<>();
        for (String key : keys) {
            if (data.indexOf(key) >= 0) {
                filteredKeys.add(key);
            }
        }
        return filteredKeys;
    }

    public static void main(String args[])  //static method  
    {  
        try {
            URL path = Week1.class.getResource("TestInput.txt");
            File inputFile = new File(path.getFile());
            Scanner reader = new Scanner(inputFile);
            Integer calibrationSum = 0;

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                calibrationSum += getCalibrationValue(data);
            }

            String outputString = String.format("Total calibration sum is %d", calibrationSum);
            System.out.println(outputString);

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    } 
}