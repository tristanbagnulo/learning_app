package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizResultsActivity extends AppCompatActivity {

    private TextView tvResultsBanner, tvQuizScore;
    private ConstraintLayout clScreen;
    Button btRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        tvResultsBanner = findViewById(R.id.tvResultsBanner);
        clScreen = findViewById(R.id.clScreen);
        tvQuizScore = findViewById(R.id.tvQuizScore);

        Intent intent = getIntent();
        int quizScore = intent.getIntExtra("Score", 0);
        //Toast.makeText(this, "Received Quiz Score: " + quizScore, Toast.LENGTH_SHORT).show();
        //Results - QuizResultsActivity is not receiving the Score from the ModuleQuizActivity

        setDisplay(quizScore);

        btRedirect = (Button) findViewById(R.id.btRedirect);

        redirect(quizScore);

    }

    public void setDisplay(int score) {
        if (score >= 3) {
            tvResultsBanner.setText("Congratulations!");
            clScreen.setBackgroundColor(getResources().getColor(R.color.threeOfThreeGreen));


        }
        if (score == 2) {
            tvResultsBanner.setText("So Close!");
            clScreen.setBackgroundColor(getResources().getColor(R.color.twoOfThreeYellow));

        }
        if (score <= 1) {
            tvResultsBanner.setText("Not quite. Try Again.");
            clScreen.setBackgroundColor(getResources().getColor(R.color.oneOrLessGreyRed));
        }
            tvQuizScore.setText("You answered " + score + " of 3 questions correctly.");
        }

    public void redirect(int score) {
        if (score >= 2) {
            btRedirect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProfileActivity();

                }
            });
            btRedirect.setText("Home");

        }

        if (score <= 1) {
            btRedirect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    retryQuiz();

                }
            });
            btRedirect.setText("Retry");
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


