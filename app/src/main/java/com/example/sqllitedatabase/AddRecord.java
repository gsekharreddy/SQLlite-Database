package com.example.sqllitedatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.DatePickerDialog;

import java.util.Calendar;

import android.widget.DatePicker;

import java.util.ArrayList;


public class AddRecord extends AppCompatActivity {

	Button b1;
	Spinner sp;
	String name, qual, dob;
	ArrayList<String> list = new ArrayList<>();
	EditText et1, et2;

	StudentData studentData;
	Toolbar tb;
	private String[] qualif = {
			"Matriculation",
			"Intermediate",
			"Bachelor of Technology",
			"Master of Technology",
			"Master of Science"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_add_record);

		b1 = findViewById(R.id.b1);
		sp = findViewById(R.id.sp);
		et1 = findViewById(R.id.et1);
		et2 = findViewById(R.id.et2);
		tb = findViewById(R.id.tb);
		setSupportActionBar(tb);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, qualif);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);

		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				qual = qualif[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// Optional: handle if nothing is selected
			}
		});

		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				name = et1.getText().toString();
				dob = et2.getText().toString();
				list.add(name + " | " + dob + " | " + qual);
				Toast.makeText(AddRecord.this, "Record Added", Toast.LENGTH_SHORT).show();
			}
		});
		et2.setFocusable(false); // So keyboard won't show
		et2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				int day = c.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog datePickerDialog = new DatePickerDialog(
						AddRecord.this,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
								// month + 1 because month starts from 0
								String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
								et2.setText(selectedDate);
							}
						},
						year, month, day
				);
				datePickerDialog.show();
			}
		});

		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
	}
}
