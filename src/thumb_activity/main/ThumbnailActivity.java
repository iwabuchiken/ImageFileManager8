package thumb_activity.main;

import ifm8.image_actv.ImageActivity;
import ifm8.lib.ButtonOnClickListener;
import ifm8.lib.ButtonOnTouchListener;
import ifm8.lib.Methods;
import ifm8.main.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ListView;

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
		/*----------------------------
		 * 1. Set listeners
			----------------------------*/
		set_listeners();
		
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
		
			
		
	}//private void initial_setup()

	private void set_listeners() {
		/*----------------------------
		 * Steps
		 * 1. "Back" button
			----------------------------*/
		//
		ImageButton ib_back = (ImageButton) findViewById(R.id.thumb_activity_ib_back);
		
		ib_back.setEnabled(true);
		ib_back.setImageResource(R.drawable.ifm8_thumb_back_50x50);
		
		ib_back.setTag(Methods.ButtonTags.thumb_activity_ib_back);
		
		ib_back.setOnTouchListener(new ButtonOnTouchListener(this));
		ib_back.setOnClickListener(new ButtonOnClickListener(this));
		 
		
		
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

				/*----------------------------
				 * 4. Re-set tiList
					----------------------------*/
				String tableName = Methods.convertPathIntoTableName(this);

				tiList.clear();
				
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
				

				/*----------------------------
				 * 4. Re-set tiList
					----------------------------*/
				String tableName = Methods.convertPathIntoTableName(this);

				tiList.clear();
				
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
			
			
			break;
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

}//public class ThumbnailActivity extends ListActivity
