package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ModuleActivity extends AppCompatActivity implements View.OnClickListener{

    private String moduleName;
    private TextView tvModuleName;
    private Button btAttemptQuiz;
    private Button btMoreInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        setModuleName();

        tvModuleName = findViewById(R.id.tvModuleName);
        tvModuleName.setText(getModuleName()+" Module");
        btMoreInfo = findViewById(R.id.btMoreInfo);
        btAttemptQuiz = findViewById(R.id.btAttemptQuiz);
        btAttemptQuiz.setOnClickListener(this);
        btMoreInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchModule(moduleName);
            }
        });
    }

    private void setModuleName(){
        Intent intent = getIntent();
        moduleName = intent.getStringExtra("Module Name");
    }

    private String getModuleName(){
        return moduleName;
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(this, ModuleQuizActivity.class);
        startActivity(intent);
        intent.putExtra("Module Name", getModuleName());
    }
    public void searchModule(String moduleName) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=" + moduleName+" health"));
        startActivity(browserIntent);
    }

}