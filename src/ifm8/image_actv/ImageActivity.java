package ifm8.image_actv;

import ifm8.lib.ButtonOnClickListener;
import ifm8.lib.ButtonOnTouchListener;

import ifm8.lib.Methods;
import ifm8.main.*;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ImageActivity extends Activity {

	//
	public static Vibrator vib;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		----------------------------*/
		super.onCreate(savedInstanceState);

		//

//		setContentView(R.layout.image_activity);
		setContentView(R.layout.image_activity_for_myview);


		//
		vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
		

		initial_setup();
//		//
//		Intent i = getIntent();
//		
//		String file_path = i.getStringExtra("file_path");
//		long file_id = i.getLongExtra("file_id", -1);
//		
//		Methods.toastAndLog(this, file_path, 2000);
		
		
	}//public void onCreate(Bundle savedInstanceState)

	private void initial_setup() {
		/*----------------------------
		 * Steps
		 * 1. Get intent and data
		 * 2. Prepare image
		 * 3. Set image to the view
		 * 4. Set file name to the view
		 * 
		 * 5. Set listeners
			----------------------------*/
		

		//
		Intent i = getIntent();
		
		String file_path = i.getStringExtra("file_path");
		long file_id = i.getLongExtra("file_id", -1);
		String file_name = i.getStringExtra("file_name");
		
		/*----------------------------
		 * 2. Prepare image
			----------------------------*/
		Bitmap bm = BitmapFactory.decodeFile(file_path);
		
		/*----------------------------
		 * 3. Set image to the view
			----------------------------*/
		// MyView
		MyView v = new MyView(this);
		
		// Set image
		v.setImageBitmap(bm);

		//
		LinearLayout LL = (LinearLayout) findViewById(R.id.image_activity_LL_image);
		
		LL.addView(v);
		
//		ImageView iv = (ImageView) findViewById(R.id.image_activity_iv_image);
//		
//		iv.setImageBitmap(bm);
		
		/*----------------------------
		 * 4. Set file name to the view
			----------------------------*/
		TextView tv = (TextView) findViewById(R.id.image_activity_tv_message);
		
		tv.setText(file_name);
		
		/*----------------------------
		 * 5. Set listeners
			----------------------------*/
		set_listeners();
		
		
//		Methods.toastAndLog(this, file_path, 2000);
		
	}//private void initial_setup()

	private void set_listeners() {
		/*----------------------------
		 * Steps
		 * 1. "Back" button
		 * 		1.1. OnTouch
		 * 		1.2. OnClick
			----------------------------*/
		ImageButton ib_back = (ImageButton) findViewById(R.id.image_activity_ib_back);
		
		ib_back.setTag(Methods.ButtonTags.image_activity_back);
		
		ib_back.setOnTouchListener(new ButtonOnTouchListener(this));
		ib_back.setOnClickListener(new ButtonOnClickListener(this));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}
}//public class ImageActivity extends Activity
