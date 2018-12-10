package com.example.rthakk20.etchasketch;

import android.content.Context;
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
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button left = null;
    private Button right = null;
    private Button up = null;
    private Button down = null;
    private Button colorButton = null;

    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity


    private ImageView imageView;

    private int color = Color.BLACK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mAccel > 12) {
            Toast toast = Toast.makeText(getApplicationContext(), "Device has shaken.", Toast.LENGTH_LONG);
            toast.show();
        }
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
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
                initCanvas();
            }
        });

    }

    public void initCanvas() {

        Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);

        //Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        //canvas.drawCircle(10, 50, 10, paint);

        imageView.setImageBitmap(bitmap);

    }

    /* Initialise layout and button components. */
    private void initControls()
    {

        left = (Button)findViewById(R.id.left);
        right = (Button)findViewById(R.id.right);
        up = (Button)findViewById(R.id.up);
        down = (Button)findViewById(R.id.down);
        colorButton = (Button)findViewById(R.id.colorButton);



        /* Must set the on click listener to this activity,
           otherwise the override onClick method will bot be invoked.*/
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        colorButton.setOnClickListener(this);

    }

    /* This method is called when any of the activity's view components is clicked. */
    @Override
    public void onClick(View view) {
        if(view != null)
        {
            String message = "";

            // Get view component id.
            int id = view.getId();

            if (id == R.id.colorButton) {
                message = "Colors";
                // initialColor is the initially-selected color to be shown in the rectangle on the left of the arrow.
                // for example, 0xff000000 is black, 0xff0000ff is blue. Please be aware of the initial 0xff which is the alpha.
                AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int inColor) {
                        // color is the color selected by the user.
                        color = inColor;
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // cancel was selected by the user
                    }

                });

                dialog.show();

            }

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

    int x = 50;
    int y = 100;

    private void draw(String direction) {
        /**
         **/
        //Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);

        Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);


        if (direction == "left") {
            x -=10;
        }

        if (direction == "right") {
            x += 10;
        }

        if (direction == "up") {
            y -= 10;
        }

        if (direction == "down") {
            y += 10;
        }

        canvas.drawCircle(x, y,  10, paint);
        imageView.setImageBitmap(bitmap);

    }


    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
            if (mAccel > 12) {
                Toast toast = Toast.makeText(getApplicationContext(), "Device has shaken.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                initCanvas();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };

    //to do function shake to clear the screen

}
