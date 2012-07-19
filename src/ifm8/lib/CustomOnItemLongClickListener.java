package ifm8.lib;

import java.io.File;

import thumb_activity.main.ThumbnailItem;

import ifm8.main.ImageFileManager8Activity;
import android.app.Activity;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

public class CustomOnItemLongClickListener implements OnItemLongClickListener {

	Activity actv;
	static Vibrator vib;

	//
	static Methods.ItemTags itemTag = null;
	
	/****************************************
	 * Constructor
	 ****************************************/
	public CustomOnItemLongClickListener(Activity actv) {
		// 
		this.actv = actv;
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

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
		 * 		1. dir_list
		 * 		2. dir_list_thumb_actv
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
