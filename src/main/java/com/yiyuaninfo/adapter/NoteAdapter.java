package com.yiyuaninfo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Note;

import java.util.ArrayList;


/**
 * Created by tan6458 on 2016/4/16.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    ArrayList<Note> noteArrayList;

    public interface OnClickListener {
        void setOnClick(View view, int position);
    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public NoteAdapter(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        holder.tv_title.setText(noteArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        public NoteHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.setOnClick(v, getAdapterPosition());
                }
            });
        }
    }
}
