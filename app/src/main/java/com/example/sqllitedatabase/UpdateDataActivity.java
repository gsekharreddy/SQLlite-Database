package com.example.sqllitedatabase;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqllitedatabase.MainActivity;

import java.util.Calendar;

public class UpdateDataActivity extends AppCompatActivity {

	EditText et_name,et_dob,et_qual;
	Button update,delete;
	DataBaseHelper dbHelper;
	String name, date, qualification;

	Calendar calendar;

	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.updatedata);
		et_name = findViewById(R.id.editTextname);
		et_dob = findViewById(R.id.editTextdate);
		et_qual = findViewById(R.id.editTextqual);
		update = findViewById(R.id.buttonUpdate);
		delete = findViewById(R.id.buttonDelete);
		dbHelper = new DataBaseHelper(UpdateDataActivity.this);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
		}

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(UpdateDataActivity.this, MainActivity.class);
				startActivity(i);
			}
		});

		name = getIntent().getStringExtra("name");
		date = getIntent().getStringExtra("dob");
		qualification = getIntent().getStringExtra("qualification");

		et_name.setText(name);
		et_dob.setText(date);
		et_qual.setText(qualification);

		et_dob.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog datePickerDialog = new DatePickerDialog(
						UpdateDataActivity.this,
						(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
							String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
							et_dob.setText(selectedDate);
							et_dob.setFocusable(false);
						},
						year, month, day
				);

				datePickerDialog.show();
			}
		});

		update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dbHelper.updateStudentData(name,et_name.getText().toString(),et_dob.getText().toString(),et_qual.getText().toString());
				Toast.makeText(UpdateDataActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(UpdateDataActivity.this, MainActivity.class);
				startActivity(i);
			}
		});

		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dbHelper.deleteStudentData(name);
				Toast.makeText(UpdateDataActivity.this, "Deleted the course", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(UpdateDataActivity.this, MainActivity.class);
				startActivity(i);
			}
		});


	}
}