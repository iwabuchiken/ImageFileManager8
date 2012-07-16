package ifm8.lib;

import ifm8.dbadmin.DBAdminActivity;
import ifm8.main.*;
import thumb_activity.main.*;

import java.io.BufferedWriter;
import java.io.File;
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
		
	}//public static enum DialogTags
	
	public static enum ButtonTags {
		// ImageFileManager8Activity.java
		ib_up,
		
		// DBAdminActivity.java
		db_manager_activity_create_table, db_manager_activity_drop_table
		
	}//public static enum ButtonTags
	
	public static enum ItemTags {
		
		// ImageFileManager8Activity.java
		dir_list,
	}
	
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

	private static String convertPathIntoTableName(Activity actv) {
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
		String targetDirPath = ImageFileManager8Activity.currentDirPath;
		
		String[] a_targetDirPath = targetDirPath.split(new File("aaa").separator);
		
		String folderName = a_targetDirPath[a_targetDirPath.length - 1];
		
//		sb.append(tableName);
//		sb.append("__");
		sb.append(folderName);
		
		tableName = sb.toString();

		
		return tableName;
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
	 * 1. ImageFileManager7Activity : onOptionsItemSelected(MenuItem item)
	 * 
	 * <Desc> 1. <Params> 1.
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
		 * 5. Filter the cursor
		 * 
		 * 3. Get the "DATA_ADDED" of the last entry item
		 * 		1. If no entry => Start date will be 0
		 * 3-2. Close DB(readable)
		 * 
		 * 4. Execute query for image files
		 * 5. Filter the cursor
		 * 
		 * 9-1. Close cursor
		 * 9-2. Close db
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
			----------------------------*/
		//
		String tableName = convertPathIntoTableName(actv);
		
		// If the table doesn't exist, create one
		if (!dbu.tableExists(wdb, tableName)) {
			
			toastAndLog(actv, "Table doesn't exist: " + tableName, 3000);
			
			boolean blResult2 = 
					dbu.createTable(wdb, tableName, DBUtils.cols, DBUtils.col_types);

			if (blResult2 == false) {
				toastAndLog(actv, "Can't create a table: "+ tableName, 3000);
				
				wdb.close();
				
				return false;
				
			} else {//if (blResult2 == false)
				
				toastAndLog(actv, "Table created: "+ tableName, 3000);
				
			}//if (blResult2 == false)
			
//			wdb.close();
			
//			return false;
		}//if (!dbu.tableExists(wdb, tableName))
		
		//
		/*----------------------------
		 * 3. Execute query for image files
			----------------------------*/
		// ContentResolver
		ContentResolver cr = actv.getContentResolver();
		
		// Uri
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        
		// Projections
		String[] proj = DBUtils.proj;

        // Query
        Cursor c = actv.managedQuery(uri, proj, null, null, null);
		
        //
        actv.startManagingCursor(c);
        
        // Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());
        
		/*----------------------------
		 * 4. Insert data into db
			----------------------------*/
		//
		c.moveToFirst();
		
		//
		String[] columns = DBUtils.cols;
		
		//
		int counter = 0;
//		long threshHoldTime = getMillSeconds(2012, 7, 5);
		long threshHoldTime = getMillSeconds(2012, 6, 5);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "threshHoldTime => " + threshHoldTime);
		
		
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
				
				blResult = dbu.insertData(wdb, tableName, columns, values);
//				blResult = true;
				
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
		//
		wdb.close();
		
		return false;

	}//public static boolean refreshMainDB(ListActivity actv)

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

}//public class Methods
