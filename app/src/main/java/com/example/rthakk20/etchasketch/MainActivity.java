package com.example.rthakk20.etchasketch;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button left = null;
    private Button right = null;
    private Button up = null;
    private Button down = null;

    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        imageView = (ImageView) findViewById(R.id.image);


        initControls();

        //this post code is the needed
        imageView.post(new Runnable() {
            @Override
            public void run() {
                initControls();
            }
        });

    }

    public void initCanvas() {

        Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);

        //Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        canvas.drawCircle(10, 50, 10, paint);

        imageView.setImageBitmap(bitmap);

    }

    /* Initialise layout and button components. */
    private void initControls()
    {

        left = (Button)findViewById(R.id.left);
        right = (Button)findViewById(R.id.right);
        up = (Button)findViewById(R.id.up);
        down = (Button)findViewById(R.id.down);



        /* Must set the on click listener to this activity,
           otherwise the override onClick method will bot be invoked.*/
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
    }

    /* This method is called when any of the activity's view components is clicked. */
    @Override
    public void onClick(View view) {
        if(view != null)
        {
            String message = "";

            // Get view component id.
            int id = view.getId();

            // Show different message when click different view component.
            if(id == R.id.left) {
                message = "Left is clicked.";
                draw("left");
            } else if(id == R.id.right) {
                message = "Right is clicked.";
                draw("right");
            } else if(id == R.id.up) {
                message = "Up is clicked.";
                draw("up");
            } else if(id == R.id.down) {
                message = "Down is clicked.";
                draw("down");
            }
            // Create the toast popup message.
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }


    //code for the drawing and stuff

    /**
     *
     * how i wanna write this function:
     *  so u start with a white bitmap
     *  then every time u click a button fill in the pixel in the proper direction
     *  keep track of current pixel location by variable of x and y coordinates
     *
     * @param direction the direction to add pixel to
     */

    int test = 50;

    private void draw(String direction) {
        /**
         **/
        Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);

        //Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        test += 10;

        canvas.drawCircle(100, test,  10, paint);

        imageView.setImageBitmap(bitmap);

    }

    //to do function shake to clear the screen

}
