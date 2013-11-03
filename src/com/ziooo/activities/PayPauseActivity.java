package com.ziooo.activities;

import org.joda.time.DateTime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;

import com.ziooo.model.Pause;
import com.ziooo.model.Person;
import com.ziooo.model.Type;
import com.ziooo.paypause.R;

public class PayPauseActivity extends MenuActivity {

	private static final int SECONDS_PER_MINUTE = 60;

	private Chronometer chronometer;
	private TextView gain;
	private Spinner pauseType;
	private boolean showGain;
	private Person person;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_pause);

		SharedPreferences settings = getApplicationContext().getSharedPreferences("userProfile", 0);

		Double s = 0d;
		Double w = 0d;

		try {
			s = Double.parseDouble(settings.getString("salary", "0.0"));
			w = Double.parseDouble(settings.getString("workedHours", "0.0"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		person = new Person(settings.getString("name", ""), (s / (w * 4)) / 3600);

		showGain = false;
		gain = (TextView) findViewById(R.id.gain);
		pauseType = (Spinner) findViewById(R.id.pauseType);
		chronometer = (Chronometer) findViewById(R.id.chronometer);
		chronometer.setOnClickListener(buttonChronometer_OnClickListener);

	}

	private String getChrono() {
		return chronometer.getText().toString();
	}

	private String[] getMinAndSec() {
		return getChrono().split(":");
	}

	private String secOfPause() {
		return getMinAndSec()[1];
	}

	private String minOfPause() {
		return getMinAndSec()[0];
	}

	private int getIntMinAndSec() {
		return Integer.parseInt(minOfPause()) * SECONDS_PER_MINUTE + Integer.parseInt(secOfPause());
	}

	final OnClickListener buttonChronometer_OnClickListener = new OnClickListener() {

		private String getDate() {
			DateTime now = DateTime.now();
			return now.getDayOfMonth() + "/" + now.getMonthOfYear() + "/" + now.getYear();
		}

		@Override
		public void onClick(final View v) {
			if (showGain) {
				chronometer.stop();
				Pause pause = new Pause(getDate(), getChrono(), person.getSalaryPerSec() * getIntMinAndSec(), person, new Type(pauseType
						.getSelectedItem().toString()));
				gain.setText(pause.toString());
			} else {
				gain.setText("");
				chronometer.setBase(SystemClock.elapsedRealtime());
				chronometer.start();
			}
			showGain = !showGain;
		}
	};

}
