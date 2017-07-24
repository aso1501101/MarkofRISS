package jp.ac.asojuku.jousenb.markofriss;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import android.support.v7.widget.AppCompatImageView;

/**
 * Created by kotaro on 2017/07/21.
 */

public class CustomImageView extends AppCompatImageView{
    private SQLiteDatabase sqlDB;
    DBManager2 dbm;
    String count = "";


    private Matrix matrix = new Matrix();
    private ScaleGestureDetector scaleGestureDetector;
    private final float SCALE_MAX = 3.0f;
    private final float SCALE_MIN = 1.0f;
    private final float PINCH_SENSITIVITY = 10.0f;

    public CustomImageView(Context context) {
        super(context);
        init(context);
    }

    public  CustomImageView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private  void init(Context context) {
       /* Intent intent = getIntent();
        count = intent.getStringExtra("count");
        final String year = intent.getStringExtra("year");
        final String season = intent.getStringExtra("season");*/

        //String path = dbm.selectcount(sqlDB,count,year,season);
        //setImageResource(R.drawable.s29_01);
        setScaleType(ScaleType.MATRIX);
        scaleGestureDetector = new ScaleGestureDetector(context, simpleOnScaleGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setImageMatrix(matrix);
        scaleGestureDetector.onTouchEvent(event);
        return scaleGestureDetector.onTouchEvent(event);
    }

    private ScaleGestureDetector.SimpleOnScaleGestureListener simpleOnScaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        float focusX;
        float focusY;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = 1.0f;
            float previousScale = getMatrixValue(Matrix.MSCALE_Y);

            if (detector.getScaleFactor() >= 1.0f) {
                scaleFactor = 1 + (detector.getScaleFactor() - 1) / (previousScale * PINCH_SENSITIVITY);
            } else {
                scaleFactor = 1 - (1 - detector.getScaleFactor()) / (previousScale * PINCH_SENSITIVITY);
            }

            float scale = scaleFactor * previousScale;

            if (scale < SCALE_MIN) {
                return  false;
            }

            if (scale > SCALE_MAX) {
                return  false;
            }

            matrix.postScale(scaleFactor, scaleFactor,focusX, focusY);

            invalidate();

            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            focusX = detector.getFocusX();
            focusY = detector.getFocusY();
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
        }
    };

    private float getMatrixValue(int index) {
        if (matrix == null) {
            matrix = getImageMatrix();
        }

        float[] values = new float[9];
        matrix.getValues(values);

        float value = values[index];
        return value;
    }

    private final GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
            //viewの縦横長
            float imageViewWidth = getWidth();
            float imageViewHeight = getHeight();
            //画像の縦横長
            float imageWidth = getImageWidth();
            float imageHeight = getImageHeight();
            //画像の左辺、右辺のx座標
            float leftSideX = getMatrixValue(Matrix.MTRANS_X);
            float rightSideX = leftSideX + imageWidth;
            //画像の上辺、底辺のy座標
            float topY = getMatrixValue(Matrix.MTRANS_Y);
            float bottomY = topY + imageHeight;

            if (imageViewWidth >= imageWidth && imageViewHeight >= imageHeight) {
                return false;
            }
            //指の動きに追随してほしいため符号を反転
            float x = -distanceX;
            float y = -distanceY;

            if (imageViewWidth > imageWidth) {
                x = 0;
            } else {
                if (leftSideX > 0 && x > 0) {
                    x = -leftSideX;
                } else if(rightSideX < imageViewWidth && x < 0) {
                    x = imageViewWidth - rightSideX;
                }
            }

            if (imageViewHeight > imageHeight) {
                y = 0;
            } else {
                if (topY > 0 && y > 0) {
                    y = -topY;
                } else if (bottomY < imageViewHeight && y < 0) {
                    y = imageViewHeight - bottomY;
                }
            }
            //Matrixを操作
            matrix.postTranslate(x,y);
            //再描画
            invalidate();
            return super.onScroll(event1, event2, distanceX, distanceY);
        }
    };

    private float getImageWidth() {
        return (getDrawable().getIntrinsicWidth()) * getMatrixValue(Matrix.MSCALE_X);
    }

    private float getImageHeight() {
        return  (getDrawable().getIntrinsicHeight()) * getMatrixValue(Matrix.MSCALE_Y);
    }
}
