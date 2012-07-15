package thumb_activity.main;

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

}//public class TIListAdapter extends ArrayAdapter<ThumbnailItem>
