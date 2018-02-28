package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private boolean answerQ1 = false;
    private boolean answerQ2 = false;
    private boolean answerQ3 = false;
    private boolean answerQ4 = false;

    private EditText userName;
    private EditText q3;
    private Button result;

    private RadioGroup radioGroupQ1;
    private RadioGroup radioGroupQ2;

    private CheckBox choice1;
    private CheckBox choice2;
    private CheckBox choice3;
    private CheckBox choice4;
    private CheckBox choice5;
    private CheckBox choice6;
    private CheckBox choice7;
    private CheckBox choice8;
    private CheckBox choice9;

    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate username edit text
        userName = (EditText) findViewById(R.id.id_user_name_text_view);

        // initiate q1 & q2 radio groups
        radioGroupQ1 = (RadioGroup) findViewById(R.id.id_radio_group_q1);
        radioGroupQ2 = (RadioGroup) findViewById(R.id.id_radio_group_q2);

        // initiate q3 edit text
        q3 = (EditText) findViewById(R.id.id_question3);


        // initialize q4 checkboxes
        choice1 = (CheckBox) findViewById(R.id.choice1_q4);
        choice2 = (CheckBox) findViewById(R.id.choice2_q4);
        choice3 = (CheckBox) findViewById(R.id.choice3_q4);
        choice4 = (CheckBox) findViewById(R.id.choice4_q4);
        choice5 = (CheckBox) findViewById(R.id.choice5_q4);
        choice6 = (CheckBox) findViewById(R.id.choice6_q4);
        choice7 = (CheckBox) findViewById(R.id.choice7_q4);
        choice8 = (CheckBox) findViewById(R.id.choice8_q4);
        choice9 = (CheckBox) findViewById(R.id.choice9_q4);


        // initiate result button
        result = (Button) findViewById(R.id.id_result_button);

        // initiate result textView
        resultTextView = (TextView) findViewById(R.id.id_result_text_view);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result.getText().equals(getString(R.string.result_button_text))) { // get result
                    result.setText(getString(R.string.reset_button_text)); // change text to (reset)
                    arrangLettersQ3(); // to run q3 method
                    vowelLettersQ4();  // to run q4 method

                    resultTextView.setVisibility(VISIBLE);
                    resultTextView.setText(setResultText());

                    Toast.makeText(getBaseContext(), succeeded() ? "succeeded" : "failed", Toast.LENGTH_LONG).show();
                } else if (result.getText().equals(getString(R.string.reset_button_text))) { // reset all values

                    result.setText((getString(R.string.result_button_text))); // change text to (result)
                    defautMode();

                }
            }
        });

    }

    /**
     * to set all values to default mode
     */
    public void defautMode() {
        userName.setText(R.string.hint_user_name); // clear username

        radioGroupQ1.clearCheck(); // clear q1 options
        radioGroupQ2.clearCheck(); // clear q2 options

        q3.setText(R.string.hint_question3); // clear q3

        // clear q4 options
        choice1.setChecked(false);
        choice2.setChecked(false);
        choice3.setChecked(false);
        choice4.setChecked(false);
        choice5.setChecked(false);
        choice6.setChecked(false);
        choice7.setChecked(false);
        choice8.setChecked(false);
        choice9.setChecked(false);

        // hide result textView
        resultTextView.setVisibility(GONE);
    }

    /**
     * method to get the text typed in the 1st edit text box
     *
     * @return whatever typed as string
     */
    public String userName() {
        return userName.getText().toString();
    }

    /**
     * to check the choosed answer of q1
     *
     * @param view
     */
    public void onRadioButtonClickedQ1(View view) {
        // check if the button checked or not
        if (((RadioButton) view).isChecked())
            // if the right answer is choosed
            answerQ1 = view.getId() == R.id.choice1_q1;
        // Toast.makeText(getBaseContext(), answerQ1 + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * to check the choosed answer of q2
     *
     * @param view
     */
    public void onRadioButtonClickedQ2(View view) {
        // check if the button checked or not
        if (((RadioButton) view).isChecked())
            // if the right answer is choosed
            answerQ2 = view.getId() == R.id.choice1_q2;
    }

    /**
     * method to get the text typed in the question3 edit text box
     *
     * @return whatever typed as string
     */
    public void arrangLettersQ3() {
        String q3Text = (q3.getText()).toString();
        if (validateText(q3Text).equalsIgnoreCase("tree")) { // to check if the input word is tree or no
            answerQ3 = true;
        }
    }

    /**
     * to assoicate the check boxes with the code
     * ande check if each one is checked or not ?
     * if the correct boxes checked > meaning that q4 is correct
     */
    public void vowelLettersQ4() {
        boolean c1_q4 = choice1.isChecked();
        boolean c2_q4 = choice2.isChecked();
        boolean c3_q4 = choice3.isChecked();
        boolean c4_q4 = choice4.isChecked();
        boolean c5_q4 = choice5.isChecked();
        boolean c6_q4 = choice6.isChecked();
        boolean c7_q4 = choice7.isChecked();
        boolean c8_q4 = choice8.isChecked();
        boolean c9_q4 = choice9.isChecked();

        // check if the incorrect boxes all checked so it's true
        answerQ4 = (!c1_q4 && !c6_q4 && !c8_q4 && !c9_q4) && (c2_q4 && c3_q4 && c4_q4 && c5_q4 && c7_q4);

    }

    /**
     * to get all Strings and arrange them to be ready to be viewed
     *
     * @return
     */
    public String setResultText() {

        return
                "Your name :" + validateText(userName()) +
                        "\nScore :" + (getPercentScore() / 4) * 100 + "%" +
                        "\nAnswer Q1 :" + answerQ1 +
                        "\nAnswer Q2 :" + answerQ2 +
                        "\nAnswer Q3 :" + answerQ3 +
                        "\nAnswer Q4 :" + answerQ4;


    }

    /**
     * to increment score by 1 for each correct question
     *
     * @return
     */
    public double getPercentScore() {
        double score = 0;
        if (answerQ1) score++;
        if (answerQ2) score++;
        if (answerQ3) score++;
        if (answerQ4) score++;
        return score;
    }

    /**
     * to check the input text by some conditions
     *
     * @param text
     * @return
     */
    public String validateText(String text) {
        //only characters allowed and not numbers?

        if (text.length() <= 30) {
            String regex = "^[a-zA-Z]+$";
            if (!text.matches(regex)) {
                text = "not valid";
            }
        } else
            text = "not valid";
        return text;
    }

    /**
     * to check if score more than or equal 50%
     *
     * @return true else false
     */
    public boolean succeeded() {
        return (getPercentScore() / 4 >= 0.5); // as 4 #Questions
    }


}
