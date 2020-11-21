package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button btNextNote;
    private TextView tvNotesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        mDatabase = FirebaseDatabase.getInstance().getReference("note");



        btNextNote = findViewById(R.id.btNextNote);
        btNextNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNote();
            }
        });

        tvNotesText = findViewById(R.id.tvNotesText);

    }

    //@Override
    protected void showNote() {
        super.onStart();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String note = snapshot.getChildren().toString();
                tvNotesText.setText(note);
                Toast.makeText(NotesActivity.this, note, Toast.LENGTH_SHORT).show();

                /*for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                }*/
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}