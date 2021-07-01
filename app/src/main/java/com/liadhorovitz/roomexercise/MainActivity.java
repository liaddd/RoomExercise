package com.liadhorovitz.roomexercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText wordET;
    private WordAdapter wordAdapter;
    private Button saveBtn;
    private RecyclerView wordRV;
    private WordViewModel wordViewModel;
    private CheckBox sortCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        initViews();
        setListeners();
        setObservers();
    }

    private void initViews() {
        wordET = findViewById(R.id.word_edit_text);
        saveBtn = findViewById(R.id.word_save_button);
        sortCheckbox = findViewById(R.id.sort_word_checkbox);

        wordRV = findViewById(R.id.word_recycler_view);
        wordRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        wordRV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        wordAdapter = new WordAdapter();
        wordRV.setAdapter(wordAdapter);
    }

    private void setListeners() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordInput = wordET.getText().toString().trim();
                if (!wordInput.isEmpty()) {
                    Word word = new Word(wordInput);
                    wordAdapter.updateList(word);
                    wordRV.smoothScrollToPosition(wordAdapter.getItemCount());
                    wordViewModel.saveWord(word);

                    // clear edit text field
                    wordET.setText(null);
                }
            }
        });

        sortCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                wordViewModel.sortAlphabetized.postValue(isChecked);
            }
        });
    }

    private void setObservers() {
        getAllWords();

        wordViewModel.sortAlphabetized.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSortAlphabetized) {
                if (isSortAlphabetized) {
                    wordViewModel.getSortedWords().observe(MainActivity.this, new Observer<List<Word>>() {
                        @Override
                        public void onChanged(List<Word> words) {
                            wordAdapter.updateList(words);
                        }
                    });
                }else getAllWords();
            }
        });
    }

    private void getAllWords(){
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                wordAdapter.updateList(words);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}