package ifm8.dbadmin;

import ifm8.lib.ButtonOnClickListener;
import ifm8.lib.ButtonOnTouchListener;
import ifm8.lib.Methods;
import ifm8.main.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class DBAdminActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		 * 3. Listeners
		 * 		1. OnClick
		 * 		2. OnTouch
		 * 4. Disenable => "Create table"
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.db_manager);

		//
		setListeners();
		
	}//public void onCreate(Bundle savedInstanceState)

	private void setListeners() {
    	/*----------------------------
		 * 1. Buttons
			----------------------------*/
		//
    	Button bt_create = (Button) findViewById(R.id.db_manager_btn_create_table);
    	Button bt_drop = (Button) findViewById(R.id.db_manager_btn_drop_table);
    	
    	/*----------------------------
		 * 2. Tags
			----------------------------*/
    	bt_create.setTag(Methods.ButtonTags.db_manager_activity_create_table);
    	bt_drop.setTag(Methods.ButtonTags.db_manager_activity_drop_table);
    	
    	/*----------------------------
		 * 3. Listeners
		 * 		1. OnClick
		 * 		2. OnTouch
			----------------------------*/
    	/*----------------------------
		 * 3.1. OnClick
			----------------------------*/
    	//
    	bt_create.setOnClickListener(new ButtonOnClickListener(this));
    	bt_drop.setOnClickListener(new ButtonOnClickListener(this));
    	
    	/*----------------------------
		 * 3.2. OnTouch
			----------------------------*/
    	//
    	bt_create.setOnTouchListener(new ButtonOnTouchListener(this));
    	bt_drop.setOnTouchListener(new ButtonOnTouchListener(this));
    	
    	/*----------------------------
		 * 4. Disenable => "Create table"
			----------------------------*/
    	bt_create.setEnabled(false);
    	
	}//private void setListeners()
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}
	
}
