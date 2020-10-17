package mongkhonsin.atsadawut.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mongkhonsin.atsadawut.quizgame.model.WordItem;

public class WordListActivity extends AppCompatActivity {
    static public WordItem[] item = {
            new WordItem(R.drawable.tiger, "TIGER"),
            new WordItem(R.drawable.dog, "DOG"),
            new WordItem(R.drawable.dolphin, "DOLPHIN"),
            new WordItem(R.drawable.rabbit, "RABBIT"),
            new WordItem(R.drawable.pig, "PIG"),
            new WordItem(R.drawable.penguin, "PENGUIN"),
            new WordItem(R.drawable.koala, "KOALA"),
            new WordItem(R.drawable.owl, "OWL"),
            new WordItem(R.drawable.lion, "LION"),
            new WordItem(R.drawable.cat, "CAT")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        //*****//

        List<WordItem> mWordList = Arrays.asList(item);
        MyAdapter myAdapter = new MyAdapter(WordListActivity.this, mWordList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WordListActivity.this);
        RecyclerView recyclerView = findViewById(R.id.word_list_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

    }
}
class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    final Context mContext;
    final List<WordItem> mWordList;

    public MyAdapter(Context context, List<WordItem> mWordList) {
        this.mContext = context;
        this.mWordList = mWordList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.imageView.setImageResource(mWordList.get(position).imageResId);
        holder.wordTextView.setText(mWordList.get(position).word);
        holder.item = mWordList.get(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        View rootView;
        ImageView imageView;
        TextView wordTextView;
        WordItem item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            imageView = itemView.findViewById(R.id.image_view);
            wordTextView = itemView.findViewById(R.id.word_text_view);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent = new Intent(mContext, WordDetailsActivity.class);
                /*intent.putExtra("Word", item.word);
                intent.putExtra("image", item.imageResId);*/
                String itemJson  = new Gson().toJson(item);
                intent.putExtra("item", itemJson);
                mContext.startActivity(intent);
                }
            });
        }
    }
}
