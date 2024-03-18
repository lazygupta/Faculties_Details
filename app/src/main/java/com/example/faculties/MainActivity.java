package com.example.faculties;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextContactDetails;
    private EditText editTextDepartment;
    private FacultyManagementSystem facultyManagementSystem;
    private MyDBHelper dbHelper;
    private TextView textViewDisplayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextContactDetails = findViewById(R.id.editTextContactDetails);
        editTextDepartment = findViewById(R.id.editTextDepartment);
        Button buttonAddFaculty = findViewById(R.id.buttonAddFaculty);
        Button buttonFetchData = findViewById(R.id.buttonFetchData);
        textViewDisplayData = findViewById(R.id.textViewDisplayData);

        // Initialize Database Helper
        dbHelper = new MyDBHelper(this);

        // Calling Database Model
        ArrayList<FacultiesModel> arrModel = dbHelper.getFaculties();

        // Initialize FacultyManagementSystem
        facultyManagementSystem = new FacultyManagementSystem();

        // Set onClickListener for the add faculty button
        buttonAddFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String contactDetails = editTextContactDetails.getText().toString().trim();
                String department = editTextDepartment.getText().toString().trim();

                // Check if any field is empty
                if (name.isEmpty() || contactDetails.isEmpty() || department.isEmpty()) {
                    // Show toast message if any field is empty
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a new Faculty object
                    Faculty faculty = new Faculty(name, contactDetails, department);

                    // Add the faculty to the FacultyManagementSystem
                    facultyManagementSystem.addFaculty(faculty);

                    // Add the faculty to the database
                    dbHelper.addFaculties(name, contactDetails, department);

                    // Show success message
                    Toast.makeText(MainActivity.this, "Faculty added successfully", Toast.LENGTH_SHORT).show();

                    // Clear EditText fields
                    editTextName.getText().clear();
                    editTextContactDetails.getText().clear();
                    editTextDepartment.getText().clear();
                }
            }
        });

        // Set onClickListener for the fetch data button
        buttonFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllFaculties();
            }
        });



        for(int i=0; i<arrModel.size(); i++){
            Log.d("FACULTIES_DETAILS", "Name: "+arrModel.get(i).name
                    + ", Phone No:"+arrModel.get(i).contact
                    + ", Department:"+arrModel.get(i).department);
        }
    }

    private void showAllFaculties() {
        ArrayList<FacultiesModel> arrModel = dbHelper.getFaculties();

        StringBuilder data = new StringBuilder();
        for(int i = 0; i < arrModel.size(); i++) {
            String name = arrModel.get(i).name;
            String contact = arrModel.get(i).contact;
            String department = arrModel.get(i).department;

            // Format faculty details
            String formattedData = String.format("Name: %s\nPhone No: %s\nDepartment: %s\n\n", name, contact, department);

            data.append(formattedData);
        }

        if (data.length() > 0) {
            // Set formatted text to TextView
            textViewDisplayData.setText(data.toString());

            // Customize text appearance
            textViewDisplayData.setTextColor(getResources().getColor(android.R.color.black));
            textViewDisplayData.setTextSize(16);
            textViewDisplayData.setLineSpacing(0, 1.5f);
            textViewDisplayData.setPadding(16, 16, 16, 16);
            textViewDisplayData.setBackgroundResource(R.drawable.ic_launcher_background); // Set a custom background drawable
        } else {
            // Display message if no faculties found
            textViewDisplayData.setText("No faculties found");
            textViewDisplayData.setTextColor(getResources().getColor(android.R.color.darker_gray));
            textViewDisplayData.setTextSize(18);
        }
    }

}