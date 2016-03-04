package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.content.DialogInterface;
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
import android.provider.MediaStore;
import java.util.UUID;

import java.util.UUID;

public class ToiletWallActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawingView drawView;

    private ImageButton currPaint, drawBtn, eraseBtn, saveBtn, backArrowButton;
    private ImageButton darkblueBtn, greenBtn, grayBtn, orangeBtn, redBtn, yellowBtn;

    boolean colorPalletisOpen = false;


    public void backToMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onClick(View view){

        //respond to clicks
        if(view.getId()==R.id.drawing){
            //draw button clicked
            drawView.setErase(false);
        }
        else if(view.getId()==R.id.eraser){
            //switch to erase - choose size
            drawView.setErase(true);
        }
        else if(view.getId()==R.id.save){

            //save drawing
            drawView.setDrawingCacheEnabled(true);
            String imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(), drawView.getDrawingCache(),
                    UUID.randomUUID().toString()+".png", "drawing");
            drawView.destroyDrawingCache();

        }

    }

    public void paintClicked(View view){
        drawView.setErase(false);
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
        setContentView(R.layout.activity_toilet_wall);

        drawView = (DrawingView)findViewById(R.id.drawing);
        currPaint = (ImageButton)findViewById(R.id.darkblueColorButton);

        eraseBtn = (ImageButton)findViewById(R.id.eraser);
        eraseBtn.setOnClickListener(this);

        saveBtn = (ImageButton)findViewById(R.id.save);
        saveBtn.setOnClickListener(this);

        backArrowButton = (ImageButton)findViewById(R.id.backArrowButton);

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
 */
/*
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
*/
}
