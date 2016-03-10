package com.parse.flushr;

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
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class CreatePageActivity extends AppCompatActivity {


    private ImageView submitButton, cancelButton;
    private RatingBar ratingBar;
    private CheckBox checkMale, checkFemale, checkAccessibility, checkTrans;
    private EditText comment;
    private EditText nameOfRestaurant;
    public static String TOAST_STRING_SUCCESS;
    public static String TOAST_STRING_FAILURE;


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
         boolean isTrans = checkTrans.isChecked();

         String commentword = String.valueOf(comment.getText());



        if(TextUtils.isEmpty(name)
                || (isMale == false && isFemale == false)) {
            Toast.makeText(CreatePageActivity.this,
                    "There were an missing information, please be completed",
                    Toast.LENGTH_SHORT).show();
            TOAST_STRING_FAILURE = "There were an missing information, please be completed";
            return;
        }
        else if (MapsActivity.userLat == 0.0 && MapsActivity.userLng == 0.0){
            Toast.makeText(CreatePageActivity.this,
                    "Invalid location.",
                    Toast.LENGTH_SHORT).show();
            TOAST_STRING_FAILURE = "Invalid location.";
            return;
        }

        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        restroom.setACL(acl);

        // checkFemale.isChecked();
        restroom.put("latitude", MapsActivity.userLat.doubleValue());
        restroom.put("longitude", MapsActivity.userLng.doubleValue());
        restroom.put("nameOfRestroom", name);
        restroom.put("male", isMale);
        restroom.put("female", isFemale);
        restroom.put("transgender",isTrans);
        restroom.put("rating", cleaness);
        restroom.put("accessibility", accessibie);
        restroom.put("comment", commentword);
        restroom.put("numberOfRates", new Integer(1));
        restroom.put("hasWall", new Boolean(false));

        restroom.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(CreatePageActivity.this, "A new restroom has been created", Toast.LENGTH_SHORT).show();
                    TOAST_STRING_SUCCESS = "A new restroom has been created";
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
        super.onBackPressed();
    }



    private void backtoself(){

        Intent intent = new Intent(this,CreatePageActivity.class);
        startActivity(intent);
    }

    private void retrieveComponents() {
        nameOfRestaurant = (EditText)findViewById(R.id.nameofres);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        checkMale = (CheckBox) findViewById(R.id.checkMale);
        checkFemale = (CheckBox) findViewById(R.id.checkFemale);
        comment = (EditText) findViewById(R.id.commentBox);
        submitButton = (ImageView) findViewById(R.id.submitButton);
        checkAccessibility = (CheckBox) findViewById(R.id.checkAccess);
        checkTrans = (CheckBox) findViewById(R.id.checkTrans);
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
