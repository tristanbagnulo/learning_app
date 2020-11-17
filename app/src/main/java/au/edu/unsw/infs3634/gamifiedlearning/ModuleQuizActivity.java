package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModuleQuizActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvQuestion, tvQuestionNumber;
    private RadioGroup radioGroup;
    private RadioButton rb_1, rb_2, rb_3;
    private Button btNextQuestion;
    private Toast toast;
    private ArrayList<QuestionsAndAnswerSet> questionsAndAnswerSets;
    private int questionNumber = 1, arrayElementNumber = 0;
    private QuestionsAndAnswerSet questionsAndAnswerSet;


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

        displayQuestion(arrayElementNumber);

    }

    String questionNumberDisplay;
    public void displayQuestion (int arrayElementNumber){
        //Load question and options
        questionsAndAnswerSet = questionsAndAnswerSets.get(arrayElementNumber);
        tvQuestion.setText(questionsAndAnswerSet.getQuestion());
        rb_1.setText(questionsAndAnswerSet.getOption_1());
        rb_2.setText(questionsAndAnswerSet.getOption_2());
        rb_3.setText(questionsAndAnswerSet.getOption_3());
        //Increase and update question number counter

        questionNumberDisplay = questionNumber+"/3";
        tvQuestionNumber.setText(questionNumberDisplay);
    }

    public void onClick (View v){
        //nextQuestion();
        increaseQuestionAndArrayNumber();
        displayQuestion(arrayElementNumber);
        if (questionNumber==3){
            btNextQuestion.setText("Finish Quiz");
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

                    break;
            case R.id.rb_2:
                if (checked)
                    toast = Toast.makeText(getApplicationContext(), "Option 2", Toast.LENGTH_LONG);
                    break;
            case R.id.rb_3:
                if (checked)
                    toast = Toast.makeText(getApplicationContext(), "Option 3", Toast.LENGTH_LONG);
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

}