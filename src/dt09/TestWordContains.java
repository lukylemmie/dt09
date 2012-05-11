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
        
        System.out.println(DT09.staticWordContains("aabbccddeef", "aaccee"));
        System.out.println(DT09.staticWordContains("aabbccddeef", "aaddee"));
        System.out.println(DT09.staticWordContains("aaddeeffiij", "aabb"));
    }
}
