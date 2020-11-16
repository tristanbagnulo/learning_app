package au.edu.unsw.infs3634.gamifiedlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements MyAdapter.OnClickListener {

    private ArrayList<Module> modules = Module.getModules();
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);

        myAdapter = new MyAdapter(this, Module.getModules(), this);

        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //@Override
    public void onClick(int position/*, View v, String name*/){
        Log.d("TAG", "onClick: clicked.");
        String name = modules.get(position).getModuleName();
        Log.d("TAG", "DATA RECEIVED: "+name);
        Intent intent = new Intent(this, ModuleActivity.class);
        intent.putExtra("Module Name", name);
        startActivity(intent);
    }


}