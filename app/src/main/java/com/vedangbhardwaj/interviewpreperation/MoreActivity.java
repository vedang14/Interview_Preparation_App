package com.vedangbhardwaj.interviewpreperation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import static com.vedangbhardwaj.interviewpreperation.MainActivity.Ans;
import static com.vedangbhardwaj.interviewpreperation.MainActivity.ExtraHeading;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        Intent intent = getIntent();
        String question = intent.getStringExtra(ExtraHeading);
        String answer = intent.getStringExtra(Ans);

        TextView t1 = findViewById(R.id.moreheading);
        TextView t2 = findViewById(R.id.moreanswer);
        TextView t3 = findViewById(R.id.textclick);
        t3.setMovementMethod(LinkMovementMethod.getInstance());

        t1.setText(question);
        t2.setText(answer);
    }
}
