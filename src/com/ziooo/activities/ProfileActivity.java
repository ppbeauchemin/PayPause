package com.ziooo.activities;

import com.ziooo.paypause.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class ProfileActivity extends MenuActivity {

	private EditText name;
	private EditText salary;
	private EditText workedHours;
	private SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		settings = getApplicationContext().getSharedPreferences("userProfile", 0);
		name = (EditText) findViewById(R.id.name);
		if (settings.contains("name")) {
			name.setText(settings.getString("name", ""));
		}
		salary = (EditText) findViewById(R.id.salary);
		if (settings.contains("salary")) {
			salary.setText(settings.getString("salary", "0.0"));
		}
		workedHours = (EditText) findViewById(R.id.workedHours);
		if (settings.contains("workedHours")) {
			workedHours.setText(settings.getString("workedHours", "0.0"));
		}

	}

	@Override
	protected void onPause() {
		super.onPause();

		SharedPreferences.Editor editor = settings.edit();
		editor.putString("name", name.getText().toString());
		editor.putString("salary", salary.getText().toString());
		editor.putString("workedHours", workedHours.getText().toString());

		// Commit the edits!
		editor.commit();

	}

}
