package dt09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: lema
 * Date: 5/10/12
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class DT09 {
    TextFileReader textFileReader = new TextFileReader();
    private ArrayList<String> words;
    private ArrayList<ArrayList<String>> letterCats = new ArrayList<ArrayList<String>>();
    

    public DT09(){
        try {
            words = textFileReader.OpenFile("wordList.txt");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    
    public DT09(ArrayList<String> wordList){
        words = wordList;
    }
    
    public void rearrangeLetters(){
        char[] charArray;
        ArrayList<String> words2 = new ArrayList<String>();

        for(String word : words){
            charArray = word.toCharArray();
            Arrays.sort(charArray);
            word = new String(charArray);
            words2.add(word);
        }

        words = words2;
    }

    public void handleCompulsoryLetter(){
        ArrayList<String> tempList;
        String temp = "";

        for(char letter = 'a'; letter <= 'z'; letter++){
            tempList = new ArrayList<String>();
            for(String word : words){
                temp += letter;
                if(word.contains(temp)){
                    tempList.add(word);
                }
                temp = "";
            }

            letterCats.add(tempList);
        }
    }
    
    public void printACat(int cat){
        for(String word : letterCats.get(cat)){
            System.out.println(word);
        }
    }
    
    public void printCatSizes(){
        char letter = 'a';
        for(ArrayList<String> cat : letterCats){
            System.out.println(letter + ": " + cat.size());
            letter++;
        }
    }
    
    public Puzzle findHighestWord(int cat){
        Puzzle puzzle;
        Hashtable<String, Integer> scores = new Hashtable<String, Integer>();
        String highestWord = "";
        ArrayList<String> nineLetterWords = new ArrayList<String>();
        Integer score;
        ArrayList<String> aCat = letterCats.get(cat);

        //collect all the 9 letter words into an arraylist
        //put the 9 letter words into the hash table with Integer = 0 values
        for(String word : aCat){
            if(word.length() == 9){
                nineLetterWords.add(word);
                score = new Integer(0);
                scores.put(word, score);
            }
        }

        //use wordContains to check if other words are in that word
        for(String wordInCat : aCat){
            for(String nineWord : nineLetterWords){
                if(wordContains(nineWord, wordInCat)){
                    score = scores.get(nineWord);
                    score++;
                }
            }
        }
        
        //find the largest score and return that word
        highestWord = nineLetterWords.get(0);
        for(String word : nineLetterWords){
            if(scores.get(word) > scores.get(highestWord)){
                highestWord = word;
            }
        }

        puzzle = new Puzzle(highestWord, scores.get(highestWord), cat);
        
        return puzzle;
    }
    
    public Puzzle findHighestOverall(){
        ArrayList<Puzzle> highestWords = new ArrayList<Puzzle>();
        Puzzle highestScore;
        int i = 0;

        for(ArrayList<String> cat : letterCats){
            System.out.println("Searching through cat:" + i);
            highestWords.add(findHighestWord(i));
            i++;
        }

        highestScore = highestWords.get(0);
        for(Puzzle puzzle : highestWords){
            if(puzzle.getScore() > highestScore.getScore()){
                highestScore = puzzle;
            }
        }
        
        return highestScore;
    }
    
    public boolean wordContains(String word, String substring){
        boolean doesContain = true;
        int j = 0;
        int i = 0;
        while(j < substring.length() && i < word.length()){
            if(word.charAt(i) == substring.charAt(j)){
                j++;
            }
            i++;
        }
        if(j != substring.length()){
            doesContain = false;
        }
        return doesContain;
    }

    public ArrayList<String> getNineLetterWords(ArrayList<String> list){
        ArrayList<String> nineLetterWords = new ArrayList<String>();

        for(String word : list){
            if(word.length() == 9){
                nineLetterWords.add(word);
            }
        }

        return nineLetterWords;
    }

    public ArrayList<String> getAllNineLetter(){
        return getNineLetterWords(words);
    }

    public ArrayList<Puzzle> compareToCats(ArrayList<String> wordList){
        Puzzle highest;
        Puzzle lowest;
        ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();
        int theCat = 0;
        int count;
        Puzzle temp = new Puzzle(wordList.get(0), countSubstrings(letterCats.get(0), wordList.get(0)), 0);
        highest = temp;
        lowest = temp;

        for(ArrayList<String> cat : letterCats){
            System.out.println("Testing category: " + theCat);
            for(String word : wordList){
                count = countSubstrings(cat, word);
                temp = new Puzzle(word, count, theCat);
                if(count > highest.getScore()){
                    highest = temp;
                }
                if(count < lowest.getScore()){
                    lowest = temp;
                }
            }
            theCat++;
        }

        puzzles.add(highest);
        puzzles.add(lowest);

        return puzzles;
    }
    
    public int countSubstrings(ArrayList<String> cat, String word){
        int count = 0;
        for(String substring : cat){
            if(wordContains(word, substring)){
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args){
        DT09 dt09 = new DT09();
        long startTime = System.currentTimeMillis();
        long endTime;
        long time;
        ArrayList<String> wordList = null;
        ArrayList<Puzzle> puzzles;
        ArrayList<String> nineLetters;

        System.out.println("Get + rearrange + sort wordList");
        try {
            wordList = TextFileReader.OpenFileRearrangeSort("wordList.txt");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        System.out.println("Starting rearrange");
//        dt09.rearrangeLetters();
        if(wordList != null){
            dt09 = new DT09(wordList);
        }
        System.out.println("Compulsory categorising");
        dt09.handleCompulsoryLetter();
        nineLetters = dt09.getAllNineLetter();
        System.out.println("Size of nineLetters: " + nineLetters.size());
        puzzles = dt09.compareToCats(nineLetters);
        System.out.println("Highest: " + puzzles.get(0).getString() + " letter: " + puzzles.get(0).getCat());
        System.out.println("Lowest: " + puzzles.get(1).getString() + " letter: " + puzzles.get(1).getCat());
        endTime = System.currentTimeMillis();
        time = endTime - startTime;

        System.out.println("Time: " + time);
    }
}
