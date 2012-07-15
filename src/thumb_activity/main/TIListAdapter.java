package thumb_activity.main;

import ifm7.main.R;

import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

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
		 * 5. Get memo
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
    	ThumbnailItem thumbnailItem = getItem(position);

    	/*----------------------------
		 * 4. Get bitmap
			----------------------------*/
    	// ContentResolver
    	ContentResolver cr = con.getContentResolver();
    	
    	// Bitmap
    	Bitmap bmp = 
				MediaStore.Images.Thumbnails.getThumbnail(
							cr, thumbnailItem.getFileId(), MediaStore.Images.Thumbnails.MICRO_KIND, null);
    	
    	// Set bitmap
    	iv.setImageBitmap(bmp);
    	
    	/*----------------------------
		 * 9. Return view
			----------------------------*/
		//
    	return v;
    	
    }//public View getView(int position, View convertView, ViewGroup parent)

}//public class TIListAdapter extends ArrayAdapter<ThumbnailItem>
