package com.example.newpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent lintent = new Intent(this, LoadingActivity.class); //수민코드(08.31)-로딩화면
        startActivity(lintent);//수민코드(08.31)-로딩화면

        ImageView imageView = (ImageView)findViewById(R.id.imageview);

        // drawable에 있는 이미지를 지정합니다.
        imageView.setImageResource(R.drawable.home);

        Button button5=findViewById(R.id.btntomain);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트를 통해 main-sub 연결
                Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
