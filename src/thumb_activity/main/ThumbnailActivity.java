package thumb_activity.main;

import ifm8.image_actv.ImageActivity;
import ifm8.lib.ButtonOnClickListener;
import ifm8.lib.ButtonOnTouchListener;
import ifm8.lib.CustomOnItemLongClickListener;
import ifm8.lib.Methods;
import ifm8.main.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ThumbnailActivity extends ListActivity {
	/*----------------------------
	 * Class fields
		----------------------------*/
	//
	public static List<ThumbnailItem> tiList;

	//
	public static TIListAdapter aAdapter;

	//
	public static ArrayList<Integer> checkedPositions;
	
	//
	public static Vibrator vib;

	//
	public static boolean move_mode = false;

	/*------------------------------------------------------------------------------------
	 * Methods
	------------------------------------------------------------------------------------*/
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Log
		Log.d("ThumbnailActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onCreate => Started");
		
		move_mode = false;
		
		
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.thumb_activity);

        Methods.toastAndLog(this, 
        		ImageFileManager8Activity.currentDirPath + " :: " +
        		Methods.getCurrentPathLabel(this) + " :: " +
        		Methods.convertPathIntoTableName(this), 
        		2000);
        
        //
        vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        
        //
        checkedPositions = new ArrayList<Integer>();

        // Log
		Log.d("ThumbnailActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "checkedPositions.size() => " + checkedPositions.size());
		
		Log.d("ThumbnailActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "move_mode => " + move_mode);

		
//        SharedPreferences prefs = 
//        			getSharedPreferences("ifm8_thumb_actv_checked_items", MODE_PRIVATE);
//        
//        SharedPreferences.Editor editor = prefs.edit();
//        
//        
//        
//        editor.put
        
        initial_setup();
    }//void onCreate(Bundle savedInstanceState)

	private void initial_setup() {
		/*----------------------------
		 * Steps
		 * 1. Set listeners
		 * 2. Show list
		 * 		2.1. Get table name
		 * 		2.2. Get ThumbnailItem list
		 * 		2.3. Prepare adapter
		 * 		2.4. Set adapter to the list
			----------------------------*/
		
		//==> Move to after 2. 
//		/*----------------------------
//		 * 1. Set listeners
//			----------------------------*/
//		set_listeners();
		
		/*----------------------------
		 * 2.1. Get table name
		 * 2.2. Get ThumbnailItem list
			----------------------------*/
		String tableName = Methods.convertPathIntoTableName(this);
		
//		List<ThumbnailItem> tiList = Methods.getAllData(this, tableName);
		tiList = Methods.getAllData(this, tableName);
		
		// Log
		Log.d("ThumbnailActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tiList.size() => " + tiList.size());
		
		/*----------------------------
		 * 2.3. Prepare adapter
			----------------------------*/
//		TIListAdapter aAdapter =
		aAdapter = 
				new TIListAdapter(
						this, 
						ifm8.main.R.layout.thumb_activity, 
//						ThumbnailActivity.tiList);
						tiList);
		
		/*----------------------------
		 * 2.4. Set adapter to the list
			----------------------------*/
		if (aAdapter != null) {
			
			setListAdapter(aAdapter);
			
		} else {//if (aAdapter != null)
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "aAdapter => null");
			
		}//if (aAdapter != null)
		
		/*----------------------------
		 * 1. Set listeners
			----------------------------*/
		set_listeners();

		
	}//private void initial_setup()

	private void set_listeners() {
		/*----------------------------
		 * Steps
		 * 1. "Back" button
		 * 2. LongClick
		 * 3. "Bottom"
			----------------------------*/
		//
		ImageButton ib_back = (ImageButton) findViewById(R.id.thumb_activity_ib_back);
		
		ib_back.setEnabled(true);
		ib_back.setImageResource(R.drawable.ifm8_thumb_back_50x50);
		
		ib_back.setTag(Methods.ButtonTags.thumb_activity_ib_back);
		
		ib_back.setOnTouchListener(new ButtonOnTouchListener(this));
		ib_back.setOnClickListener(new ButtonOnClickListener(this));
		 
		/*----------------------------
		 * 2. LongClick
			----------------------------*/
//		ListView lv = (ListView) findViewById(android.R.layout.activity_list_item);
		ListView lv = this.getListView();
		
		lv.setTag(Methods.ItemTags.dir_list_thumb_actv);
		
		lv.setOnItemLongClickListener(new CustomOnItemLongClickListener(this));
		
		/*----------------------------
		 * 3. "Bottom"
		 * 		1. Set up
		 * 		2. Listeners
			----------------------------*/
		ImageButton bt_bottom = (ImageButton) findViewById(R.id.thumb_activity_ib_toBottom);
		
		bt_bottom.setEnabled(true);
		bt_bottom.setImageResource(R.drawable.ifm8_thumb_bottom_50x50);
		
		// Tag
		bt_bottom.setTag(Methods.ButtonTags.thumb_activity_ib_bottom);
		
		/*----------------------------
		 * 2. Listeners
			----------------------------*/
		bt_bottom.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_bottom.setOnClickListener(new ButtonOnClickListener(this, lv));
		
//		int firstVisiblePosition = lv.getFirstVisiblePosition();
//				
//		// Log
//		Log.d("ThumbnailActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "firstVisiblePosition => " + firstVisiblePosition);
//
//		Log.d("ThumbnailActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", "lv.getChildCount() => " + lv.getChildCount());
//				+ "]", "lv.getChildCount() => " + lv.getChildCount());
		
		
	}//private void set_listeners()

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 0. Vibrate
		 * 1. Get item
		 * 2. Intent
		 * 		2.1. Set data
		 * 3. Start intent
			----------------------------*/
		/*----------------------------
		 * 0. Vibrate
			----------------------------*/
		vib.vibrate(Methods.vibLength_click);
		
		if (move_mode == false) {
			/*----------------------------
			 * 1. Get item
				----------------------------*/
			ThumbnailItem ti = (ThumbnailItem) lv.getItemAtPosition(position);
			
			/*----------------------------
			 * 2. Intent
			 * 		2.1. Set data
				----------------------------*/
			Intent i = new Intent();
			
			i.setClass(this, ImageActivity.class);
			
			i.putExtra("file_id", ti.getFileId());
			i.putExtra("file_path", ti.getFile_path());
			i.putExtra("file_name", ti.getFile_name());
			
			this.startActivity(i);		
		} else if (move_mode == true) {//if (move_mode == false)
			
			/*----------------------------
			 * CheckBox on, then click on the item, then nothing happens (20120717_221403)
				----------------------------*/
			
			checkedPositions.add(position);
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "New position => " + position +
					" / " + "(length=" + checkedPositions.size() + ")");
			
			
		}//if (move_mode == false)
//				/*----------------------------
//				 * 1. Get item
//					----------------------------*/
//				ThumbnailItem ti = (ThumbnailItem) lv.getItemAtPosition(position);
//				
//				/*----------------------------
//				 * 2. Intent
//				 * 		2.1. Set data
//					----------------------------*/
//				Intent i = new Intent();
//				
//				i.setClass(this, ImageActivity.class);
//				
//				i.putExtra("file_id", ti.getFileId());
//				i.putExtra("file_path", ti.getFile_path());
//				i.putExtra("file_name", ti.getFile_name());
//				
//				this.startActivity(i);
		
		super.onListItemClick(lv, v, position, id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.thumb_actv_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/*----------------------------
		 * Steps
		 * 1. R.id.thumb_actv_menu_move_mode
		 * 2. R.id.thumb_actv_menu_move_files
			----------------------------*/
		
		
		case R.id.thumb_actv_menu_move_mode:
			
			if (move_mode == true) {
				/*----------------------------
				 * Steps: Current mode => false
				 * 1. Set icon => On
				 * 2. move_mode => true
				 * 4. Re-set tiList
				 * 3. Update aAdapter
					----------------------------*/
				
				item.setIcon(R.drawable.ifm8_thumb_actv_opt_menu_move_mode_off);
				
				move_mode = false;

				// Log
				Log.d("ThumbnailActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "move_mode => Now false");
				
				
				/*----------------------------
				 * 4. Re-set tiList
					----------------------------*/
				String tableName = Methods.convertPathIntoTableName(this);

				tiList.clear();

				// Log
				Log.d("ThumbnailActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "tiList => Cleared");

				Log.d("ThumbnailActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "checkedPositions.size() => " + checkedPositions.size());


//				ThumbnailActivity.tiList = Methods.getAllData(actv, tableName);
				
				tiList.addAll(Methods.getAllData(this, tableName));

				// Log
				Log.d("ThumbnailActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "tiList.size() => " + tiList.size());
				
				/*----------------------------
				 * 3. Update aAdapter
					----------------------------*/
//				aAdapter.clear();
				
				aAdapter = 
						new TIListAdapter(
								this, 
								ifm8.main.R.layout.thumb_activity, 
//								ThumbnailActivity.tiList);
								tiList,
								Methods.MoveMode.OFF);
				
				
				setListAdapter(aAdapter);

			} else {//if (move_mode)
				/*----------------------------
				 * Steps: Current mode => false
				 * 1. Set icon => On
				 * 2. move_mode => true
				 * 3. Update aAdapter
				 * 4. Re-set tiList
					----------------------------*/
				
				item.setIcon(R.drawable.ifm8_thumb_actv_opt_menu_move_mode_on);
				
				move_mode = true;
				
				// Log
				Log.d("ThumbnailActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "move_mode => Now true");
				

				/*----------------------------
				 * 4. Re-set tiList
					----------------------------*/
				String tableName = Methods.convertPathIntoTableName(this);

				tiList.clear();

				// Log
				Log.d("ThumbnailActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "tiList => Cleared");

				Log.d("ThumbnailActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "checkedPositions.size() => " + checkedPositions.size());

//				ThumbnailActivity.tiList = Methods.getAllData(actv, tableName);
				
				tiList.addAll(Methods.getAllData(this, tableName));

				// Log
				Log.d("ThumbnailActivity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "tiList.size() => " + tiList.size());
				
				/*----------------------------
				 * 3. Update aAdapter
					----------------------------*/
//				aAdapter.clear();
				
				aAdapter = 
						new TIListAdapter(
								this, 
								ifm8.main.R.layout.thumb_activity, 
//								ThumbnailActivity.tiList);
								tiList,
								Methods.MoveMode.ON);
//								Methods.MoveMode.ON, this);
				
				
				setListAdapter(aAdapter);
				
				
			}//if (move_mode)
			
			
			break;// case R.id.thumb_actv_menu_move_mode
			
		case R.id.thumb_actv_menu_move_files:	//------------------------------------------
			
			if (move_mode == false) {
				
				// debug
				Toast.makeText(ThumbnailActivity.this, "Move mode is not on", 2000)
						.show();
				
				return false;
				
			} else if (move_mode == true) {
				/*----------------------------
				 * Steps
				 * 1. checkedPositions => Has contents?
				 * 2. If yes, show dialog
					----------------------------*/
				if (checkedPositions.size() < 1) {
					
					Methods.toastAndLog(this, "No item selected", 2000);
					
					return false;
					
				}//if (checkedPositions.size() < 1)
				
				
				/*----------------------------
				 * 2. If yes, show dialog
					----------------------------*/
				Methods.dlg_moveFiles(this);
				
				
				
			}//if (move_mode == false)
			
			break;// case R.id.thumb_actv_menu_move_files
			
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

	@Override
	protected void onDestroy() {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Preferences
		 * 3. move_mode => false
			----------------------------*/
		
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		
		// Log
		Log.d("ThumbnailActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onDestroy");

		SharedPreferences prefs = 
		getSharedPreferences("ifm8_thumb_actv_checked_items", MODE_PRIVATE);
		
		SharedPreferences.Editor editor = prefs.edit();

		if (checkedPositions.size() > 0) {
			
//			Integer[] positions = (Integer[]) checkedPositions.toArray();
			Object[] positions = checkedPositions.toArray();
			
			String s_positions = StringUtils.join(positions, "/");
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "s_positions => " + s_positions);
			
			//
			editor.putString("checkd_positions", s_positions);
			
			editor.commit();
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Preferences => Stored");
			
			
			
		} else {//if (tiList.size() > 0)
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "checkedPositions.size() => equal to or less than 0");
			
		}//if (tiList.size() > 0)

		/*----------------------------
		 * 3. move_mode => false
			----------------------------*/
		move_mode = false;
		
	}//protected void onDestroy()


	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		// Log
		Log.d("ThumbnailActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onResume");
		
		
		
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();

		// Log
		Log.d("ThumbnailActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onPause");

	}

}//public class ThumbnailActivity extends ListActivity
