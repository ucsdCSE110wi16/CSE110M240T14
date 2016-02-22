package com.parse.flushr;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class RestroomInfoActivity extends AppCompatActivity {

    String nameOfRestroom;
    String comment;
    double rating;
    double numberOfRates;
    Boolean male, female, accessbility;
    EditText nameOfRestroomInfo;
    CheckBox checkMale, checkFemale, checkAccess;
    RatingBar toiletRatingBar;
    TextView commentBox;
    ImageButton drawingWallButton;
    double newRatingToSave;


    public void viewWall(View view) {

        Intent intent = new Intent(this, ToiletWallActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restroom_info);

        drawingWallButton = (ImageButton)findViewById(R.id.drawingWallButton);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Restroom");
        query.getInBackground(MapsActivity.markerID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    nameOfRestroom = object.getString("nameOfRestroom");
                    comment = object.getString("comment");
                    rating = object.getDouble("rating");
                    numberOfRates = object.getDouble("numberOfRates");
                    male = object.getBoolean("male");
                    female = object.getBoolean("female");
                    accessbility = object.getBoolean("accessbility");

                    nameOfRestroomInfo = (EditText)findViewById(R.id.nameofresInfo);
                    nameOfRestroomInfo.setText(nameOfRestroom);
                    nameOfRestroomInfo.setClickable(false);

                    checkMale = (CheckBox)findViewById(R.id.checkMaleInfo);
                    checkMale.setClickable(false);
                    checkFemale = (CheckBox)findViewById(R.id.checkFemaleInfo);
                    checkFemale.setClickable(false);
                    checkAccess = (CheckBox)findViewById(R.id.checkAccessInfo);
                    checkAccess.setClickable(false);

                    if(male){
                        checkMale.setChecked(true);
                    }

                    if(female){
                        checkFemale.setChecked(true);
                    }

                    if(accessbility) {
                        checkAccess.setChecked(true);
                    }

                    toiletRatingBar = (RatingBar)findViewById(R.id.ratingBarInfo);
                    toiletRatingBar.setClickable(false);
                    toiletRatingBar.setRating((float) rating);


                    commentBox = (TextView)findViewById(R.id.commentBoxInfo);
                    commentBox.setClickable(false);
                    commentBox.setText(comment);




                } else {
                    Log.i("FATAL", "Restroom Info Activity");
                }




            }
        });

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LayerDrawable stars = (LayerDrawable) toiletRatingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(1).setColorFilter(ContextCompat.getColor(this, R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        /*
        Log.i("RATING", new Double(rating).toString());
        double newRating = 0; //CHANGE THIS!!!
        newRatingToSave = (numberOfRates * rating + newRating)/(numberOfRates + 1);

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Restroom");
        query2.getInBackground(MapsActivity.markerID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                object.put("rating", newRatingToSave);
                object.put("numOfRates", numberOfRates + 1);
                object.saveInBackground();
            }
        });
        */
    }
}
