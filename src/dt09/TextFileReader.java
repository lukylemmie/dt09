package dt09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 23/04/12
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class TextFileReader {
    private static String path = "wordList.txt";

    public static void main (String[] args) throws IOException {
        TextFileReader reader = new TextFileReader();

        ArrayList<String> fileByLines = reader.OpenFile(path);

        int i = 0;
        for(String line : fileByLines){
            System.out.println(i + ":" + line);
            i++;
        }
    }

    public TextFileReader(){

    }

    public ArrayList<String> OpenFile(String path) throws IOException{
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> fileByLines = new ArrayList<String>();
        String line;

        while((line = bufferedReader.readLine()) != null){
            fileByLines.add(line);
        }

        return fileByLines;
    }
}
