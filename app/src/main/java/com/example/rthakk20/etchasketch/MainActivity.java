package com.example.rthakk20.etchasketch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Left(android.view.View left) {
        Toast.makeText(this, "You pressed the Left button", Toast.LENGTH_SHORT).show();
    }
    public void Right(android.view.View right) {
        Toast.makeText(this, "You pressed the Right button", Toast.LENGTH_SHORT).show();
    }
    public void Up(android.view.View up) {
        Toast.makeText(this, "You pressed the Up button", Toast.LENGTH_SHORT).show();
    }
    public void Down(android.view.View down) {
        Toast.makeText(this, "You pressed the Down button", Toast.LENGTH_SHORT).show();
    }
}
