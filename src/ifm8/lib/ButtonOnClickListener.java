package ifm8.lib;
import java.io.File;

import thumb_activity.main.ThumbnailActivity;
import thumb_activity.main.ThumbnailItem;

import ifm8.main.ImageFileManager8Activity;
import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
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
	
	//
	ListView lv;
	
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

	public ButtonOnClickListener(Activity actv, ListView lv) {
		// 
		this.actv = actv;
		this.lv = lv;
		
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
			
			vib.vibrate(Methods.vibLength_click);
			
			Methods.dlg_createTable(actv);
			
			break;
			
		case db_manager_activity_drop_table:
			
			vib.vibrate(Methods.vibLength_click);
			
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
			/*----------------------------
			 * Steps
			 * 1. If already checked, unlist from ThumbnailActivity.checkedPositions
			 * 2. If not yet, enlist into it
			 * 3. Then, notify to adapter
				----------------------------*/
			/*----------------------------
			 * 1. If already checked, unlist from ThumbnailActivity.checkedPositions
				----------------------------*/
			if (ThumbnailActivity.checkedPositions.contains((int)position)) {
				// Log
				Log.d("ButtonOnClickListener.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "position exists => " + position);
				
//				ThumbnailActivity.checkedPositions.add(position);
//				ThumbnailActivity.checkedPositions.remove(position);
				ThumbnailActivity.checkedPositions.remove((Integer) position);
				
				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "position removed => " + position);
				
				
//				// Log
//				Log.d("ThumbnailActivity.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", "New position => " + position +
//						" / " + "(length=" + ThumbnailActivity.checkedPositions.size() + ")");
//	
//				Log.d("ThumbnailActivity.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", 
//						"tiList(position=" + position + ") => " + 
//						ThumbnailActivity.tiList.get(position).getFile_name());
	
			} else {//if (ThumbnailActivity.checkedPositions.contains((int)position))
				/*----------------------------
				 * 2. If not yet, enlist into it
					----------------------------*/
				
				ThumbnailActivity.checkedPositions.add(position);
				
				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "new position added => " + String.valueOf(position));
				
				
			}//if (ThumbnailActivity.checkedPositions.contains((int)position))
			
			/*----------------------------
			 * 3. Then, notify to adapter
				----------------------------*/
			ThumbnailActivity.aAdapter.notifyDataSetChanged();
			
			break;
		
		case thumb_activity_ib_bottom: //----------------------------------------------
			
			vib.vibrate(Methods.vibLength_click);
			
			int numOfGroups = ThumbnailActivity.tiList.size() / lv.getChildCount();
			
			int indexOfLastChild = lv.getChildCount() * numOfGroups;
			
//			// Log
//			Log.d("ButtonOnClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "numOfGroups => " + numOfGroups);
//			Log.d("ButtonOnClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "indexOfLastChild => " + indexOfLastChild);
//			Log.d("ButtonOnClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "ThumbnailItem => " + ((ThumbnailItem) lv.getItemAtPosition(indexOfLastChild)).getFile_name());
			
			
			lv.setSelection(indexOfLastChild);
			
//			// Log
//			Log.d("ButtonOnClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "lv.getFirstVisiblePosition() => " + lv.getFirstVisiblePosition());
//			Log.d("ButtonOnClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "lv.getChildCount() => " + lv.getChildCount());
			
//			lv.setSelection(2);
//			
//			// Log
//			Log.d("ButtonOnClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "lv.getChildCount() => " + lv.getChildCount());
			
			
			break;// case thumb_activity_ib_bottom 
			
		case thumb_activity_ib_top://--------------------------------------------
			
			vib.vibrate(Methods.vibLength_click);
			
			lv.setSelection(0);
			
			break;// thumb_activity_ib_top
			
		default:
			break;
		}//switch (tag_name)
	}

}
