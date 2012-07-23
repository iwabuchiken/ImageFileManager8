package ifm8.main;

import java.util.List;

import ifm8.lib.DBUtils;
import ifm8.lib.Methods;
import android.app.Activity;
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
		 * 2. Add ThumbnailItem to tiLIst
		 * 3. Close db
			----------------------------*/
		/*----------------------------
		 * 1. Get table names list
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, ImageFileManager8Activity.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		List<String> tableNames = Methods.getTableList(rdb);
		
		/*----------------------------
		 * 2. Add ThumbnailItem to tiLIst
			----------------------------*/
		String targetTable = tableNames.get(0);
		
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
				
				// Log
				Log.d("SearchTask.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "sw[0]: string => " + string);
				
				
				if (memo.matches(".*" + string + ".*")) {
					
					// Log
					Log.d("SearchTask.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "memo => " + memo);
					
					break;
					
				}//if (memo.matches(".*" + ))
				
			}//for (String string : sw[0])
			
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		/*----------------------------
		 * 3. Close db
			----------------------------*/
		rdb.close();
		
		return "Search done";
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO 自動生成されたメソッド・スタブ
		super.onPostExecute(result);

		// debug
		Toast.makeText(actv, result, 2000).show();
		
	}//protected void onPostExecute(String result)
	
	
}//public class SearchTask
