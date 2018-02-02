/*
 * Copyright © 2018 Hanwen Yang, CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute ot modify this code under terms and conditions of Code of Student Behavior at
 *  University of Alberta.
 * You can find a copy of the license in this project, otherwise please contact at
 *   hyang4@ualberta.ca
 * /
 */

package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.reflect.TypeToken;


/**
 * This is the main activity, get informations and store them
 * @author dezfuli
 * @version 1.0
 */
public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	private ArrayList<Tweet> tweetList;
	private ArrayAdapter<Tweet> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("LifeCycle ---->", "onCreate is called");
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
		Button clearButton = (Button) findViewById(R.id.clear);


		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				adapter.clear();
				saveInFile();//clear and save to clear the disk
			}
		});

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();//get text from bodytext and set to string type

				Tweet tweet = new NormalTweet(text);
				tweetList.add(tweet);

				adapter.notifyDataSetChanged();

				saveInFile();
			}
		});
	}

	/**
	 * set up an adapter for listView
	 */
	@Override
	protected void onStart() {

		// TODO Auto-generated method stub
		super.onStart();
		Log.i("LifeCycle --->", "onStart is called");
		loadFromFile();

		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweetList);

		oldTweetsList.setAdapter(adapter);

	}

	/**
	 * load informations that had been stored
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			//https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
			//2018-01-25
			Gson gson = new Gson();
			Type listType = new TypeToken<List<NormalTweet>>(){}.getType();
			tweetList = gson.fromJson(in, listType);
			if (tweetList == null){
				tweetList = new ArrayList<Tweet>();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			saveInFile();
			tweetList = new ArrayList<Tweet>();
			//e.printStackTrace();
		}
// catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * Save data and infos, for saving purpose
	 */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

			Gson gson = new Gson();
			gson.toJson(tweetList, out);
			out.flush();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("Lifecycle", "onDestroy is called");
	}
}