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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {

     private EditText fName, lName, mobNo, email, pass, conpass;
    private Button reg;
    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fName = (EditText)findViewById(R.id.fName);
        //lName = (EditText)findViewById(R.id.lName);
        mobNo = (EditText)findViewById(R.id.moNum);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        conpass = (EditText)findViewById(R.id.conpass);
        reg = (Button)findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass.getText().toString().equals(conpass.getText().toString())) {
                    char[] c = new char[11];
                    fName.getText().toString().getChars(0, 2, c, 0);
                    email.getText().toString().getChars(0, 2, c, 3);
                    StringBuilder b = new StringBuilder(new String(c));
                    int i = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) + (int)(Math.random()*10000);
                    b.append(i);
                    final String f1 = b.toString();
                    final String f = fName.getText().toString().trim();
                    //l changed to email from last name
                    final String l = email.getText().toString().trim();
                    final String m = mobNo.getText().toString().trim();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference ref = database.getReference("Users");
                    final ProgressDialog progressDialog = ProgressDialog.show(signup.this, "Please wait...", "Processing...", true);
                    (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        String id = firebaseAuth.getCurrentUser().getUid();
                                        DatabaseReference d = ref.child(id);
                                        d.child("First Name: ").setValue(f);
                                        d.child("email: ").setValue(l);
                                        d.child("Mobile number: ").setValue(m);
                                        d.child("UID: ").setValue(f1);
                                        Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(signup.this, login.class);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Log.e("ERROR", task.getException().toString());
                                        Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                }
                else
                    Toast.makeText(signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
