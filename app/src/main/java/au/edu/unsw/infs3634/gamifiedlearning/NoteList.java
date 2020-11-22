package au.edu.unsw.infs3634.gamifiedlearning;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;


//This is the adapter for the listView that displays all of the notes made for a particular module
public class NoteList extends ArrayAdapter<String> {

    private Activity context;
    private List<String> noteList;

    public NoteList(Activity context, List<String> noteList) {
        super(context, R.layout.list_layout, noteList);
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView tvNote = (TextView) listViewItem.findViewById(R.id.tvNotes);

        String note = noteList.get(position);

        tvNote.setText(note);

        return listViewItem;
    }
}
