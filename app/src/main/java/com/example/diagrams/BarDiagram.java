package com.example.diagrams;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BarDiagram extends View {
    Paint barPaint;
    Paint textPaint;
    private String xData[]={"Argentina","Bolivia","Brazil","Canada",
            "Chile","Colombia","Ecuador","Guyana","Peru","China"};
    private float data[]={20.7f,46.6f,28.6f,14.5f,23.4f,27.4f,32.9f,28.3f,54.9f,10.1f};

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

    protected float findMaxValue(){
        float maxValue=0;
        for(int i = 0; i < data.length; i++)
            maxValue = (maxValue < data[i]) ? (float) data[i] : maxValue;
        return maxValue;
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int paddingW = 30;
        int paddingH = 120;
        float HEIGHT = getMeasuredHeight()-paddingH;
        //eje Y
        float maxValue=findMaxValue();
        int limite = (((int)(maxValue/10)+1)*10);
        Log.d("LINE", "Limite: " + String.valueOf(limite));
        int escala = 5;
        int nLineas = limite/escala + 1;
        float division = HEIGHT/nLineas;

        //Lines
        for(int j=nLineas; j>0; j--){
            canvas.drawLine(paddingW, division*j,
                    (getMeasuredWidth() - paddingW)+ 20,
                    division*j, textPaint);
        }

        //DIBUJAR LOS INDICES
        for(int i=0; i<nLineas; i++){
            String medida = String.valueOf(i*escala);
            canvas.drawText(medida,20, HEIGHT - (i*division) + 5, textPaint);
        }

        //draw bar
        int paddingBars = 30;
        float barWidth = (getMeasuredWidth()-paddingBars)/data.length;
        for (int i=0; i<data.length;i++){
            canvas.drawRect(paddingBars*2 + (barWidth*i),
                    division * ((limite-data[i])/escala+1),
                    paddingBars + barWidth * (i+1),
                    HEIGHT, barPaint);
            canvas.rotate(-90, paddingBars +(barWidth/2) + (barWidth*i), getMeasuredHeight());
            canvas.drawText(xData[i], paddingBars + (barWidth/2) + (barWidth*i), getMeasuredHeight()+paddingBars/2, textPaint);
            canvas.rotate(90, paddingBars + (barWidth/2) + (barWidth*i), getMeasuredHeight());
        }
    }

    private void init() {
        barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setColor(Color.GREEN);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.YELLOW);
        textPaint.setTextSize(25);
    }
}
