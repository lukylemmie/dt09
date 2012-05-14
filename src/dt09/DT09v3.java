package dt09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: lema
 * Date: 5/14/12
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DT09v3 {
    public static void main(String[] args){
        DT09 dt09 = null;
        long startTime = System.currentTimeMillis();
        long endTime;
        long time;
        ArrayList<String> wordList = null;
        ArrayList<Puzzle> puzzles;
        ArrayList<String> nineLetters;
        int count = 0;
        ArrayList<ArrayList<String>> allWordsInCats = null;
        ArrayList<ArrayList<String>> nineWordsInCats = null;
        Puzzle temp = null;
        Puzzle highest = null;
        Puzzle highest2 = null;
        Puzzle highest3 = null;
        Puzzle lowest = null;
        Puzzle lowest2 = null;
        Puzzle lowest3 = null;
        Puzzle lowest4 = null;
        Puzzle lowest5 = null;
        Puzzle lowest6 = null;


        System.out.println("Get + rearrange + sort wordList");
        try {
            wordList = OpenFileRearrangeSort("wordList.txt");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public static ArrayList<String> OpenFileRearrangeSort(String path) throws IOException{
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> sortedLines = new ArrayList<String>();
        String line;
        int index;

        while((line = bufferedReader.readLine()) != null){
            line = rearrangeLetters(line);
            index = Collections.binarySearch(sortedLines, line);
            if(index < 0){
                index = -(index + 1);
            }
            sortedLines.add(index, line);
        }

        return sortedLines;
    }

    public static String rearrangeLetters(String word){
        char[] charArray = word.toCharArray();

        Arrays.sort(charArray);
        word = new String(charArray);

        return word;
    }
}
