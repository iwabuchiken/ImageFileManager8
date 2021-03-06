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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ImageActivity extends Activity {

	//
	public static Vibrator vib;

	//
	long file_id;
	
	//
	Bitmap bm;
	
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
		 * 
		 * 3-2
		 * 
		 * 4. Set file name to the view
		 * 
		 * 5. Set listeners
			----------------------------*/
		

		//
		Intent i = getIntent();
		
		String file_path = i.getStringExtra("file_path");
		file_id = i.getLongExtra("file_id", -1);
		String file_name = i.getStringExtra("file_name");
		
		/*----------------------------
		 * 2. Prepare image
			----------------------------*/
//		Bitmap bm = BitmapFactory.decodeFile(file_path);
		bm = BitmapFactory.decodeFile(file_path);
		
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
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.image_actv_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.image_actv_menu_add_memo://------------------------------------
			
			// Log
			Log.d("ImageActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "file_id => " + file_id);
			
			
			Methods.dlg_addMemo(this, file_id, Methods.convertPathIntoTableName(this));
			
//			Methods.addMemo(this, file_id, Methods.convertPathIntoTableName(this));
			
			break;
			
		case R.id.image_actv_menu_add_patterns://------------------------------------
			
			Methods.dlg_register_patterns(this);
			
			break;
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

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
		
		// REF=> http://ameblo.jp/yolluca/entry-10725668557.html
		if (bm != null) {
			bm.recycle();
			
			// Log
			Log.d("ImageActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "bm => recycled");
			
			
		}//if (bm != null)
		
	}

}//public class ImageActivity extends Activity
