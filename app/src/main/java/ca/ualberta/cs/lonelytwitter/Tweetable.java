package ca.ualberta.cs.lonelytwitter;

/**
 * Created by dezfuli on 1/16/18.
 */
import java.util.Date;

/**
 * Interface to determine if the tweet is tweetable or not
 */
public interface Tweetable {
    public String getMessage();
    public Date getDate();
}
