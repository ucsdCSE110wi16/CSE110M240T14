package com.example.qishanwu.newproject;

<<<<<<< HEAD:Flushr App/ToiletWall/Code/ToiletWall/app/src/main/java/com/visiondq/www/toiletwall/MainActivity.java
import android.media.Image;
=======
import android.content.Intent;
>>>>>>> 8d0ad6d276ba96ff2f9e0f53ad6b30ce348485dc:Flushr_App/Create_Rate_Page/app/src/main/java/com/example/qishanwu/newproject/MainActivity.java
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD:Flushr App/ToiletWall/Code/ToiletWall/app/src/main/java/com/visiondq/www/toiletwall/MainActivity.java
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




=======
>>>>>>> 8d0ad6d276ba96ff2f9e0f53ad6b30ce348485dc:Flushr_App/Create_Rate_Page/app/src/main/java/com/example/qishanwu/newproject/MainActivity.java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

<<<<<<< HEAD:Flushr App/ToiletWall/Code/ToiletWall/app/src/main/java/com/visiondq/www/toiletwall/MainActivity.java
        drawView = (DrawingView)findViewById(R.id.drawing);
        currPaint = (ImageButton)findViewById(R.id.darkblueColorButton);
        eraseBtn = (ImageButton)findViewById(R.id.eraser);

       // eraseBtn.setOnClickListener(this);
=======
>>>>>>> 8d0ad6d276ba96ff2f9e0f53ad6b30ce348485dc:Flushr_App/Create_Rate_Page/app/src/main/java/com/example/qishanwu/newproject/MainActivity.java

    public void onClickFunction(View view) {
        Intent intent = new Intent(this, submission.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
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
