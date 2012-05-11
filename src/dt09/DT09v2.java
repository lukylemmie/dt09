package dt09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: lema
 * Date: 5/11/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class DT09v2 {
    static int[] PRIMES = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
    static int ALPHA_COUNT = 26;

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        long endTime;
        long time;
        ArrayList<ArrayList<Long>> numsNNines = null;
        ArrayList<Long> numbers = new ArrayList<Long>();
        ArrayList<Long> nineLetterNumbers = new ArrayList<Long>();
        ArrayList<ArrayList<Long>> numbersInCats = null;
        ArrayList<ArrayList<Long>> ninesInCats = null;
        ArrayList<Puzzle2> puzzles;
        Puzzle2 temp = null;
        Puzzle2 highest = null;
        Puzzle2 highest2 = null;
        Puzzle2 highest3 = null;
        Puzzle2 lowest = null;
        Puzzle2 lowest2 = null;
        Puzzle2 lowest3 = null;
        Puzzle2 lowest4 = null;
        Puzzle2 lowest5 = null;
        Puzzle2 lowest6 = null;

        try {
            numsNNines = OpenFilePrimed("wordList.txt");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        assert(numsNNines != null);

        numbers = numsNNines.get(0);
        nineLetterNumbers = numsNNines.get(1);

//        for(int i = 0; i < numbers.size(); i++){
//            System.out.println(i + ": " + numbers.get(i));
//        }


        numbersInCats = putIntoCats(numbers);
        ninesInCats = putIntoCats(nineLetterNumbers);

        for(int i = 0; i < ALPHA_COUNT; i++){
            System.out.println("Processing cat: " + i);
            puzzles = findSet(numbersInCats.get(i), ninesInCats.get(i), i);
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

        System.out.println("Highest: " + convertToWord(highest.getWord()) + " score: " + highest.getScore() + " cat: " + highest.getCat());
        System.out.println("Highest2: " + convertToWord(highest2.getWord()) + " score: " + highest2.getScore() + " cat: " + highest2.getCat());
        System.out.println("Highest3: " + convertToWord(highest3.getWord()) + " score: " + highest3.getScore() + " cat: " + highest3.getCat());
        System.out.println("Lowest: " + convertToWord(lowest.getWord()) + " score: " + lowest.getScore() + " cat: " + lowest.getCat());
        System.out.println("Lowest2: " + convertToWord(lowest2.getWord()) + " score: " + lowest2.getScore() + " cat: " + lowest2.getCat());
        System.out.println("Lowest3: " + convertToWord(lowest3.getWord()) + " score: " + lowest3.getScore() + " cat: " + lowest3.getCat());
        System.out.println("Lowest4: " + convertToWord(lowest4.getWord()) + " score: " + lowest4.getScore() + " cat: " + lowest4.getCat());
        System.out.println("Lowest5: " + convertToWord(lowest5.getWord()) + " score: " + lowest5.getScore() + " cat: " + lowest5.getCat());
        System.out.println("Lowest6: " + convertToWord(lowest6.getWord()) + " score: " + lowest6.getScore() + " cat: " + lowest6.getCat());

        endTime = System.currentTimeMillis();
        time = endTime - startTime;

        System.out.println("Time: " + time);
    }

    public static String convertToWord(Long number){
        String word = "";

        for(int i = 0; i < PRIMES.length; i++){
            while(number % PRIMES[i] == 0){
                number /= PRIMES[i];
                word += (char)(i + 'a');
            }
        }

        return word;
    }

    public static ArrayList<Puzzle2> findSet(ArrayList<Long> wordList, ArrayList<Long> nineLetterWords, int cat){
        ArrayList<Puzzle2> set = new ArrayList<Puzzle2>();
        Puzzle2 lowest = null;
        Puzzle2 highest = null;
        Puzzle2 temp = null;
        int count;

        for(Long nineWord : nineLetterWords){
            count = 0;
            for(Long word : wordList){
                if(nineWord % word == 0){
                    count++;
                }
            }
            temp = new Puzzle2(nineWord, count, cat);
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

    public static ArrayList<ArrayList<Long>> putIntoCats(ArrayList<Long> numberList){
        ArrayList<ArrayList<Long>> cats = new ArrayList<ArrayList<Long>>();
        ArrayList<Long> temp;

        for(int i = 0; i < PRIMES.length; i++){
            temp = new ArrayList<Long>();
            for(Long number : numberList){
                if(number % PRIMES[i] == 0){
                    temp.add(number);
                }
            }
            cats.add(temp);
        }

        return cats;
    }

    public static ArrayList<ArrayList<Long>> OpenFilePrimed(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<Long> numbers = new ArrayList<Long>();
        ArrayList<Long> nineLetterNumbers = new ArrayList<Long>();
        ArrayList<ArrayList<Long>> numsNNines = new ArrayList<ArrayList<Long>>();
        String line;
        long word;

        while((line = bufferedReader.readLine()) != null){
            word = convertToNum(line);
            numbers.add(word);
            if(line.length() == 9){
                nineLetterNumbers.add(word);
            }
        }

        numsNNines.add(numbers);
        numsNNines.add(nineLetterNumbers);

        return numsNNines;
    }

    public static long convertToNum(String word){
        long number = 1;
        int temp;
        char[] charArray = word.toCharArray();

        for(char letter : charArray){
            temp = letter - 'a';
            number *= PRIMES[temp];
        }

        return number;
    }
}
