package mongkhonsin.atsadawut.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import mongkhonsin.atsadawut.quizgame.model.WordItem;

public class WordDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details);

        Intent intent = getIntent();
        /*String word = intent.getStringExtra("Word");
        int image = intent.getIntExtra("image", 0);*/

        String itemJsom = intent.getStringExtra("item");
        WordItem item = new Gson().fromJson(itemJsom, WordItem.class);

        ImageView iv = findViewById(R.id.image_view);
        TextView tv = findViewById(R.id.word_text_view);

        iv.setImageResource(item.imageResId);
        tv.setText(item.word);
    }
}