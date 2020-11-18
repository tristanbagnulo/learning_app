package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModuleQuizActivity extends AppCompatActivity implements View.OnClickListener {

    public int quizScore = 0;
    private TextView tvQuestion, tvQuestionNumber;
    private RadioGroup radioGroup;
    private RadioButton rb_1, rb_2, rb_3;
    private Button btNextQuestion;
    private Toast toast;
    private ArrayList<QuestionsAndAnswerSet> questionsAndAnswerSets;
    private int questionNumber = 1, arrayElementNumber = 0;
    private QuestionsAndAnswerSet questionsAndAnswerSet;
    private Boolean finalQuestion = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        radioGroup = findViewById(R.id.radioGroup);
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
        if (finalQuestion){
            Intent intent = new Intent(this, QuizResultsActivity.class);
            startActivity(intent);
            intent.putExtra("Score", quizScore);
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
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_1:
                if (checked)
                    toast = Toast.makeText(getApplicationContext(), "Option 1", Toast.LENGTH_LONG);
                    markAnswer1((RadioButton) findViewById(R.id.rb_1));
                    break;
            case R.id.rb_2:
                if (checked)
                    toast = Toast.makeText(getApplicationContext(), "Option 2", Toast.LENGTH_LONG);
                    markAnswer2((RadioButton) findViewById(R.id.rb_2));
                    break;
            case R.id.rb_3:
                if (checked)
                    toast = Toast.makeText(getApplicationContext(), "Option 3", Toast.LENGTH_LONG);
                markAnswer3((RadioButton) findViewById(R.id.rb_3));
                break;
        }
        toast.show();
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

    String chosenAnswer;
    public void markAnswer1 (RadioButton optionView){
        //get the selected answer
        chosenAnswer = optionView.getText().toString();
        //get the correct answer
        //compare selected and correct answer and act accordingly
        if(chosenAnswer.equals(questionsAndAnswerSet.getOption_1())){
            quizScore++;
        }
    }
    public void markAnswer2 (RadioButton optionView){
        //get the selected answer
        chosenAnswer = optionView.getText().toString();
        //get the correct answer
        //compare selected and correct answer and act accordingly
        if(chosenAnswer.equals(questionsAndAnswerSet.getOption_2())){
            quizScore++;
        }
    }
    public void markAnswer3 (RadioButton optionView){
        //get the selected answer
        chosenAnswer = optionView.getText().toString();
        //get the correct answer
        //compare selected and correct answer and act accordingly
        if(chosenAnswer.equals(questionsAndAnswerSet.getOption_3())){
            quizScore++;
        }
    }

}