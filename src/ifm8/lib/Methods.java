package ifm8.lib;

import ifm8.dbadmin.DBAdminActivity;
import ifm8.main.*;
import thumb_activity.main.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang.StringUtils;

public class Methods {

	static int tempRecordNum = 20;
	
	public static enum DialogTags {
		// Generics
		dlg_generic_dismiss, dlg_generic_dismiss_second_dialog,
		
		
		// dlg_create_folder.xml
		dlg_create_folder_ok, dlg_create_folder_cancel,

		// dlg_input_empty.xml
		dlg_input_empty_reenter, dlg_input_empty_cancel,
		
		// dlg_confirm_create_folder.xml
		dlg_confirm_create_folder_ok, dlg_confirm_create_folder_cancel,

		// dlg_confirm_remove_folder.xml
		dlg_confirm_remove_folder_ok, dlg_confirm_remove_folder_cancel,

		// dlg_drop_table.xml
		dlg_drop_table_btn_cancel, dlg_drop_table,
		
		// dlg_confirm_drop.xml
		dlg_confirm_drop_table_btn_ok, dlg_confirm_drop_table_btn_cancel,
		
		// dlg_add_memos.xml
		dlg_add_memos_bt_add, dlg_add_memos_bt_cancel,

		// dlg_move_files.xml
		dlg_move_files_move, dlg_move_files,
		
		// dlg_confirm_move_files.xml	=> ok, cancel, dlg tag
		dlg_confirm_move_files_ok, dlg_confirm_move_files_cancel, dlg_confirm_move_files,

		// dlg_item_menu.xml
		dlg_item_menu_bt_cancel, dlg_item_menu,

		
	}//public static enum DialogTags
	
	public static enum ButtonTags {
		// ImageFileManager8Activity.java
		ib_up,
		
		// DBAdminActivity.java
		db_manager_activity_create_table, db_manager_activity_drop_table,
		
		// thumb_activity.xml
		thumb_activity_ib_back,
		
		// image_activity.xml
		image_activity_back,
		
		// TIListAdapter.java
		tilist_cb,
		
	}//public static enum ButtonTags
	
	public static enum ItemTags {
		
		// ImageFileManager8Activity.java
		dir_list,
		
		// ThumbnailActivity.java
		dir_list_thumb_actv,
	}

	public static enum MoveMode {
		// TIListAdapter.java
		ON, OFF,
		
	}//public static enum MoveMode
	//
	public static final int vibLength_click = 35;
	
	public static void dlg_createFolder(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_create_folder);
		
		// Title
		dlg.setTitle(R.string.dlg_create_folder_title);
		
		//
		
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_create_folder_bt_ok);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_create_folder_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_create_folder_ok);
		btn_cancel.setTag(DialogTags.dlg_create_folder_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		//
		dlg.show();
		
	}//public static void dlg_createFolder(Activity actv)

	public static void dlg_isEmpty(Activity actv, Dialog dlg) {
		/*----------------------------
		 * 1. Check if the input exists. If not, show a dialog
		 * 2. If yes, go to Methods.createFolder()
			----------------------------*/
		/*----------------------------
		 * 1. Check if the input exists. If not, show a dialog
			----------------------------*/
		//
		EditText et = (EditText) dlg.findViewById(R.id.dlg_create_folder_et);
		String folderName = et.getText().toString();
		
		//
		if (!folderName.equals("")) {
			/*----------------------------
			 * 2. If yes, go to Methods.createFolder()
				----------------------------*/
//			dlg.dismiss();
			
//			createFolder(actv, folderName);
			dlg_confirm_createFolder(actv, dlg);
			
			return;
			
		}//if (!folderName.equals(""))
		
		/*----------------------------
		 * If not, show a dialog
			----------------------------*/
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_input_empty);
		
		// Title
		dlg2.setTitle(R.string.dlg_input_empty_title);
		
		//
		
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(R.id.dlg_input_empty_btn_reenter);
		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_input_empty_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_input_empty_reenter);
		btn_cancel.setTag(DialogTags.dlg_input_empty_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		//
		dlg2.show();
		
	}//public static void dlg_isEmpty(Activity actv, Dialog dlg)

	private static void dlg_confirm_createFolder(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Set folder name to text view
			----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_confirm_create_folder);
		
		// Title
		dlg2.setTitle(R.string.generic_tv_confirm);
		
		/*----------------------------
		 * 2. Set folder name to text view
			----------------------------*/
		EditText et = (EditText) dlg.findViewById(R.id.dlg_create_folder_et);
		
		TextView tv = (TextView) dlg2.findViewById(R.id.dlg_confirm_create_folder_tv_table_name);
		
		tv.setText(et.getText().toString());
		
		/*----------------------------
		 * 3. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(R.id.dlg_confirm_create_folder_btn_ok);
		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_confirm_create_folder_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_confirm_create_folder_ok);
		btn_cancel.setTag(DialogTags.dlg_confirm_create_folder_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		 * 4. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		/*----------------------------
		 * 5. Show dialog
			----------------------------*/
		dlg2.show();
		
	}//private static void dlg_confirm_createFolder

	public static void createFolder(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get folder name
		 * 2. Get current directory path
		 * 3. Create a file object
		 * 4. Create a file => Use BufferedWriter
		 * 5. If successful, dismiss dialog. Otherwise, toast a message
			----------------------------*/
		
		
		
	}//public static void createFolder(Activity actv, Dialog dlg)

	public static void createFolder(Activity actv, Dialog dlg, Dialog dlg2) {
		/*----------------------------
		 * Steps
		 * 1. Get folder name from dlg2
		 * 1-2. Dismiss dlg2
		 * 2. Get current directory path
		 * 3. Create a file object
		 * 4. Create a dir
		 * 5. If successful, dismiss dialog. Otherwise, toast a message
		 * 6. Create a "list.txt"
		 * 7. Refresh list view 
		 * 
		 * 8. Create a new table
			----------------------------*/
		//
		TextView tv_folderName = (TextView) dlg2.findViewById(R.id.dlg_confirm_create_folder_tv_table_name);
		String folderName = tv_folderName.getText().toString();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Folder name => " + tv_folderName.getText().toString());
		
		/*----------------------------
		 * 1-2. Dismiss dlg2
			----------------------------*/
		dlg2.dismiss();
		
		/*----------------------------
		 * 2. Get current directory path
		 * 3. Create a file object
			----------------------------*/
		File newDir = new File(ImageFileManager8Activity.currentDirPath, folderName);
		
		/*----------------------------
		 * 4. Create a file => Use BufferedWriter
			----------------------------*/
		//
		if (newDir.exists()) {
			// debug
			Toast.makeText(actv, "この名前のフォルダはすでにあります！： " + folderName, 3000).show();
			
		} else {//if (newDir.exists())
			//
			try {
				newDir.mkdir();
				
				/*----------------------------
				 * 5. If successful, dismiss dialog. 
					----------------------------*/
				dlg.dismiss();
				
				// debug
				Toast.makeText(actv, "フォルダを作りました : " + newDir.getAbsolutePath(), 3000).show();
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Folder created => " + newDir.getAbsolutePath());
				
				
			} catch (Exception e) {
				// debug
				Toast.makeText(actv, "フォルダを作れませんでした : " + newDir.getName(), 3000).show();
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "newDir.getName() => " + newDir.getName());
				
			}//try
			
		}//if (newDir.exists())
		
		/*----------------------------
		 * 6. Create a "list.txt"
			----------------------------*/
		File listFile = new File(newDir, ImageFileManager8Activity.listFileName);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "listFile => " + listFile.getAbsolutePath());
		
		if (listFile.exists()) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "listFile => Exists");
			
			// debug
			Toast.makeText(actv, "list.txt => すでにあります", 3000).show();
			
		} else {//if (listFile.exists())
			try {
				BufferedWriter br = new BufferedWriter(new FileWriter(listFile));
				
				br.close();
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "listFile => Created");
				
				// debug
				Toast.makeText(actv, "list.txt => 作成されました", 3000).show();
				
			} catch (IOException e) {
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create listFile => Failed: " + listFile.getAbsolutePath());
				// debug
				Toast.makeText(actv, "list.txt => 作成できませんでした", 3000).show();
			}
		}//if (listFile.exists())
		
		/*----------------------------
		 * 7. Refresh list view
			----------------------------*/
		refreshListView(actv);
		
		/*----------------------------
		 * 8. Create a new table
		 * 		8.1. Build a table name
		 * 		8.2. Create a table
			----------------------------*/
//		String[] currentPathArray = ImageFileManager8Activity.currentDirPath.split(newDir.separator);
		String[] currentPathArray = getCurrentPathLabel(actv).split(newDir.separator);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "currentPathArray.length => " + currentPathArray.length);
		
		String tableName = null;
		StringBuilder sb = new StringBuilder();
		
		if (currentPathArray.length > 1) {
			
			tableName = StringUtils.join(currentPathArray, "__");
			
			sb.append(tableName);
			sb.append("__");
			
		} else {//if (currentPathArray.length > 1)
			
			sb.append(currentPathArray[0]);
			sb.append("__");
			
		}//if (currentPathArray.length > 1)
		
//			tableName = StringUtils.join(currentPathArray, "__");
		
		
		
//		sb.append(tableName);
//		sb.append("__");
		sb.append(folderName);
		
		tableName = sb.toString();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tableName => " + tableName);
		
		/*----------------------------
		 * 8.2. Create a table
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase db = dbu.getWritableDatabase();
		
		dbu.createTable(db, tableName, 
//					dbu.get_cols_with_index(), dbu.get_col_types_with_index());
				dbu.get_cols(), dbu.get_col_types());
		
		db.close();
		
	}//public static void createFolder(Activity actv, Dialog dlg, Dialog dlg2)

	/****************************************
	 * 		refreshListView(Activity actv)
	 * 
	 * <Caller> 
	 * 1. Methods.createFolder(Activity actv, Dialog dlg, Dialog dlg2)
	 * 
	 * <Desc> 
	 * 1. Refresh the dir list in ImageFileManager8Activity.java
	 * 
	 * 
	 * <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static void refreshListView(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Get file list
		 * 1-2. Sort list
		 * 2. Clear => ImageFileManager8Activity.file_names
		 * 3. Add file names to => ImageFileManager8Activity.file_names
		 * 4. Notify adapter of changes
		 * 
		 * 5. Update image buttons
			----------------------------*/
		
		// 
		File currentDir = new File(ImageFileManager8Activity.currentDirPath);
		
		File[] files = currentDir.listFiles();
		
		/*----------------------------
		 * 1-2. Sort list
			----------------------------*/
		sortFileList(files);
		
//		File[] files = dir.listFiles();
		
//		List<String> file_names = new ArrayList<String>();
		ImageFileManager8Activity.file_names.clear();
		
		if (ImageFileManager8Activity.file_names == null) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "ImageFileManager8Activity.file_names => null");
			
			// debug
			Toast.makeText(actv, "ImageFileManager8Activity.file_names => null", 3000).show();
			
			return;
			
		}//if (ImageFileManager8Activity.file_names == null)
		
		/*----------------------------
		 * 3. Add file names to => ImageFileManager8Activity.file_names
			----------------------------*/
		for (File item : files) {
			ImageFileManager8Activity.file_names.add(item.getName());
		}
		
		/*----------------------------
		 * 4. Notify adapter of changes
			----------------------------*/
		if (ImageFileManager8Activity.adapter != null) {
			
			ImageFileManager8Activity.adapter.notifyDataSetChanged();
			
		} else {//if (condition)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "ImageFileManager8Activity.adapter => null");
			
			// debug
			Toast.makeText(actv, "ImageFileManager8Activity.adapter => null", 3000).show();

		}//if (condition)
		
		/*----------------------------
		 * 5. Update image buttons
			----------------------------*/
//		if (ImageFileManager8Activity.currentDirPath == ImageFileManager8Activity.baseDirPath) {
		if (ImageFileManager8Activity.currentDirPath.equals(ImageFileManager8Activity.baseDirPath)) {
			
//			//debug
//			toastAndLog(actv, 
//					"ImageFileManager8Activity.currentDirPath.equals(ImageFileManager8Activity.baseDirPath)", 
//					3000 );
			
			//
			ImageButton ib = (ImageButton) actv.findViewById(R.id.v1_bt_up);
			
			ib.setImageResource(R.drawable.ifm8_up_disenabled);
			ib.setEnabled(false);
			
		} else {//if (ImageFileManager8Activity.currentDirPath.equals(ImageFileManager8Activity.baseDirPath))
//			//debug
//			toastAndLog(actv, 
//					"!ImageFileManager8Activity.currentDirPath.equals(ImageFileManager8Activity.baseDirPath)", 
//					3000 );
		}//if (ImageFileManager8Activity.currentDirPath.equals(ImageFileManager8Activity.baseDirPath))
		
	}//private static void refreshListView()

	/*----------------------------
	 * makeComparator(Comparator comparator)
	 * 
	 * REF=> C:\WORKS\WORKSPACES_ANDROID\Sample\09_Matsuoka\プロジェクト
	 * 					\Step10\10_LiveWallpaper\src\sample\step10\livewallpaper\FilePicker.java
		----------------------------*/
	public static void makeComparator(Comparator comparator){
		
		comparator=new Comparator<Object>(){
			
			public int compare(Object object1, Object object2) {
			
				int pad1=0;
				int pad2=0;
				
				File file1=(File)object1;
				File file2=(File)object2;
				
				if(file1.isDirectory())pad1=-65536;
				if(file2.isDirectory())pad2=-65536;
				
				return pad1-pad2+file1.getName().compareToIgnoreCase(file2.getName());
			}
		};
	}

	public static void sortFileList(File[] files) {
		// REF=> http://android-coding.blogspot.jp/2011/10/sort-file-list-in-order-by-implementing.html
		Comparator<? super File> filecomparator = new Comparator<File>(){
			
			public int compare(File file1, File file2) {
				int pad1=0;
				int pad2=0;
				
				if(file1.isDirectory())pad1=-65536;
				if(file2.isDirectory())pad2=-65536;
				
				// Order => folders, files
//				return pad1-pad2+file1.getName().compareToIgnoreCase(file2.getName());
				
				// Order => files, folders
				return pad2-pad1+file1.getName().compareToIgnoreCase(file2.getName());
				
//				return String.valueOf(file1.getName()).compareTo(file2.getName());
			} 
		 };//Comparator<? super File> filecomparator = new Comparator<File>()
		 
		 //
		Arrays.sort(files, filecomparator);

	}//public static void sortFileList(File[] files)

	public static void dlg_removeFolder(Activity actv, String folderName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Set folder name to text view
			----------------------------*/
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_confirm_remove_folder);
		
		// Title
		dlg.setTitle(R.string.generic_tv_confirm);
		
		/*----------------------------
		 * 2. Set folder name to text view
			----------------------------*/
		TextView tv = (TextView) dlg.findViewById(R.id.dlg_confirm_remove_folder_tv_table_name);
		
		tv.setText(folderName);
		
		/*----------------------------
		 * 3. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_confirm_remove_folder_btn_ok);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_confirm_remove_folder_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_confirm_remove_folder_ok);
		btn_cancel.setTag(DialogTags.dlg_confirm_remove_folder_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 4. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		/*----------------------------
		 * 5. Show dialog
			----------------------------*/
		dlg.show();

		
	}//public static void dlg_removeFolder(Activity actv)

	public static void removeFolder(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get folder name
		 * 2. Validate
		 * 3. Remove
		 * 4. Refresh list
		 * 5. Dismiss dialog
		 * 
		 * 6. Drop table
			----------------------------*/
		
		//
		TextView tv = (TextView) dlg.findViewById(R.id.dlg_confirm_remove_folder_tv_table_name);
		String folderName = tv.getText().toString();
		
		//
		File targetDir = new File(ImageFileManager8Activity.currentDirPath, folderName);
		
		if (!targetDir.exists()) {
			// debug
			Toast.makeText(actv, "存在しません", 2000).show();
			
			return;
		}
		
		if (!targetDir.isDirectory()) {
			// debug
			Toast.makeText(actv, "フォルダではありません", 2000).show();
			
			return;
		}//if (!targetDir.exists() || !targetDir.isDirectory())
		
		/*----------------------------
		 * 3. Remove
			----------------------------*/
		String path = targetDir.getAbsolutePath();
		
		boolean result = deleteDirectory(targetDir);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "result => " + result);

		if (result == true) {
			/*----------------------------
			 * 5. Dismiss dialog
				----------------------------*/
			dlg.dismiss();
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Dir => Removed: " + path);
			
			// debug
			Toast.makeText(actv, "削除しました" + path, 3000).show();
		} else {//if (result == true)
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Remove dir => Failed: " + path);
			
			// debug
			Toast.makeText(actv, "削除できませんでした: " + path, 3000).show();
		}//if (result == true)
		
		
		
//		try {
//			targetDir.delete();
//			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Dir => Removed: " + path);
//			
//			// debug
//			Toast.makeText(actv, "削除しました" + path, 3000).show();
//			
//			/*----------------------------
//			 * 5. Dismiss dialog
//				----------------------------*/
//			dlg.dismiss();
//			
//		} catch (Exception e) {
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Remove dir => Failed: " + path);
//			
//			// debug
//			Toast.makeText(actv, "削除できませんでした: " + path, 3000).show();
//			
//		}//try
		
		/*----------------------------
		 * 4. Refresh list
			----------------------------*/
		refreshListView(actv);
		
		/*----------------------------
		 * 6. Drop table
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase db = dbu.getWritableDatabase();
		
		String tableName = Methods.convertPathIntoTableName(actv, targetDir);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tableName => " + tableName);
		
		dbu.dropTable(actv, db, tableName);

		db.close();
		
		return;
		
	}//public static void removeFolder(Activity actv, Dialog dlg)

	/****************************************
	 * convertPathIntoTableName(Activity actv, File targetDir)
	 * 
	 * <Caller> 1. 
	 * 
	 * <Desc> 
	 * 1. Used when creating a new folder in IFM8Activity option menu
	 * 
	 * <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	private static String convertPathIntoTableName(Activity actv, File targetDir) {
		/*----------------------------
		 * Steps
		 * 1. Get table name => Up to the current path
		 * 2. Add name => Target folder name
			----------------------------*/
		
		String[] currentPathArray = getCurrentPathLabel(actv).split(new File("aaa").separator);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "currentPathArray.length => " + currentPathArray.length);
		
		String tableName = null;
		StringBuilder sb = new StringBuilder();
		
		if (currentPathArray.length > 1) {
			
			tableName = StringUtils.join(currentPathArray, "__");
			
			sb.append(tableName);
			sb.append("__");
			
		} else {//if (currentPathArray.length > 1)
			
			sb.append(currentPathArray[0]);
			sb.append("__");
			
		}//if (currentPathArray.length > 1)
		
//			tableName = StringUtils.join(currentPathArray, "__");
		
		/*----------------------------
		 * 2. Add name => Target folder name
			----------------------------*/
		String targetDirPath = targetDir.getAbsolutePath();
		
		String[] a_targetDirPath = targetDirPath.split(new File("aaa").separator);
		
		String folderName = a_targetDirPath[a_targetDirPath.length - 1];
		
//		sb.append(tableName);
//		sb.append("__");
		sb.append(folderName);
		
		tableName = sb.toString();

		
		return tableName;
	}//private static String convertPathIntoTableName(String absolutePath)

	public static String convertPathIntoTableName(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Get table name => Up to the current path
		 * 2. Add name => Target folder name
			----------------------------*/
		String tableName = null;
		StringBuilder sb = new StringBuilder();

		if(getCurrentPathLabel(actv).equals(ImageFileManager8Activity.baseDirName)) {
			tableName = getCurrentPathLabel(actv);
		} else {
			String[] currentPathArray = getCurrentPathLabel(actv).split(new File("aaa").separator);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "getCurrentPathLabel(actv) => " + getCurrentPathLabel(actv));
			
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "currentPathArray.length => " + currentPathArray.length);
			
			if (currentPathArray.length > 1) {
				
				tableName = StringUtils.join(currentPathArray, "__");
				
//				sb.append(tableName);
//				sb.append("__");
				
			} else {//if (currentPathArray.length > 1)
				
				sb.append(currentPathArray[0]);
//				sb.append("__");
				
			}//if (currentPathArray.length > 1)
			
	//			tableName = StringUtils.join(currentPathArray, "__");
			
			/*----------------------------
			 * 2. Add name => Target folder name
				----------------------------*/
//			String targetDirPath = ImageFileManager8Activity.currentDirPath;
//			
//			String[] a_targetDirPath = targetDirPath.split(new File("aaa").separator);
//			
//			String folderName = a_targetDirPath[a_targetDirPath.length - 1];
//			
//	//		sb.append(tableName);
//	//		sb.append("__");
//			sb.append(folderName);
//			
//			tableName = sb.toString();

		}//if(getCurrentPathLabel(actv).equals(ImageFileManager8Activity.baseDirName))
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tableName => " + tableName);
		
		
		return tableName;
	}//private static String convertPathIntoTableName(String absolutePath)

	/****************************************
	 * convertPathIntoTableName(Activity actv, String pathLabel)
	 * 
	 * <Caller> 
	 * 1. moveFiles(Activity actv, Dialog dlg1, Dialog dlg2)
	 * 		=> From folder path, create a table name, to which to move files
	 * 
	 * <Desc>
	 *  1. 
	 *  
	 *  <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static String convertPathIntoTableName(Activity actv, String pathLabel) {
		/*----------------------------
		 * Steps
		 * 1. Get path array from path label
		 * 2. Format the path array with table name format
		 * 3. Add the base dir name to the array
		 * 4. Return sb
			----------------------------*/
		String tableName = null;
		StringBuilder sb = new StringBuilder();

//		if(getCurrentPathLabel(actv).equals(ImageFileManager8Activity.baseDirName)) {
//			tableName = getCurrentPathLabel(actv);
//		} else {
//			String[] currentPathArray = getCurrentPathLabel(actv).split(new File("aaa").separator);
		String[] pathArray = pathLabel.split(new File("aaa").separator);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "pathArray.length => " + pathArray.length);
			
			if (pathArray.length > 1) {
			
				/*----------------------------
				 * 2. Format the path array with table name format
					----------------------------*/
				tableName = StringUtils.join(pathArray, ImageFileManager8Activity.tableNameSeparator);
			
				/*----------------------------
				 * 3. Add the base dir name to the array
					----------------------------*/
				sb.append(ImageFileManager8Activity.baseDirName);
				sb.append(ImageFileManager8Activity.tableNameSeparator);
				
				sb.append(tableName);
				
			} else {//if (pathArray.length > 1)
				
				/*----------------------------
				 * 3. Add the base dir name to the array
					----------------------------*/
				sb.append(ImageFileManager8Activity.baseDirName);
				sb.append(ImageFileManager8Activity.tableNameSeparator);
				
				sb.append(pathArray[0]);
//				sb.append("__");
				
			}//if (pathArray.length > 1)
			
	//			tableName = StringUtils.join(currentPathArray, "__");
			
			/*----------------------------
			 * 2. Add name => Target folder name
				----------------------------*/
//			String targetDirPath = ImageFileManager8Activity.currentDirPath;
//			
//			String[] a_targetDirPath = targetDirPath.split(new File("aaa").separator);
//			
//			String folderName = a_targetDirPath[a_targetDirPath.length - 1];
//			
//	//		sb.append(tableName);
//	//		sb.append("__");
//			sb.append(folderName);
//			
//			tableName = sb.toString();

//		}//if(getCurrentPathLabel(actv).equals(ImageFileManager8Activity.baseDirName))
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tableName => " + tableName);
		
		/*----------------------------
		 * 4. Return sb
			----------------------------*/
		return sb.toString();
		
	}//private static String convertPathIntoTableName(String absolutePath)

	/*----------------------------
	 * deleteDirectory(File target)()
	 * 
	 * 1. REF=> http://www.rgagnon.com/javadetails/java-0483.html
		----------------------------*/
	public static boolean deleteDirectory(File target) {
		
		if(target.exists()) {
			//
			File[] files = target.listFiles();
			
			//
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					
					deleteDirectory(files[i]);
					
				} else {//if (files[i].isDirectory())
					
					String path = files[i].getAbsolutePath();
					
					files[i].delete();
					
					// Log
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "Removed => " + path);
					
					
				}//if (files[i].isDirectory())
				
			}//for (int i = 0; i < files.length; i++)
			
		}//if(target.exists())
		
		return (target.delete());
	}

	public static void toastAndLog(Activity actv, String message, int duration) {
		// 
		// debug
		Toast.makeText(actv, message, duration)
				.show();
		
		// Log
		Log.d("ImageFileManager8Activity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
				message);
		
	}//public static void toastAndLog(Activity actv, String message)

	public static void toastAndLog(Activity actv, String fileName, String message, int duration) {
		// 
		// debug
		Toast.makeText(actv, message, duration)
				.show();
		
		// Log
		Log.d(fileName + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
				message);
		
	}//public static void toastAndLog(Activity actv, String fileName, String message, int duration)

	public static void recordLog(Activity actv, String fileName, String message) {
		// Log
		Log.d(fileName + 
				"[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
				message);
		
	}//public static void toastAndLog(Activity actv, String message)

	public static void enterDir(Activity actv, File newDir) {
		/*----------------------------
		 * Steps
		 * 1. Update the current path
		 * 2. Refresh list view
		 * 3. "Up" button => Enable
		 * 
		 * 4. Update path view
			----------------------------*/
		ImageFileManager8Activity.currentDirPath = newDir.getAbsolutePath();
		
		refreshListView(actv);
		
		/*----------------------------
		 * 3. "Up" button => Enable
			----------------------------*/
		ImageButton ib = (ImageButton) actv.findViewById(R.id.v1_bt_up);
		
		if (!ib.isEnabled()) {
			
			ib.setEnabled(true);
			
			ib.setImageResource(R.drawable.ifm8_up);
			
			
		}//if (!ib.isEnabled())
		
		/*----------------------------
		 * 4. Update path view
			----------------------------*/
		updatePathLabel(actv);
		
		
	}//public static void enterDir(Activity actv, File newDir)

	public static String getCurrentPathLabel(Activity actv) {
		// 
		String[] pathArray = ImageFileManager8Activity.currentDirPath.split(new File("abc").separator);		
		String currentBaseDirName = pathArray[pathArray.length - 1];
		
		
//		ArrayList<String> newPath = new ArrayList<String>();
		
		int location = -1;
		
		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(ImageFileManager8Activity.baseDirName)) {
				location = i;
				break;
			}//if (pathArray[i].equals(ImageFileManager8Activity.baseDirName))
		}//for (int i = 0; i < pathArray.length; i++)
		
		//REF=> http://stackoverflow.com/questions/4439595/how-to-create-a-sub-array-from-another-array-in-java
//		String[] newPath = Arrays.copyOfRange(pathArray, location, pathArray.length - 1);
		String[] newPath = Arrays.copyOfRange(pathArray, location, pathArray.length);
		
		String s_newPath = StringUtils.join(newPath, new File("abc").separator);
		
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "s_newPath => " + s_newPath);
		
		return s_newPath;
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "location => " + location);
//		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", 
//				"ImageFileManager8Activity.currentDirPath => " + 
//				ImageFileManager8Activity.currentDirPath);
//		
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "pathArray.length => " + pathArray.length);
		
		
		
//		return null;
		
//		String[] tempArray = ImageFileManager8Activity.baseDirPath.split(new File("abc").separator);
//		String projectBaseDirName = tempArray[tempArray.length - 1];
		
		
		
//
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "pathArray[pathArray.length] => " + pathArray[pathArray.length - 1]);
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "pathSeparator => " + new File("abc").pathSeparator);
//		
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Separator => " + new File("abc").separator);
		
//		return null;
	}//public static String getCurrentPathLabel(Activity actv)

	public static void upDirectory(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Vibrate
		 * 2. Is the current path "roof"?
		 * 3. Go up the path
		 * 3-2. New path => Equal to base dir path?
		 * 4. Refresh list
		 * 5. Update path view
			----------------------------*/
		ImageFileManager8Activity.vib.vibrate(ImageFileManager8Activity.vibLength_click);
		
		/*----------------------------
		 * 2. Is the current path "roof"?
			----------------------------*/
		if (ImageFileManager8Activity.currentDirPath == ImageFileManager8Activity.baseDirPath) {
			
			Methods.toastAndLog(actv, "No more upward directory", 3000);
		
			return;
		}//if (ImageFileManager8Activity.currentDirPath == ImageFileManager8Activity.baseDirPath)
		
		/*----------------------------
		 * 3. Go up the path
			----------------------------*/
//		ImageFileManager8Activity.currentDirPath = 
//						(new File(ImageFileManager8Activity.currentDirPath))
		
		File f = new File(ImageFileManager8Activity.currentDirPath);
		
		ImageFileManager8Activity.currentDirPath = f.getParent();
		
//		Methods.toastAndLog(actv, "f.getParent() => " + f.getParent(), 3000);
		
		/*----------------------------
		 * 3-2. New path => Equal to base dir path?
			----------------------------*/
		
		
		/*----------------------------
		 * 4. Refresh list
			----------------------------*/
		Methods.refreshListView(actv);
		
		/*----------------------------
		 * 5. Update path view
			----------------------------*/
		updatePathLabel(actv);
		
	}//public static void upDirectory(Activity actv)

	public static void updatePathLabel(Activity actv) {
		// 
		TextView tv = (TextView) actv.findViewById(R.id.v1_tv_dir_path);
		
		tv.setText(getCurrentPathLabel(actv));
		
	}//public static void updatePathLabel(Activity actv)

	public static void startThumbnailActivity(Activity actv, File target) {
		/*----------------------------
		 * Steps
		 * 1. "list.txt"?
		 * 2. If yes, start activity
			----------------------------*/
		if (!target.getName().equals(ImageFileManager8Activity.listFileName)) {
			
			toastAndLog(actv, "Not a \"list.txt\"", 2000);
			
			return;
		}//if (!target.getName().equals(ImageFileManager8Activity.listFileName))
		
		/*----------------------------
		 * 2. If yes, start activity
			----------------------------*/
		Intent i = new Intent();
		
		i.setClass(actv, ThumbnailActivity.class);
		
		actv.startActivity(i);
		
	}//public static void startThumbnailActivity(Activity actv, File target)

	/****************************************
	 * method_name(param_type)
	 * 
	 * <Caller> 
	 * 1. ImageFileManager8Activity : onOptionsItemSelected(MenuItem item)
	 * 
	 * <Desc> 
	 * 1. REF=> C:\WORKS\WORKSPACES_ANDROID\Sample\05_Techbooster\AsyncTask\src\org\jpn\techbooster\sample\asynctask\AsyncTaskActivity.java
	 * 
	 * <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static boolean refreshMainDB(ListActivity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up DB(writable)
		 * 2. Table exists?
		 * 2-1. If no, then create one

		 * 3. Execute query for image files

		 * 4. Insert data into db
		 *
		 * 5. Update table "refresh_log"
		 * 
		 * 9. Close db
		 * 
		 * 10. Return
			----------------------------*/
		//
		boolean blResult;
		/*----------------------------
		 * 1. Set up DB(writable)
			----------------------------*/
		//
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		//
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		/*----------------------------
		 * 2. Table exists?
		 * 2-1. If no, then create one
		 * 		1. baseDirName
		 * 		2. backupTableName
			----------------------------*/
		//
//		String tableName = convertPathIntoTableName(actv);
		String tableName = ImageFileManager8Activity.baseDirName;
		String backupTableName = ImageFileManager8Activity.backupTableName;
		
		
		/*----------------------------
		 * 2-1.1. baseDirName
			----------------------------*/
		boolean result = dbu.tableExists(wdb, tableName);
		
		// If the table doesn't exist, create one
//		if (!dbu.tableExists(wdb, tableName)) {
		if (result == false) {
			
//			toastAndLog(actv, "Table doesn't exist: " + tableName, 3000);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);
			
			
			boolean blResult2 = 
					dbu.createTable(wdb, tableName, DBUtils.cols, DBUtils.col_types);
			
			if (blResult2 == false) {
//				toastAndLog(actv, "Can't create a table: "+ tableName, 3000);
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Can't create a table: "+ tableName);
				
				
				wdb.close();
				
				return false;
				
			} else {//if (blResult2 == false)
				
//				toastAndLog(actv, "Table created: "+ tableName, 3000);
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Table created: "+ tableName);
				
				
			}//if (blResult2 == false)
			
//			wdb.close();
			
//			return false;
//		} else {//if (!dbu.tableExists(wdb, tableName))
		} else {//if (result == false)
			
//			toastAndLog(actv, "Table exists: "+ tableName, 3000);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: "+ tableName);
			
			
//		}//if (!dbu.tableExists(wdb, tableName))
		}//if (result == false)
		
		/*----------------------------
		 * 2-1.2. backupTableName
			----------------------------*/
		result = dbu.tableExists(wdb, backupTableName);
		
		// If the table doesn't exist, create one
//		if (!dbu.tableExists(wdb, backupTableName)) {
		if (result == false) {
			
//			toastAndLog(actv, "Table doesn't exist: " + backupTableName, 3000);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + backupTableName);
			
			
			boolean blResult2 = 
					dbu.createTable(wdb, backupTableName, DBUtils.cols, DBUtils.col_types);
			
			if (blResult2 == false) {
//				toastAndLog(actv, "Can't create a table: "+ backupTableName, 3000);
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "message");
				
				
				wdb.close();
				
				return false;
				
			} else {//if (blResult2 == false)
				
//				toastAndLog(actv, "Table created: "+ backupTableName, 3000);
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Table created: "+ backupTableName);
				
				
			}//if (blResult2 == false)
			
//			wdb.close();
			
//			return false;
//		} else {//if (!dbu.tableExists(wdb, backupTableName))
		} else {//if (result == false)
			
//			toastAndLog(actv, "Table exists: "+ backupTableName, 3000);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: "+ backupTableName);
			
			
//		}//if (!dbu.tableExists(wdb, tableName))
		}//if (result == false)
		
		
		
		//
		/*----------------------------
		 * 3. Execute query for image files
		 * 		1. ContentResolver
		 * 		2. Uri
		 * 		3. proj
		 * 		4. Last refreshed date
		 * 		5. Execute query
			----------------------------*/
		/*----------------------------
		 * 3.1. ContentResolver
		 * 3.2. Uri
		 * 3.3. proj
			----------------------------*/
		
		// ContentResolver
		ContentResolver cr = actv.getContentResolver();
		
		// Uri
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        
		// Projections
		String[] proj = DBUtils.proj;

		/*----------------------------
		 * 3.4. Last refreshed date
			----------------------------*/
//		String lastRefreshedDate;
		long lastRefreshedDate = 0;

//		result = dbu.tableExists(wdb, "regresh_log");
		result = dbu.tableExists(wdb, ImageFileManager8Activity.refreshLogTableName);
		
		if (result != false) {
			//		String sql = "SELECT * FROM refresh_log ORDER BY id DESC";
			
			// REF=> http://www.accessclub.jp/sql/10.html
			String sql = "SELECT * FROM refresh_log ORDER BY " + android.provider.BaseColumns._ID + " DESC";
			
			Cursor tempC = wdb.rawQuery(sql, null);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "tempC.getCount() => " + tempC.getCount());
	
			if (tempC.getCount() > 0) {
				
				tempC.moveToFirst();
				
				lastRefreshedDate = tempC.getLong(1);
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", 
						"lastRefreshedDate => " + String.valueOf(lastRefreshedDate));
				
				
//				for (int i = 0; i < tempC.getCount(); i++) {
//					
//					// Log
//					Log.d("Methods.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", "tempC.getLong(1) => " + tempC.getLong(1));
//					
//					tempC.moveToNext();
//					
//				}//for (int i = 0; i < tempC.getCount(); i++)
				
			}//if (tempC.getCount() > 0)
		} else {//if (result != false)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "result => false");
			
		}//if (result != false)
		
		/*----------------------------
		 * 3.5. Execute query
			----------------------------*/
        // Query
//        Cursor c = actv.managedQuery(uri, proj, null, null, null);
		
		
		// REF=> http://blog.csdn.net/uoyevoli/article/details/4970860
		Cursor c = actv.managedQuery(
											uri, 
											proj,
											MediaStore.Images.Media.DATE_ADDED + " > ?",
//											new String[] {String.valueOf(getMillSeconds(2012, 6, 15) / 1000)},
//											new String[] {String.valueOf(lastRefreshedDate / 1000)},
											new String[] {String.valueOf(lastRefreshedDate)},
											
											null);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "String.valueOf(lastRefreshedDate / 1000) => " + String.valueOf(lastRefreshedDate / 1000));
		
		
        //
        actv.startManagingCursor(c);
        
        // Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());
		
		if (c.getCount() > 0) {
			/*----------------------------
			 * 4. Insert data into db
				----------------------------*/
	//		int numOfItemsAdded = insertDataIntoDB(wdb, dbu, c, tableName);
			int numOfItemsAdded = insertDataIntoDB(wdb, dbu, c, tableName, backupTableName);
			
			/*----------------------------
			 * 5. Update table "refresh_log"
				----------------------------*/
	//		updateRefreshLog(wdb);
			c.moveToPrevious();
			
			long lastItemDate = c.getLong(3);
			
			updateRefreshLog(actv, wdb, dbu, lastItemDate, numOfItemsAdded);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getLong(3) => " + c.getLong(3));
		
		}//if (c.getCount() > 0)
		
		/*----------------------------
		 * 9. Close db
			----------------------------*/
		wdb.close();
		
		/*----------------------------
		 * 10. Return
			----------------------------*/
//		return false;
		return true;

	}//public static boolean refreshMainDB(ListActivity actv)

	private static void updateRefreshLog(
												Activity actv, SQLiteDatabase wdb, 
												DBUtils dbu, long lastItemDate, int numOfItemsAdded) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. If no, create one
		 * 2-2. Create table failed => Return
		 * 3. Insert data
			----------------------------*/
		String tableName = ImageFileManager8Activity.refreshLogTableName;
		
		if(!dbu.tableExists(wdb, tableName)) {
			
//			toastAndLog(actv, "Table doesn't exitst: " + tableName, 2000);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exitst: " + tableName);
			
			
			/*----------------------------
			 * 2. If no, create one
				----------------------------*/
			if(dbu.createTable(wdb, tableName, 
								DBUtils.cols_refresh_log, DBUtils.col_types_refresh_log)) {
				
//				toastAndLog(actv, "Table created: " + tableName, 3000);
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Table created: " + tableName);
				
				
			} else {//if
				/*----------------------------
				 * 2-2. Create table failed => Return
					----------------------------*/
//				toastAndLog(actv, "Create table failed: " + tableName, 3000);
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create table failed: " + tableName);
				
				
				return;
				
			}//if
			
		} else {//if(dbu.tableExists(wdb, ImageFileManager8Activity.refreshLogTableName))
			
//			toastAndLog(actv, "Table exitsts: " + tableName, 2000);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exitsts: " + tableName);
			
			
		}//if(dbu.tableExists(wdb, ImageFileManager8Activity.refreshLogTableName))
		
		/*----------------------------
		 * 3. Insert data
			----------------------------*/
//		dbu.insertData(wdb, tableName, DBUtils.cols_refresh_log, DBUtils.col_types_refresh_log);
//		dbu.insertData(wdb, tableName, long lastItemDate, int numOfItemsAdded);
		dbu.insertData(
						wdb, 
						tableName, 
						DBUtils.cols_refresh_log, 
						new long[] {lastItemDate, (long) numOfItemsAdded});
		
		
	}//private static void updateRefreshLog(SQLiteDatabase wdb, long lastItemDate)

	
	/****************************************
	 *		insertDataIntoDB()
	 * 
	 * <Caller> 
	 * 1. public static boolean refreshMainDB(ListActivity actv)
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	private static int insertDataIntoDB(SQLiteDatabase wdb, DBUtils dbu, Cursor c,
									String tableName, String backupTableName) {
		/*----------------------------
		 * Steps
		 * 1. Move to first
		 * 2. Set variables
		 * 3. Obtain data
		 * 4. Insert data
		 * 5. Return => counter
			----------------------------*/
		
		//
		c.moveToFirst();
		
		//
//		String[] columns = DBUtils.cols;
		
		/*----------------------------
		 * 2. Set variables
			----------------------------*/
		int counter = 0;
//		long threshHoldTime = getMillSeconds(2012, 7, 5);
		long threshHoldTime = getMillSeconds(2012, 6, 5);
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "threshHoldTime => " + threshHoldTime);
		
		/*----------------------------
		 * 3. Obtain data
			----------------------------*/
//		for (int i = c.getCount() - tempRecordNum; i < c.getCount(); i++) {
		for (int i = 0; i < c.getCount(); i++) {
			//
//			if(c.getLong(3) >= threshHoldTime) {
//			if(c.getLong(3) * 1000 >= threshHoldTime) {
//			if(i > c.getCount() - tempRecordNum) {
				//
				String[] values = {
						String.valueOf(c.getLong(0)),
						c.getString(1),
						c.getString(2),
						String.valueOf(c.getLong(3)),
						String.valueOf(c.getLong(4))
				};

				/*----------------------------
				 * 4. Insert data
				 * 		1. Insert data to tableName
				 * 		2. Record result
				 * 		3. Insert data to backupTableName
				 * 		4. Record result
					----------------------------*/
//				boolean blResult = true;

//				boolean blResult = dbu.insertData(wdb, tableName, columns, values);
				boolean blResult = 
							dbu.insertData(wdb, tableName, DBUtils.cols_for_insert_data, values);
				
//				// Log
//				Log.d("Methods.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", 
//						StringUtils.join(values, "/"));
				
				
				
//				if (blResult == false) {
//					// Log
//					Log.d("Methods.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", "i => " + i + "/" + "c.getLong(0) => " + c.getLong(0));
//				} 	
				
				
				if (blResult == false) {
					// Log
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "i => " + i + "/" + "c.getLong(0) => " + c.getLong(0));
				} else {//if (blResult == false)
					counter += 1;
				}
//			}//if(c.getLong(3) >= threshHoldTime)
			
			//
			c.moveToNext();
			
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "counter => " + counter);
		
		/*----------------------------
		 * 5. Return => counter
			----------------------------*/
		return counter;
		
	}//private static int insertDataIntoDB(Cursor c)
	/*****************************************
	 *		insertDataIntoDB()
	 * 
	 * <Caller> 
	 * 1. public static boolean refreshMainDB(ListActivity actv)
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	private static int insertDataIntoDB(SQLiteDatabase wdb, DBUtils dbu, Cursor c,
									String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Move to first
		 * 2. Set variables
		 * 3. Obtain data
		 * 4. Insert data
		 * 5. Return => counter
			----------------------------*/
		
		//
		c.moveToFirst();
		
		//
//		String[] columns = DBUtils.cols;
		
		/*----------------------------
		 * 2. Set variables
			----------------------------*/
		int counter = 0;
//		long threshHoldTime = getMillSeconds(2012, 7, 5);
		long threshHoldTime = getMillSeconds(2012, 6, 5);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "threshHoldTime => " + threshHoldTime);
		
		/*----------------------------
		 * 3. Obtain data
			----------------------------*/
//		for (int i = c.getCount() - tempRecordNum; i < c.getCount(); i++) {
		for (int i = 0; i < c.getCount(); i++) {
			//
//			if(c.getLong(3) >= threshHoldTime) {
			if(c.getLong(3) * 1000 >= threshHoldTime) {
//			if(i > c.getCount() - tempRecordNum) {
				//
				String[] values = {
						String.valueOf(c.getLong(0)),
						c.getString(1),
						c.getString(2),
						String.valueOf(c.getLong(3)),
						String.valueOf(c.getLong(4))
				};

				/*----------------------------
				 * 4. Insert data
					----------------------------*/
//				boolean blResult = true;

//				boolean blResult = dbu.insertData(wdb, tableName, columns, values);
				boolean blResult = 
							dbu.insertData(wdb, tableName, DBUtils.cols_for_insert_data, values);
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", 
						StringUtils.join(values, "/"));
				
				
				if (blResult == false) {
					// Log
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "i => " + i + "/" + "c.getLong(0) => " + c.getLong(0));
				} else {//if (blResult == false)
					counter += 1;
				}
			}//if(c.getLong(3) >= threshHoldTime)
			
			//
			c.moveToNext();
			
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "counter => " + counter);
		
		/*----------------------------
		 * 5. Return => counter
			----------------------------*/
		return counter;
		
	}//private static int insertDataIntoDB(Cursor c)

	public static long getMillSeconds(int year, int month, int date) {
		// Calendar
		Calendar cal = Calendar.getInstance();
		
		// Set time
		cal.set(year, month, date);
		
		// Date
		Date d = cal.getTime();
		
		return d.getTime();
		
	}//private long getMillSeconds(int year, int month, int date)

	public static void startDBAdminActivity(Activity actv) {
		//
		Intent i = new Intent();
		
		i.setClass(actv, DBAdminActivity.class);
		
		actv.startActivity(i);
		
	}//public static void startDBAdminActivity(Activity actv)

	public static void dlg_dropTable(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Dialog
		 * 		1.1. Set up
		 * 		1.2. OnTouch
		 * 		1.3. OnClick
		 * 2. Adapter
		 * 		2.1. Prepare data
		 * 		2.2. Create adapter
		 * 3. ListView
		 * 		3.1 Get view
		 * 		3.2. Set adapter to list view
		 * 		3.3. Set listener to list view
		 * 		1.5. OnListItem
		 * 4. Set listener to list view
		 * 5. Show dialog
			----------------------------*/
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_drop_table);
		
		// Title
		dlg.setTitle(R.string.dlg_drop_table_title);
		
		/*----------------------------
		 * 1.2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_drop_table_btn_cancel);
		
		//
		btn_cancel.setTag(DialogTags.dlg_drop_table_btn_cancel);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 1.3. Add listeners => OnClick
			----------------------------*/
		//
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		
		/*----------------------------
		 * 2. Adapter
			----------------------------*/
		// 
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		List<String> tableList = getTableList(wdb);
		
//		Collections.sort(tableList);		//=> Sort not done here, because getTableList() itself sorts out
													//		the list in the method.
		
//		//=> source: http://stackoverflow.com/questions/4681744/android-get-list-of-tables : "Just had to do the same. This seems to work:"
//		String q = "SELECT name FROM " + "sqlite_master"+
//						" WHERE type = 'table' ORDER BY name";
//		
//		Cursor c = null;
//		try {
//			c = wdb.rawQuery(q, null);
//		} catch (Exception e) {
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Exception => " + e.toString());
//		}
//		
//		// Table names list
//		List<String> tableList = new ArrayList<String>();
//		
//		// Log
//		if (c != null) {
//			c.moveToFirst();
//			
//			for (int i = 0; i < c.getCount(); i++) {
//				// Log
//				Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getString(0) => " + c.getString(0));
//				
//				//
//				tableList.add(c.getString(0));
//				
//				// Next
//				c.moveToNext();
//				
//			}//for (int i = 0; i < c.getCount(); i++)
//
//		} else {//if (c != null)
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c => null");
//		}//if (c != null)
		
		
		wdb.close();
		
		// Adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						actv,
						android.R.layout.simple_list_item_1, 
						tableList
				);
		
		/*----------------------------
		 * 3. ListView
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_drop_table_lv_list);
		
		// Set adapter
		lv.setAdapter(adapter);
		
		
//		LayoutParams params = lv.getLayoutParams();
//		
//		params.width = 100;
//		
//		lv.setLayoutParams(params);
		
		/*----------------------------
		 * 4. Set listener to list view
			----------------------------*/
//		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg, DialogTags.dlg_drop_table));
		
		/*----------------------------
		 * 5. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_dropTable(Activity actv)

	public static List<String> getTableList(SQLiteDatabase wdb) {
		//=> source: http://stackoverflow.com/questions/4681744/android-get-list-of-tables : "Just had to do the same. This seems to work:"
		String q = "SELECT name FROM " + "sqlite_master"+
						" WHERE type = 'table' ORDER BY name";
		
		Cursor c = null;
		try {
			c = wdb.rawQuery(q, null);
		} catch (Exception e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
		}
		
		// Table names list
		List<String> tableList = new ArrayList<String>();
		
		// Log
		if (c != null) {
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {
				// Log
				Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getString(0) => " + c.getString(0));
				
				//
				tableList.add(c.getString(0));
				
				// Next
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)

		} else {//if (c != null)
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c => null");
		}//if (c != null)
		
		return tableList;
	}//public static List<String> getTableList()

	public static void dlg_confirm_dropTable(Activity actv, Dialog dlg, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Set up dialog
		 * 2. Set table name to view
		 * 3. Set listener => onTouch
		 * 4. Set listener => Cancel
		 * 5. Set listener => Drop
		 * 6. Show dialog
			----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_confirm_drop_table);
		
		// Title
		dlg2.setTitle(R.string.generic_tv_confirm);
		
		/*----------------------------
		 * 2. Set table name to view
			----------------------------*/
		//
//		TextView tv_table_name = (TextView) actv.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		TextView tv_table_name = (TextView) dlg2.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		
		tv_table_name.setText(tableName);

		/*----------------------------
		 * 3. Set listener => onTouch
			----------------------------*/
		// Buttons
		Button btn_ok = (Button) dlg2.findViewById(R.id.dlg_confirm_drop_table_btn_ok);
		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_confirm_drop_table_btn_cancel);
		
		// Tags
		btn_ok.setTag(DialogTags.dlg_confirm_drop_table_btn_ok);
		btn_cancel.setTag(DialogTags.dlg_confirm_drop_table_btn_cancel);
		
		// Set
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 4. Set listener => Cancel
			----------------------------*/
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		/*----------------------------
		 * 5. Set listener => Drop
			----------------------------*/
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		/*----------------------------
		 * 6. Show dialog
			----------------------------*/
		dlg2.show();
		
	}//public static void dlg_confirm_dropTable


	public static void dropTable(Activity actv, Dialog dlg, Dialog dlg2) {
		/*----------------------------
		 * Steps
		 * 1. Get table name
		 * 2. Open db
		 * 3. Drop table
		 * 4. Dismiss dialog
		 * 5. Close db
			----------------------------*/
		
		// 
		TextView tv_table_name = (TextView) dlg2.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		
		String tableName = tv_table_name.getText().toString();
		
		/*----------------------------
		 * 2. Open db
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase db = dbu.getWritableDatabase();

		/*----------------------------
		 * 3. Drop table
			----------------------------*/
		boolean result = dbu.dropTable(actv, db, tv_table_name.getText().toString());
		
		if (result == true) {
			
			toastAndLog(actv, "Table dropped => " + tableName, 3000);
			
			dlg2.dismiss();
			dlg.dismiss();
			
//			// debug
//			Toast.makeText(actv, "Table dropped => " + tableName, 3000).show();
//			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Table dropped => " + tableName);
			
		} else {//if (result == true)
			
			toastAndLog(actv, "Drop table => Failed: " + tableName, 3000);
			
			dlg2.dismiss();
			
//			// debug
//			Toast.makeText(actv, "Drop table => Failed: " + tableName, 3000).show();
//			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Drop table => Failed: " + tableName);
			
		}//if (result == true)
		
		/*----------------------------
		 * 4. Dismiss dialog
			----------------------------*/
//		dlg.dismiss();
		
		db.close();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db => Closed");
		
	}//public static void dropTable(Activity actv, Dialog dlg, Dialog dlg2)

	public static void dropTable(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get table name
		 * 2. Open db
		 * 3. Drop table
		 * 4. Dismiss dialog
		 * 5. Close db
			----------------------------*/
		
		// 
		TextView tv_table_name = (TextView) dlg.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		
		String tableName = tv_table_name.getText().toString();
		
		/*----------------------------
		 * 2. Open db
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase db = dbu.getWritableDatabase();

		/*----------------------------
		 * 3. Drop table
			----------------------------*/
		boolean result = dbu.dropTable(actv, db, tv_table_name.getText().toString());
		
		if (result == true) {
			
			toastAndLog(actv, "Table dropped => " + tableName, 3000);
			
//			// debug
//			Toast.makeText(actv, "Table dropped => " + tableName, 3000).show();
//			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Table dropped => " + tableName);
			
		} else {//if (result == true)
			
			toastAndLog(actv, "Drop table => Failed: " + tableName, 3000);
			
//			// debug
//			Toast.makeText(actv, "Drop table => Failed: " + tableName, 3000).show();
//			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Drop table => Failed: " + tableName);
			
		}//if (result == true)
		
		/*----------------------------
		 * 4. Dismiss dialog
			----------------------------*/
		dlg.dismiss();
		
		db.close();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db => Closed");
		
		
	}//public static void dropTable(Activity actv, Dialog dlg)

	/****************************************
	 *
	 * 
	 * <Caller> 
	 * 1. ThumbnailActivity.initial_setup()
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static List<ThumbnailItem> getAllData(Activity actv, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. DB setup
		 * 2. Get data
		 * 		2.1. Get cursor
		 * 		2.2. Add to list
		 * 
		 * 9. Close db
		 * 10. Return value
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		/*----------------------------
		 * 2. Get data
		 * 		2.1. Get cursor
		 * 		2.2. Add to list
			----------------------------*/
		//
		String sql = "SELECT * FROM " + tableName;
		
		Cursor c = null;
		
		try {
			c = rdb.rawQuery(sql, null);
		} catch (Exception e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return null;
		}
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());

		/*----------------------------
		 * 2.2. Add to list
			----------------------------*/
		c.moveToNext();
		
		List<ThumbnailItem> tiList = new ArrayList<ThumbnailItem>();
		
		for (int i = 0; i < c.getCount(); i++) {

			ThumbnailItem ti = new ThumbnailItem(
					c.getLong(1),	// file_id
					c.getString(2),	// file_path
					c.getString(3),	// file_name
					c.getLong(4),	// date_added
//					c.getLong(5)		// date_modified
					c.getLong(5),		// date_modified
					c.getString(6),	// memos
					c.getString(7)	// tags
			);
	
			// Add to the list
			tiList.add(ti);
			
			//
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		
		
		/*----------------------------
		 * 9. Close db
			----------------------------*/
		rdb.close();
		
		/*----------------------------
		 * 10. Return value
			----------------------------*/
		return tiList;
		
	}//public static List<ThumbnailItem> getAllData

	public static void addMemo(Activity actv, long file_id, String tableName) {
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "addMemo() => Started");
		
		
		/* VERSION = MOCKUP */
		
//		toastAndLog(actv, getCurrentPathLabel(actv), 3000);
//		toastAndLog(actv, convertPathIntoTableName(actv), 3000);
		
		/*----------------------------
		 * Steps
		 * 1. Get tuhumbnail item
		 * 2. Set memo
		 * 3. Update db
		 * 
		 * 4. Refresh thumbnails list
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		ThumbnailItem ti = dbu.getData(actv, rdb, tableName, file_id);
		
		rdb.close();
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "DB => closed");
//		
//		toastAndLog(actv, ti.getFile_name() + "/" + "memo=" + ti.getMemo(), 2000);
		
		/*----------------------------
		 * 2. Set memo
			----------------------------*/
//		ti.setMemo("abcdefg");
//		ti.setMemo("123456");
		ti.setMemo("WHERE句を省略した場合はテーブルに含まれる全てのデータの指定のカラムの値が指定の値で更新されます。");
		
		/*----------------------------
		 * 3. Update db
			----------------------------*/
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		boolean result = dbu.updateData_memos(actv, wdb, tableName, ti);
		
		if (result) {
			
		}//if (result)
		
		wdb.close();
		
		/*----------------------------
		 * 4. Refresh thumbnails list
			----------------------------*/
		refreshTIList(actv);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "TIList => Refreshed");
		
		
	}//public static void addMemo(Activity actv, long file_id)

	private static void refreshTIList(Activity actv) {
		
		String tableName = Methods.convertPathIntoTableName(actv);

		ThumbnailActivity.tiList.clear();
		
//		ThumbnailActivity.tiList = Methods.getAllData(actv, tableName);
		
		ThumbnailActivity.tiList.addAll(Methods.getAllData(actv, tableName));
		
		ThumbnailActivity.aAdapter.notifyDataSetChanged();
	}

	public static void dlg_addMemo(Activity actv, long file_id, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 1-2. Set text to edit text
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_add_memos);
		
		// Title
		dlg.setTitle(R.string.dlg_add_memos_tv_title);
		
		/*----------------------------
		 * 1-2. Set text to edit text
			----------------------------*/
		ThumbnailItem ti = getData(actv, tableName, file_id);
		
		EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
		
		if (ti.getMemo() != null) {
			
			et.setText(ti.getMemo());
			
			et.setSelection(ti.getMemo().length());
			
		} else {//if (ti.getMemo() != null)
			
			et.setSelection(0);
			
		}//if (ti.getMemo() != null)
		
		
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_add = (Button) dlg.findViewById(R.id.dlg_add_memos_bt_add);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_add_memos_cancel);
		
		// Tags
		btn_add.setTag(DialogTags.dlg_add_memos_bt_add);
		btn_cancel.setTag(DialogTags.dlg_generic_dismiss);
		
		//
		btn_add.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "file_id => " + file_id);
		
		
		//
//		btn_add.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_add.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, file_id, tableName));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		//
		dlg.show();
		
	}//public static void dlg_addMemo(Activity actv, long file_id, String tableName)

	public static ThumbnailItem getData(Activity actv, String tableName, long file_id) {

		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		ThumbnailItem ti = dbu.getData(actv, rdb, tableName, file_id);
		
		rdb.close();
		
		return ti;
		
	}//public ThumbnailItem getData(Activity actv, String tableName, long file_id)

	public static void addMemo(Activity actv, Dialog dlg, long file_id, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Get tuhumbnail item
		 * 1-2. Get text from edit text
		 * 2. Set memo
		 * 3. Update db
		 * 
		 * 4. Refresh thumbnails list
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		ThumbnailItem ti = dbu.getData(actv, rdb, tableName, file_id);
		
		rdb.close();
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "DB => closed");
//		
//		toastAndLog(actv, ti.getFile_name() + "/" + "memo=" + ti.getMemo(), 2000);
		
		/*----------------------------
		 * 1-2. Get text from edit text
			----------------------------*/
		EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
		
		/*----------------------------
		 * 2. Set memo
			----------------------------*/
//		ti.setMemo("abcdefg");
//		ti.setMemo("123456");
//		ti.setMemo("WHERE句を省略した場合はテーブルに含まれる全てのデータの指定のカラムの値が指定の値で更新されます。");
		
		ti.setMemo(et.getText().toString());
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "New memo => " + et.getText().toString());
		
		/*----------------------------
		 * 3. Update db
			----------------------------*/
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		boolean result = dbu.updateData_memos(actv, wdb, tableName, ti);
		
		wdb.close();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "wdb => Closed");
		
		if (result) {
			
			dlg.dismiss();
			
		} else {//if (result)
			
			return;
			
		}//if (result)
		
		
		
		/*----------------------------
		 * 4. Refresh thumbnails list
			----------------------------*/
		refreshTIList(actv);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "TIList => Refreshed");
		
	}//public static void addMemo()

	public static void dlg_moveFiles(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Get generic dialog
		 * 2. Get dir list
		 * 2-1. Set list to the adapter
		 * 3. Set adapter to the list view
		 * 4. Set listener to the view
		 * 
		 * 9. Show dialog
			----------------------------*/
		
		Dialog dlg = dlg_template_cancel(
				// Activity, layout, title
				actv, R.layout.dlg_move_files, R.string.thumb_actv_menu_move_files,
				// Ok button, Cancel button
				R.id.dlg_move_files_bt_cancel,
				// Ok tag, Cancel tag
				DialogTags.dlg_generic_dismiss
							);
		
		/*----------------------------
		 * 2. Get dir list
			----------------------------*/
//		String[] fileNames = new File(ImageFileManager8Activity.baseDirPath).list();
//		String[] fileNames = new File(ImageFileManager8Activity.baseDirPath).list(new FileFilter(){
//
//			@Override
//			public boolean accept(File pathname) {
//				// TODO 自動生成されたメソッド・スタブ
//				
//				return pathname.isDirectory();
//			}
//			
//		});//String[] fileNames

		File[] files = new File(ImageFileManager8Activity.baseDirPath).listFiles(new FileFilter(){

			@Override
			public boolean accept(File pathname) {
				// TODO 自動生成されたメソッド・スタブ
				
				return pathname.isDirectory();
			}
			
		});//File[] files
		
		List<String> fileNameList = new ArrayList<String>();
		
//		for (String fileName : fileNames) {
		for (File eachFile : files) {
			
//			fileNameList.add(fileName);
			fileNameList.add(eachFile.getName());
			
		}//for (String fileName : fileNames)
		
		Collections.sort(fileNameList);
		
		/*----------------------------
		 * 2-1. Set list to the adapter
			----------------------------*/
		ArrayAdapter<String> dirListAdapter = new ArrayAdapter<String>(
												actv,
												android.R.layout.simple_list_item_1,
												fileNameList
											);
		
		/*----------------------------
		 * 3. Set adapter to the list view
			----------------------------*/
		//
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_move_files_lv_list);
		
		lv.setAdapter(dirListAdapter);
		
		/*----------------------------
		 * 4. Set listener to the view
			----------------------------*/
		
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg, DialogTags.dlg_move_files));
		
		/*----------------------------
		 * 9. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_moveFiles(Activity actv)

	public static Dialog dlg_template_okCancel(Activity actv, int layoutId, int titleStringId,
									int okButtonId, int cancelButtonId, DialogTags okTag, DialogTags cancelTag) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		//
//		dlg.show();
		
		return dlg;
		
	}//public static Dialog dlg_template_okCancel()

	public static Dialog dlg_template_okCancel_2_dialogues(
											Activity actv, int layoutId, int titleStringId,
											int okButtonId, int cancelButtonId, 
											DialogTags okTag, DialogTags cancelTag,
											Dialog dlg1) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg1, dlg2));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel_2_dialogues()

	public static Dialog dlg_template_cancel(Activity actv, int layoutId, int titleStringId,
											int cancelButtonId, DialogTags cancelTag) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
		
	}//public static Dialog dlg_template_okCancel()

	public static void dlg_confirm_moveFiles(Activity actv, Dialog dlg, String folderPath) {
		/*----------------------------
		 * Steps
		 * 1. Get a confirm dialog
		 * 2. Set a chosen folder name to the view
		 * 9. Show dialog
			----------------------------*/
		
		Dialog dlg2 = dlg_template_okCancel_2_dialogues(
				// Activity, layout, title
				actv, R.layout.dlg_confirm_move_files, R.string.generic_tv_confirm,
				// Ok button, Cancel button
				R.id.dlg_confirm_move_files_btn_ok, R.id.dlg_confirm_move_files_btn_cancel,
				// Ok tag, Cancel tag
				DialogTags.dlg_confirm_move_files_ok, DialogTags.dlg_generic_dismiss_second_dialog,
				// Dialogues
				dlg
		);
		
		/*----------------------------
		 * 2. Set a chosen folder name to the view
			----------------------------*/
		TextView tv_folder_name = (TextView) dlg2.findViewById(R.id.dlg_confirm_move_files_tv_table_name);
		
		tv_folder_name.setText(folderPath);
		
		/*----------------------------
		 * 9. Show dialog
			----------------------------*/
		dlg2.show();
		
	}//public static void dlg_confirm_moveFiles(Activity actv, Dialog dlg)

	public static void moveFiles(Activity actv, Dialog dlg1, Dialog dlg2) {
		/*----------------------------
		 * Steps
		 * 1. Move files
		 * 2. Update the list view
		 * 3. Dismiss dialogues
			----------------------------*/
		/*----------------------------
		 * 1. Move files
		 * 		1.1. Prepare toMoveFiles
		 * 		1.2. Get target dir path from dlg2
		 * 		1.3. Insert items in toMoveFiles to the new table
		 * 		1.4. Delete the items from the source table
			----------------------------*/
		List<ThumbnailItem> toMoveFiles = new ArrayList<ThumbnailItem>();
		
		for (int position : ThumbnailActivity.checkedPositions) {
			
			toMoveFiles.add(ThumbnailActivity.tiList.get(position));
			
		}//for (int position : ThumbnailActivity.checkedPositions)
		
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "toMoveFiles.size() => " + toMoveFiles.size());
		
		for (ThumbnailItem thumbnailItem : toMoveFiles) {
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "thumbnailItem.getFile_name() => " + thumbnailItem.getFile_name());
		}
		
		/*----------------------------
		 * 1.2. Get target dir path from dlg2
			----------------------------*/
		TextView tv = (TextView) dlg2.findViewById(R.id.dlg_confirm_move_files_tv_table_name);
		
		String folderPath = tv.getText().toString();
		
		String targetTableName = convertPathIntoTableName(actv, folderPath);
		
		String sourceTableName = convertPathIntoTableName(actv);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "folderPath => " + folderPath);
		
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "targetTableName => " + targetTableName);
		
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sourceTableName => " + sourceTableName);
		
		/*----------------------------
		 * 1.3. Insert items in toMoveFiles to the new table
		 * 		1.3.1. Insert data to the new table
			----------------------------*/
		/*----------------------------
		 * 1.3.1. Insert data to the new table
		 * 		1. Set up db
		 * 		2. Table exists?
		 * 		2-2. If no, create one
		 * 		3. Get item from toMoveFiles
		 * 
		 * 		4. Insert data into the new table
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		/*----------------------------
		 * 1.3.1.2. Table exists?
			----------------------------*/
		boolean result = dbu.tableExists(wdb, targetTableName);
		
		if (result == false) {
			
			/*----------------------------
			 * 1.3.2-2. If no, create one
				----------------------------*/
			result = dbu.createTable(
								wdb, targetTableName, 
								dbu.get_cols(), dbu.get_col_types());
			
			if (result == false) {
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "toast and log");
				
				toastAndLog(actv, "Can't create a table", 2000);
				
				wdb.close();
				
				return;
				
			}//if (result == false)
			
		}//if (result == true)
		
		
		/*----------------------------
		 * 1.3.1.3. Get item from toMoveFiles
			----------------------------*/
		for (ThumbnailItem thumbnailItem : toMoveFiles) {
			
			/*----------------------------
			 * 1.3.4. Insert data into the new table
				----------------------------*/
			dbu.insertData(wdb, targetTableName, thumbnailItem);
			
		}//for (ThumbnailItem thumbnailItem : toMoveFiles)
		
		
		/*----------------------------
		 * 1.4. Delete the items from the source table
		 * 		1. Delete data from the source table
		 * 		2. Delete the item from tiList 
		 * 
		 * 		9. Close db
			----------------------------*/
		/*----------------------------
		 * 1.4.1. Delete data from the source table
			----------------------------*/
//		for (ThumbnailItem thumbnailItem : toMoveFiles) {
//			
//			deleteItem_fileId(actv, thumbnailItem, int position);
//			
//		}//for (ThumbnailItem thumbnailItem : toMoveFiles)
		
		/*----------------------------
		 * 1.4.9. Close wdb
			----------------------------*/
		wdb.close();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "wdb => Closed");
		
		/*----------------------------
		 * 2. Update the list view
			----------------------------*/
		ThumbnailActivity.checkedPositions.clear();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "checkedPositions => Cleared");
		
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", 
				"ThumbnailActivity.checkedPositions.size() => " + 
				ThumbnailActivity.checkedPositions.size());
		
		ThumbnailActivity.aAdapter.notifyDataSetChanged();
		
		
		
		/*----------------------------
		 * 3. Dismiss dialogues
			----------------------------*/
		dlg1.dismiss();
		dlg2.dismiss();
		
	}//public static void moveFiles(Activity actv, Dialog dlg1, Dialog dlg2)


	public static void dlg_thumb_actv_item_menu(Activity actv, ThumbnailItem ti) {
		/*----------------------------
		 * Steps
		 * 1. Get a generic dialog
		 * 2. Prepare data
		 * 3. Prepare adapter
		 * 
		 * 4. Set data to adapter
		 * 5. Set adapter to the list
		 * 5-1. Listener to the view
		 *
		 * 6. Show dialog
			----------------------------*/
		/*----------------------------
		 * 1. Get a generic dialog
			----------------------------*/
		Dialog dlg = dlg_template_cancel(actv, R.layout.dlg_item_menu, R.string.generic_tv_menu,
				R.id.dlg_item_menu_bt_cancel, DialogTags.dlg_generic_dismiss);
		
		/*----------------------------
		 * 2. Prepare data
			----------------------------*/
		List<String> itemList = new ArrayList<String>();
		
		itemList.add(actv.getString(R.string.dlg_item_menu_item_edit_memo));
		itemList.add(actv.getString(R.string.dlg_item_menu_item_delete));
		itemList.add(actv.getString(R.string.dlg_item_menu_item_move));
		
		/*----------------------------
		 * 3. Prepare adapter
		 * 4. Set data to adapter
			----------------------------*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						actv,
						android.R.layout.simple_list_item_1, 
						itemList
				);

		/*----------------------------
		 * 5. Set adapter to the list
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_item_menu_lv);
		
		lv.setAdapter(adapter);
		
		
		/*----------------------------
		 * 5-1. Listener to the view
			----------------------------*/
		
		lv.setOnItemClickListener(
						new DialogOnItemClickListener(actv, dlg, DialogTags.dlg_item_menu, ti));
		
		/*----------------------------
		 * 6. Show dialog
			----------------------------*/
		dlg.show();
		
		
	}//public static void dlg_thumb_actv_item_menu(Activity actv, ThumbnailItem ti)

	public static void deleteItem_fileId(Activity actv, ThumbnailItem ti, int position, Dialog dlg) {
		/*----------------------------
		 * 1. db setup
		 * 2. Delete data
		 * 3. Close db
		 * 4. If unsuccesful, toast a message (Not dismiss the dialog)
		 * 4-2. If successful, delete the item from tiList, as well, and,
		 * 4-3. Notify adapter
		 * 5. Dismiss dialog
			----------------------------*/
		
		/*----------------------------
		 * 1. db setup
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		/*----------------------------
		 * 2. Delete data
			----------------------------*/
		boolean result = dbu.deleteData(
							actv,
							wdb, 
							Methods.convertPathIntoTableName(actv), 
							ti.getFileId());
		
		/*----------------------------
		 * 3. Close db
			----------------------------*/
		wdb.close();
		
		/*----------------------------
		 * 4. If unsuccesful, toast a message (Not dismiss the dialog)
			----------------------------*/
		if (result == false) {
			
			Methods.toastAndLog(
					actv, 
//					"Data deleted => file id = " + String.valueOf(ti.getFileId()), 
					"Data wasn't deleted => file id = " + String.valueOf(ti.getFileId()),
					2000);
			
		} else if (result == true) {//if (result == true)
			/*----------------------------
			 * 4-2. If successful, delete the item from tiList, as well
				----------------------------*/
			Methods.toastAndLog(
					actv, 
//					"Data deleted => file id = " + String.valueOf(ti.getFileId()), 
					"Data deleted => file name = " + ti.getFile_name(),
					2000);

			ThumbnailActivity.tiList.remove(position);
			
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Data removed from tiList => " + ti.getFile_name());
			
			/*----------------------------
			 * 4-3. Notify adapter
				----------------------------*/
			ThumbnailActivity.aAdapter.notifyDataSetChanged();
			
		}//if (result == true)
		
//		/*----------------------------
//		 * 5. Notify adapter
//			----------------------------*/
//		ThumbnailActivity.aAdapter.notifyDataSetChanged();
//		
		/*----------------------------
		 * 5. Dismiss dialog
			----------------------------*/
		dlg.dismiss();

	}//public static void deleteItem_fileId(Activity actv, ThumbnailItem ti, int position, Dialog dlg)
	
	public static void deleteItem_fileId(Activity actv, String tableName, ThumbnailItem ti, int position) {
		/*----------------------------
		 * 1. db setup
		 * 2. Delete data
		 * 3. Close db
		 * 4. If unsuccesful, toast a message (Not dismiss the dialog)
		 * 4-2. If successful, delete the item from tiList, as well, and,
		 * 4-3. Notify adapter
		 * 5. Dismiss dialog
			----------------------------*/
		
		/*----------------------------
		 * 1. db setup
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		/*----------------------------
		 * 2. Delete data
			----------------------------*/
		boolean result = dbu.deleteData(
							actv,
							wdb, 
//							Methods.convertPathIntoTableName(actv),
							tableName,
							ti.getFileId());
		
		/*----------------------------
		 * 3. Close db
			----------------------------*/
		wdb.close();
		
		/*----------------------------
		 * 4. If unsuccesful, toast a message (Not dismiss the dialog)
			----------------------------*/
		if (result == false) {
			
			Methods.toastAndLog(
					actv, 
//					"Data deleted => file id = " + String.valueOf(ti.getFileId()), 
					"Data wasn't deleted => file id = " + String.valueOf(ti.getFileId()),
					2000);
			
		} else if (result == true) {//if (result == true)
			/*----------------------------
			 * 4-2. If successful, delete the item from tiList, as well
				----------------------------*/
			Methods.toastAndLog(
					actv, 
//					"Data deleted => file id = " + String.valueOf(ti.getFileId()), 
					"Data deleted => file name = " + ti.getFile_name(),
					2000);

			ThumbnailActivity.tiList.remove(position);
			
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Data removed from tiList => " + ti.getFile_name());
			
			/*----------------------------
			 * 4-3. Notify adapter
				----------------------------*/
			ThumbnailActivity.aAdapter.notifyDataSetChanged();
			
		}//if (result == true)
		
//		/*----------------------------
//		 * 5. Notify adapter
//			----------------------------*/
//		ThumbnailActivity.aAdapter.notifyDataSetChanged();
//		
//		/*----------------------------
//		 * 5. Dismiss dialog
//			----------------------------*/
//		dlg.dismiss();

	}//public static void deleteItem_fileId(Activity actv, ThumbnailItem ti, int position)
	
}//public class Methods
