package com.example.sqllitedatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
	private ArrayList<StudentData> studentDataArrayList;
	private Context context;

	public DataAdapter(ArrayList<StudentData> studentDataArrayList, Context context) {
		this.studentDataArrayList = studentDataArrayList;
		this.context = context;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		StudentData studentData = studentDataArrayList.get(position);
		holder.name.setText(studentData.getName());
		holder.dob.setText(studentData.getDob());
		holder.qualification.setText(studentData.getQualification());

		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context,UpdateDataActivity.class);
				i.putExtra("name",studentData.getName());
				i.putExtra("dob",studentData.getDob());
				i.putExtra("qualification",studentData.getQualification());
				context.startActivity(i);
			}
		});

	}

	@Override
	public int getItemCount() {
		return studentDataArrayList.size();
	}
	public class ViewHolder extends RecyclerView.ViewHolder {

		// creating variables for our text views.
		private TextView name, dob, qualification;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			// initializing our text views
			name = itemView.findViewById(R.id.tv_name);
			dob = itemView.findViewById(R.id.tv_dob);
			qualification = itemView.findViewById(R.id.tv_qual);

		}
	}
}
