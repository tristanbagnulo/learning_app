package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class QuizResultsActivity extends AppCompatActivity {

    private TextView tvResultsBanner, tvQuizScore;
    private ConstraintLayout clScreen;

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
    }

    public void setDisplay(int score){
        if (score >= 3){
            tvResultsBanner.setText("Congratulations!");
            clScreen.setBackgroundColor(getResources().getColor(R.color.threeOfThreeGreen));
        } if (score ==2) {
            tvResultsBanner.setText("So Close!");
            clScreen.setBackgroundColor(getResources().getColor(R.color.twoOfThreeYellow));
        } if (score <= 1) {
            tvResultsBanner.setText("Not quite. Try Again.");
            clScreen.setBackgroundColor(getResources().getColor(R.color.oneOrLessGreyRed));
        }
        tvQuizScore.setText("You answered "+score+" of 3 questions correctly.");
    }
}