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

    public static ArrayList<ArrayList<String>> handleCompulsoryLetter(ArrayList<String> wordList){
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        ArrayList<String> tempList;
        String temp = "";

        for(char letter = 'a'; letter <= 'z'; letter++){
            tempList = new ArrayList<String>();
            for(String word : wordList){
                temp += letter;
                if(word.contains(temp)){
                    tempList.add(word);
                }
                temp = "";
            }

            output.add(tempList);
        }

        return output;
    }

    public static ArrayList<String> getNineLetterWords(ArrayList<String> list){
        ArrayList<String> nineLetterWords = new ArrayList<String>();

        for(String word : list){
            if(word.length() == 9){
                nineLetterWords.add(word);
            }
        }

        return nineLetterWords;
    }

    public static boolean staticWordContains(String word, String substring){
        boolean doesContain = true;
        int j = 0;
        int i = 0;
        while(j < substring.length() && i < word.length() && doesContain){
            if(word.charAt(i) > substring.charAt(j)){
                doesContain = false;
            }
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
    
    public static ArrayList<Puzzle> findHighestPuzzle(ArrayList<String> wordList, ArrayList<String> nineLetterWords, int cat){
        ArrayList<Puzzle> set = new ArrayList<Puzzle>();
        Puzzle lowest = null;
        Puzzle highest = null;
        Puzzle temp = null;
        int count;

        for(String nineWord : nineLetterWords){
            count = 0;
            for(String word : wordList){
                if(staticWordContains(nineWord, word)){
                    count++;
                }
            }
            temp = new Puzzle(nineWord, count, cat);
            if(highest == null || temp.getScore() > highest.getScore()){
                highest = temp;
            }
            if(lowest == null || temp.getScore() < lowest.getScore()){
                lowest = temp;
            }
        }

        set.add(highest);
        set.add(lowest);

        return set;
    }
    
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
            wordList = TextFileReader.OpenFileRearrangeSort("wordList.txt");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        nineLetters = getNineLetterWords(wordList);
        
        allWordsInCats = handleCompulsoryLetter(wordList);
        nineWordsInCats = handleCompulsoryLetter(nineLetters);

        int cat = 0;
        for(ArrayList<String> aCat : allWordsInCats){
            System.out.println("Processing cat: " + cat);
            puzzles = findHighestPuzzle(allWordsInCats.get(cat), nineWordsInCats.get(cat), cat);
            cat++;
            if(puzzles.size() > 0){
                temp = puzzles.get(0);
                if(temp != null){
                    if(highest == null || temp.getScore() > highest.getScore()){
                        highest3 = highest2;
                        highest2 = highest;
                        highest = temp;
                    } else if(highest2 == null || temp.getScore() > highest2.getScore()){
                        highest3 = highest2;
                        highest2 = temp;
                    } else if(highest3 == null || temp.getScore() > highest3.getScore()){
                        highest3 = temp;
                    }
                }
                temp = puzzles.get(1);
                if(temp != null){
                    if(lowest == null || temp.getScore() < lowest.getScore()){
                        lowest6 = lowest5;
                        lowest5 = lowest4;
                        lowest4 = lowest3;
                        lowest3 = lowest2;
                        lowest2 = lowest;
                        lowest = temp;
                    } else if(lowest2 == null || temp.getScore() < lowest2.getScore()){
                        lowest6 = lowest5;
                        lowest5 = lowest4;
                        lowest4 = lowest3;
                        lowest3 = lowest2;
                        lowest2 = temp;
                    } else if(lowest3 == null || temp.getScore() < lowest3.getScore()){
                        lowest6 = lowest5;
                        lowest5 = lowest4;
                        lowest4 = lowest3;
                        lowest3 = temp;
                    } else if(lowest4 == null || temp.getScore() < lowest4.getScore()){
                        lowest6 = lowest5;
                        lowest5 = lowest4;
                        lowest4 = temp;
                    } else if(lowest5 == null || temp.getScore() < lowest5.getScore()){
                        lowest6 = lowest5;
                        lowest5 = temp;
                    } else if(lowest6 == null || temp.getScore() < lowest6.getScore()){
                        lowest6 = temp;
                    }
                }
            }
        }

        System.out.println("Highest: " + highest.getString() + " score: " + highest.getScore() + " cat: " + highest.getCat());
        System.out.println("Highest2: " + highest2.getString() + " score: " + highest2.getScore() + " cat: " + highest2.getCat());
        System.out.println("Highest3: " + highest3.getString() + " score: " + highest3.getScore() + " cat: " + highest3.getCat());
        System.out.println("Lowest: " + lowest.getString() + " score: " + lowest.getScore() + " cat: " + lowest.getCat());
        System.out.println("Lowest2: " + lowest2.getString() + " score: " + lowest2.getScore() + " cat: " + lowest2.getCat());
        System.out.println("Lowest3: " + lowest3.getString() + " score: " + lowest3.getScore() + " cat: " + lowest3.getCat());
        System.out.println("Lowest4: " + lowest4.getString() + " score: " + lowest4.getScore() + " cat: " + lowest4.getCat());
        System.out.println("Lowest5: " + lowest5.getString() + " score: " + lowest5.getScore() + " cat: " + lowest5.getCat());
        System.out.println("Lowest6: " + lowest6.getString() + " score: " + lowest6.getScore() + " cat: " + lowest6.getCat());

//
//        for(String word : nineLetters){
//            System.out.println(count + ": " +word);
//            count++;
//        }
//        System.out.println("Starting rearrange");
//        dt09.rearrangeLetters();
//        if(wordList != null){
//            dt09 = new DT09(wordList);
//        }
//        System.out.println("Compulsory categorising");
//        dt09.handleCompulsoryLetter();
//        nineLetters = dt09.getAllNineLetter();
//        System.out.println("Size of nineLetters: " + nineLetters.size());
//        puzzles = dt09.compareToCats(nineLetters);
//        System.out.println("Highest: " + puzzles.get(0).getString() + " letter: " + puzzles.get(0).getCat());
//        System.out.println("Lowest: " + puzzles.get(1).getString() + " letter: " + puzzles.get(1).getCat());
        endTime = System.currentTimeMillis();
        time = endTime - startTime;

        System.out.println("Time: " + time);
    }
}
