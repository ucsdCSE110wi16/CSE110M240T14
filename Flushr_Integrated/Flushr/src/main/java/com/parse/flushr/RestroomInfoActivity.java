package com.parse.flushr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class RestroomInfoActivity extends AppCompatActivity {

    String nameOfRestroom;
    String comment;
    Double rating;
    Boolean male, female, accessbility;
    EditText nameOfRestroomInfo;
    CheckBox checkMale, checkFemale, checkAccess;
    RatingBar toiletRatingBar;
    EditText commentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restroom_info);



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Restroom");
        query.getInBackground(MapsActivity.markerID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    nameOfRestroom = object.getString("nameOfRestroom");
                    comment = object.getString("comment");
                    rating = object.getDouble("rating");
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
                    toiletRatingBar.setRating((float) rating.doubleValue());

                    commentBox = (EditText)findViewById(R.id.commentBoxInfo);
                    commentBox.setClickable(false);
                    commentBox.setText(comment);


                    /*
                    TextView t2 = (TextView) findViewById(R.id.textView2);
                    if (male) {
                        t2.setText("Gender: Male");
                    } else if (female) {
                        t2.setText("Gender: Female");
                    } else {
                        t2.setText("Gender: Male and Female");
                    }

                    TextView t3 = (TextView) findViewById(R.id.textView3);
                    t3.setText("Rating: " + rating.toString());

                    TextView t4 = (TextView) findViewById(R.id.textView4);
                    t4.setText("Description: " + comment);
                    */

                } else {
                    Log.i("FATAL", "Restroom Info Activity");
                }
            }
        });



    }
}
