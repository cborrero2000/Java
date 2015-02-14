package com.example.mobileshoppingassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends Activity{
    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Button button1 = (Button) findViewById(R.id.button1);
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);

        TextView textView1 = (TextView) findViewById(R.id.textView1);

        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        
        

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Create transition from SplashScreenActivity to MainActivity
            	// Create an intent object
            	// MainActivity.class returns a MainActivity class (Introspection)
            	Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            	startActivity(intent);
            	
            	
            }
        });

    }
}
