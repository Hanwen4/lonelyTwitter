package ca.ualberta.cs.lonelytwitter;
import java.util.Date;
/**
 * Created by yanghanwen on 2018-01-18.
 */

public class NormalTweets extends Tweets {
    public NormalTweets(String message) {
        super(message);
    }

    public NormalTweets(String message, Date date) {
        super(message,date);
    }

    @Override
    public boolean isImportant() {
        return false;
    }
}
