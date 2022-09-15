package edu.bd.ewu.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class showdetails extends AppCompatActivity {
    TextView name1,datetime1,place1,email1,capacity1,budget1,phone1,description1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetails);

        name1 = findViewById(R.id.name);
        datetime1 = findViewById(R.id.datetime);
        place1 = findViewById(R.id.place);
        email1 = findViewById(R.id.email);
        capacity1 = findViewById(R.id.capacity);
        budget1 = findViewById(R.id.budget);
        phone1 = findViewById(R.id.phone);
        description1 = findViewById(R.id.description);





        Intent i = this.getIntent();
        String Name = i.getStringExtra("nameKey");
        String DateTime = i.getStringExtra("datetimeKey");
        String Place = i.getStringExtra("placeKey");
        String Email=i.getStringExtra("emailKey");
        String Capacity=i.getStringExtra("capacityKey");
        String Budget=i.getStringExtra("budgetKey");
        String Phone=i.getStringExtra("phoneKey");
        String Description=i.getStringExtra("descriptionKey");



        name1.setText(Name);
        datetime1.setText(DateTime);
        place1.setText(Place);
        email1.setText(Email);
        capacity1.setText(Capacity);
        budget1.setText(Budget);
        phone1.setText(Phone);
        description1.setText(Description);





    }
}

