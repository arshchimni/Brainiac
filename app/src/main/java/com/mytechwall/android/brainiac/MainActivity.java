package com.mytechwall.android.brainiac;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
//game it is
    Button startButton;
    Button playAgain;
    Button button;
    Button button2;
    Button button3;
    Button button4;

    TextView scoreTextView;
    TextView questionText;
    TextView answerShow;
    TextView timerTextView;

    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score;
    int questionNumber;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    public void start(View go) {
        startButton.setVisibility(View.INVISIBLE);
        RelativeLayout startGame=(RelativeLayout) findViewById(R.id.startGane);
        startGame.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgain));


    }

    public void playAgain(View view){
        score=0;
        questionNumber=0;
        generateQuestions();
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        answerShow.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        button.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        new CountDownTimer(30000+100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("00");
                playAgain.setVisibility(View.VISIBLE);
                answerShow.setVisibility(View.VISIBLE);
                answerShow.setText("Your Score:: "+score+"/"+questionNumber);
                button.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);

            }
        }.start();

    }

    public void selectAnswer(View selected) {
        Button answerSelect = (Button) selected;
        System.out.println(Integer.parseInt(answerSelect.getTag().toString()));
        if (Integer.parseInt(answerSelect.getTag().toString()) == locationOfCorrectAnswer) {
            answerShow.setText("Correct");
            score++;
        } else {
            answerShow.setText("Incorrect");
        }
        answerShow.setVisibility(View.VISIBLE);
        questionNumber++;
        scoreTextView.setText(score + "/" + questionNumber);
        generateQuestions();
    }

    public void generateQuestions() {
        Random random = new Random();

        int a = random.nextInt(51);
        int b = random.nextInt(51);

        questionText.setText(a + "+" + b);
        locationOfCorrectAnswer = random.nextInt(4);
        int inccorect;
        answer.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add(a + b);
            } else {
                inccorect = random.nextInt(199);
                while (inccorect == (a + b)) {
                    inccorect = random.nextInt(199);
                }
                answer.add(inccorect);
            }
        }

        button.setText("" + answer.get(0));
        button2.setText("" + answer.get(1));
        button3.setText("" + answer.get(2));
        button4.setText("" + answer.get(3));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        answerShow = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionText = (TextView) findViewById(R.id.questionTextView);
        timerTextView =(TextView)findViewById(R.id.timerTextView);

        playAgain=(Button)findViewById(R.id.playAgain);
        startButton = (Button) findViewById(R.id.startButton);
        button = (Button) findViewById(R.id.scoreButton);
        button2 = (Button) findViewById(R.id.scoreButton2);
        button3 = (Button) findViewById(R.id.scoreButton3);
        button4 = (Button) findViewById(R.id.scoreButton4);



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
