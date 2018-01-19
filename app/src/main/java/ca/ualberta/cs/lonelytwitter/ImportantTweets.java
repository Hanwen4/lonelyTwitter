package ca.ualberta.cs.lonelytwitter;
import java.util.Date;
/**
 * Created by yanghanwen on 2018-01-18.
 */

public class ImportantTweets extends Tweets {

    public ImportantTweets(String message) {
        super(message);
    }
    public ImportantTweets(String message, Date date) {
        super(message, date);
    }

    @Override
    public boolean isImportant() {
        return true;
    }
}
