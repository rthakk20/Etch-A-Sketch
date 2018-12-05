package com.example.rthakk20.etchasketch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button left = null;
    private Button right = null;
    private Button up = null;
    private Button down = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initControls();

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
            if(id == R.id.left)
            {
                message = "Left is clicked.";
            }else if(id == R.id.right)
            {
                message = "Right is clicked.";
            }else if(id == R.id.up)
            {
                message = "Up is clicked.";
            }
            else if(id == R.id.down)
            {
                message = "Down is clicked.";
            }
            // Create the toast popup message.
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }


}
