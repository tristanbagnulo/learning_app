package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ModuleActivity extends AppCompatActivity implements View.OnClickListener{

    private String moduleName;
    private TextView tvModuleName;
    private Button btAttemptQuiz;
    private Button btMoreInfo;
    private TextView  tvDescription;
    private ArrayList <Module> modules;
    private Module module;
    private TextView tvData;
    private CheckBox readBox;
    private EditText etMakeNotes;
    private Button btMakeNotes, btSeeMyNotes;

    // 1.1 - Get a DatabaseReference
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        //1.2- Get a database reference (cont.)
        mDatabase = FirebaseDatabase.getInstance().getReference("note");

        tvDescription = findViewById(R.id.tvDesctiption);
        tvData = findViewById(R.id.tvData);
        setModuleName();
        modules = Module.getModules();

        //Iterate through the array list of modules to get the one that was selected and display that
        //module's data
        for (final Module module : modules) {
            if(moduleName.equals(module.getModuleName())){
                tvDescription.setText(module.getModuleDescription());
                tvData.setText(module.getModuleData());
            }
        }
        tvModuleName = findViewById(R.id.tvModuleName);
        String textModuleName = getModuleName()+getString(R.string.module_text);
        tvModuleName.setText(textModuleName);
        btMoreInfo = findViewById(R.id.btMoreInfo);
        btAttemptQuiz = findViewById(R.id.btAttemptQuiz);
        btAttemptQuiz.setOnClickListener(this);
        btMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchModule(moduleName);
            }
        });
        readBox = findViewById(R.id.checkBox);

        //Adds a little gamification flare for the user. Green text follows completion of task
        // (positive reinforcement).
        readBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    readBox.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.threeOfThreeGreen));

                } else {
                    readBox.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                }
            }
        });
        etMakeNotes = findViewById(R.id.etAddNotes);
        btMakeNotes = findViewById(R.id.btMakeNotes);
        btMakeNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setNote();
            }
        });
        btSeeMyNotes = findViewById(R.id.btSeeMyNotes);
        btSeeMyNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModuleActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
    }

    //Retrieves the selected module name from previous activity
    private void setModuleName(){
        Intent intent = getIntent();
        moduleName = intent.getStringExtra("Module Name");

    }

    //Takes user input
    String note;
    private void setNote(){
        //Accept text input from the user and assign to String variable 'note'
        note = etMakeNotes.getText().toString().trim();
        Toast.makeText(this, "Notes entered are: " + note, Toast.LENGTH_LONG).show();
        //Clear contents of the Plain Text widget for allow space for new notes
        etMakeNotes.setText("");
        uploadNote();
    }

    //Uploads the note into Firebase under the "note" entity (referenced above)
    private void uploadNote(){
        //Aim: store the note with a unique ID in Firebase's Realtime Database

        //2.1 - Create unique string inside of Note. getKey() gets that key for us.
        String id = mDatabase.push().getKey();

        //2.2 - Use setValue() method to store the notes into the Firebase database
        assert id != null;
        mDatabase.child(id).setValue(note);
    }

    private String getModuleName(){
        return moduleName;
    }

    //When user clicks "Attempt Quiz" they're showed to the quiz screen
    @Override
    public void onClick(View v){
        Intent intent = new Intent(this, ModuleQuizActivity.class);
        Toast.makeText(this, "String in Module Activity: "+moduleName, Toast.LENGTH_LONG).show();
        intent.putExtra("module_name", moduleName);
        startActivity(intent);
    }

    //Sends user to a Google search of the health topic
    public void searchModule(String moduleName) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=" + moduleName+" health"));
        startActivity(browserIntent);
    }

}