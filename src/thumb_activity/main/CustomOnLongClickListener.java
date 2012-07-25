package thumb_activity.main;

import ifm8.lib.Methods;
import ifm8.lib.Methods.ItemTags;
import android.app.Activity;
import android.view.View;
import android.view.View.OnLongClickListener;

public class CustomOnLongClickListener implements OnLongClickListener {

	//
	Activity actv;
	
	//
	int position;
	
	//
	ItemTags itemTag;
	
	public CustomOnLongClickListener(Activity actv, int position) {
		
		this.actv = actv;
		this.position = position;
		
	}
	
	public CustomOnLongClickListener(Activity actv, int position,
											ItemTags tag) {
		
		this.actv = actv;
		this.position = position;
		this.itemTag = tag;
		
	}//public CustomOnLongClickListener

	@Override
	public boolean onLongClick(View v) {
		
		if (itemTag != null && itemTag instanceof Methods.ItemTags) {
			
			switch (itemTag) {
			case tilist_checkbox:
				/*----------------------------
				 * Steps
				 * 1. If the list contains the position
				 * 2. If not
					----------------------------*/
				/*----------------------------
				 * 1. If the list contains the position
					----------------------------*/
				if (ThumbnailActivity.checkedPositions.contains((Integer) position)) {

					ThumbnailActivity.checkedPositions.clear();
					
					ThumbnailActivity.aAdapter.notifyDataSetChanged();
				/*----------------------------
				 * 2. If not
					----------------------------*/
				} else {//if (ThumbnailActivity.checkedPositions.contains((Integer) position))
					
					ThumbnailActivity.checkedPositions.clear();
					
					for (int i = 0; i < ThumbnailActivity.tiList.size(); i++) {
						
						ThumbnailActivity.checkedPositions.add((Integer) i);
						
					}//for (int i = 0; i < ThumbnailActivity.tiList.size(); i++)
					
					ThumbnailActivity.aAdapter.notifyDataSetChanged();
					
				}//if (ThumbnailActivity.checkedPositions.contains((Integer) position))
				
				
				break;// case tilist_checkbox
			
			}//switch (tag)
			
		}//if (tag != null)
		
		return true;
	}//public boolean onLongClick(View arg0)

}//public class CustomOnLongClickListener implements OnLongClickListener
