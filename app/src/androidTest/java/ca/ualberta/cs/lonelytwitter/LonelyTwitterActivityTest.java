package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void  testTweet() {
        solo.assertCurrentActivity("wrong activity", LonelyTwitterActivity.class);

        solo.enterText((EditText) solo.getView(R.id.body), "test Tweet!");

        solo.clickOnButton("Save");

        solo.clearEditText((EditText) solo.getView(R.id.body));

        assertTrue(solo.waitForText("test Tweet!"));

        solo.clickOnButton("Clear");

        assertFalse(solo.waitForText("test Tweet!"));

    }

    public void testClickTweetList() {

        LonelyTwitterActivity activity = (LonelyTwitterActivity)solo.getCurrentActivity();

        solo.assertCurrentActivity("wrong activity", LonelyTwitterActivity.class);

        solo.enterText((EditText) solo.getView(R.id.body), "test Tweet!");

        solo.clickOnButton("Save");

        solo.clearEditText((EditText) solo.getView(R.id.body));

        assertTrue(solo.waitForText("test Tweet!"));

        final ListView oldTweetsList = activity.getOldTweetsList();

        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);

        Log.d("print: ", "aaaa" + tweet.getMessage());
        assertEquals("test Tweet!", tweet.getMessage());

        solo.clickInList(0);

        solo.assertCurrentActivity("wrong activity", EditTweetActivity.class);

        solo.goBack();

        solo.assertCurrentActivity("wrong activity", LonelyTwitterActivity.class);
    }

    public void testDataPass() {

        LonelyTwitterActivity activity = (LonelyTwitterActivity)solo.getCurrentActivity();

        solo.assertCurrentActivity("wrong activity", LonelyTwitterActivity.class);

        final ListView oldTweetsList = activity.getOldTweetsList();

        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);

        solo.searchText("test Tweet!");

        assertTrue(solo.searchText("test Tweet!"));

        solo.clickInList(0);

        solo.assertCurrentActivity("wrong activity", EditTweetActivity.class);

        assertTrue(solo.searchText("test Tweet!"));
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}