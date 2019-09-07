package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.TestQuestion;


import static com.example.myapplication.Common.list;
import static com.example.myapplication.Common.score;

public class TestQuestionPlaying extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton answer1,answer2,answer3,answer4;
    TextView questionText, correctAnswers, badAnswers, remainingAnswers;
    Button confirmBtn;
    int index=-1;
    String correctAnswer;
    int numberCorrAns, numberBadAns, numberRemainAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_question_playing);
        init();
    }

    private void init() {
        radioGroup = findViewById(R.id.rg_qt);
        answer1=findViewById(R.id.rb_tq_answer1);
        answer2=findViewById(R.id.rb_tq_answer2);
        answer3=findViewById(R.id.rb_tq_answer3);
        answer4=findViewById(R.id.rb_tq_answer4);

        correctAnswers = findViewById(R.id.rigthAnswers);
        badAnswers = findViewById(R.id.badAnswers);
        remainingAnswers = findViewById(R.id.remaining_questions);

        questionText = findViewById(R.id.questionText);
        confirmBtn=findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);
                if(checkedRadioButton.getText().equals(correctAnswer)){
                    //correct question
                    score++;
                }
                showQuestion(++index);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showQuestion(++index);
    }

    private void showQuestion(int i) {
        //inicialization of list of question - without database
        if(list.isEmpty()) {
            list.add(new TestQuestion("Every ________ got a small present at the Christmas party.", "children", "the child", "child", "the children", "child"));
            list.add(new TestQuestion("Otazka2", "odpoved1", "odpoved2", "odpoved3", "odpoved4", "odpoved1"));
            list.add(new TestQuestion("Otazka3", "odpoved1", "odpoved2", "odpoved3", "odpoved4", "odpoved1"));
            list.add(new TestQuestion("Otazka4", "odpoved1", "odpoved2", "odpoved3", "odpoved4", "odpoved1"));
            list.add(new TestQuestion("Otazka5", "odpoved1", "odpoved2", "odpoved3", "odpoved4", "odpoved1"));
            list.add(new TestQuestion("Otazka6", "odpoved1", "odpoved2", "odpoved3", "odpoved4", "odpoved1"));
        }
        setTitle("All, every, none, no, each, either, apod.");
        int nbrQuestions = list.size();
        if(i<nbrQuestions){
            TestQuestion question = list.get(i);
            correctAnswer = question.getCorrectAnswer();
            questionText.setText(question.getQuestionText());
            answer1.setText(question.getAnswer1());
            answer2.setText(question.getAnswer2());
            answer3.setText(question.getAnswer3());
            answer4.setText(question.getAnswer4());

            correctAnswers.setText("Správně: "+ score);
            badAnswers.setText("Špatně: " + (i-score));
            remainingAnswers.setText("Nevyzkoušeno: " + (nbrQuestions-i));
        }
        else{
            Toast.makeText(TestQuestionPlaying.this,"Konec!",Toast.LENGTH_SHORT).show();
        }


    }
}
