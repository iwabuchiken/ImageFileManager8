package thumb_activity.main;

import ifm8.main.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ThumbnailActivity extends ListActivity {
	/*----------------------------
	 * Class fields
		----------------------------*/
	//
	static List<ThumbnailItem> tiList;

	//
	static TIListAdapter aAdapter;
	
	/*------------------------------------------------------------------------------------
	 * Methods
	------------------------------------------------------------------------------------*/
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thumb_activity);
        
        try1();
    }//void onCreate(Bundle savedInstanceState)

    public void try1() {
    	//
    	initializeVars();
    	
    	// Up to => Set items to aAdapter
    	ThumbActivityMethods.setThumbList(this);
    	
    	// Set adapter to the list
    	setListAdapter(aAdapter);
    	
//    	//
//    	DBUtils dbu = new DBUtils(this, "IFM7");
//    	
//    	//
//    	SQLiteDatabase db = dbu.getReadableDatabase();
//    	
//    	//
////    	String q = "SELECT * FROM image_root where _id > 0 AND _id =< 10 ORDER BY _id DESC"; //=> Error
////    	String q = "SELECT * FROM image_root where _id > 0 AND _id <= 10 ORDER BY _id DESC";
//    	String q = "SELECT * FROM image_root where _id > 0 AND _id <= 10 ORDER BY _id ASC";
//
//    	//
//    	Cursor c = db.rawQuery(q, null);
//    	
//    	// Log
//		Log.d("ThumbnailActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount() => " + c.getCount());
//		
//		//
//		c.moveToFirst();
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

    }//public void try1()

    public void initializeVars() {
    	//
    	tiList = new ArrayList<ThumbnailItem>();
    	
    	//
    	
    	
    }//public void initializeVars()
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	/*----------------------------
		 * Steps
		 * 1. Get item
			----------------------------*/
		
    	/*----------------------------
		 * Vars
			----------------------------*/
		//
    	boolean boolResult;
		
		/*----------------------------
		 * 1. Get item
			----------------------------*/
    	//
		ThumbnailItem item = (ThumbnailItem) l.getItemAtPosition(position);
    	//

    }//protected void onListItemClick(ListView l, View v, int position, long id)

}//public class ThumbnailActivity extends ListActivity
