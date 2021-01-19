package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class Bola extends View {

    Paint pincel = new Paint();
    Paint p2=new Paint();
    Paint p3=new Paint();
    int size=100;
    int border=15;
    float axis=0,axisy=0,axisz=0;
    String x,y,z;
    int goles;
    int side=25;
    Drawable bolaEspa,campo;


    public Bola(Context interfaces) {
        super(interfaces);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        campo = getResources().getDrawable(R.drawable.campo);
        campo.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        campo.draw(canvas);

        super.onDraw(canvas);
        pincel.setStrokeWidth(25);
        pincel.setColor(Color.BLUE);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),pincel);

        canvas.drawLine(canvas.getWidth()/2-(size+100),canvas.getHeight(),canvas.getWidth()/2+(size+100),canvas.getHeight(),p2);
        p2.setStyle(Paint.Style.STROKE);
        p2.setStrokeWidth(25);
        p2.setColor(Color.RED);
        p2.setStyle(Paint.Style.STROKE);


        bolaEspa = getResources().getDrawable(R.drawable.es);
        bolaEspa.setBounds((int)axis-size,(int)axisy-size,(int)axis+size,(int)axisy+size);
        bolaEspa.draw(canvas);

        canvas.drawText("Coordenada X: "+x,550,100,p3);
        canvas.drawText("Coordenada Y: "+y,550,200,p3);
        canvas.drawText("Coordenada Z: "+z,550,300,p3);
        canvas.drawText("Goles: "+goles,550,400,p3);
        p3.setTextSize(50);
        p3.setColor(Color.YELLOW);



    }
}

