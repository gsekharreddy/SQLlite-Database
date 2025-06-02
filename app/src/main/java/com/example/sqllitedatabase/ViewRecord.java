package com.example.sqllitedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewRecord extends AppCompatActivity {

	ArrayList<StudentData> studentDataArrayList;
	DataBaseHelper dbHelper;
	DataAdapter dataAdapter;
	RecyclerView rv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_view_record);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
		}

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ViewRecord.this, MainActivity.class);
				startActivity(i);
			}
		});
		studentDataArrayList = new ArrayList<>();
		dbHelper = new DataBaseHelper(ViewRecord.this);
		rv = findViewById(R.id.rv);
		studentDataArrayList = dbHelper.fetchStudentData();
		dataAdapter = new DataAdapter(studentDataArrayList,ViewRecord.this);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewRecord.this, RecyclerView.VERTICAL, false);
		rv.setLayoutManager(linearLayoutManager);
		rv.setAdapter(dataAdapter);

	}
}