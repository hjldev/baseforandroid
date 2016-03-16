package com.baiyyy.byhjl.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.baiyyy.byhjl.R;

/**
 * Created by huangjinlong on 2015/12/23.
 */
public class MyView extends View {

    public MyView(Context context, AttributeSet set) {
        super(context, set);
    }
    public MyView(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas代表依附于view的画布
        super.onDraw(canvas);
        // 把整张画布绘制成白色
        canvas.drawColor(Color.WHITE);
        // 定义一个canvas上的画笔
        Paint paint = new Paint();
        // 去锯齿
        paint.setAntiAlias(true);
        // 设置画笔颜色
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        // 绘制圆形
        canvas.drawCircle(40, 40, 30, paint);
        // 绘制正方形
        canvas.drawRect(10, 80, 70, 140, paint);
        // 绘制矩形
        canvas.drawRect(10, 150, 70, 190, paint);
        //绘制圆角矩形
        RectF rectF = new RectF(10, 200, 70, 230);
        canvas.drawRoundRect(rectF, 15, 15, paint);
        // 绘制椭圆
        RectF ovalF = new RectF(10, 240, 70, 270);
        canvas.drawOval(ovalF, paint);
        // 定义一个Path对象，封闭成一个三角形
        Path path1 = new Path();
        path1.moveTo(10, 340);
        path1.lineTo(70, 340);
        path1.lineTo(40, 290);
        path1.close();
        // 根据path进行绘制三角形
        canvas.drawPath(path1, paint);
        // 定义一个Path对象，封闭成一个五角形
        Path path2 = new Path();
        path2.moveTo(26, 360);
        path2.lineTo(54, 360);
        path2.lineTo(70, 392);
        path2.lineTo(40, 420);
        path2.lineTo(10, 392);
        path2.close();
        // 根据path在画布上绘制五角形
        canvas.drawPath(path2, paint);
        /**
         * 设置填充风格后绘制
         */
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        // 绘制圆形
        canvas.drawCircle(120, 40, 30, paint);
        // 绘制正方形
        canvas.drawRect(90, 80, 150, 140, paint);
        // 绘制矩形
        canvas.drawRect(90, 150, 150, 190, paint);
        //绘制圆角矩形
        RectF rectF2 = new RectF(90, 200, 150, 230);
        canvas.drawRoundRect(rectF2, 15, 15, paint);
        // 绘制椭圆
        RectF ovalF2 = new RectF(90, 240, 150, 270);
        canvas.drawOval(ovalF2, paint);
        // 定义一个Path对象，封闭成一个三角形
        Path path3 = new Path();
        path3.moveTo(90, 340);
        path3.lineTo(150, 340);
        path3.lineTo(120, 290);
        path3.close();
        // 根据path进行绘制三角形
        canvas.drawPath(path3, paint);
        // 定义一个Path对象，封闭成一个五角形
        Path path4 = new Path();
        path4.moveTo(106, 360);
        path4.lineTo(134, 360);
        path4.lineTo(150, 392);
        path4.lineTo(120, 420);
        path4.lineTo(90, 392);
        path4.close();
        // 根据path在画布上绘制五角形
        canvas.drawPath(path4, paint);

        /**
         * 设置渐变器后绘制
         */
        Shader shader = new LinearGradient(0,0,40,60,new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW},null,Shader.TileMode.REPEAT);
        paint.setShader(shader);
        paint.setShadowLayer(45,10,10,Color.GRAY);
        // 绘制圆形
        canvas.drawCircle(200, 40, 30, paint);
        // 绘制正方形
        canvas.drawRect(170, 80, 230, 140, paint);
        // 绘制矩形
        canvas.drawRect(170, 150, 230, 190, paint);
        //绘制圆角矩形
        RectF rectF3 = new RectF(170, 200, 230, 230);
        canvas.drawRoundRect(rectF3, 15, 15, paint);
        // 绘制椭圆
        RectF ovalF3 = new RectF(170, 240, 230, 270);
        canvas.drawOval(ovalF3, paint);
        // 定义一个Path对象，封闭成一个三角形
        Path path5 = new Path();
        path5.moveTo(170, 340);
        path5.lineTo(230, 340);
        path5.lineTo(200, 290);
        path5.close();
        // 根据path进行绘制三角形
        canvas.drawPath(path5, paint);
        // 定义一个Path对象，封闭成一个五角形
        Path path6 = new Path();
        path6.moveTo(186, 360);
        path6.lineTo(214, 360);
        path6.lineTo(230, 392);
        path6.lineTo(200, 420);
        path6.lineTo(170, 392);
        path6.close();
        // 根据path在画布上绘制五角形
        canvas.drawPath(path6, paint);
        /**
         * 设置字符大小后绘制
         */
        paint.setTextSize(24);
        paint.setShader(null);
        canvas.drawText(getResources().getString(R.string.circle), 240, 50, paint);
        canvas.drawText(getResources().getString(R.string.square), 240,120,paint);
        canvas.drawText(getResources().getString(R.string.rect), 240,175,paint);
        canvas.drawText(getResources().getString(R.string.round_rect), 240,220,paint);
        canvas.drawText(getResources().getString(R.string.oval), 240,260,paint);
        canvas.drawText(getResources().getString(R.string.triangle), 240,325,paint);
        canvas.drawText(getResources().getString(R.string.pentagon), 240,390,paint);
    }
}
