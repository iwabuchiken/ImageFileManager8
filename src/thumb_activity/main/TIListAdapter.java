package thumb_activity.main;

import java.util.List;

import ifm8.lib.ButtonOnClickListener;
import ifm8.lib.Methods;
import ifm8.main.*;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class TIListAdapter extends ArrayAdapter<ThumbnailItem> {

	/*--------------------------------------------------------
	 * Class fields
		--------------------------------------------------------*/
	// Context
	Context con;

	// Inflater
	LayoutInflater inflater;

	//
	Methods.MoveMode moveMode = null;
//	Methods.MoveMode moveMode = Methods.MoveMode.OFF;
	
	/*--------------------------------------------------------
	 * Constructor
		--------------------------------------------------------*/
	//
	public TIListAdapter(Context con, int resourceId, List<ThumbnailItem> items) {
		// Super
		super(con, resourceId, items);

		// Context
		this.con = con;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		

	}//public TIListAdapter(Context con, int resourceId, List<ThumbnailItem> items)


	public TIListAdapter(Context con, int resourceId, List<ThumbnailItem> items, 
											Methods.MoveMode moveMode) {
		// Super
		super(con, resourceId, items);

		// Context
		this.con = con;
		this.moveMode = moveMode;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		

	}//public TIListAdapter(Context con, int resourceId, List<ThumbnailItem> items, Methods.MoveMode moveMode)

//	public TIListAdapter(Context con, int resourceId, List<ThumbnailItem> items, 
//			Methods.MoveMode moveMode, Activity actv) {
//		// Super
//		super(con, resourceId, items);
//		
//		// Context
//		this.con = con;
//		this.moveMode = moveMode;
//		
//		// Inflater
//		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	}//public TIListAdapter(Context con, int resourceId, List<ThumbnailItem> items, Methods.MoveMode moveMode)

	/*--------------------------------------------------------

	 * Methods
		--------------------------------------------------------*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	/*----------------------------
		 * Steps
		 * 0. View
		 * 0-2. Move mode is ?
		 * 1. null or OFF
		 * 2. ON
		 * 3. Return view
			----------------------------*/
		
    	// View to return
    	View v;

//    	// Log
//    	if (moveMode != null) {
//    		Log.d("TIListAdapter.java" + "["
//    				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//    				+ "]", "moveMode.name() => " + moveMode.name());
//		} else {//if (moveMode != null)
//    		Log.d("TIListAdapter.java" + "["
//    				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//    				+ "]", "moveMode => null");
//		}//if (moveMode != null)
    	
    	/*----------------------------
		 * 0-2. Move mode is ?
			----------------------------*/
    	/*----------------------------
		 * 1. null or OFF
			----------------------------*/
//		if (moveMode != null || moveMode == Methods.MoveMode.OFF) { //--------------------
    	if (moveMode == null || moveMode == Methods.MoveMode.OFF) { //--------------------
//    	if (moveMode != null || 
//    			moveMode == null || 
//    			moveMode == Methods.MoveMode.OFF) { //--------------------
    		/*----------------------------
			 * Steps
			 * 1.1. Set layout
			 * 1.2. Get view
			 * 1.3. Get item
			 * 1.4. Get bitmap
			 * 1.5. Get memo, or, file name
				----------------------------*/
	    	/*----------------------------
			 * 1.1. Set layout
				----------------------------*/
	    	if (convertView != null) {
				v = convertView;
			} else {//if (convertView != null)
	//			v = inflater.inflate(R.layout.list_row, null);
				v = inflater.inflate(R.layout.list_row, null);
			}//if (convertView != null)
	
	    	/*----------------------------
			 * 1.2. Get view
				----------------------------*/
	    	ImageView iv = (ImageView) v.findViewById(R.id.iv_thumbnail);
	
	    	/*----------------------------
			 * 1.3. Get item
				----------------------------*/
	    	ThumbnailItem ti = getItem(position);
	
	//    	// Log
	//		Log.d("TIListAdapter.java" + "["
	//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
	//				+ "]", "position => " + position);
			
	    	
	    	/*----------------------------
			 * 1.4. Get bitmap
				----------------------------*/
	    	// ContentResolver
	    	ContentResolver cr = con.getContentResolver();
	    	
	    	// Bitmap
	    	Bitmap bmp = 
					MediaStore.Images.Thumbnails.getThumbnail(
								cr, ti.getFileId(), MediaStore.Images.Thumbnails.MICRO_KIND, null);
	    	
	    	// Set bitmap
	    	iv.setImageBitmap(bmp);
	    	
	    	/*----------------------------
			 * 1.5. Get memo, or, file name
				----------------------------*/
			TextView tv = (TextView) v.findViewById(R.id.textView1);
			
			tv.setText(ti.getFile_name());

			// move_mode
//			if (ThumbnailActivity.move_mode == true &&
//					ThumbnailActivity.checkedPositions.contains((Integer) position)) {
//			if (ThumbnailActivity.move_mode == true &&
//					ThumbnailActivity.checkedPositions.contains((Integer) position)) {
//				
//				tv.setBackgroundColor(Color.BLUE);
//				
//			}//if (ThumbnailActivity.move_mode == true)

			
			
			TextView tv_memo = (TextView) v.findViewById(R.id.textView2);
			
			String memo = ti.getMemo();
			
	//		// Log
	//		Log.d("TIListAdapter.java" + "["
	//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
	//				+ "]", "ti.getMemo()(pre-if) => " + ti.getMemo() + " / " + ti.getFile_name());
			
	//		tv_memo.setText(ti.getMemo());
	//		
	//		// Log
	//		Log.d("TIListAdapter.java" + "["
	//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
	//				+ "]", "memo => " + memo + " / " + ti.getFile_name());
			
			
			if (memo != null) {
				tv_memo.setText(memo);
				
	//			// Log
	//			Log.d("TIListAdapter.java" + "["
	//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
	//					+ "]", "memo => " + memo + " / " + ti.getFile_name());
				
				
			} else {//if (memo)
	//			tv_memo.setText("NULL");
				tv_memo.setText("");
			}//if (memo)
			
	//		memo = null;
		} else {//if (moveMode != null) -----------------------------------------------------------------
			/*----------------------------
			 * 2. ON
				----------------------------*/
			/*----------------------------
			 * 2.1. Set layout
			 * 2.2. Get view
			 * 2.3. Get item
			 * 2.4. Get bitmap
			 * 2.5. Get memo, or, file name
			 * 2.6. CheckedBox => Set listener
				----------------------------*/
	    	/*----------------------------
			 * 2.1. Set layout
				----------------------------*/
	    	if (convertView != null) {
				v = convertView;
			} else {//if (convertView != null)
//				v = inflater.inflate(R.layout.list_row, null);
				v = inflater.inflate(R.layout.list_row_checked_box, null);

			}//if (convertView != null)
	
	    	/*----------------------------
			 * 2.2. Get view
				----------------------------*/
	    	ImageView iv = (ImageView) v.findViewById(R.id.list_row_checked_box_iv_thumbnail);
	
	    	/*----------------------------
			 * 2.3. Get item
				----------------------------*/
	    	ThumbnailItem ti = getItem(position);
	
	    	/*----------------------------
			 * 2.4. Get bitmap
				----------------------------*/
	    	// ContentResolver
	    	ContentResolver cr = con.getContentResolver();
	    	
	    	// Bitmap
	    	Bitmap bmp = 
					MediaStore.Images.Thumbnails.getThumbnail(
								cr, ti.getFileId(), MediaStore.Images.Thumbnails.MICRO_KIND, null);
	    	
	    	// Set bitmap
	    	iv.setImageBitmap(bmp);
	    	
	    	/*----------------------------
			 * 2.5. Get memo, or, file name
				----------------------------*/
			TextView tv = (TextView) v.findViewById(R.id.list_row_checked_box_textView1);
			
			tv.setText(ti.getFile_name());
			
			// move_mode
			if (ThumbnailActivity.move_mode == true &&
					ThumbnailActivity.checkedPositions.contains((Integer) position)) {
				
				tv.setBackgroundColor(Color.BLUE);
				
			} else {//if (ThumbnailActivity.move_mode == true)
				
				tv.setBackgroundColor(Color.BLACK);
			}
			
			
			TextView tv_memo = (TextView) v.findViewById(R.id.list_row_checked_box_textView2);
			
			String memo = ti.getMemo();
			
			if (memo != null) {
				tv_memo.setText(memo);
				
			} else {//if (memo)
	//			tv_memo.setText("NULL");
				tv_memo.setText("");
			}//if (memo)
			
			/*----------------------------
			 * 2.6. CheckedBox => Set listener
				----------------------------*/
			CheckBox cb = (CheckBox) v.findViewById(R.id.list_row_checked_box_checkBox1);
			
			cb.setTag(Methods.ButtonTags.tilist_cb);
			
			if (ThumbnailActivity.checkedPositions.contains((Integer) position)) {
				
				cb.setChecked(true);
				
				// Log
				Log.d("TIListAdapter.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", 
						"cb => true" + "(position => " + ThumbnailActivity.checkedPositions.size() + ")");
				
				
			} else {//if (ThumbnailActivity.checkedPositions.contains((Integer) position)
				
				cb.setChecked(false);
				
			}//if (ThumbnailActivity.checkedPositions.contains((Integer) position)
			
			cb.setOnClickListener(new ButtonOnClickListener((Activity) con, position));
			
		}////if (moveMode != null) -----------------------------------------------------------------
    	
		
    	/*----------------------------
		 * 3. Return view
			----------------------------*/
		//
    	return v;
    	
    }//public View getView(int position, View convertView, ViewGroup parent)

}//public class TIListAdapter extends ArrayAdapter<ThumbnailItem>
