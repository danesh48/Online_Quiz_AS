package com.example.online_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answersRadioGroup;
    private Button submitBtn;

    private int currentQuestionIndex = 0;
    private int score = 0;

    // Questions and Answers (Replace with actual quiz data)
    private String[] questions = {
            "What is the maximum length of a Python identifier?",
            "How is a code block indicated in Python?",
            "Which of the following concepts is/are not a part of Python?",
            "Which of the following statements are used in Exception handling in Python?",
            "Which of the following types of loops are not supported in Python?"

    };

    private String[][] answerOptions = {
            {"32", "16", "128", "No fixed length is specified"},
            {"Brackets", "Indentation", "Key", "None of the above"},
            {"Pointers", "Loops", "Dynamic Typing", "All of the above"},
            {"try", "except", "finally", "All of the above"},
            {"for", "while", "do-while", "None of the above"}
    };

    private int[] correctAnswers = {3, 1, 0,3,2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answersRadioGroup = findViewById(R.id.answersRadioGroup);
        submitBtn = findViewById(R.id.submitBtn);

        displayQuestion(currentQuestionIndex);

        submitBtn.setOnClickListener(v -> checkAnswer());
    }

    private void displayQuestion(int index) {
        if (index < questions.length) {
            questionTextView.setText(questions[index]);
            answersRadioGroup.removeAllViews();
            for (int i = 0; i < answerOptions[index].length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(answerOptions[index][i]);
                radioButton.setId(i);
                answersRadioGroup.addView(radioButton);
            }
        } else {
            // Quiz finished, show the score
            questionTextView.setText("Quiz Finished! Your Score: " + score);
            answersRadioGroup.setVisibility(View.GONE);
            submitBtn.setVisibility(View.GONE);
        }
    }

    private void checkAnswer() {
        int selectedAnswerId = answersRadioGroup.getCheckedRadioButtonId();
        if (selectedAnswerId == correctAnswers[currentQuestionIndex]) {
            score++;
        }
        currentQuestionIndex++;
        displayQuestion(currentQuestionIndex);
    }
}

