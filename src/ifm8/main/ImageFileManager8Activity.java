package ifm8.main;

//import ifm8.lib.ButtonOnClickListener;
//import ifm8.lib.ButtonOnTouchListener;
//import ifm8.lib.CustomOnItemLongClickListener;
//import ifm8.lib.Methods;
import ifm8.lib.*;
//import ifm8.lib.DBUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageFileManager8Activity extends ListActivity {
	
	public static String currentDirPath = null;
	
	/*----------------------------
	 * baseDirPath => "/mnt/sdcard-ext", baseDirName
		----------------------------*/
	public static String baseDirPath = null;

	public static String dirSDCard = "/mnt/sdcard-ext";
	
	public static String baseDirName = "IFM8";
	public static String listFileName = "list.txt";
	public static String dbName = "IFM8";

	public static String backupTableName = "IFM8_backup";
	
	public static String refreshLogTableName = "refresh_log";
	
	public static String tableNameSeparator = "__";
			
	public static List<String> file_names = null;

	public static ArrayAdapter<String> adapter = null;
	
	static Comparator fileNameComparator;

	public static Vibrator vib;

//	public static final int vibLength_click = 40;
//	public static final int vibLength_click = 30;
	public static final int vibLength_click = 35;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v1);
        
        vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        
        set_initial_dir_list();
        
        setup_image_buttons();
    }

	private void setup_image_buttons() {
		/*----------------------------
		 * Steps
		 * 1. Get views
		 * 2. "Up" button => Set up
		 * 3. Listeners => Click
			----------------------------*/
		
		ImageButton ib_up = (ImageButton) findViewById(R.id.v1_bt_up);
//		ImageButton ib_back = (ImageButton) findViewById(R.id.v1_bt_back);
//		ImageButton ib_forward = (ImageButton) findViewById(R.id.v1_bt_forward);
		
		if (this.currentDirPath == this.baseDirPath) {
			
			ib_up.setEnabled(false);
			
		}//if (this.currentDirPath == this.baseDirPath)
		
		/*----------------------------
		 * 3. Listeners => Click
			----------------------------*/
		ib_up.setTag(Methods.ButtonTags.ib_up);
		
		ib_up.setOnTouchListener(new ButtonOnTouchListener(this));
		ib_up.setOnClickListener(new ButtonOnClickListener(this));
		
		
//		ib_back.setEnabled(false);
//		ib_forward.setEnabled(false);
		
	}//private void setup_image_button()

	private void set_initial_dir_list() {
		/*----------------------------
		 * Steps
		 * 	1. Part 1
			 * 1. Create root dir
			 * 1-2. Create "list.txt"
			 * 2. Set variables => currentDirPath, baseDirPath
			 * 3. Get file list
			 * 3-1. Sort file list
			 * 4. Set list to adapter
			 * 5. Set adapter to list view
			 * 
			 * 6. Set listener to list
		 * 
		 * 	2. Part 2
			 * 1. Set path label to text view
			 * 2. Create a table
			----------------------------*/
		
		set_initial_dir_list_part1();
		set_initial_dir_list_part2();
		
//		// TODO 自動生成されたメソッド・スタブ
////		File dir = Environment.getExternalStorageDirectory();
//		File dir = new File("/mnt/sdcard-ext");
//		
//		File file = new File(dir, baseDirName);
//		
//		if (!file.exists()) {
//			try {
//				file.mkdir();
//				
//				// Log
//				Log.d("ImageFileManager8Activity.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "Dir created => " + file.getAbsolutePath());
//				
//			} catch (Exception e) {
//				// Log
//				Log.d("ImageFileManager8Activity.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "Exception => " + e.toString());
//				
//			}
//		} else {//if (file.exists())
//			// Log
//			Log.d("ImageFileManager8Activity.java"
//					+ "["
//					+ Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + "]", "Dir exists => " + file.getAbsolutePath());
//		}//if (file.exists())
//		
//		/*----------------------------
//		 * 1-2. Create "list.txt"
//			----------------------------*/
//		File list_file = new File(file, listFileName);
//		
//		if (list_file.exists()) {
//			// Log
//			Log.d("ImageFileManager8Activity.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "\"list.txt\" => Exists");
//			
//		} else {//if (list_file.exists())
//			try {
//				BufferedWriter br = new BufferedWriter(new FileWriter(list_file));
//				br.close();
//			} catch (IOException e) {
//				// Log
//				Log.d("ImageFileManager8Activity.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "BufferedWrite: Exception => " + e.toString());
//				
//			}
//		}//if (list_file.exists())
//		
//		
//		/*----------------------------
//		 * 2. Set variables => currentDirPath, baseDirPath
//			----------------------------*/
//		baseDirPath = file.getAbsolutePath();
//		currentDirPath = file.getAbsolutePath();
//		
//		/*----------------------------
//		 * 3. Get file list
//		 * 3-1. Sort file list
//			----------------------------*/
//		File[] files = file.listFiles();
//		
////		File[] files = dir.listFiles();
//		
////		Methods.makeComparator(fileNameComparator);
//		
////		Collections.sort(files, fileNameComparator);
//		
//		// Sort
//		Methods.sortFileList(files);
//		
////		List<String> file_names = new ArrayList<String>();
//		file_names = new ArrayList<String>();
//		
//		for (File item : files) {
//			file_names.add(item.getName());
//		}
//		
//		/*----------------------------
//		 * 4. Set list to adapter
//			----------------------------*/
////		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//		adapter = new ArrayAdapter<String>(
//									this,
//									android.R.layout.simple_list_item_1,
//									file_names
//									);
//		
//		
//		/*----------------------------
//		 * 5. Set adapter to list view
//			----------------------------*/
//		this.setListAdapter(adapter);
//		
//		// Log
//		Log.d("ImageFileManager8Activity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "adapter => set");
//		
//		if (adapter == null) {
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "adapter => null");
//
//		} else {//if (adapter == null)
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "adapter => Not null");
//		}//if (adapter == null)
//		
//		/*----------------------------
//		 * 6. Set listener to list
//			----------------------------*/
//		ListView lv = this.getListView();
//		
//		lv.setTag(Methods.ItemTags.dir_list);
//		
//		lv.setOnItemLongClickListener(new CustomOnItemLongClickListener(this));
////				new OnItemLongClickListener(){
////
////			@Override
////			public boolean onItemLongClick(AdapterView<?> parent, View v,
////					int position, long id) {
////				// TODO 自動生成されたメソッド・スタブ
////				
////				String name = (String) parent.getItemAtPosition(position);
////				
////				//
//////				vib.vibrate(400);
////				vib.vibrate(40);
////
////				Methods.dlg_removeFolder(this);
////				
////				return false;
////			}});
//		
//		/*----------------------------
//		 * 7. Set path label to text view
//			----------------------------*/
//		
//		Methods.updatePathLabel(this);
//		
//		
////		TextView tv = (TextView) findViewById(R.id.v1_tv_dir_path);
////		
////		String currentPathLabel = Methods.getCurrentPathLabel(this);
////		
////		// Log
////		Log.d("ImageFileManager8Activity.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", "currentPathLabel => " + currentPathLabel);
//		
//		/*----------------------------
//		 * 8. Create a table
//			----------------------------*/
//		
//		
		
	}//private void set_initial_dir_list()

	private void set_initial_dir_list_part1() {
		/*----------------------------
		 * Steps
		 * 1. Create root dir
		 * 1-2. Create "list.txt"
		 * 2. Set variables => currentDirPath, baseDirPath
		 * 3. Get file list
		 * 3-1. Sort file list
		 * 4. Set list to adapter
		 * 5. Set adapter to list view
		 * 
		 * 6. Set listener to list
			----------------------------*/
		
		// TODO 自動生成されたメソッド・スタブ
//		File dir = Environment.getExternalStorageDirectory();
		File dir = new File("/mnt/sdcard-ext");
		
		File file = new File(dir, baseDirName);
		
		if (!file.exists()) {
			try {
				file.mkdir();
				
				// Log
				Log.d("ImageFileManager8Activity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Dir created => " + file.getAbsolutePath());
				
			} catch (Exception e) {
				// Log
				Log.d("ImageFileManager8Activity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Exception => " + e.toString());
				
			}
		} else {//if (file.exists())
			// Log
			Log.d("ImageFileManager8Activity.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Dir exists => " + file.getAbsolutePath());
		}//if (file.exists())
		
		/*----------------------------
		 * 1-2. Create "list.txt"
			----------------------------*/
		File list_file = new File(file, listFileName);
		
		if (list_file.exists()) {
			// Log
			Log.d("ImageFileManager8Activity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "\"list.txt\" => Exists");
			
		} else {//if (list_file.exists())
			try {
				BufferedWriter br = new BufferedWriter(new FileWriter(list_file));
				br.close();
			} catch (IOException e) {
				// Log
				Log.d("ImageFileManager8Activity.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "BufferedWrite: Exception => " + e.toString());
				
			}
		}//if (list_file.exists())
		
		
		/*----------------------------
		 * 2. Set variables => currentDirPath, baseDirPath
			----------------------------*/
		baseDirPath = file.getAbsolutePath();
		currentDirPath = file.getAbsolutePath();
		
		/*----------------------------
		 * 3. Get file list
		 * 3-1. Sort file list
			----------------------------*/
		File[] files = file.listFiles();
		
//		File[] files = dir.listFiles();
		
//		Methods.makeComparator(fileNameComparator);
		
//		Collections.sort(files, fileNameComparator);
		
		// Sort
		Methods.sortFileList(files);
		
//		List<String> file_names = new ArrayList<String>();
		file_names = new ArrayList<String>();
		
		for (File item : files) {
			file_names.add(item.getName());
		}
		
		/*----------------------------
		 * 4. Set list to adapter
			----------------------------*/
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		adapter = new ArrayAdapter<String>(
									this,
									android.R.layout.simple_list_item_1,
									file_names
									);
		
		
		/*----------------------------
		 * 5. Set adapter to list view
			----------------------------*/
		this.setListAdapter(adapter);
		
		// Log
		Log.d("ImageFileManager8Activity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "adapter => set");
		
		if (adapter == null) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "adapter => null");

		} else {//if (adapter == null)
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "adapter => Not null");
		}//if (adapter == null)
		
		/*----------------------------
		 * 6. Set listener to list
			----------------------------*/
		ListView lv = this.getListView();
		
		lv.setTag(Methods.ItemTags.dir_list);
		
		lv.setOnItemLongClickListener(new CustomOnItemLongClickListener(this));
		
	}//private void set_initial_dir_list()

	private void set_initial_dir_list_part2() {
		/*----------------------------
		 * Steps
		 * 1. Set path label to text view
		 * 2. Create a table
			----------------------------*/
		/*----------------------------
		 * 1. Set path label to text view
			----------------------------*/
		
		Methods.updatePathLabel(this);
		
		
		/*----------------------------
		 * 2. Create a table
		 * 
		 * 		=> Decided not to create a table at the initial set up process: 20120716_124208
			----------------------------*/
//		DBUtils dbu = new DBUtils(this, this.dbName);
//		
//		SQLiteDatabase db = dbu.getWritableDatabase();
//		
//		boolean result = dbu.createTable(db, this.baseDirName, 
////					dbu.get_cols_with_index(), dbu.get_col_types_with_index());
//				dbu.get_cols(), dbu.get_col_types());
//		
//		if (result == true) {
//			// Log
//			Log.d("ImageFileManager8Activity.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Table created: " + this.baseDirName);
//			
//		} else {//if (result == true)
//			// Log
//			Log.d("ImageFileManager8Activity.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Create table => Failed: " + this.baseDirName);
//		}//if (result == true)
//		
//		
//		db.close();
		
	}//private void set_initial_dir_list()

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		
		Methods.clearPreference_currentPath(this);
		
	}//protected void onDestroy()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.main_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.main_opt_menu_create_folder://----------------------------------
			
			Methods.dlg_createFolder(this);
			
			break;
		case R.id.main_opt_menu_refresh_db://---------------------------------------
			/*----------------------------
			 * Steps
			 * 1. Vibrate
			 * 2. Task
				----------------------------*/
			
			vib.vibrate(this.vibLength_click);
			
			/*----------------------------
			 * 2. Task
				----------------------------*/
			RefreshDBTask task_ = new RefreshDBTask(this);
			
			// debug
			Toast.makeText(ImageFileManager8Activity.this, "Starting a task...", 2000)
					.show();
			
			task_.execute("Start");
			
//			boolean result = Methods.refreshMainDB(this);
//			
//			if (result == true) {
//				
//				// debug
//				Toast.makeText(ImageFileManager8Activity.this, "DB refreshed",
//						2000).show();
//				
//			} else {//if (result == true)
//				
//				// debug
//				Toast.makeText(ImageFileManager8Activity.this, "Sorry. Refresh DB => Failed",
//						2000).show();
//				
//			}//if (result == true)
			
			
			
			break;
			
		case R.id.main_opt_menu_db_activity://-----------------------------------------------
			
			Methods.startDBAdminActivity(this);

			break;
			
		case R.id.main_opt_menu_search://-----------------------------------------------
			
			Methods.dlg_seratchItem(this);
			
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
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 0. Vibrate
		 * 1. Get item name
		 * 2. Get file object
		 * 3. Is a directory?
		 * 		=> If yes, update the current path
			----------------------------*/
		//
		vib.vibrate(vibLength_click);
		
		String itemName = (String) lv.getItemAtPosition(position);
		
//		// Log
//		Log.d("ImageFileManager8Activity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "itemName => " + itemName);
//		
//		// debug
//		Toast.makeText(ImageFileManager8Activity.this, itemName, 2000)
//				.show();
		
		/*----------------------------
		 * 2. Get file object
			----------------------------*/
		File target = new File(this.currentDirPath, itemName);
		
		// Log
		Log.d("ImageFileManager8Activity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "this.currentDirPath: " + this.currentDirPath);
		
		
		/*----------------------------
		 * 3. Is a directory?
			----------------------------*/
		if (!target.exists()) {
			// debug
			Toast.makeText(ImageFileManager8Activity.this, 
					
					"This item doesn't exist in the directory: " + itemName, 
					2000)
					.show();
			
			// Log
			Log.d("ImageFileManager8Activity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", 
					"This item doesn't exist in the directory: " + itemName);

			return;
		}//if (!target.exists())
		
		//
		if (target.isDirectory()) {
			
			Methods.enterDir(this, target);
			
		} else if (target.isFile()) {//if (target.isDirectory())
			
			Methods.startThumbnailActivity(this, target);
			
//			Methods.toastAndLog(this, "This is a file: " + itemName, 2000);
			
			
		}//if (target.isDirectory())
		
		
		super.onListItemClick(lv, v, position, id);
	}//protected void onListItemClick(ListView l, View v, int position, long id)

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			
			AlertDialog.Builder dialog=new AlertDialog.Builder(this);
			
	        dialog.setTitle("アプリの終了");
	        dialog.setMessage("アプリを終了しますか？");
	        
	        dialog.setPositiveButton("終了",new DialogListener(this, dialog, 0));
	        dialog.setNegativeButton("キャンセル",new DialogListener(this, dialog, 1));
	        
	        dialog.create();
	        dialog.show();
			
		}//if (keyCode==KeyEvent.KEYCODE_BACK)
		
		
		return super.onKeyDown(keyCode, event);
	}

	class DialogListener implements DialogInterface.OnClickListener {

		Activity actv;
//		Dialog dlg;
		Builder dialog;
		int type;
		
		public DialogListener(Activity actv, Builder dialog, int type) {
			
			this.actv = actv;
			this.dialog = dialog;
			this.type = type;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			/*----------------------------
			 * Steps
				----------------------------*/
			switch (type) {
			
			case 0:	// OK
				
				dialog.dismiss();
				actv.finish();
				
				break;
				
			case 1: // Cancel
				
				dialog.dismiss();
				
				break;
			
			}//switch (type)
			
		}//public void onClick(DialogInterface dialog, int which)
		
	}//class DialogListener implements DialogInterface.OnClickListener

}//public class ImageFileManager8Activity extends ListActivity

