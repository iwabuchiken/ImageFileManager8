package ifm8.image_actv;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.widget.ImageView;

public class MyView extends ImageView {

	private Matrix baseMatrix = new Matrix(); // タッチダウン時の画像保存用
	private Matrix imgMatrix = new Matrix(); //　画像変換用
	private PointF po0 = new PointF();   // 移動の開始点

	private static final int TOUCH_NONE   = 0;
	private static final int TOUCH_SINGLE = 1;
	private static final int TOUCH_MULTI  = 2;
	private int touchMode = TOUCH_NONE;
	
	private ScaleGestureDetector gesDetect = null;
	
	/*----------------------------
	 * Constructor
		----------------------------*/
	public MyView(Context context) {
		super(context);
		
//		setImageResource(R.drawable.banana);
		
		setScaleType(ImageView.ScaleType.MATRIX);
		
		gesDetect = new ScaleGestureDetector(context, onScaleGestureListener);
	}

//	public void setImageDrawable(Drawable drawable) {
//		super.setImageDrawable(drawable);
//	}
	
	private final SimpleOnScaleGestureListener onScaleGestureListener = 
				new SimpleOnScaleGestureListener() {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			imgMatrix.set(baseMatrix);
			imgMatrix.postScale(detector.getScaleFactor(), detector.getScaleFactor(),
			detector.getFocusX(), detector.getFocusY());
			return super.onScale(detector);
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			Log.v("touch", "onScaleBegin");
			baseMatrix.set(imgMatrix);
			touchMode = TOUCH_MULTI;
			return super.onScaleBegin(detector);
		}

		@Override
			public void onScaleEnd(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			Log.v("touch", "onScaleEnd");
			touchMode = TOUCH_NONE;
			super.onScaleEnd(detector);
			} 
		};//private final SimpleOnScaleGestureListener onScaleGestureListener
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		// Set up
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int count = event.getPointerCount();
		
		// Log
		Log.d("MyView.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "count => " + count);
		
		// Switch
		switch (action) {
			case MotionEvent.ACTION_DOWN:
//				this.setBackgroundColor(Color.YELLOW);
				
				if(touchMode == TOUCH_NONE && count == 1) {
					Log.v("touch", "DOWN");
					po0.set(event.getX(), event.getY());
					baseMatrix.set(imgMatrix);
					touchMode = TOUCH_SINGLE;
				}
				
			break;
			
			case MotionEvent.ACTION_MOVE:
				if(touchMode == TOUCH_SINGLE) {
					Log.v("touch", "MOVE");
					// 移動処理
					imgMatrix.set(baseMatrix);
					imgMatrix.postTranslate(event.getX() - po0.x, event.getY() - po0.y);
				}
			break;
			
			case MotionEvent.ACTION_UP:
//				this.setBackgroundColor(Color.BLUE);
				
				if(touchMode == TOUCH_SINGLE) {
					Log.v("touch", "UP");
					touchMode = TOUCH_NONE;
				}
				
			break;
		}//switch (action)
		
		if(count >= 2) {
			gesDetect.onTouchEvent(event);
		}
		
		setImageMatrix(imgMatrix);
		
		return true;
	}//public boolean onTouchEvent(MotionEvent event)
	
}//public class MyView extends ImageView
