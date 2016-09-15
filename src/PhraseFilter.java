/**
 * Created by Paul on 9/14/2016.
 */
public class PhraseFilter implements Filter {

    private String where;
    private String phrase;

    public PhraseFilter(String loc, String word) {
        where = loc;
        phrase = word;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        int index = title.indexOf(phrase);
        if (where.equals("any") && index != -1) {
            return true;
        } else if (where.equals("start") && index == 0) {
            return true;
        } else if (where.equals("end") && index == title.length() - phrase.length()) {
                return true;
        }
        return false;
    }
}