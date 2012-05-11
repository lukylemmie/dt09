package dt09;

/**
 * Created by IntelliJ IDEA.
 * User: lema
 * Date: 5/11/12
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Puzzle2 {
    private final Long word;
    private final Integer score;
    private final int cat;

    public Puzzle2(Long word, Integer score, int cat){
        this.word = word;
        this.score = score;
        this.cat = cat;
    }

    public Long getWord() {
        return word;
    }

    public Integer getScore() {
        return score;
    }

    public int getCat() {
        return cat;
    }
}
