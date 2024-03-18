import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.faculties.R;
import com.example.faculties;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextContactDetails;
    private EditText editTextDepartment;
    private Button buttonAddFaculty;
    private FacultyManagementSystem facultyManagementSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextContactDetails = findViewById(R.id.editTextContactDetails);
        editTextDepartment = findViewById(R.id.editTextDepartment);
        buttonAddFaculty = findViewById(R.id.buttonAddFaculty);

        // Initialize FacultyManagementSystem
        facultyManagementSystem = new FacultyManagementSystem();

        // Set onClickListener for the button
        buttonAddFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values from EditText fields
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

                    // Show success message
                    Toast.makeText(MainActivity.this, "Faculty added successfully", Toast.LENGTH_SHORT).show();

                    // Clear EditText fields
                    editTextName.getText().clear();
                    editTextContactDetails.getText().clear();
                    editTextDepartment.getText().clear();
                }
            }
        });
    }
}
