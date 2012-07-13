package ifm8.lib;

import ifm8.main.*;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang.StringUtils;

public class Methods {

	public static enum DialogTags {
		// dlg_create_folder.xml
		dlg_create_folder_ok, dlg_create_folder_cancel,

		// dlg_input_empty.xml
		dlg_input_empty_reenter, dlg_input_empty_cancel,
		
		// dlg_confirm_create_folder.xml
		dlg_confirm_create_folder_ok, dlg_confirm_create_folder_cancel,
		
	}//public static enum DialogTags
	
	public static enum ButtonTags {
		
	}
	
	public static void dlg_createFolder(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_create_folder);
		
		// Title
		dlg.setTitle(R.string.dlg_create_folder_title);
		
		//
		
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_create_folder_bt_ok);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_create_folder_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_create_folder_ok);
		btn_cancel.setTag(DialogTags.dlg_create_folder_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		
		//
		dlg.show();
		
	}//public static void dlg_createFolder(Activity actv)

	public static void dlg_isEmpty(Activity actv, Dialog dlg) {
		/*----------------------------
		 * 1. Check if the input exists. If not, show a dialog
		 * 2. If yes, go to Methods.createFolder()
			----------------------------*/
		/*----------------------------
		 * 1. Check if the input exists. If not, show a dialog
			----------------------------*/
		//
		EditText et = (EditText) dlg.findViewById(R.id.dlg_create_folder_et);
		String folderName = et.getText().toString();
		
		//
		if (!folderName.equals("")) {
			/*----------------------------
			 * 2. If yes, go to Methods.createFolder()
				----------------------------*/
//			dlg.dismiss();
			
//			createFolder(actv, folderName);
			dlg_confirm_createFolder(actv, dlg);
			
			return;
			
		}//if (!folderName.equals(""))
		
		/*----------------------------
		 * If not, show a dialog
			----------------------------*/
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_input_empty);
		
		// Title
		dlg2.setTitle(R.string.dlg_input_empty_title);
		
		//
		
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(R.id.dlg_input_empty_btn_reenter);
		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_input_empty_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_input_empty_reenter);
		btn_cancel.setTag(DialogTags.dlg_input_empty_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		//
		dlg2.show();
		
	}//public static void dlg_isEmpty(Activity actv, Dialog dlg)

	private static void dlg_confirm_createFolder(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Set folder name to text view
			----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_confirm_create_folder);
		
		// Title
		dlg2.setTitle(R.string.generic_tv_confirm);
		
		/*----------------------------
		 * 2. Set folder name to text view
			----------------------------*/
		EditText et = (EditText) dlg.findViewById(R.id.dlg_create_folder_et);
		
		TextView tv = (TextView) dlg2.findViewById(R.id.dlg_confirm_create_folder_tv_table_name);
		
		tv.setText(et.getText().toString());
		
		/*----------------------------
		 * 3. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(R.id.dlg_confirm_create_folder_btn_ok);
		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_confirm_create_folder_btn_cancel);
		
		//
		btn_ok.setTag(DialogTags.dlg_confirm_create_folder_ok);
		btn_cancel.setTag(DialogTags.dlg_confirm_create_folder_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		 * 4. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		/*----------------------------
		 * 5. Show dialog
			----------------------------*/
		dlg2.show();
		
	}//private static void dlg_confirm_createFolder

	public static void createFolder(Activity actv, Dialog dlg) {
		// 
		
		
	}//public static void createFolder(Activity actv, Dialog dlg)
	
}//public class Methods
