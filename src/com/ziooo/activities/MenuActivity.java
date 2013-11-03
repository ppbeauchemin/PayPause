package com.ziooo.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.ziooo.paypause.R;

public class MenuActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pay_pause, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_profile:
			startActivity(new Intent(this, ProfileActivity.class));
			return true;
		case R.id.action_history:
			startActivity(new Intent(this, HistoryActivity.class));
			return true;
			// case R.id.action_settings:
			// openSettings();
			// return true;
		case R.id.action_paypause:
			startActivity(new Intent(this, PayPauseActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
