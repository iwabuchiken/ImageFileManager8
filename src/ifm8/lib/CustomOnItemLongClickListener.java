package ifm8.lib;

import java.io.File;

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
		 * 4. Is a directory?
		 * 5. If yes, call a method
		 * 6. If no, show a message
			----------------------------*/
		
		String folderName = (String) parent.getItemAtPosition(position);
		
		//
		Methods.ItemTags tag = (Methods.ItemTags) parent.getTag();
		
		// Log
		Log.d("CustomOnItemLongClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tag => " + tag.name());
		
		
		//
//		vib.vibrate(400);
		vib.vibrate(40);

		/*----------------------------
		 * 4. Is a directory?
			----------------------------*/
		File targetFile = new File(ImageFileManager8Activity.currentDirPath, folderName);
		
		if (targetFile.exists() && targetFile.isFile()) {
			// debug
			Toast.makeText(actv, "ƒtƒ@ƒCƒ‹", 2000).show();
			
			return false;
		}//if (targetFile.exists() && targetFile.isFile())
		
		
		/*----------------------------
		 * 5. If yes, call a method
			----------------------------*/
		Methods.dlg_removeFolder(actv, folderName);
		
		return false;
	}

}
