package ifm8.lib;

import thumb_activity.main.ThumbnailItem;
import ifm8.lib.Methods.DialogTags;
import ifm8.main.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class DialogOnItemClickListener implements OnItemClickListener {

	//
	Activity actv;
	Dialog dlg;
	Dialog dlg2;
	
	//
	Vibrator vib;
	
	//
	Methods.DialogTags dlgTag = null;

	//
	ThumbnailItem ti;
	
	public DialogOnItemClickListener(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DialogOnItemClickListener(Activity actv, Dialog dlg, Methods.DialogTags dlgTag) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		this.dlgTag = dlgTag;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DialogOnItemClickListener(Activity actv, Dialog dlg,
								Methods.DialogTags dlgTag, ThumbnailItem ti) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		this.dlgTag = dlgTag;
		this.ti = ti;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener

	public DialogOnItemClickListener(Activity actv, 
												Dialog dlg, Dialog dlg2,
												Methods.DialogTags dlgTag) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.actv = actv;
		this.dlg = dlg;
		this.dlgTag = dlgTag;
		this.dlg2 = dlg2;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

		
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 0. Vibrate
			----------------------------*/
		/*----------------------------
		 * 0. Vibrate
			----------------------------*/
		vib.vibrate(40);
		
		if (parent.getTag() instanceof Methods.DialogTags) {
			
//			this.parentTagIsDialogTag(parent, position);
			parentTagIsDialogTag(parent, position);
			
		}//if (parent.getTag() instanceof Methods.DialogTags)
		
//		if ((DialogTags) parent.getTag() != null) {
//		
//			// Log
//			Log.d("DialogOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "parent.getTag() => " + parent.getTag());
//			
//			switch ((DialogTags)parent.getTag()) {
//			case dlg_add_memos_gv://----------------------------------------------
//				
//				String word = (String) parent.getItemAtPosition(position);
//				
//				EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
//				
//				String content = et.getText().toString();
//				
//				content += word + " ";
//				
//				et.setText(content);
//				
//				et.setSelection(et.getText().toString().length());
//				
////				// debug
////				Toast.makeText(actv, word, 2000).show();
//				
//				break;
//				
//			}//switch (parent.getTag())
//			
//			
//		} else {//if ((DialogTags) parent.getTag() != null)
//			
//			// Log
//			Log.d("DialogOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "parent.getTag() => null");
//			
//		}//if ((DialogTags) parent.getTag() != null)
		
		
//		String tableName = (String) parent.getItemAtPosition(position);
//		
//		// Log
//		Log.d("DialogOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", 
//				"tableName => " + tableName + " / " + 
//				"position => " + position + " / " + "id => " + id);
//		
		/*----------------------------
		 * 2. Call a method
			----------------------------*/
		//
		if (dlgTag != null && dlgTag == Methods.DialogTags.dlg_drop_table) {
			
			String tableName = (String) parent.getItemAtPosition(position);
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", 
					"tableName => " + tableName + " / " + 
					"position => " + position + " / " + "id => " + id);
			
//			Methods.dlg_confirmTableDrop(actv, dlg, tableName);
			
			Methods.dlg_confirm_dropTable(actv, dlg, tableName);
			
		}//if (dlgName != null && dlgName == "confirm_table_drop")

		if (dlgTag == null) {
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "dlgTag == null");
			
			if ((DialogTags) v.getTag() != null) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "v.getTag().toString() => " + v.getTag().toString());
				
				
			}//if (v.getTag())
			
		} else if (dlgTag != null) {//if (dlgTag != null)
			
			switch (dlgTag) {
			case dlg_move_files:
				
				String folderPath = (String) parent.getItemAtPosition(position);
				
				Methods.dlg_confirm_moveFiles(actv, dlg, folderPath);
				
				break;
				
			case dlg_item_menu:
				/*----------------------------
				 * Steps
				 * 1. Get operation name
				 * 2. Op name is "Edit"
				 * 
					----------------------------*/
				
				
				String opName = (String) parent.getItemAtPosition(position);
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "opName => " + opName);
				
				/*----------------------------
				 * 2. Op name is "Edit"
				 * 		1. db setup
				 * 		2. Delete data
				 * 		3. Close db
				 * 		4. If unsuccesful, toast a message (Not dismiss the dialog)
				 * 		4-2. If successful, delete the item from tiList, as well, and,
				 * 		4-3. Notify adapter
				 * 		5. Dismiss dialog
					----------------------------*/
				// Edit memo
				if (opName.equals(actv.getString(R.string.dlg_item_menu_item_delete))) {

					Methods.deleteItem_fileId(actv, ti, position, dlg);
					
//					/*----------------------------
//					 * 2.1. db setup
//						----------------------------*/
//					DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
//					
//					SQLiteDatabase wdb = dbu.getWritableDatabase();
//					
//					/*----------------------------
//					 * 2. Delete data
//						----------------------------*/
//					boolean result = dbu.deleteData(
//										actv,
//										wdb, 
//										Methods.convertPathIntoTableName(actv), 
//										ti.getFileId());
//					
//					/*----------------------------
//					 * 3. Close db
//						----------------------------*/
//					wdb.close();
//					
//					/*----------------------------
//					 * 4. If unsuccesful, toast a message (Not dismiss the dialog)
//						----------------------------*/
//					if (result == false) {
//						
//						Methods.toastAndLog(
//								actv, 
////								"Data deleted => file id = " + String.valueOf(ti.getFileId()), 
//								"Data wasn't deleted => file id = " + String.valueOf(ti.getFileId()),
//								2000);
//						
//					} else if (result == true) {//if (result == true)
//						/*----------------------------
//						 * 4-2. If successful, delete the item from tiList, as well
//							----------------------------*/
//						Methods.toastAndLog(
//								actv, 
////								"Data deleted => file id = " + String.valueOf(ti.getFileId()), 
//								"Data deleted => file name = " + ti.getFile_name(),
//								2000);
//
//						ThumbnailActivity.tiList.remove(position);
//						
//						// Log
//						Log.d("DialogOnItemClickListener.java"
//								+ "["
//								+ Thread.currentThread().getStackTrace()[2]
//										.getLineNumber() + "]", "Data removed from tiList => " + ti.getFile_name());
//						
//						/*----------------------------
//						 * 4-3. Notify adapter
//							----------------------------*/
//						ThumbnailActivity.aAdapter.notifyDataSetChanged();
//						
//					}//if (result == true)
//					
////					/*----------------------------
////					 * 5. Notify adapter
////						----------------------------*/
////					ThumbnailActivity.aAdapter.notifyDataSetChanged();
////					
//					/*----------------------------
//					 * 5. Dismiss dialog
//						----------------------------*/
//					dlg.dismiss();
					
				}//if (opName.equals(actv.getString(R.string.dlg_item_menu_item_delete)))
				
				break;
				
			case dlg_memo_patterns://------------------------------------------------
				
				String word = (String) parent.getItemAtPosition(position);
//				Cursor c = (Cursor) parent.getItemAtPosition(position);
				
				dlg2.dismiss();
				
//				// Log
//				Log.d("DialogOnItemClickListener.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "c.getCount() => " + c.getCount());
//				
//				Log.d("DialogOnItemClickListener.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "c.getColumnCount() => " + c.getColumnCount());
//
//				Log.d("DialogOnItemClickListener.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "id => " + id);

				EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
				
				String text = et.getText().toString();
				
				text += word;
				
				et.setText(text);
				
				et.setSelection(et.getText().toString().length());
				
				break;

			default://------------------------------------------------------------------
				break;
			}//switch (dlgTag)
			
		} else {//if (dlgTag != null)
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Unknown tag");
			
		}
		
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void parentTagIsDialogTag(AdapterView<?> parent, int position) {
//		if ((DialogTags) parent.getTag() != null) {
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "parent.getTag() => " + parent.getTag());
			
			switch ((DialogTags)parent.getTag()) {
			case dlg_add_memos_gv://----------------------------------------------
				
				String word = (String) parent.getItemAtPosition(position);
				
				EditText et = (EditText) dlg.findViewById(R.id.dlg_add_memos_et_content);
				
				String content = et.getText().toString();
				
				content += word + " ";
				
				et.setText(content);
				
				et.setSelection(et.getText().toString().length());
				
//				// debug
//				Toast.makeText(actv, word, 2000).show();
				
				break;
				
			}//switch (parent.getTag())
			
			
//		} else {//if ((DialogTags) parent.getTag() != null)
//			
//			// Log
//			Log.d("DialogOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "parent.getTag() => null");
//			
//		}//if ((DialogTags) parent.getTag() != null)
		
	}//private void parentTagIsDialogTag(AdapterView<?> parent, int position)
}
