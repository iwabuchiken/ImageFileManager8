package ifm8.lib;
import java.io.File;

import ifm8.main.ImageFileManager8Activity;
import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class ButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	public ButtonOnClickListener(Activity actv) {
		//
		this.actv = actv;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	@Override
	public void onClick(View v) {
		//
		Methods.ButtonTags tag_name = (Methods.ButtonTags) v.getTag();

		//
		switch (tag_name) {
		case ib_up:
			/*----------------------------
			 * Steps
			 * 1. Vibrate
			 * 2. Is the current path "roof"?
			 * 3. Go up the path
			 * 3-2. New path => Equal to base dir path?
			 * 4. Refresh list
				----------------------------*/
			vib.vibrate(ImageFileManager8Activity.vibLength_click);
			
			/*----------------------------
			 * 2. Is the current path "roof"?
				----------------------------*/
			if (ImageFileManager8Activity.currentDirPath == ImageFileManager8Activity.baseDirPath) {
				
				Methods.toastAndLog(actv, "No more upward directory", 3000);
			
				break;
			}//if (ImageFileManager8Activity.currentDirPath == ImageFileManager8Activity.baseDirPath)
			
			/*----------------------------
			 * 3. Go up the path
				----------------------------*/
//			ImageFileManager8Activity.currentDirPath = 
//							(new File(ImageFileManager8Activity.currentDirPath))
			
			File f = new File(ImageFileManager8Activity.currentDirPath);
			
			ImageFileManager8Activity.currentDirPath = f.getParent();
			
//			Methods.toastAndLog(actv, "f.getParent() => " + f.getParent(), 3000);
			
			/*----------------------------
			 * 3-2. New path => Equal to base dir path?
				----------------------------*/
			
			
			/*----------------------------
			 * 4. Refresh list
				----------------------------*/
			Methods.refreshListView(actv);
			
			break;
		default:
			break;
		}//switch (tag_name)
	}

}
