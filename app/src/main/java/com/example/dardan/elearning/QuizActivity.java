package com.example.dardan.elearning;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    Category currentCategory;
    ArrayList<Thing> things;
    private ImageView mainPicture;
    private RelativeLayout relativeLayout;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private Thing thingAnswer;
    private TextView questionTextView;
    private TextView scoreTextView;
    private int score = 0;
    private int questionNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();//return the intent that started this activity
        int position = intent.getIntExtra("position", 0);
        currentCategory = CategoriesActivity.categories.get(position);
        setTheme(currentCategory.theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button

        things = currentCategory.getListOfThings();
        relativeLayout = (RelativeLayout) findViewById(R.id.quizLayout);
        mainPicture = (ImageView) findViewById(R.id.quizImage);
        answer1 = (RadioButton) findViewById(R.id.answer1);
        answer2 = (RadioButton) findViewById(R.id.answer2);
        answer3 = (RadioButton) findViewById(R.id.answer3);
        scoreTextView = (TextView) findViewById(R.id.scoreCounter);
        questionTextView = (TextView) findViewById(R.id.questionCounter);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        updateResources();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    protected void updateResources() {
        // if the quiz has just started
        if (questionNumber == 1) {
            scoreTextView.setText("Score: " + 0);
        } else if (questionNumber > 10) {
            this.finish();
            Highscores.open(this);
            if(Highscores.setHighscore(currentCategory.columnName, score))
                Toast.makeText(this,"New Highscore!",Toast.LENGTH_LONG).show();
            Highscores.close();
            return;
        }
        questionTextView.setText("Question: " + questionNumber);
        questionNumber++;
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = this.getTheme(); //gets the current Theme
        theme.resolveAttribute(R.attr.colorPrimaryLight, typedValue, true); //merr vleren e atributit background - fillimisht duhet te deklarohet ne attrs.xml
        int primaryLightColor = typedValue.data;
        mainPicture.setBackgroundColor(primaryLightColor);
        relativeLayout.setBackgroundColor(primaryLightColor);

        Random r = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 3) {
            set.add(r.nextInt(things.size()));
        }
        // three random Thing indexes. e.g., [2, 6, 9] which may represent:
        // [Apple, Mango, Pear]
        Integer[] answers = set.toArray(new Integer[set.size()]);
        // indexes [0, 1 , 2] to randomly fetch the Thing indexes:
        // something like: [0-Apple, 1-Mango and 2-Pear]
        ArrayList<Integer> indexes = new ArrayList<>(Arrays.asList(0, 1, 2));
        //a random index to set the picture question:
        // e.g., 1-Mango
        int index = indexes.get(r.nextInt(indexes.size()));
        thingAnswer = things.get(answers[index]);
        mainPicture.setImageResource(thingAnswer.getImage());

        setRandomAnswer(answer1, indexes, answers);
        setRandomAnswer(answer2, indexes, answers);
        setRandomAnswer(answer3, indexes, answers);
    }

    private void setRandomAnswer(RadioButton button, ArrayList<Integer> indexes, Integer[] answers) {
        // params:
        // e.g., indexes = [0, 1, 2]
        // e.g., answers [2, 6, 9]
        Random r = new Random();
        // random index from [0, 1, 2]. e.g., 1-Mango
        int index = indexes.get(r.nextInt(indexes.size()));
        // e.g., remove the index 1 so Mango won't appear two times as answer
        indexes.remove(Integer.valueOf(index));
        // indexes = [0, 2]
        button.setText(things.get(answers[index]).getText());
    }


    @Override
    public void onClick(final View v) {
        if (v instanceof RadioButton) {
            if (((RadioButton) v).getText() == thingAnswer.getText()) {
                score++;
                scoreTextView.setText("Score: " + score);
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
            }
        }
        Handler handler = new Handler();
        // wait 2 seconds before going to the next question
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateResources();
                if (v instanceof RadioButton)
                    ((RadioButton) v).setChecked(false);
            }
        }, 2000);
//        // lambda expression as a replacement for the Runnable anonymous class
//        handler.postDelayed(() -> updateResources(), 2000);
    }
}
