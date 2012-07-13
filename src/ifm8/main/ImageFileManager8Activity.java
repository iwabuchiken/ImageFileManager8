package ifm8.main;

import ifm8.lib.Methods;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ImageFileManager8Activity extends ListActivity {
	
	public static String currentDirPath = null;
	public static String baseDirPath = null;
	public static String baseDirName = "IFM8";
	public static String listFileName = "list.txt";
	
	public static List<String> file_names = null;

	public static ArrayAdapter<String> adapter = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v1);
        
        set_initial_dir_list();
    }

	private void set_initial_dir_list() {
		/*----------------------------
		 * Steps
		 * 1. Create root dir
		 * 1-2. Create "list.txt"
		 * 2. Set variables => currentDirPath, baseDirPath
		 * 3. Get file list
		 * 4. Set list to adapter
		 * 5. Set adapter to list view
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
			----------------------------*/
		File[] files = file.listFiles();
		
//		File[] files = dir.listFiles();
		
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
		
		
		
		
	}//private void set_initial_dir_list()

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

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
		case R.id.main_opt_menu_create_folder:
			
			Methods.dlg_createFolder(this);
			
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
}