package com.example.khalilurrehman.roomcrud.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khalilurrehman.roomcrud.Models.NotesData;
import com.example.khalilurrehman.roomcrud.R;

import java.util.List;


/**
 * Created by mandeep on 4/9/17.
 */

public class getNotesAdapter extends RecyclerView.Adapter<getNotesAdapter.MyViewHolder> {
    private Context mContext;
    private List<NotesData> notelist;
    public class MyViewHolder extends RecyclerView.ViewHolder  {

        public TextView note_title,
                note_text,
                note_date;
        CardView note_cardview;

        public MyViewHolder(View view) {
            super(view);
            note_title = view.findViewById(R.id.note_title);
            note_text = view.findViewById(R.id.note_text);
            note_date = view.findViewById(R.id.note_date);
            note_cardview = view.findViewById(R.id.note_card);

        }
    }


    public getNotesAdapter(Context mContext, List<NotesData> noticeLists) {
        this.mContext = mContext;
        this.notelist = noticeLists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notice_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NotesData notesData = notelist.get(position);
        holder.note_title.setText(notesData.getTITLE());
        holder.note_text.setText(notesData.getNOTES());
        holder.note_date.setText(notesData.getDATE());

    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }


}
