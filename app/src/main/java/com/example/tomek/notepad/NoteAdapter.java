package com.example.tomek.notepad;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {

    private Context context;
    private int layoutResourceId;
    private List<Note> data = null;

    public NoteAdapter(Context context, int layoutResourceId, List<Note> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        NoteHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new NoteHolder();
            holder.noteTitle = (TextView)row.findViewById(R.id.noteTitle);
            holder.noteContent = (TextView)row.findViewById(R.id.noteContent);
            holder.noteDate = (TextView)row.findViewById(R.id.noteDate);


            row.setTag(holder);
        }
        else
        {
            holder = (NoteHolder)row.getTag();
        }

        Note note = data.get(position);
        holder.noteTitle.setText(String.format(context.getString(R.string.note_number), note.getId()));

        String title = note.getRawText();
        if (title.length() != 0) {
            holder.noteContent.setText(note.getRawText());
        }
        else {
            //TODO Finding out if there is picture on note
            holder.noteContent.setText("INFO: Note has no text");
        }

        holder.noteDate.setText(context.getString(R.string.last_updated) + ": " + note.getFormattedDateUpdatted());

        return row;
    }

    public void setData(List<Note> data) {
        this.data = (ArrayList<Note>) data;
    }

    public List<Note> getData() {
        return data;
    }

    static class NoteHolder
    {
        private TextView noteTitle;
        private TextView noteContent;
        private TextView noteDate;
    }
}