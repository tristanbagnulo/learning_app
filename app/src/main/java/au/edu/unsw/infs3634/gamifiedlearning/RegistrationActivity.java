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

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView4, textView5;
    private EditText fullName, age, email, password;
    private ProgressBar progressBar;
    private Button registerUser;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        //Initialise the variables
        registerUser = (Button) findViewById(R.id.btRegisterUser);
        registerUser.setOnClickListener(this);

        fullName = (EditText) findViewById(R.id.etFullName);
        age = (EditText) findViewById(R.id.etAge);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btRegisterUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        //Add validation to make sure the user enters appropriate information
        String finalFullName = fullName.getText().toString().trim();
        String finalAge = age.getText().toString().trim();
        String finalEmail = email.getText().toString().trim();
        String finalPassword = password.getText().toString().trim();

        if(finalFullName.isEmpty()){
            fullName.setError("Full name is required.");
            fullName.requestFocus();
            return;
        }

        if(finalAge.isEmpty()){
            age.setError("Age is required.");
            age.requestFocus();
            return;
        }

        if(finalEmail.isEmpty()){
            email.setError("Email is required.");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(finalEmail).matches()){
            email.setError("Please provide valid email!.");
            email.requestFocus();
            return;
        }

        if(finalPassword.length() < 6){
            password.setError("Min. password length is 6 characters.");
            password.requestFocus();
            return;
        }

        if(finalPassword.isEmpty()){
            password.setError("Password is required.");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        setEmail(finalEmail);
        setAge(finalAge);
        setFullName(finalFullName);

        mAuth.createUserWithEmailAndPassword(finalEmail, finalPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Create the User object
                            User user = new User(getFullName(),getEmail(),getAge());

                            //Now send that object to the Realtime Database
                            FirebaseDatabase.getInstance().getReference("Users")
                                    //Get registered user's ID and set it the the User object
                                    //so that they're connected
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    //pass the user object. OnCompleteListener is used to Check if
                                    //the data was added to the database.
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                //Inside OnCompleteListener you must complete that
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegistrationActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //Then redirect to login layout
                                    } else {
                                        Toast.makeText(RegistrationActivity.this,"Failed to register. Try again.", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });
                        } else {
                            Toast.makeText(RegistrationActivity.this,"Failed to register. Try again.", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }

    //Getters and setters to make the email, fullName and age info that the user entered
    // available throughout this class once input is validated above. This was done to
    // correct an error stating that this variables were called from within an
    // inner class and to avoid fixing it using Global Variables (which are bad
    // according to http://wiki.c2.com/?GlobalVariablesAreBad or declaring them 'final' as
    // recommended by the Android Studio console.
    String emailHolder;
    String fullNameHolder;
    String ageHolder;

    private void setEmail(String finalEmail){
        emailHolder = finalEmail;
    }
    private String getEmail(){
    return emailHolder;
    }

    private void setFullName(String finalPassword) {
        fullNameHolder = finalPassword;
    }
    private String getFullName(){
        return fullNameHolder;
    }

    private void setAge(String finalAge) {
        ageHolder = finalAge;
    }
    private String getAge(){
        return ageHolder;
    }
}