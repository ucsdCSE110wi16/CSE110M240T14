    package com.parse.flushr;

    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.graphics.Canvas;
    import android.graphics.Color;
    import android.graphics.drawable.BitmapDrawable;
    import android.graphics.drawable.Drawable;
    import android.os.Bundle;
    import android.provider.MediaStore;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageButton;
    import android.widget.Toast;

    import com.parse.FindCallback;
    import com.parse.GetCallback;
    import com.parse.GetDataCallback;
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
    import java.util.logging.Handler;

    public class ToiletWallActivity extends AppCompatActivity implements View.OnClickListener {

        private DrawingView drawView;
        private ImageButton uploadBtn;
        private ImageButton currPaint, drawBtn, saveBtn, backArrowButton;
        private ImageButton darkblueBtn, greenBtn, grayBtn, orangeBtn, redBtn, yellowBtn;

        boolean colorPalletisOpen = false;
        static ParseFile file;
        boolean hasWall;

        public void onClick(View view){

            if(view.getId()==R.id.upload){
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
                Toast savedToast = Toast.makeText(getApplicationContext(),
                        "Drawing saved online!", Toast.LENGTH_SHORT);
                savedToast.show();
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
            else if(view.getId()==R.id.save){

                drawView.setDrawingCacheEnabled(true);
                String imgSave = MediaStore.Images.Media.insertImage(
                        getContentResolver(), drawView.getDrawingCache(),
                        UUID.randomUUID().toString()+" .png", "drawing"
                );
                if (imgSave != null) {
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                } else {
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }
                drawView.destroyDrawingCache();
            }

        }

        public void paintClicked(View view){
            //drawView.setErase(false);
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


            ParseQuery<ParseObject> q = ParseQuery.getQuery("Restroom");
            q.getInBackground(MapsActivity.markerID, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    Boolean hasWallBeta = object.getBoolean("hasWall");
                    hasWall = hasWallBeta.booleanValue();
                    if (hasWall) {
                        file = object.getParseFile("wall");
                        Log.i("ParseFile", "Previous wall detected.");
                    } else
                        Log.i("ParseFile", "No wall found.");
                    setHasWall(hasWall, file);
                }
            });



            currPaint = (ImageButton)findViewById(R.id.darkblueColorButton);

            uploadBtn = (ImageButton)findViewById(R.id.upload);
            uploadBtn.setOnClickListener(this);
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
        private void setHasWall(boolean has_wall, ParseFile fileBeta){
            this.hasWall = has_wall;
            Log.i("Wall", "hasWall is " + hasWall);
            if (this.hasWall){
                Log.i("Wall", "hasWall entered");
                fileBeta.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            Bitmap store = BitmapFactory.decodeByteArray(data, 0, data.length);
                            store = store.copy(Bitmap.Config.ARGB_8888, true);
                            Canvas c = new Canvas(store);
                            /*Drawable background = drawView.getBackground();
                            if(background != null)
                                background.draw(c);
                            else
                                c.drawColor(Color.WHITE);*/
                            drawView = (DrawingView)findViewById(R.id.drawing);
                            //drawView.draw(c);
                            drawView.setImageBitmap(store);
                            Log.i("RestroomWall", "Previous wall loaded.");
                        }
                    });
            }
            else
                drawView = (DrawingView)findViewById(R.id.drawing);
        }
    }
