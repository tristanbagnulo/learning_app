package au.edu.unsw.infs3634.gamifiedlearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //This is the Adapter for the RecyclerView where modules can be selected from

    private ArrayList<Module> modules;
    private OnClickListener mOnClickListener;
    private Context context;

    public MyAdapter(Context context, ArrayList<Module> modules, OnClickListener onClickListener){
        this.modules = modules;
        this.context = context;
        this.mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view, mOnClickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Module module = modules.get(position);

        holder.tvModuleName.setText(module.getModuleName());
        holder.ivModuleIcon.setImageResource(module.getModuleIcon());
        holder.tvModuleDescription.setText(module.getModuleDescription());

    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvModuleName, tvModuleDescription;
        ImageView ivModuleIcon;
        OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView, OnClickListener onClickListener){
            super(itemView);
            tvModuleName = itemView.findViewById(R.id.tvModuleName);
            ivModuleIcon = itemView.findViewById(R.id.ivModuleIcon);
            tvModuleDescription = itemView.findViewById(R.id.tvModuleDescription);
            this.onClickListener = onClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onClickListener.onClick(getAdapterPosition()/*, v, v.getTag().toString()*/);
        }
    }

    public interface OnClickListener{
        void onClick(int position/*, View v, String name*/);
    }
}
