package ifm8.lib;

import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class DialogButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg;
	Dialog dlg2;		//=> Used in dlg_input_empty_btn_XXX

	//
	Vibrator vib;
	
	public DialogButtonOnClickListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public DialogButtonOnClickListener(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.dlg = dlg1;
		this.dlg2 = dlg2;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	@Override
	public void onClick(View v) {
		//
		Methods.DialogTags tag_name = (Methods.DialogTags) v.getTag();

		//
		switch (tag_name) {
		
		// dlg_create_folder.xml
		case dlg_create_folder_cancel:
			
			dlg.dismiss();
			
			break;
			
		case dlg_create_folder_ok:
			
			Methods.dlg_isEmpty(actv, dlg);
			
			break;
		
		// dlg_input_empty.xml
		case dlg_input_empty_reenter:
			
			dlg2.dismiss();
//			dlg.dismiss();
			
			
			
			break;
		case dlg_input_empty_cancel:
			
			dlg2.dismiss();
			dlg.dismiss();
			
			break;
		
		// dlg_confirm_create_folder.xml
		case dlg_confirm_create_folder_ok:
			
			Methods.createFolder(actv, dlg, dlg2);
			
			break;
		case dlg_confirm_create_folder_cancel:
			
			dlg2.dismiss();
			
			break;

		// dlg_confirm_remove_folder.xml
		case dlg_confirm_remove_folder_ok:
			
			Methods.removeFolder(actv, dlg);
			
			break;
			
		case dlg_confirm_remove_folder_cancel:
			
			dlg.dismiss();
			
			break;
			
		default:
			break;
		}//switch (tag_name)
	}

}
