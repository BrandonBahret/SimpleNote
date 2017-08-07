package com.brandonbahret.simplenote;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Brandon on 8/6/2017.
 * RecyclerView Adapter class for notes.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    //region Member attributes
    private ArrayList<Note> mNotes;
    private AppCompatActivity mContext;
    //endregion

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        public Toolbar toolbar;
        public TextView text;
        public LinearLayout noteContent;

        public NoteViewHolder(View itemView) {
            super(itemView);

            toolbar = (Toolbar) itemView.findViewById(R.id.note_toolbar);
            text = (TextView) itemView.findViewById(R.id.note_text);
            noteContent = (LinearLayout) itemView.findViewById(R.id.note_content);
        }
    }

    public NoteAdapter(AppCompatActivity context, ArrayList<Note> notes) {
        mContext = context;
        mNotes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_view_layout, parent, false);

        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final Note note = mNotes.get(position);

        holder.text.setText(note.getText());
        holder.toolbar.setTitle(note.getName());

        holder.toolbar.inflateMenu(R.menu.note_view_menu);
        holder.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case(R.id.action_delete):{
                        //TODO: Delete note from database
                    }break;

                    case(R.id.action_send):{
                        //TODO: Send note
                    }break;
                }
                return false;
            }
        });

        holder.noteContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Open note editor activity for note
                NoteEditorActivity.startActivity(mContext, note);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

}
