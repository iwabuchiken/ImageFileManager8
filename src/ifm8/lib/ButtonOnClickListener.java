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
			
		default:
			break;
		}//switch (tag_name)
	}

}
