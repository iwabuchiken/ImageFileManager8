package ifm8.lib;
import java.io.File;

import thumb_activity.main.ThumbnailActivity;

import ifm8.main.ImageFileManager8Activity;
import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.util.Log;
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
	
	//
	int position;
	
	public ButtonOnClickListener(Activity actv) {
		//
		this.actv = actv;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public ButtonOnClickListener(Activity actv, int position) {
		//
		this.actv = actv;
		this.position = position;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
		
		
	}//public ButtonOnClickListener(Activity actv, int position)

	@Override
	public void onClick(View v) {
		//
		Methods.ButtonTags tag_name = (Methods.ButtonTags) v.getTag();

		//
		switch (tag_name) {
		case ib_up:
			Methods.upDirectory(actv);
			
			break;// case ib_up
			
		case db_manager_activity_create_table:
			
			Methods.dlg_dropTable(actv);
			
			break;
			
		case db_manager_activity_drop_table:
			
			Methods.dlg_dropTable(actv);
			
			break;
			
		case thumb_activity_ib_back:
			
			vib.vibrate(Methods.vibLength_click);
			
			actv.finish();
			
			break;
			
		case image_activity_back:
			
			vib.vibrate(Methods.vibLength_click);
			
			actv.finish();
			
			break;

		case tilist_cb:
			
			// Log
			Log.d("ButtonOnClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "position => " + position);
			
			ThumbnailActivity.checkedPositions.add(position);
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "New position => " + position +
					" / " + "(length=" + ThumbnailActivity.checkedPositions.size() + ")");

			break;
		
		default:
			break;
		}//switch (tag_name)
	}

}
