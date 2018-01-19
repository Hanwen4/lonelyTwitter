package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
/**
 * Created by yanghanwen on 2018-01-18.
 */

public abstract class Tweets implements Tweetable{
    private String message;
    private Date date;

    public Tweets(String message) {
        this.message = message;
    }
    public Tweets(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) throws TweetTooLongException{
        if(message.length() > 140) {
            throw new TweetTooLongException();
        }
        else {
            this.message = message;
        }
    }

    public abstract boolean isImportant();
}
