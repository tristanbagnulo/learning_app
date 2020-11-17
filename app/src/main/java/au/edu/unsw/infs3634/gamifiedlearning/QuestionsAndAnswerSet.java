package au.edu.unsw.infs3634.gamifiedlearning;

import java.util.ArrayList;

public class QuestionsAndAnswerSet {

    public String question, option_1, option_2, option_3, answer;

    public QuestionsAndAnswerSet (String question, String option_1, String option_2, String option_3, String answer){
        this.question = question;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption_1() {
        return option_1;
    }

    public void setOption_1(String option_1) {
        this.option_1 = option_1;
    }

    public String getOption_2() {
        return option_2;
    }

    public void setOption_2(String option_2) {
        this.option_2 = option_2;
    }

    public String getOption_3() {
        return option_3;
    }

    public void setOption_3(String option_3) {
        this.option_3 = option_3;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static ArrayList<QuestionsAndAnswerSet> getQuestionsAndAnswerSets(){
        ArrayList<QuestionsAndAnswerSet> questionsAndAnswerSets = new ArrayList<>();
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("What is the key predictor of your cardio vascular health?", "Resting Heart Rate", "Heart Rate", "Heart Size","Resting Heart Rate"));
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("What hormone is released you get 20-30 minutes of aerobic activity?", "Dopamine", "Serotonin", "BDNF","BDNF"));
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("What age are you at high risk of heart disease?", "Under 20", "Between 20 and 45", "Over 45","Over 45"));
    return questionsAndAnswerSets;
    }
}
