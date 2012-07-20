package ifm8.lib;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import thumb_activity.main.ThumbnailItem;

import ifm8.main.ImageFileManager8Activity;
import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class CustomOnItemLongClickListener implements OnItemLongClickListener {

	Activity actv;
	static Vibrator vib;

	//
	static Methods.ItemTags itemTag = null;
	
	//
	ArrayAdapter<String> dirListAdapter;	// Used in => case dir_list_move_files
	
	//
	Dialog dlg;	// Used in => case dir_list_move_files
	
	//
	List<String> fileNameList;	// Used in => case dir_list_move_files
	
	/****************************************
	 * Constructor
	 ****************************************/
	public CustomOnItemLongClickListener(Activity actv) {
		// 
		this.actv = actv;
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	/*----------------------------
	 * Used in => case dir_list_move_files
		----------------------------*/
	public CustomOnItemLongClickListener(Activity actv,
			Dialog dlg, ArrayAdapter<String> dirListAdapter, List<String> fileNameList) {
		// 
		this.actv = actv;
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		this.dlg = dlg;
		this.fileNameList = fileNameList;
		this.dirListAdapter = dirListAdapter;
		
	}//public CustomOnItemLongClickListener

	/****************************************
	 * Methods
	 ****************************************/
	@Override
	public boolean onItemLongClick(
										AdapterView<?> parent, View v,
										int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get item
		 * 2. Get tag
		 * 3. Vibrate
		 * 
		 * 4. Is the tag null?
		 * 
		 * 5. If no, the switch
			----------------------------*/
		
//		// Log
//		Log.d("CustomOnItemLongClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "parent.getTag() => " + parent.getTag());
		
		
		
//		String folderName = (String) parent.getItemAtPosition(position);
		
		//
		Methods.ItemTags itemTag = (Methods.ItemTags) parent.getTag();
		
		// Log
		Log.d("CustomOnItemLongClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "itemTag => " + itemTag.name());
		
		
		//
//		vib.vibrate(400);
		vib.vibrate(40);
		
		if (itemTag == null) {
			
		} else {//if (itemTag == null)
			switch (itemTag) {
			/*----------------------------
			 * 5. If no, the switch
			 * 		1. dir_list
			 * 		2. dir_list_thumb_actv
			 * 		3. dir_list_move_files
				----------------------------*/
			/*----------------------------
			 * 5.1. dir_list
			 * 		0. Get folder name
			 * 		1. Is a directory?
			 * 		2. If yes, call a method
				----------------------------*/
			case dir_list:
				/*----------------------------
				 * 0. Get folder name
					----------------------------*/
				String folderName = (String) parent.getItemAtPosition(position);
				
				/*----------------------------
				 * 5.1.1. Is a directory?
					----------------------------*/
				File targetFile = new File(ImageFileManager8Activity.currentDirPath, folderName);
				
				if (targetFile.exists() && targetFile.isFile()) {
					// debug
					Toast.makeText(actv, "ファイル", 2000).show();
					
		//			return false;
					return true;		//=> "false" => Then, onClick process starts
					
				}//if (targetFile.exists() && targetFile.isFile())
				
				
				/*----------------------------
				 * 5.1.2. If yes, call a method
					----------------------------*/
				Methods.dlg_removeFolder(actv, folderName);
				
				break;
				
				/*----------------------------
				 * 5.2. dir_list_thumb_actv
					----------------------------*/
				case dir_list_thumb_actv:
					
					ThumbnailItem ti = (ThumbnailItem) parent.getItemAtPosition(position);
					
					// Log
					Log.d("CustomOnItemLongClickListener.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "ti.getFile_name() => " + ti.getFile_name());
					
					
					Methods.dlg_thumb_actv_item_menu(actv, ti);
					
					break;
				/*----------------------------
				 * 5.3. dir_list_move_files
					----------------------------*/
				case dir_list_move_files:
					/*----------------------------
					 * Steps
					 * 1. Get folder name from dialog view
					 * 2. Create a new file object, using => 
						----------------------------*/
					
					folderName = (String) parent.getItemAtPosition(position);
					
//					// debug
//					Toast.makeText(actv, folderName, 2000).show();
					
//					File f = new File(ImageFileManager8Activity.currentDirPath, folderName);
//					File f = new File(ImageFileManager8Activity.currentDirPath, folderName + "/abcde");
					
//					File f = new File(ImageFileManager8Activity.currentDirPath, folderName);
					File f = new File(ImageFileManager8Activity.baseDirPath, folderName);
					
					// Log
					Log.d("CustomOnItemLongClickListener.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "f.getAbsolutePath() => " + f.getAbsolutePath());
					
					
					if (!f.exists()) {
						
						// debug
						Toast.makeText(actv, "File doesn't exist", 3000).show();
						
						return false;
						
					} else if (f.isFile()) {
						
						// debug
						Toast.makeText(actv, "The item is a file", 3000).show();
						
						return false;
						
					}//if (f.isFile())
					
					File[] files = new File(f.getAbsolutePath()).listFiles(new FileFilter(){

						@Override
						public boolean accept(File pathname) {
							// TODO 自動生成されたメソッド・スタブ
							
							return pathname.isDirectory();
						}
						
					});//File[] files
					
					fileNameList.clear();
					
//					for (String fileName : fileNames) {
					for (File eachFile : files) {
						
//						fileNameList.add(fileName);
//						fileNameList.add(eachFile.getName());
//						fileNameList.add(eachFile.getAbsolutePath());
						fileNameList.add(Methods.convertAbsolutePathIntoPath(actv, eachFile.getAbsolutePath()));
						
					}//for (String fileName : fileNames)
					
					Collections.sort(fileNameList);

					dirListAdapter.notifyDataSetChanged();

					
//					// debug
//					Toast.makeText(actv, f.getAbsolutePath(), 2000).show();
					
					
					break;
					
			}//switch (itemTag)
			
		}//if (itemTag == null)
		
		
//			/*----------------------------
//			 * 4. Is a directory?
//				----------------------------*/
//			File targetFile = new File(ImageFileManager8Activity.currentDirPath, folderName);
//			
//			if (targetFile.exists() && targetFile.isFile()) {
//				// debug
//				Toast.makeText(actv, "ファイル", 2000).show();
//				
//	//			return false;
//				return true;		//=> "false" => Then, onClick process starts
//				
//			}//if (targetFile.exists() && targetFile.isFile())
//			
//			
//			/*----------------------------
//			 * 5. If yes, call a method
//				----------------------------*/
//			Methods.dlg_removeFolder(actv, folderName);
		
		return false;
		
	}//public boolean onItemLongClick()

}
