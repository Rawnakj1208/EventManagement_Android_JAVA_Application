package edu.bd.ewu.signuplogin;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpcomingEvents extends AppCompatActivity {

    private TextView createNew;
    ListView listView ;

    ArrayList <Event> ListItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

        createNew = findViewById(R.id.createnew_btn);
        listView = findViewById(R.id.listview);

        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpcomingEvents.this, EventInfoActivity.class);
                startActivity(i);
            }
        });

        loadData();

        CustomEventAdapter cse = new CustomEventAdapter(this, ListItems);
        listView.setAdapter(cse);

    }

    private  void loadData(){

        KeyValueDB kvd = new KeyValueDB(getApplicationContext());
        Cursor res = kvd.getAllKeyValues();

        if(res.getCount()==0){
            return;
        }
        while(res.moveToNext()){
            String key = res.getString(0);
            String values = res.getString(1);

            String[] allValues = values.split(",");

            String name = allValues[0];
            String date = allValues[1];
            String place = allValues[2];
            String email = allValues[3];
            String Capacity = allValues[4];
            String Budget = allValues[5];

            String phone=allValues[6];
            String description = allValues[7];


            Event e = new Event(key,name,date,place,email, Capacity, Budget,phone,description);
            ListItems.add(e);

        }


    }
}