package au.edu.unsw.infs3634.gamifiedlearning;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;

public class EmailPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private TextView register;

    private EditText etEmail, etPassword;
    private Button btSignIn;
    private ProgressBar progressBar;


    //Here Users can sign in using registered user credentials or can select to register a new account

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);

        //Initialise Firebase Auth
        //mAuth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.tvRegister);
        register.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        btSignIn = (Button) findViewById(R.id.btSignIn);
        btSignIn.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tvRegister:
                //Sent to user registration activity
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.btSignIn:
                userLogin();
                break;
        }
    }


    private void userLogin() {
        //Preparing user input for processing
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        //Throws errors if text is empty, the email address is invalid or if the passwrod has less
        //than 6 characters
        if(email.isEmpty()){
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter valid email!");
            etEmail.requestFocus();
        }
        if(password.isEmpty()){
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            etPassword.setError("Min. password length is 6 characters.");
            etPassword.requestFocus();
            return;
        }

        //Loads a spinnering prograss circle animation to improve app usability
        progressBar.setVisibility(View.VISIBLE);

        //If the login passes the above filters the user is directed to the next page. This involves
        //using the authentication code provided by the Firebase Authentication sevice.
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to user profile
                    startActivity(new Intent(EmailPasswordActivity.this , ProfileActivity.class));
                } else {
                    Toast.makeText(EmailPasswordActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}