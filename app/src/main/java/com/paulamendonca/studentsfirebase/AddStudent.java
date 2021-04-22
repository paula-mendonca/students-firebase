package com.paulamendonca.studentsfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference students = root.child(MainActivity.STUDENTS_KEY);
    EditText txtName, txtCourse, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        txtName = findViewById(R.id.txtName);
        txtCourse = findViewById(R.id.txtCourse);
        txtEmail = findViewById(R.id.txtEmail);
    }

    public void addStudent (View v){
        Student s = new Student();
        s.setName(txtName.getText().toString());
        s.setCourse(txtCourse.getText().toString());
        s.setEmail(txtEmail.getText().toString());

        String key = students.push().getKey();
        students.child(key).setValue(s);
        finish();
    }

}