package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ModuleActivity extends AppCompatActivity {

    private TextView tvModuleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        Intent intent = getIntent();
        String moduleName = intent.getStringExtra("Module Name");

        tvModuleName = findViewById(R.id.tvModuleName);
        tvModuleName.setText(moduleName);
    }


}