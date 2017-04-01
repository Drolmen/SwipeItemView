package com.drolmen.swipeitemdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testSwipe();
    }

    void testSwipe(){
        LinearLayout topLayout = (LinearLayout) findViewById(R.id.top_view);
        LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottom_view);
        Button btn1 = (Button) bottomLayout.findViewById(R.id.btn_1);
        Button btn2 = (Button) bottomLayout.findViewById(R.id.btn_2);


        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello world", Toast.LENGTH_SHORT).show();
            }
        });

        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "((Button) v).getText():" + ((Button) v).getText(), Toast.LENGTH_SHORT).show();
            }
        };
        btn1.setOnClickListener(l);
        btn2.setOnClickListener(l);
    }
}
