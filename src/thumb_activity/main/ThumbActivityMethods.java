package thumb_activity.main;

import ifm7.main.R;
import helper.main.DBUtils;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;

public class ThumbActivityMethods {

	public static void setThumbList(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Get db
		 * 2. Query

		 * 3. Add ThumbnailItem to the list
		 * 4. Close db

		 * 5. Set list to the adapter
			----------------------------*/
		/*----------------------------
		 * 1. Get db
			----------------------------*/
    	// DBUtils
    	DBUtils dbu = new DBUtils(actv, "IFM7");
    	
    	// DB
    	SQLiteDatabase db = dbu.getReadableDatabase();
    	
    	/*----------------------------
		 * 2. Query
			----------------------------*/
    	//
//    	String q = "SELECT * FROM image_root where _id > 0 AND _id =< 10 ORDER BY _id DESC"; //=> Error
//    	String q = "SELECT * FROM image_root where _id > 0 AND _id <= 10 ORDER BY _id DESC";
//    	String q = "SELECT * FROM image_root where _id > 0 AND _id <= 10 ORDER BY _id ASC";
    	String q = "SELECT * FROM image_root ORDER BY _id ASC";

    	//
    	Cursor c = db.rawQuery(q, null);
    	
		//
		c.moveToFirst();
		
		/*----------------------------
		 * 3. Add ThumbnailItem to the list
			----------------------------*/
		// Clear
		ThumbnailActivity.tiList.clear();
		
		// Add
		for (int i = 0; i < c.getCount(); i++) {
			// Instance
			ThumbnailItem ti = new ThumbnailItem(
							c.getLong(1),	// file_id
							c.getString(2),	// file_path
							c.getLong(3),	// date_added
							c.getLong(4)		// date_modified
					);
			
			// Add to the list
			ThumbnailActivity.tiList.add(ti);
			
			// Move cursor
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		// Log
		Log.d("ThumbActivityMethods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tiList.size() => " + ThumbnailActivity.tiList.size());

		/*----------------------------
		 * 4. Close db
			----------------------------*/
		db.close();

		/*----------------------------
		 * 5. Set list to the adapter
			----------------------------*/
		//
		ThumbnailActivity.aAdapter = 
				new TIListAdapter(
						actv, 
						ifm7.main.R.layout.thumb_activity, 
						ThumbnailActivity.tiList);

//		/*----------------------------
//		 * 3-3. Set adapter to the list view
//			----------------------------*/
//		//
////		ListView lv = (ListView) findViewById(R.id.th);
//		if (actv instanceof ListActivity) {
//			//
//			((ListActivity) actv).setListActivity(ThumbnailActivity.aAdapter);
//			
//		} else {//if (actv instanceof ListActivity)
//			
//		}//if (actv instanceof ListActivity)
//		
		
		
//    	// Log
//		Log.d("ThumbnailActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount() => " + c.getCount());
//		
//		// Log
//		Log.d("ThumbnailActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getString(0) => " + c.getString(0));
//
//		Log.d("ThumbnailActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getString(1) => " + c.getString(1));
//		
//		Log.d("ThumbnailActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getString(2) => " + c.getString(2));
//
//		Log.d("ThumbnailActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getString(3) => " + c.getString(3));

	}//public static void setThumbList(Activity actv)
	
}//public class ThumbActivityMethods
