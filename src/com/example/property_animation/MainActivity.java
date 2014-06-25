package com.example.property_animation;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private LinearLayout xLinLay;
		private TextView xTxt;
		private Button btnFallAnim, btnAlphaAnim, btnRotateAnim;

		private float y, yEnd;
		private boolean xIsFallingFirst = true;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			xLinLay = (LinearLayout) rootView.findViewById(R.id.linLay);
			xTxt = (TextView) rootView.findViewById(R.id.txt);
			btnFallAnim = (Button) rootView.findViewById(R.id.btnFallAnim);
			btnAlphaAnim = (Button) rootView.findViewById(R.id.btnAlphaAnim);
			btnRotateAnim = (Button) rootView.findViewById(R.id.btnRotateAnim);

			btnFallAnim.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (xIsFallingFirst) {
						y = xTxt.getY();
						yEnd = xLinLay.getHeight() - xTxt.getHeight();
						xIsFallingFirst = false;
					}
					
					ObjectAnimator animTxtFalling = ObjectAnimator.ofFloat(xTxt, "y", y, yEnd);
					animTxtFalling.setDuration(2000);
					animTxtFalling.setRepeatCount(ObjectAnimator.INFINITE);
					animTxtFalling.setInterpolator(new BounceInterpolator());
					animTxtFalling.start();

				}
			});

			btnAlphaAnim.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ObjectAnimator animTxtAlpha = ObjectAnimator.ofFloat(xTxt,
							"alpha", 1, 0);
					animTxtAlpha.setDuration(2000);
					animTxtAlpha.setRepeatCount(1);
					animTxtAlpha.setRepeatMode(ObjectAnimator.REVERSE);
					animTxtAlpha.setInterpolator(new LinearInterpolator());
					animTxtAlpha.start();
				}
			});

			btnRotateAnim.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ObjectAnimator animTxtRotate = ObjectAnimator.ofFloat(xTxt,
							"rotation", 0, 360);
					// Log.d("debug", "step1");

					animTxtRotate.setDuration(1000);
					// Log.d("debug", "step2");
					// animTxtRotate.setRepeatCount(ObjectAnimator.INFINITE);
					animTxtRotate.setRepeatCount(1);
					// Log.d("debug", "step3");
					animTxtRotate.setRepeatMode(ObjectAnimator.REVERSE);
					// Log.d("debug", "step4");
					animTxtRotate
							.setInterpolator(new AccelerateDecelerateInterpolator());
					// animTxtRotate.setInterpolator(new
					// AccelerateInterpolator());
					// Log.d("debug", "step5");
					animTxtRotate.start();
					// Log.d("debug", "step6");

				}
			});

			return rootView;
		}
	}

}
