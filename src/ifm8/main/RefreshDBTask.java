package ifm8.main;

import ifm8.lib.Methods;
import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class RefreshDBTask extends AsyncTask<String, Integer, String> {

	//
	Activity actv;
	
	public RefreshDBTask(Activity actv) {
		
		this.actv = actv;
		
	}//public RefreshDBTask(Activity actv)
	
	@Override
	protected String doInBackground(String... params) {
		// TODO 自動生成されたメソッド・スタブ
		
		boolean result = Methods.refreshMainDB((ListActivity) actv);
		
		// Log
		Log.d("RefreshDBTask.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "result => " + result);
		
		
		if (result == true) {
			
			return "DB refreshed";
			
		} else {//if (result == true)

			return "failed";
			
		}//if (result == true)
		
//		return null;
	}//protected String doInBackground(String... params)

	@Override
	protected void onPostExecute(String result) {
		// TODO 自動生成されたメソッド・スタブ
		super.onPostExecute(result);

		// debug
		Toast.makeText(actv, result, 2000).show();
		
	}//protected void onPostExecute(String result)

}
