package dt09;

/**
 * Created by IntelliJ IDEA.
 * User: lema
 * Date: 5/10/12
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringNScore {
    private final String string;
    private final Integer score;
    private final int cat;
    
    public StringNScore(String string, Integer score, int cat){
        this.string = string;
        this.score = score;
        this.cat = cat;
    }

    public String getString() {
        return string;
    }

    public Integer getScore() {
        return score;
    }
}
