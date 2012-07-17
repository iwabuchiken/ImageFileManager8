package ifm8.lib;

import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.util.Log;
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
	
	// Used in => Methods.dlg_addMemo(Activity actv, long file_id, String tableName)
	long file_id;
	String tableName;
	
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

	public DialogButtonOnClickListener(Activity actv, Dialog dlg, long file_id, String tableName) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		
		this.tableName = tableName;
		
		this.file_id = file_id;
		
	}//public DialogButtonOnClickListener(Activity actv, Dialog dlg, long file_id, String tableName)

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

		case dlg_drop_table_btn_cancel:
			
			dlg.dismiss();
			
			break;
			
		case dlg_confirm_drop_table_btn_ok:
			
			Methods.dropTable(actv, dlg, dlg2);
			
			break;
			
		case dlg_confirm_drop_table_btn_cancel:
			
			dlg2.dismiss();
			
			break;

		case dlg_add_memos_bt_add:
			
			// Log
			Log.d("DialogButtonOnClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "file_id => " + file_id);
			
			
			// Log
			Log.d("DialogButtonOnClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Calling => Methods.addMemo()");
			
			
			Methods.addMemo(actv, dlg, file_id, tableName);
			
			break;
			
		case dlg_add_memos_bt_cancel:
			
			dlg.dismiss();
			
			break;
			
		case dlg_generic_dismiss:
			
			dlg.dismiss();
			
			break;
		default:
			break;
		}//switch (tag_name)
	}

}
