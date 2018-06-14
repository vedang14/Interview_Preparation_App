package com.vedangbhardwaj.interviewpreperation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private EditText email, pass;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private TextView forgotpassword;

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
 email = (EditText) findViewById(R.id.moNo);
        pass = (EditText) findViewById(R.id.pass3);
        login = (Button) findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();
        TextView signup = (TextView) findViewById(R.id.signUp);

         forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, forgotpass.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ot = new Intent(login.this, signup.class);
                startActivity(ot);
            }
        });
login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (email != null & pass != null) {
                    final ProgressDialog progressDialog = ProgressDialog.show(login.this, "Please wait...", "Processing...", true);
                    (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();

                                    if (task.isSuccessful()) {
                                        Toast.makeText(login.this, "Login successful", Toast.LENGTH_LONG).show();
                                        //DataPosition.set_Email(email.getText().toString());
                                        Intent i = new Intent(login.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Log.e("ERROR", task.getException().toString());
                                        Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(login.this, "Please fill the credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
