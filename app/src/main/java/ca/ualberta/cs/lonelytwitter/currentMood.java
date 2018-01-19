package ca.ualberta.cs.lonelytwitter;

import java.util.Date;


/**
 * Created by yanghanwen on 2018-01-18.
 */

public abstract class currentMood {
    public String mood;
    private Date date;

    public currentMood(String mood) {
        this.mood = mood;
        this.date = new Date();//set date as default
    }

    public currentMood(Date date) {
        this.date = date;
    }

   public Date getDate() {
        return date;
   }

   public void setDate(Date date) {
        this.date = date;
   }

    public abstract String getMood();
}
