package com.jovialway.miprince;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    TextView poemName, poemWriter, poemDetails;
    ImageView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        btn = findViewById(R.id.button);
        poemName = findViewById(R.id.poemNameTV);
        poemWriter = findViewById(R.id.poemWriterTV);
        poemDetails = findViewById(R.id.poemDetailTV);


        Intent intent = getIntent();
        String name = intent.getStringExtra("poemName");
        String writer = intent.getStringExtra("writer");
        String detail = intent.getStringExtra("poem");
        poemName.setText(name);
        poemWriter.setText(writer);
        poemDetails.setText(detail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
