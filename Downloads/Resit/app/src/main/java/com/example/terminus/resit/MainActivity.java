package com.example.terminus.resit;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtEmail;
    private TextView txtPwd;
    private Button btnSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPwd = (EditText) findViewById(R.id.txtPWD);

        btnSignUp.setOnClickListener(this);




    }

    private void registerUser(){
        String email = txtEmail.getText().toString().trim();
        String password = txtPwd.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            //Stop function executing

            return;

        }

        if(TextUtils.isEmpty(password)) {

            Toast.makeText(this, "Please enter a valid Password", Toast.LENGTH_SHORT).show();
            //Stop function executing

            return;

        }

            //If completed

            progressDialog.setMessage("Registering user");
            progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //message
                            Toast.makeText(MainActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(MainActivity.this, "Failed To Register User", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void onClick(View v) {

        if(v == btnSignUp){
            registerUser();

        }

    }
}
