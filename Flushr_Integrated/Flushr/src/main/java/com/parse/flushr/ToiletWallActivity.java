package com.parse.flushr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

public class ToiletWallActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawingView drawView;

    private ImageButton currPaint, drawBtn, eraseBtn, saveBtn, backArrowButton;
    private ImageButton darkblueBtn, greenBtn, grayBtn, orangeBtn, redBtn, yellowBtn;

    boolean colorPalletisOpen = false;
    static ParseFile file;
    static Boolean hasWall;

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
            /*String imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(), drawView.getDrawingCache(),
                    UUID.randomUUID().toString()+".png", "drawing");*/
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Bitmap map = drawView.getDrawingCache();
            map.compress(Bitmap.CompressFormat.PNG, 100, output);
            byte[] b = output.toByteArray();

            file = new ParseFile("wall.png", b);
            file.saveInBackground();
            Log.i("ParseFile", "PNG saved on Parse.");

            ParseQuery<ParseObject> q = ParseQuery.getQuery("Restroom");
            q.getInBackground(MapsActivity.markerID, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    object.put("wall", file);
                    object.put("hasWall", new Boolean(true));
                    object.saveInBackground();
                    Log.i("ParseFile", "Picture associated with restroom.");
                }
            });

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


        /*ParseQuery<ParseObject> q = ParseQuery.getQuery("Restroom");
        q.getInBackground(MapsActivity.markerID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                hasWall = object.getBoolean("hasWall");
                if (hasWall) {
                    file = object.getParseFile("wall.png");
                    Log.i("ParseFile", "Previous wall detected.");
                }
                Log.i("ParseFile", "No wall found.");
            }
        });

        if (hasWall){
            try {
                byte[] b = file.getData();
                Bitmap store = BitmapFactory.decodeByteArray(b, 0, b.length);
                store = store.copy(Bitmap.Config.ARGB_8888, true);
                BitmapDrawable drawable_map = new BitmapDrawable(getResources(), store);
                drawView.setBackground(drawable_map);
                Log.i("RestroomWall", "Previous wall loaded.");

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/


        //else
            drawView = (DrawingView)findViewById(R.id.drawing);

        currPaint = (ImageButton)findViewById(R.id.darkblueColorButton);

        eraseBtn = (ImageButton)findViewById(R.id.eraser);
        eraseBtn.setOnClickListener(this);

        saveBtn = (ImageButton)findViewById(R.id.save);
        saveBtn.setOnClickListener(this);

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
