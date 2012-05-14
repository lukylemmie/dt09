package dt09;

/**
 * Created by IntelliJ IDEA.
 * User: lema
 * Date: 5/14/12
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Trie {
    public static int ALPHA_COUNT = 26;
    int value = 0;

    Trie[] branches = new Trie[ALPHA_COUNT];
    
    public Trie(){
    }

    public Trie getBranch(int index){
        return branches[index];
    }

    public void incValue(){
        value++;
    }
}
