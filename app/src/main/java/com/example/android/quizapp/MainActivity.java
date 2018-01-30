package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private boolean answerQ1 = false;
    private boolean answerQ2 = false;
    private boolean answerQ3 = false;
    private boolean answerQ4 = false;
    private double score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * method to get the text typed in the 1st edit text box
     *
     * @return whatever typed as string
     */
    public String userName() {
        EditText userName = (EditText) findViewById(R.id.id_user_name_text_view);
        return userName.getText().toString();
    }

    /**
     * to check the choosed answer of q1
     *
     * @param view
     */
    public void onRadioButtonClickedQ1(View view) {
        // check if the button checked or not
        boolean checked = ((RadioButton) view).isChecked();
        // if the right answer is choosed
        if (view.getId() == R.id.choice1_q1) {
            answerQ1 = true; score++;
        } else {
            answerQ1 = false;
        }
       // Toast.makeText(getBaseContext(), answerQ1 + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * to check the choosed answer of q2
     *
     * @param view
     */
    public void onRadioButtonClickedQ2(View view) {
        // check if the button checked or not
        boolean checked = ((RadioButton) view).isChecked();
        // if the right answer is choosed
        if (view.getId() == R.id.choice1_q2) {
            answerQ2 = true; score++;
        } else {
            answerQ2 = false;
        }
    }

    /**
     * method to get the text typed in the question3 edit text box
     *
     * @return whatever typed as string
     */
    public void arrangLettersQ3() {
        EditText q3 = (EditText) findViewById(R.id.id_question3);
        String q3Text = (q3.getText()).toString();
        if (validateText(q3Text).equalsIgnoreCase("tree")){ // to check if the input word is tree or no
            answerQ3 = true; score ++;
        }
    }

    /**
     * to assoicate the check boxes with the code
     * ande check if each one is checked or not ?
     * if the correct boxes checked > meaning that q4 is correct
     */
    public void vowelLettersQ4() {
        CheckBox choice1 = (CheckBox) findViewById(R.id.choice1_q4);
        boolean c1_q4 = choice1.isChecked();
        CheckBox choice2 = (CheckBox) findViewById(R.id.choice2_q4);
        boolean c2_q4 = choice2.isChecked();
        CheckBox choice3 = (CheckBox) findViewById(R.id.choice3_q4);
        boolean c3_q4 = choice3.isChecked();
        CheckBox choice4 = (CheckBox) findViewById(R.id.choice4_q4);
        boolean c4_q4 = choice4.isChecked();
        CheckBox choice5 = (CheckBox) findViewById(R.id.choice5_q4);
        boolean c5_q4 = choice5.isChecked();
        CheckBox choice6 = (CheckBox) findViewById(R.id.choice6_q4);
        boolean c6_q4 = choice6.isChecked();
        CheckBox choice7 = (CheckBox) findViewById(R.id.choice7_q4);
        boolean c7_q4 = choice7.isChecked();
        CheckBox choice8 = (CheckBox) findViewById(R.id.choice8_q4);
        boolean c8_q4 = choice8.isChecked();
        CheckBox choice9 = (CheckBox) findViewById(R.id.choice9_q4);
        boolean c9_q4 = choice9.isChecked();

        // check if the incorrect boxes all checked so it's true
        if ((!c1_q4 && !c6_q4 && !c8_q4 && !c9_q4)&&(c2_q4 && c3_q4 && c4_q4 && c5_q4 && c7_q4 )) {
            answerQ4 = true; score ++;
        }
        else {
            answerQ4 = false;
        }

    }

    /**
     * run when the button clicked
     * set the result text view visible and show the result on it
     *
     * @param view
     */
    public void clickResultButton(View view) {

        arrangLettersQ3(); // to run q3 method
        vowelLettersQ4();  // to run q4 method
        TextView text = (TextView) findViewById(R.id.id_result_text_view);
        text.setVisibility(view.VISIBLE);
        text.setText(setResultText());
        Toast.makeText(getBaseContext(), succeeded()? "succeeded" : "failed" ,Toast.LENGTH_LONG).show();
        score = 0; //reset score
    }

    /**
     *  to get all Strings and arrange them to be ready to be viewed
     * @return
     */
    public String setResultText() {

        return
                "Your name :" + validateText(userName()) +
                        "\nScore :" + (score/4)*100+ "%" +
                        "\nAnswer Q1 :" + answerQ1 +
                        "\nAnswer Q2 :" + answerQ2 +
                        "\nAnswer Q3 :" + answerQ3 +
                        "\nAnswer Q4 :" + answerQ4 ;


    }

    /**
     * to check the input text by some conditions
     * @param text
     * @return
     */
    public String validateText(String text) {
        //only characters allowed and not numbers?

        if (text.length() <= 30 ){
        String regex = "^[a-zA-Z]+$";
        if (!text.matches(regex)) {
            text = "not valid";
        }
        }
        else
            text = "not valid";
        return text;
    }

    /**
     * to check if score more than or equal 50%
     * @return true else false
     */
    public boolean succeeded (){
        return (score/4 >= 0.5) ; // as 4 #Questions
    }





}
