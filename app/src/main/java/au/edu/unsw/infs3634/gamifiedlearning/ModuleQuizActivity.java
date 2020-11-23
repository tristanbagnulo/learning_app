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
    private int questionNumber = 1, arrayElementNumber;
    private QuestionsAndAnswerSet questionsAndAnswerSet;
    private Boolean finalQuestion = false;
    String selectedAnswer;
    String moduleName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_quiz);

        questionsAndAnswerSets = QuestionsAndAnswerSet.getQuestionsAndAnswerSets();

        Intent intent = getIntent();
        moduleName = intent.getStringExtra("module_name");
        Toast.makeText(this, "String in Quiz Activity " + moduleName, Toast.LENGTH_LONG).show();

        setModuleQuestionGroup(moduleName);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        radioGroup = findViewById(R.id.radioGroup);
        rb_1 = findViewById(R.id.rb_1);
        rb_2 = findViewById(R.id.rb_2);
        rb_3 = findViewById(R.id.rb_3);
        btNextQuestion = findViewById(R.id.btNextQuestion);
        btNextQuestion.setOnClickListener(this);

        //questionsAndAnswerSet = questionsAndAnswerSets.get(0);
        displayQuestion();

    }

    private void setModuleQuestionGroup(String tModuleName){
        switch (tModuleName) {
            case "Heart":
                arrayElementNumber = 0;
                break;
            case "Muscle":
                arrayElementNumber = 3;
                break;
            case "Mental":
                arrayElementNumber = 6;
                break;
            default:
                arrayElementNumber = 2;
                break;
        } ;
        questionsAndAnswerSet = questionsAndAnswerSets.get(arrayElementNumber);
    }

    //Retrieves a set of 1 question, 3 potential answers to select and the right answer from the
    //QuestionsAndAnswerSet activity
    public void setQuestionsAndAnswerSet(){
        questionsAndAnswerSet = questionsAndAnswerSets.get(arrayElementNumber);
    }


    public QuestionsAndAnswerSet getQuestionsAndAnswerSet(){
        return questionsAndAnswerSet;
    }

    //Displays the new question/answers set on the screen for the user to select from
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

    //When "Next Question" button is selected it launches the checkRadioButton activity
    public void onClick (View v){
        checkRadioButton(v);
    }


    public void updateQuestion(View v){

        //When the final question has been entered, the results are displayed in the next activity
        if (finalQuestion){
            Intent intent = new Intent(this, QuizResultsActivity.class);
            intent.putExtra("Score", quizScore);
            startActivity(intent);
        //If its not the final question, the question number and ArrayList element number from
        //the ArrayList of QuestionsAndAnswerObjects are increased and the new set is displayed
        } else {
            increaseQuestionAndArrayNumber();
            setQuestionsAndAnswerSet();
            displayQuestion();
            //If this is the final question, the button changes colour and text
            if (questionNumber == 3) {
                btNextQuestion.setText(R.string.finish_quiz);
                btNextQuestion.setBackgroundColor(getResources().getColor(R.color.red));
                //it is declared that this is the final question
                finalQuestion = true;
            }
        }


    }

    //Find the id of the Radio button that is checked and draw the answer text from it
    public void checkRadioButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        selectedAnswer = radioButton.getText().toString();
        //Compare the text in the radio button to the correct answer and increase the score if
        //the match
        if(selectedAnswer.equals(questionsAndAnswerSet.getAnswer())){
            quizScore++;
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