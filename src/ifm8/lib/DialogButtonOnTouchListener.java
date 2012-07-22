package ifm8.lib;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DialogButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg;
	
	public DialogButtonOnTouchListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
	}
	
	public DialogButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		Methods.DialogTags tag_name = (Methods.DialogTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
				switch (tag_name) {
					//
				case dlg_create_folder_ok:
				case dlg_input_empty_reenter:
				case dlg_input_empty_cancel:
				case dlg_confirm_create_folder_ok:
				case dlg_confirm_create_folder_cancel:
				case dlg_confirm_remove_folder_ok:
				case dlg_confirm_remove_folder_cancel:
				case dlg_drop_table_btn_cancel:
				case dlg_confirm_drop_table_btn_ok:
				case dlg_confirm_drop_table_btn_cancel:
				case dlg_add_memos_bt_add:
				case dlg_add_memos_bt_cancel:
				
				case dlg_generic_dismiss:
					
				case dlg_add_memos_bt_patterns:
					//
					v.setBackgroundColor(Color.GRAY);
					
					break;
					
				}//switch (tag_name)
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {
				//
				case dlg_create_folder_ok:
				case dlg_input_empty_reenter:
				case dlg_input_empty_cancel:
				case dlg_confirm_create_folder_ok:
				case dlg_confirm_create_folder_cancel:
				case dlg_confirm_remove_folder_ok:
				case dlg_confirm_remove_folder_cancel:
				case dlg_drop_table_btn_cancel:
				case dlg_confirm_drop_table_btn_ok:
				case dlg_confirm_drop_table_btn_cancel:
				case dlg_add_memos_bt_add:
				case dlg_add_memos_bt_cancel:
					
				case dlg_generic_dismiss:
					
				case dlg_add_memos_bt_patterns:
					//
					v.setBackgroundColor(Color.WHITE);
					
					break;
					
				}//switch (tag_name)
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
