package com.yiyuaninfo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.yiyuaninfo.R;
import com.yiyuaninfo.adapter.NoteAdapter;
import com.yiyuaninfo.entity.Note;
import com.yiyuaninfo.view.FullLinearLayout;

import java.util.ArrayList;


/**
 * Created by gaocongcong on 2017/8/14.
 */

public  class  Activity  extends   AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<Note> noteArrayList;
    NoteAdapter noteAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        recyclerView=(RecyclerView)findViewById(R.id.rv1);
        initData();
        noteAdapter = new NoteAdapter(noteArrayList);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new FullLinearLayout(this));
        //recyclerView.addItemDecoration(new RecycleViewDivider(getApplication(), 8));
        recyclerView.setHasFixedSize(true);
    }
    private void initData() {
        noteArrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Note note = new Note();
            note.setTitle("tan6458_" + i);
            noteArrayList.add(note);
        }
    }

}
