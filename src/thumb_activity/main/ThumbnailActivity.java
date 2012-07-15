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

//        initial_setup();
    }//void onCreate(Bundle savedInstanceState)

}//public class ThumbnailActivity extends ListActivity
