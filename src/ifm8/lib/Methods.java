package ifm8.lib;

import ifm8.main.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentResolver;
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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang.StringUtils;

public class Methods {

	public static enum DialogTags {
		// dlg_create_folder.xml
		dlg_create_folder_ok, dlg_create_folder_cancel,

		// dlg_input_empty.xml
		dlg_input_empty_reenter, dlg_input_empty_cancel,
		
		// dlg_confirm_create_folder.xml
		dlg_confirm_create_folder_ok, dlg_confirm_create_folder_cancel,

		// dlg_confirm_remove_folder.xml
		dlg_confirm_remove_folder_ok, dlg_confirm_remove_folder_cancel,
		
	}//public static enum DialogTags
	
	public static enum ButtonTags {
		// ImageFileManager8Activity.java
		ib_up,
		
	}
	
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
		
		
	}//public static void createFolder(Activity actv, Dialog dlg, Dialog dlg2)

	private static void refreshListView(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Get file list
		 * 1-2. Sort list
		 * 2. Clear => ImageFileManager8Activity.file_names
		 * 3. Add file names to => ImageFileManager8Activity.file_names
		 * 4. Notify adapter of changes
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
		
		return;
		
	}//public static void removeFolder(Activity actv, Dialog dlg)

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

	public static void enterDir(Activity actv, File newDir) {
		/*----------------------------
		 * Steps
		 * 1. Update the current path
		 * 2. Refresh list view
		 * 3. "Up" button => Enable
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
		
	}//public static void enterDir(Activity actv, File newDir)
}//public class Methods
