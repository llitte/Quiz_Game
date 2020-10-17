package mongkhonsin.atsadawut.quizgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import mongkhonsin.atsadawut.quizgame.model.WordItem;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mQuestionImageView;
    private Button[] mChoiceButton = new Button[4];
    private String mAnswerWord;
    private TextView scoreText;
    private Random r;
    private List<WordItem> itemsList;
    private int count = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionImageView = findViewById(R.id.question_image_view);
        scoreText = findViewById(R.id.score_text_view);
        scoreText.setText(score + " คะแนน");

        mChoiceButton[0] = findViewById(R.id.choice_1_button);
        mChoiceButton[1] = findViewById(R.id.choice_2_button);
        mChoiceButton[2] = findViewById(R.id.choice_3_button);
        mChoiceButton[3] = findViewById(R.id.choice_4_button);
        for(int i = 0; i < 4; i++){
            mChoiceButton[i].setOnClickListener(this);
        }
        r = new Random();
        newQuiz(r);
    }

    private void newQuiz(Random r) {
        itemsList = new ArrayList<>(Arrays.asList(WordListActivity.item));

        int answerIndex = r.nextInt(itemsList.size()), choiceIndex = r.nextInt(4);

        WordItem item = itemsList.get(answerIndex);
        mQuestionImageView.setImageResource(item.imageResId);
        mAnswerWord = item.word;
        mChoiceButton[choiceIndex].setText(item.word);
        itemsList.remove(answerIndex);

        Collections.shuffle(itemsList);
        for(Integer i = 0; i < 4; i++){
            if(i.equals(choiceIndex))
                continue;
            mChoiceButton[i].setText(itemsList.get(i).word);
        }
    }

    @Override
    public void onClick(View view){
        Button b = findViewById(view.getId());
        String buttonText = b.getText().toString();
        if(buttonText.equals(mAnswerWord)){
            score++;
            scoreText.setText(score + " คะแนน");
        }
        if(count < 5){
            count++;
            newQuiz(r);
        }
        if(count == 5){
            AlertDialog.Builder result = new AlertDialog.Builder(GameActivity.this)
                    .setTitle("สรุปผล")
                    .setMessage("คุณได้ "+ score +" คะแนน\n\nต้องการเล่นเกมใหม่หรือไม่")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            count = 0;
                            score = 0;
                            scoreText.setText(score + " คะแนน");
                            newQuiz(r);
                        }
                    });
            result.show();
        }
    }
}