package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yanghanwen on 2018-01-18.
 */

public class mood_happy extends currentMood {

    public mood_happy(String mood) {
        super(mood);
    }

    public mood_happy(Date date) {
        super(date);
    }

    @Override
    public String getMood() {
        return mood;
    }
}
