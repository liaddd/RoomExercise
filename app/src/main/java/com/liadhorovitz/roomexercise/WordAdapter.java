package com.liadhorovitz.roomexercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liad Horovitz on 01,July,2021
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private List<Word> words = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word word = words.get(position);

        holder.wordTV.setText(word.getWord());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public void updateList(List<Word> newWords){
        words.clear();
        words.addAll(newWords);
        notifyDataSetChanged();
    }

    public void updateList(Word newWord){
        words.add(newWord);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView wordTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wordTV = itemView.findViewById(R.id.word_list_item_text_view);
        }
    }
}
