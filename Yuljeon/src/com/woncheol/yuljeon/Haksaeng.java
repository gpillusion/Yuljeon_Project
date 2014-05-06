package com.woncheol.yuljeon;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class Haksaeng extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.setAppTheme(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_haksaeng);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#96aa39")));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	};
}