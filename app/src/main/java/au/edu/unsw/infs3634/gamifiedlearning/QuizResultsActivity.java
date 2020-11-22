package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuizResultsActivity extends AppCompatActivity {

    //This activity displays the result of the user completing the quiz. It's UI depends on what
    //the user's score is out of 3. If it's 3, it's green and they receive congratulations. If it's
    //too they're reassured that they made a solid attempts and are encouraged to try again quickly.
    //When a user scores 1, they're told they've failed and should reattempt.
    //This plays on rules of gamification including positive and negative reinforcement.

    private TextView tvResultsBanner, tvQuizScore;
    private ConstraintLayout clScreen;
    Button btRedirect;
    // 1.1 - Get a DatabaseReference
    private DatabaseReference mDatabase;
    Button btShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        tvResultsBanner = findViewById(R.id.tvResultsBanner);
        clScreen = findViewById(R.id.clScreen);
        tvQuizScore = findViewById(R.id.tvQuizScore);

        //Get the user's score from the quiz activity using an Intent.
        Intent intent = getIntent();
        final int quizScore = intent.getIntExtra("Score", 0);

        setDisplay(quizScore);

        //1.2 - Get a database reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Write user's score for the module to Firebase
        btRedirect = findViewById(R.id.btRedirect);
        btShare = findViewById(R.id.btShare);

        redirect(quizScore);
        btShare.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String shareBody = "I just scored "+quizScore+"!!!";

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
});
    }

    //Dynamic updating of the display and results
    public void setDisplay(int score) {
        if (score >= 3) {
            tvResultsBanner.setText(R.string.congratulations_message);
            clScreen.setBackgroundColor(getResources().getColor(R.color.threeOfThreeGreen));
        }
        if (score == 2) {
            tvResultsBanner.setText(R.string.so_close_text);
            clScreen.setBackgroundColor(getResources().getColor(R.color.twoOfThreeYellow));
        }
        if (score <= 1) {
            tvResultsBanner.setText(R.string.try_again_text);
            clScreen.setBackgroundColor(getResources().getColor(R.color.oneOrLessGreyRed));
        }
            String youScored = "You answered " + score + " of 3 questions correctly.";
            tvQuizScore.setText(youScored);
        }

    //Buttons change depending on the user's score. If it's 3, they return to the module selection
    //page, ProfileActivity. If it's 2 or less, they'regiven only the option to reattempt it.
    public void redirect(int score) {
        if (score == 3) {
            btRedirect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProfileActivity();

                }
            });
            btRedirect.setText(R.string.home_text);
        }
        if (score <= 2) {
            btRedirect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    retryQuiz();

                }
            });
            btRedirect.setText(R.string.retry_text);
        }
    }

    public void openProfileActivity () {
        Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileActivityIntent);
    }
    public void retryQuiz () {
        Intent retryIntent = new Intent(this, ModuleActivity.class);
        startActivity(retryIntent);
    }

}


