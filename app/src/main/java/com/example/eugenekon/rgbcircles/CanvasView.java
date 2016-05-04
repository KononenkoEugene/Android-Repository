package com.example.eugenekon.rgbcircles;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Toast;

/**
 * Created by Eugenekon on 07.04.2016.
 */
public class CanvasView extends View implements ICanvasView{
    private static int width;
    private static int height;
    private GameManager gameManager;
    private Paint paint;
    private Paint scorepaint;
    private Canvas canvas;
    private Toast toast;
    private int points = 0;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        initPaint();
        gameManager = new GameManager(this, width, height);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        scorepaint = new Paint();
        scorepaint.setTextSize(3);
        scorepaint.setColor(Color.BLACK);




    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }
    public  void updateCounter() {
        points = points + 10; // whatever u want
        canvas.drawText(String.valueOf(points), 350, 50, scorepaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();
        scorepaint.setTextSize(3);



    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            gameManager.onTouchEvent(x, y);
        }
        invalidate();
        return true;
    }

    // todo ДЗ
//    public static int recalculateRadius(int radius) {
//        return radius * 768 / width < height ? width : height;
//    }
}