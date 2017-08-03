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

        currentThing = currentCategory.currentThing();
        updateResources();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // it is considered good practice to release the Media Player object
        // when the activity is stopped
        releaseMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // it is considered good practice to release the Media Player object
        // when the activity is paused
        releaseMediaPlayer();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // closes the Activity when the back button on the action bar is pressed
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
                playSound(currentThing.getSound());
                break;
            case R.id.thingImage:
                if (currentThing.hasNoise()) {
                    playSound(currentThing.getNoise());
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

    /**
     * Plays the appropriate sound/noise for the current thing and
     * updates the UI (button color & text, background color etc.) based on the
     * current Category and Thing
     */
    protected void updateResources() {
        if (currentThing.hasNoise()) {
            playSound(currentThing.getNoise());
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer player) {
                    player.reset();
                    playSound(currentThing.getSound());
                }
            });
        } else
            playSound(currentThing.getSound());

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = this.getTheme(); //gets the current Theme

        // retrieves the color value from this theme and puts it in the typedValue variable
        theme.resolveAttribute(R.attr.colorAccent, typedValue, true);
        int accentColor = typedValue.data;

        setButtonColor(leftButton, accentColor);
        setButtonColor(rightButton, accentColor);
        setButtonColor(audioButton, accentColor);

        // merr vleren e atributit background - fillimisht duhet te deklarohet ne attrs.xml
        theme.resolveAttribute(R.attr.colorPrimaryLight, typedValue, true);
        int primaryLightColor = typedValue.data;

        mainPicture.setBackgroundColor(primaryLightColor);
        relativeLayout.setBackgroundColor(primaryLightColor);
        mainName.setBackgroundColor(primaryLightColor);
        setTitle(currentCategory.title);

        // make the picture Invisible and then Visible to add some animation
        mainPicture.setVisibility(View.INVISIBLE);
        mainPicture.setImageResource(currentThing.getImage());
        mainPicture.setVisibility(View.VISIBLE);
        quizButton.setImageResource(currentCategory.quizImage);
        mainName.setText(currentThing.getText());

        rightButton.setVisibility(currentCategory.hasNextThing() ? View.VISIBLE : View.INVISIBLE);
        leftButton.setVisibility(currentCategory.hasPrevThing() ? View.VISIBLE : View.INVISIBLE);
    }

    private void setButtonColor(ImageButton button, int color) {
        GradientDrawable bgShape = (GradientDrawable) button.getBackground();
        bgShape.setColor(color);
    }

    /**
     * Plays the sound/noise which is passed as an argument.
     * If the media player is in the middle of playing another sound/noise,
     * it stops and resets the player and starts playing the sound.
     *
     * @param sound the sound to be played by the player
     */
    private void playSound(int sound) {
        // if the player is in the middle of playing another sound/noise
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            stopAndResetPlayer();
        }
        mediaPlayer = MediaPlayer.create(this, sound);
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer player) {
                player.reset();
            }
        });
    }

    /**
     * Stops and resets the Media Player associated with this Activity
     */
    private void stopAndResetPlayer() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    /**
     * Releases the media player (if is not null) and sets it to null
     */
    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
