package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

public class EditTweetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        TextView textview = (TextView) findViewById(R.id.text_view);
        Intent intent = getIntent();
        String text = intent.getStringExtra("bodyText").toString();
        textview.setText(text);
    }
}
