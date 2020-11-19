package au.edu.unsw.infs3634.gamifiedlearning;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ModuleQuizActivity extends AppCompatActivity implements View.OnClickListener {

    public int quizScore = 0;
    private TextView tvQuestion, tvQuestionNumber;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton rb_1, rb_2, rb_3;
    private Button btNextQuestion;
    private Toast toast;
    private ArrayList<QuestionsAndAnswerSet> questionsAndAnswerSets;
    private int questionNumber = 1, arrayElementNumber = 0;
    private QuestionsAndAnswerSet questionsAndAnswerSet;
    private Boolean finalQuestion = false;
    String selectedAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        radioGroup = findViewById(R.id.radioGroup);
        // Testing removal of: radioGroup.setOnClickListener(this);
        rb_1 = findViewById(R.id.rb_1);
        rb_2 = findViewById(R.id.rb_2);
        rb_3 = findViewById(R.id.rb_3);
        btNextQuestion = findViewById(R.id.btNextQuestion);
        btNextQuestion.setOnClickListener(this);

        questionsAndAnswerSets = QuestionsAndAnswerSet.getQuestionsAndAnswerSets();
        questionsAndAnswerSet = questionsAndAnswerSets.get(0);
        displayQuestion();

    }

    public void setQuestionsAndAnswerSet(){
        questionsAndAnswerSet = questionsAndAnswerSets.get(arrayElementNumber);
    }
    public QuestionsAndAnswerSet getQuestionsAndAnswerSet(){
        return questionsAndAnswerSet;
    }
    String questionNumberDisplay;
    public void displayQuestion (){
        //Load question and options
        tvQuestion.setText(questionsAndAnswerSet.getQuestion());
        rb_1.setText(questionsAndAnswerSet.getOption_1());
        rb_2.setText(questionsAndAnswerSet.getOption_2());
        rb_3.setText(questionsAndAnswerSet.getOption_3());
        //Increase and update question number counter
        questionNumberDisplay = questionNumber+"/3";
        tvQuestionNumber.setText(questionNumberDisplay);
    }

    public void onClick (View v){
        checkRadioButton(v);
    }

    public void updateQuestion(View v){
        if (finalQuestion){
            //Toast.makeText(this, "Quiz Score in updateQuestion: " + quizScore, Toast.LENGTH_SHORT).show();
            //Result, this function sees the right quiz score
            Intent intent = new Intent(this, QuizResultsActivity.class);
            intent.putExtra("Score", quizScore);
            startActivity(intent);

        } else {
            increaseQuestionAndArrayNumber();
            setQuestionsAndAnswerSet();
            displayQuestion();
            if (questionNumber == 3) {
                btNextQuestion.setText("Finish Quiz");
                btNextQuestion.setBackgroundColor(getResources().getColor(R.color.red));
                finalQuestion = true;
            }
        }
        rb_1.setSelected(false);
        rb_2.setSelected(false);
        rb_3.setSelected(false);
    }



    public void checkRadioButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        selectedAnswer = radioButton.getText().toString();
        //Toast.makeText(this, "Selected Radio Button: " + selectedAnswer, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Correct Answer: " + questionsAndAnswerSet.getAnswer(), Toast.LENGTH_SHORT).show();
        if(selectedAnswer.equals(questionsAndAnswerSet.getAnswer())){
            quizScore++;
            //Toast.makeText(this, "Score: " + quizScore, Toast.LENGTH_SHORT).show();
        }
        updateQuestion(view);

    }

    public int getArrayElementNumber(){
        return arrayElementNumber;
    }

    public int getQuestionNumber(){
        return questionNumber;
    }

    public void increaseQuestionAndArrayNumber(){
        questionNumber++;
        arrayElementNumber++;
    }

}