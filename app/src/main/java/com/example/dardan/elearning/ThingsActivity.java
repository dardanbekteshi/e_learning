package com.example.dardan.elearning;

import android.accounts.AccountManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ThingsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton rightButton;
    private ImageButton leftButton;
    private ImageView quizButton;
    private ImageView mainPicture;
    private TextView mainName;
    private ImageButton audioButton;
    private RelativeLayout relativeLayout;
    private MediaPlayer mediaPlayer;
    Thing currentThing;
    Category currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();//return the intent that started this activity
        int position = intent.getIntExtra("position", 0);
        currentCategory = CategoriesActivity.categories.get(position);
        currentCategory.goToFirstThing();
        setTheme(currentCategory.theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainName = (TextView) findViewById(R.id.thingName);
        mainPicture = (ImageView) findViewById(R.id.thingImage);
        rightButton = (ImageButton) findViewById(R.id.buttonRightThing);
        leftButton = (ImageButton) findViewById(R.id.buttonLeftThing);
        audioButton = (ImageButton) findViewById(R.id.buttonAudioThing);
        relativeLayout = (RelativeLayout) findViewById(R.id.thingLayout);
        quizButton = (ImageView) findViewById(R.id.buttonQuiz);

        rightButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
        audioButton.setOnClickListener(this);
        mainPicture.setOnClickListener(this);
        quizButton.setOnClickListener(this);

        currentThing = currentCategory.currentThing();//
        updateResources();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLeftThing:
                currentThing = currentCategory.prevThing();
                updateResources();
                break;
            case R.id.buttonRightThing:
                currentThing = currentCategory.nextThing();
                updateResources();
                break;
            case R.id.buttonAudioThing:
                playSound();
                break;
            case R.id.thingImage:
                if (currentThing.hasNoise()) {
                    playNoise();
                }
                break;
            case R.id.buttonQuiz:
                Intent previousIntent = getIntent();
                int position = previousIntent.getIntExtra("position", 0);
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
                break;
        }


    }

    protected void updateResources() {
        if (currentThing.hasNoise()) {
            playNoise();
            //wait until the sound noise finishes
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer player) {
                    player.release();
                    playSound();
                }
            });

        } else {
            playSound();
        }

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = this.getTheme(); //gets the current Theme
        theme.resolveAttribute(R.attr.colorAccent, typedValue, true);//merr vleren e nje atributi te kesaj theme dhe e vendos ne typedValue
        int accentColor = typedValue.data;

        setButtonColor(leftButton, accentColor);
        setButtonColor(rightButton, accentColor);
        setButtonColor(audioButton, accentColor);

        theme.resolveAttribute(R.attr.colorPrimaryLight, typedValue, true);//merr vleren e atributit background - fillimisht duhet te deklarohet ne attrs.xml
        int primaryLightColor = typedValue.data;

        mainPicture.setBackgroundColor(primaryLightColor);
        relativeLayout.setBackgroundColor(primaryLightColor);
        mainName.setBackgroundColor(primaryLightColor);

        setTitle(currentCategory.title);
        mainPicture.setImageResource(currentThing.getImage());
        mainName.setText(currentThing.getText());

        rightButton.setVisibility(currentCategory.hasNextThing() ? View.VISIBLE : View.INVISIBLE);
        leftButton.setVisibility(currentCategory.hasPrevThing() ? View.VISIBLE : View.INVISIBLE);
    }

    private void setButtonColor(ImageButton button, int color) {
        GradientDrawable bgShape = (GradientDrawable) button.getBackground();
        bgShape.setColor(color);
    }

    protected void playSound() {
        mediaPlayer = MediaPlayer.create(this, currentThing.getSound());
        mediaPlayer.start();
    }

    protected void playNoise() {
        mediaPlayer = MediaPlayer.create(this, currentThing.getNoise());
        mediaPlayer.start();
    }
}
