package com.baiyyy.byhjl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by huangjinlong on 2015/12/24.
 */
public class GestureDetectorActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private GestureDetector detector;
    private ViewFlipper viewfilpper;
    private Animation[] animations = new Animation[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        viewfilpper = (ViewFlipper) findViewById(R.id.viewfilpper);
        viewfilpper.addView(addImageView(R.drawable.ic_launcher));
        viewfilpper.addView(addImageView(R.drawable.show_image_other));
        viewfilpper.addView(addImageView(R.drawable.show_image_other2));
        detector = new GestureDetector(this);
        animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
        animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
        animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
        animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);
    }

    private View addImageView(int ic_launcher) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(ic_launcher);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return imageView;
    }


    // 将Activity上的触碰事件交给GestureDetector来处理

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // 如果第一个触点的x坐标大于第二个触点的x坐标，从右向左滑动
        if (e1.getX() - e2.getX() > 50){
            viewfilpper.setInAnimation(animations[0]);
            viewfilpper.setOutAnimation(animations[1]);
            viewfilpper.showPrevious();
            return true;
        } else if (e2.getX() - e1.getX() > 50){
            viewfilpper.setInAnimation(animations[2]);
            viewfilpper.setOutAnimation(animations[3]);
            viewfilpper.showNext();
            return true;
        }
        return false;
    }
}
