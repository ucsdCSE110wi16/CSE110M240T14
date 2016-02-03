package com.visiondq.www.toiletwall;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawView;

    private ImageButton currPaint, drawBtn, eraseBtn, redBtn, yellowBtn;
    private ImageButton darkblueBtn, greenBtn, grayBtn,orangeBtn;

    boolean colorPalletisOpen = false;


    public void paintClicked(View view){
        //use chosen color
        if(view!=currPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            currPaint = imgView;
        }
    }


    public void changeColor(View view){

        if(colorPalletisOpen == false) {
            yellowBtn = (ImageButton)findViewById(R.id.yellowColorButton);
            yellowBtn.setVisibility(View.VISIBLE);
            redBtn = (ImageButton)findViewById(R.id.redColorButton);
            redBtn.setVisibility(View.VISIBLE);
            greenBtn = (ImageButton)findViewById(R.id.greenColorButton);
            greenBtn.setVisibility(View.VISIBLE);
            grayBtn = (ImageButton)findViewById(R.id.greyColorButton);
            grayBtn.setVisibility(View.VISIBLE);
            darkblueBtn = (ImageButton)findViewById(R.id.darkblueColorButton);
            darkblueBtn.setVisibility(View.VISIBLE);
            orangeBtn = (ImageButton)findViewById(R.id.orangeColorButton);
            orangeBtn.setVisibility(View.VISIBLE);
            colorPalletisOpen = true;
            //LinearLayout layout = (LinearLayout) findViewById(R.id.colorPalleteLayout);
            //layout.setVisibility(View.VISIBLE);
        }else{
            yellowBtn = (ImageButton)findViewById(R.id.yellowColorButton);
            yellowBtn.setVisibility(View.INVISIBLE);
            redBtn = (ImageButton)findViewById(R.id.redColorButton);
            redBtn.setVisibility(View.INVISIBLE);
            greenBtn = (ImageButton)findViewById(R.id.greenColorButton);
            greenBtn.setVisibility(View.INVISIBLE);
            grayBtn = (ImageButton)findViewById(R.id.greyColorButton);
            grayBtn.setVisibility(View.INVISIBLE);
            darkblueBtn = (ImageButton)findViewById(R.id.darkblueColorButton);
            darkblueBtn.setVisibility(View.INVISIBLE);
            orangeBtn = (ImageButton)findViewById(R.id.orangeColorButton);
            orangeBtn.setVisibility(View.INVISIBLE);
            colorPalletisOpen = false;
            //
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DrawingView)findViewById(R.id.drawing);
        currPaint = (ImageButton)findViewById(R.id.darkblueColorButton);
        eraseBtn = (ImageButton)findViewById(R.id.eraser);

       // eraseBtn.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
