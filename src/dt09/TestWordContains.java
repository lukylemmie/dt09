package dt09;

/**
 * Created by IntelliJ IDEA.
 * User: lema
 * Date: 5/10/12
 * Time: 7:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestWordContains {
    public static void main(String[] args){
        DT09 dt09 = new DT09();
        
        System.out.println(dt09.wordContains("aalltb", "altb"));
        System.out.println(dt09.wordContains("aalltb", "altbsd"));
        System.out.println(dt09.wordContains("sdfasdf", "ff"));
    }
}
