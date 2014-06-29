package com.example.property_animation;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
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
		private Button btnFallAnim, btnAlphaAnim, btnRotateAnim, btnScaleAnim,
				btnMoveAnim, btnBackColorAnim;

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
			btnScaleAnim = (Button) rootView.findViewById(R.id.btnScaleAnim);
			btnMoveAnim = (Button) rootView.findViewById(R.id.btnMoveAnim);
			btnBackColorAnim = (Button) rootView
					.findViewById(R.id.btnBackColorAnim);

			btnFallAnim.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (xIsFallingFirst) {
						y = xTxt.getY();
						yEnd = xLinLay.getHeight() - xTxt.getHeight();
						xIsFallingFirst = false;
					}

					ObjectAnimator animTxtFalling = ObjectAnimator.ofFloat(
							xTxt, "y", y, yEnd);
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
			btnScaleAnim.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ValueAnimator animTxtScale = ValueAnimator.ofInt(0, 50);
					animTxtScale.setDuration(4000);
					animTxtScale.setRepeatCount(1);
					animTxtScale.setRepeatMode(ObjectAnimator.REVERSE);
					animTxtScale.setInterpolator(new LinearInterpolator());
					animTxtScale
							.addUpdateListener(new AnimatorUpdateListener() {

								@Override
								public void onAnimationUpdate(
										ValueAnimator animation) {
									// TODO Auto-generated method stub
									int val = (Integer) animation
											.getAnimatedValue();
									xTxt.setTextSize(
											TypedValue.COMPLEX_UNIT_SP,
											15 + val);

								}
							});
					animTxtScale.start();
				}
			});
			btnMoveAnim.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					float x, xEnd1, xEnd2;

					x = xTxt.getX();
					xEnd1 = 0;
					xEnd2 = xLinLay.getWidth() - xTxt.getWidth();

					ObjectAnimator animTxtMove1 = ObjectAnimator.ofFloat(xTxt,
							"x", x, xEnd1);
					animTxtMove1.setDuration(2000);
					animTxtMove1.setInterpolator(new AccelerateInterpolator());

					ObjectAnimator animTxtMove2 = ObjectAnimator.ofFloat(xTxt,
							"x", x, xEnd2);
					animTxtMove2.setDuration(3000);
					animTxtMove2.setRepeatCount(1);
					animTxtMove2
							.setInterpolator(new AccelerateDecelerateInterpolator());

					ObjectAnimator animTxtMove3 = ObjectAnimator.ofFloat(xTxt,
							"x", xEnd2, x);
					animTxtMove3.setDuration(2000);
					animTxtMove3
							.setInterpolator(new AccelerateDecelerateInterpolator());

					AnimatorSet animTxtMove = new AnimatorSet();
					animTxtMove.playSequentially(animTxtMove1, animTxtMove2,
							animTxtMove3);
					animTxtMove.start();

				}
			});

			btnBackColorAnim.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					int iBackColorRedVal, iBackColorRedEnd;
					// final int iBackColor =
					// ((ColorDrawable)(xLinLay.getBackground())).getColor();

					// iBackColorRedVal = (iBackColor & 0x00FF0000) >> 16;
					iBackColorRedVal = (0x00FF0000) >> 16;

					if (iBackColorRedVal > 127) {
						iBackColorRedEnd = 0;
					} else {
						iBackColorRedEnd = 255;
					}

					ValueAnimator animScreenBackColor = ValueAnimator.ofInt(
							iBackColorRedVal, iBackColorRedEnd);

					animScreenBackColor.setDuration(3000);
					animScreenBackColor
							.setInterpolator(new LinearInterpolator());
					animScreenBackColor.start();

					animScreenBackColor
							.addUpdateListener(new AnimatorUpdateListener() {

								@Override
								public void onAnimationUpdate(
										ValueAnimator animation) {
									// TODO Auto-generated method stub
									int val = (Integer) animation
											.getAnimatedValue();

									// xLinLay.setBackgroundColor(iBackColor
									// &0xFF00FFFF | val << 16);
									xLinLay.setBackgroundColor(0xFF00FFFF | val << 16);
								}
							});
				}
			});

			return rootView;
		}
	}

}
