package com.vedangbhardwaj.interviewpreperation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class forgotpass extends AppCompatActivity {

    private EditText Email1;
    private Button repass;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        Email1 = (EditText)findViewById(R.id.Email1);
        repass = (Button)findViewById(R.id.repass);
        firebaseAuth = FirebaseAuth.getInstance();

        repass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = Email1.getText().toString().trim();

                if(useremail.equals(""))
                {
                    Toast.makeText(forgotpass.this,"please enter the E-Mail ID",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(forgotpass.this,"An E-Mail has been sent to your E-mail ID",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(forgotpass.this,login.class));
                            }
                            else
                            {
                                Toast.makeText(forgotpass.this,"Error in sending the E-Mail",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
