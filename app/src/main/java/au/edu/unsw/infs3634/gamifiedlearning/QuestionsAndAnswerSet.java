package au.edu.unsw.infs3634.gamifiedlearning;

import java.util.ArrayList;

public class QuestionsAndAnswerSet {

    public String question, option_1, option_2, option_3, answer;

    //This just holds Question, Options and Answer set objects. These are drawn from in the quiz activity
    //They're just collection of a question and a few options for users to choose from and a correct answer.
    //Same principle here as the Module Activity.

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
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("What activities should you avoid before going to sleep?", "Washing your dishes", "Doing some light reading", "Working on your laptop","Working on your laptop"));
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("How much water should you drink in a day?", "more that 6 liters", "5 liters", "2 liters","2 liters"));
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("Recommended hours of sleep a day?", "10 hours", "6 hours", "8 hours","8 hours"));
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("How many muscles in your body?", "600", "400", "894","400"));
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("What is not in the heart?", "bronchioles", "valves", "veins","bronchioles"));
        questionsAndAnswerSets.add(new QuestionsAndAnswerSet("What system is the hear apart of?", "circulatory system", "endocrine system", "lymphatic system","circulatory system"));
    return questionsAndAnswerSets;
    }
}
