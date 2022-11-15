package com.example.diagrams;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BarDiagram extends View {
    public double width;
    public double heignt;
    Paint barPaint;
    Paint textPaint;
    private String xData[]={"Argentina","Bolivia","Brazil","Canada",
            "Chile","Colombia","Ecuador","Guyana"};
    private double data[]={20.7,46.6,28.6,14.5,23.4,27.4,32.9,28.3};
    public BarDiagram(Context context) {
        super(context);
        init();
    }
    public BarDiagram(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public BarDiagram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected double findMaxValue(){
        double maxValue=0;
        for(int i = 0; i < data.length; i++)
            maxValue = (maxValue < data[i]) ? data[i] : maxValue;
        return maxValue;
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int paddingH = 80;
        double HEIGHT = getMeasuredHeight()-paddingH;
        //eje Y
        double maxValue=findMaxValue();
        int limite = (((int)(maxValue/10)+1)*10);
        Log.d("LINE", "Limite: " + String.valueOf(limite));
        Log.d("LINE", "Height: " + String.valueOf(getMeasuredHeight()));
        int escala = 5;
        int nLineas = limite/escala + 1;
        double division = HEIGHT/nLineas;
        Log.d("LINE", "Division: " + String.valueOf(division));
        //draw bar
        int paddingBars = 40;
        double barWidth = (getMeasuredWidth()-paddingBars * 2)/data.length;
        for (int i=0; i<data.length;i++){
            canvas.drawRect((float) (paddingBars*2 + (barWidth*i)),
                    (float) (division * ((limite-data[i])/escala+1)),
                    (float) (paddingBars + barWidth * (i+1)),
                    limite, barPaint);
            canvas.rotate((float) -90, (float) (paddingBars + (barWidth/2) + (barWidth*i)), (float) (HEIGHT + paddingH));
            canvas.drawText(xData[i], (float) (paddingBars + (barWidth/2) + (barWidth*i)), (float) (HEIGHT + paddingH), textPaint);
            canvas.rotate(90F, (float) (paddingBars + (barWidth/2) + (barWidth*i)), (float) (HEIGHT + paddingH));
        }
    }

    private void init() {
        barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setColor(Color.GREEN);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.YELLOW);
    }
}
