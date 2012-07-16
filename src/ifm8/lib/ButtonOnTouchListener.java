package ifm8.lib;

import ifm8.main.R;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class ButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	public ButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Methods.ButtonTags tag_name = (Methods.ButtonTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			switch (tag_name) {
				//
			case ib_up:
				//
//				(ImageButton) v).setImageResource(R.drawable.ifm8_up_disenabled);
//				ImageButton ib = new ImageButton(actv);
				
				ImageButton ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_up_disenabled);
				
				break;
			case db_manager_activity_create_table:
			case db_manager_activity_drop_table:
				
				v.setBackgroundColor(Color.GRAY);
			
			break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {
				//
			case ib_up:
				//
				
				ImageButton ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_up);

				break;
			case db_manager_activity_create_table:
			case db_manager_activity_drop_table:

				v.setBackgroundColor(Color.WHITE);
				
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
