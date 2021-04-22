

package com.paulamendonca.studentsfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String STUDENTS_KEY = "students";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference students = root.child(STUDENTS_KEY);
    FirebaseListAdapter<Student> listAdapter;
    ListView listStudent;

    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        listStudent = findViewById(R.id.listStudent);
        listAdapter = new FirebaseListAdapter<Student>(this, Student.class, R.layout.student_list_item, students) {
            @Override
            protected void populateView(View v, Student model, int position) {
                TextView txtStudentName = v.findViewById(R.id.txtStudentName);
                txtStudentName.setText(model.getName());
            }
        };
        listStudent.setAdapter(listAdapter);
        listStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference item = listAdapter.getRef(position);
                item.removeValue();
                return false;
            }
        });

        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference item = listAdapter.getRef(position);
                changeToUpdate(item.getKey(), listAdapter.getItem(position));
            }
        });
    }

    public void changeToAdd (View v) {
        Intent it = new Intent(getBaseContext(), AddStudent.class);
        startActivity(it);
    }

    public void changeToUpdate (String key, Student s) {
        Intent it = new Intent(getBaseContext(), EditStudent.class);
        it.putExtra("KEY", key);
        it.putExtra("STD", s);
        startActivity(it);
    }
}