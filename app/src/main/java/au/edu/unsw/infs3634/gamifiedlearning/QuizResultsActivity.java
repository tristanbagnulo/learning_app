package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuizResultsActivity extends AppCompatActivity {

    private TextView tvResultsBanner;
    private ConstraintLayout clScreen;
    private int quizScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        tvResultsBanner = findViewById(R.id.tvResultsBanner);
        clScreen = findViewById(R.id.clScreen);

        quizScore = getIntent().getIntExtra("Score", 0);

        setDisplay(quizScore);
    }

    public void setDisplay(int quizScore){
        if (quizScore >= 3){
            tvResultsBanner.setText("Congratulations!");
            clScreen.setBackgroundColor(getResources().getColor(R.color.threeOfThreeGreen));
        } if (quizScore ==2) {
            tvResultsBanner.setText("So Close!");
            clScreen.setBackgroundColor(getResources().getColor(R.color.twoOfThreeYellow));
        } if (quizScore <= 1) {
            tvResultsBanner.setText("Not quite. Try Again.");
            clScreen.setBackgroundColor(getResources().getColor(R.color.oneOrLessGreyRed));
        }
    }
}