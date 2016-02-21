package com.parse.starter;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class CreatePageActivity extends AppCompatActivity {


    private ImageView submitButton, cancelButton;
    private RatingBar ratingBar;
    private CheckBox checkMale, checkFemale, checkAccessibility;
    private EditText comment;
    private EditText nameOfRestaurant;

    ParseObject restroom = new ParseObject("Restroom");


    private float rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_page);

        // find all components
        retrieveComponents();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.yellow), PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(1).setColorFilter(ContextCompat.getColor(this, R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        }

        // update accessibility icon color
        Drawable left = ContextCompat.getDrawable(this, R.drawable.ic_accessability_black);
        left.setColorFilter(ContextCompat.getColor(this, R.color.blue), PorterDuff.Mode.SRC_ATOP);
        checkAccessibility.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);

        // update send and cancel buttons color
        cancelButton.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        submitButton.setColorFilter(ContextCompat.getColor(this, R.color.gray));

        // action send
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        // retrieve rating
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // rating
                CreatePageActivity.this.rating = rating;
            }
        });

        // set comment box style
        setCommentBox();
    }

    public void cancelFuntion(View view){

        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }



    private void submit() {
         String name = String.valueOf(nameOfRestaurant.getText());
         float cleaness = ratingBar.getRating();
         boolean isMale = checkMale.isChecked();
         boolean isFemale = checkFemale.isChecked();
         boolean accessibie = checkAccessibility.isChecked();
         String commentword = String.valueOf(comment.getText());



        if(TextUtils.isEmpty(name) || (isMale == false && isFemale == false)) {
            Toast.makeText(CreatePageActivity.this, "There were an missing information, please be completed", Toast.LENGTH_SHORT).show();
            return;
        }

        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        restroom.setACL(acl);

        // checkFemale.isChecked();
        restroom.put("latitude", MapsActivity.userLat.doubleValue());
        restroom.put("longitude", MapsActivity.userLng.doubleValue());
        restroom.put("nameOfRestroom", name);
        restroom.put("male", isMale);
        restroom.put("female", isFemale);
        restroom.put("rating", cleaness);
        restroom.put("accessibility", accessibie);
        restroom.put("comment", commentword);

        restroom.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(CreatePageActivity.this, "A new restroom has been created", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            backtoMap();
                        }
                    }, 2000);
                } else {
                    Toast.makeText(CreatePageActivity.this, "There were an error, please try again", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }


    private void backtoMap(){

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }



    private void backtoself(){

        Intent intent = new Intent(this,CreatePageActivity.class);
        startActivity(intent);
    }

    private void retrieveComponents() {
        nameOfRestaurant = (EditText)findViewById(R.id.nameofres);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        cancelButton = (ImageView)findViewById(R.id.cancelButton);
        checkMale = (CheckBox) findViewById(R.id.checkMale);
        checkFemale = (CheckBox) findViewById(R.id.checkFemale);
        comment = (EditText) findViewById(R.id.commentBox);
        submitButton = (ImageView) findViewById(R.id.submitButton);
        checkAccessibility = (CheckBox) findViewById(R.id.checkAccess);
    }

    private void setCommentBox() {
        comment.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
                for (int i = s.length(); i > 0; i--) {

                    if (s.subSequence(i - 1, i).toString().equals("\n"))
                        s.replace(i - 1, i, "");

                }

                String myTextString = s.toString();
            }
        });
    }

}
