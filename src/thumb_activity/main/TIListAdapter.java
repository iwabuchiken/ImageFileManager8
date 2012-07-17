package thumb_activity.main;

import java.util.List;

import ifm8.main.*;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

	/*--------------------------------------------------------
	 * Methods
		--------------------------------------------------------*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	/*----------------------------
		 * Steps
		 * 1. Set layout
		 * 2. Get view
		 * 3. Get item
		 * 4. Get bitmap
		 * 5. Get memo, or, file name
		 * 
		 * 9. Return view
			----------------------------*/
		
    	// View to return
    	View v;
    	
    	/*----------------------------
		 * 1. Set layout
			----------------------------*/
    	if (convertView != null) {
			v = convertView;
		} else {//if (convertView != null)
			v = inflater.inflate(R.layout.list_row, null);
		}//if (convertView != null)

    	/*----------------------------
		 * 2. Get view
			----------------------------*/
    	ImageView iv = (ImageView) v.findViewById(R.id.iv_thumbnail);

    	/*----------------------------
		 * 3. Get item
			----------------------------*/
    	ThumbnailItem ti = getItem(position);

//    	// Log
//		Log.d("TIListAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "position => " + position);
		
    	
    	/*----------------------------
		 * 4. Get bitmap
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
		 * 5. Get memo, or, file name
			----------------------------*/
		TextView tv = (TextView) v.findViewById(R.id.textView1);
		
		tv.setText(ti.getFile_name());
		
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
		
    	/*----------------------------
		 * 9. Return view
			----------------------------*/
		//
    	return v;
    	
    }//public View getView(int position, View convertView, ViewGroup parent)

}//public class TIListAdapter extends ArrayAdapter<ThumbnailItem>
