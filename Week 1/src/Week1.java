import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

class Week1{

    public static Map<Integer, Integer> wordsToInteger(Map<Integer, Integer> list, String data) {
        Map<Integer, Integer> hitList = list;

        // Replacement dictionary 
        Map<String, Integer> replacementDict = new HashMap<>();
        replacementDict.put("zero", 0);
        replacementDict.put("one", 1);
        replacementDict.put("two", 2);
        replacementDict.put("three", 3);
        replacementDict.put("four", 4);
        replacementDict.put("five", 5);
        replacementDict.put("six", 6);
        replacementDict.put("seven", 7);
        replacementDict.put("eight", 8);
        replacementDict.put("nine", 9);

        for (Map.Entry<String, Integer> entry : replacementDict.entrySet()) {
            int index = data.indexOf(entry.getKey());
            while (index >= 0) {
                hitList.put(index, entry.getValue());
                index = data.indexOf(entry.getKey(), index + 1);
            }
        }

        return hitList;
    }

    public static Map<Integer, Integer> digitToInteger(String data) {
        Map<Integer, Integer> hitList = new HashMap<>();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c >= '0' && c <= '9') {
                hitList.put(i, c - '0');
            }
        }
        return hitList;
    }

    public static Integer getCalibrationValue(String data) {
        System.out.println(data);
        Map<Integer, Integer> hitList = digitToInteger(data);
        hitList = wordsToInteger(hitList, data);
        List<Integer> keys = new ArrayList<>(hitList.keySet());
        Collections.sort(keys);
        String returnValue = "" + hitList.get(keys.get(0)) + hitList.get(keys.get(keys.size() - 1));
        System.out.println(returnValue);
        return Integer.parseInt(returnValue);
    } 

    public static void main(String args[])  //static method  
    {  
        try {
            URL path = Week1.class.getResource("Input.txt");
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