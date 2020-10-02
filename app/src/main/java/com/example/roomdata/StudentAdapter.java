package com.example.roomdata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    List<StudentEntity> entities;

    public StudentAdapter(Context context, List<StudentEntity> entities) {
        this.context = context;
        this.entities = entities;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(context).inflate(R.layout.row_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.rollNum.setText(entities.get(position).getRollNum());
        holder.name.setText(entities.get(position).getName());


        holder.delete.setOnClickListener(v -> {
//            MainActivity.studentDataBase.studentDAO().deleteData(entities.get(position));
            MainActivity.viewModel.delete(entities.get(position));
            Toast.makeText(context, "Deleted " + holder.name.getText().toString(), Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        final TextView edit, name, delete, rollNum;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            edit = itemView.findViewById(R.id.edit);
            name = itemView.findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);
            rollNum = itemView.findViewById(R.id.rollNum);

            edit.setOnClickListener(v -> {
                String name = this.name.getText().toString();
                String rollNum = this.rollNum.getText().toString();

                Intent intent = new Intent(context , UpDateActivity.class);
                intent.putExtra("name" , name);
                intent.putExtra("rollNum" , rollNum);
                context.startActivity(intent);
            });
        }
    }
}
