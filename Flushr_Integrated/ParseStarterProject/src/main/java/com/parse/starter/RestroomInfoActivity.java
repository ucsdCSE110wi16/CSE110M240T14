package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

                    TextView t1 = (TextView) findViewById(R.id.textView1);
                    t1.setText("Restroom Name: " + nameOfRestroom);

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


                } else {
                    Log.i("FATAL", "Restroom Info Activity");
                }
            }
        });



    }
}
