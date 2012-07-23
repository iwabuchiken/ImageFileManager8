package ifm8.main;

import java.util.ArrayList;
import java.util.List;

import thumb_activity.main.ThumbnailActivity;
import thumb_activity.main.ThumbnailItem;

import ifm8.lib.DBUtils;
import ifm8.lib.Methods;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SearchTask extends AsyncTask<String[], Integer, String>{

	//
	Activity actv;
	
	//
	String[] search_words;

	//
	static long[] long_searchedItems;
	
	public SearchTask(Activity actv, String[] search_words) {
		
		this.actv = actv;
		this.search_words = search_words;
		
	}//public SearchTask(Activity actv, String[] search_words)


	public SearchTask(Activity actv) {
		
		this.actv = actv;
		
	}//public SearchTask(Activity actv)


	@Override
	protected String doInBackground(String[]... sw) {
		/*----------------------------
		 * Steps
		 * 1. Get table names list
		 * 2. Construct data			##Add ThumbnailItem to tiLIst
		 * 3. Close db
		 * 4. Set up intent
		 * 5. Return
			----------------------------*/
		/*----------------------------
		 * 1. Get table names list
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		List<String> tableNames = Methods.getTableList(rdb);
		
		/*----------------------------
		 * 2. Construct data
		 * 		1. Table name
		 * 		1-2. Declare => List<Long> searchedItems
		 * 		2. Exec query
		 * 		3. Search
		 * 		4. List<Long> searchedItems => file id
		 * 		
		 * 		5. List<Long> searchedItems => to array
			----------------------------*/
		String targetTable = tableNames.get(0);
		
		List<Long> searchedItems = new ArrayList<Long>();
		
		/*----------------------------
		 * 2.2. Exec query
			----------------------------*/
		String sql = "SELECT * FROM " + targetTable;
		
		// Log
		Log.d("SearchTask.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "targetTable: " + targetTable);
		
		
		Cursor c = rdb.rawQuery(sql, null);
		
		c.moveToFirst();

//		// Log
//		Log.d("SearchTask.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount(): " + c.getCount() + " / " + "c.getColumnCount(): " + c.getColumnCount());
		
		/*----------------------------
		 * 2.3. Search
			----------------------------*/
		for (int i = 0; i < c.getCount(); i++) {
			
			String memo = c.getString(6);
			
			if (memo == null) {

				c.moveToNext();
				
				continue;
				
			}//if (memo == null)
			
//			// Log
//			Log.d("SearchTask.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "memo => " + memo);
			
			
			for (String string : sw[0]) {
				
//				// Log
//				Log.d("SearchTask.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "sw[0]: string => " + string);
				
				
				if (memo.matches(".*" + string + ".*")) {
					
					// Log
					Log.d("SearchTask.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "memo => " + memo);
					
//					ThumbnailItem = Methods.convertCursorToThumbnailItem(c);
//					ThumbnailItem ti = Methods.convertCursorToThumbnailItem(c);
//					ThumbnailActivity.tiList.add(Methods.convertCursorToThumbnailItem(c));
				
					/*----------------------------
					 * 2.4. List<Long> searchedItems => file id
						----------------------------*/
					searchedItems.add(c.getLong(1));
					
					break;
					
				}//if (memo.matches(".*" + ))
				
			}//for (String string : sw[0])
			
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
//		// Log
//		Log.d("SearchTask.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "ThumbnailActivity.tiList.size() => " + ThumbnailActivity.tiList.size());
		
		/*----------------------------
		 * 2.5. List<Long> searchedItems => to array
			----------------------------*/
		int len = searchedItems.size();
		
//		long[] long_searchedItems = new long[len];
		long_searchedItems = new long[len];
		
		for (int i = 0; i < len; i++) {
			
			long_searchedItems[i] = searchedItems.get(i);
			
		}//for (int i = 0; i < len; i++)
		
		/*----------------------------
		 * 3. Close db
			----------------------------*/
		rdb.close();
		
//		/*----------------------------
//		 * 4. Set up intent
//			----------------------------*/
//		// Log
//		Log.d("SearchTask.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "long_searchedItems.length => " + long_searchedItems.length);
//		
//		Log.d("SearchTask.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "long_searchedItems[0] => " + long_searchedItems[0]);
//		
//		Intent i = new Intent();
//		
//		i.setClass(actv, ThumbnailActivity.class);
//		
//		i.putExtra("long_searchedItems", long_searchedItems);
//		
//		actv.startActivity(i);
		
		/*----------------------------
		 * 5. Return
			----------------------------*/
		return "Search done";
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO 自動生成されたメソッド・スタブ
		super.onPostExecute(result);

		// debug
		Toast.makeText(actv, result, 2000).show();

		/*----------------------------
		 * 1. Set up intent
			----------------------------*/
		// Log
		Log.d("SearchTask.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "long_searchedItems.length => " + long_searchedItems.length);
		
		Log.d("SearchTask.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "long_searchedItems[0] => " + long_searchedItems[0]);
		
		Intent i = new Intent();
		
		i.setClass(actv, ThumbnailActivity.class);
		
		i.putExtra("long_searchedItems", long_searchedItems);
		
		/*----------------------------
		 * 2. Start activity
			----------------------------*/
		actv.startActivity(i);

	}//protected void onPostExecute(String result)
	
	
}//public class SearchTask
