package edu.bd.ewu.signuplogin;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomEventAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final ArrayList<Event> values;

    public CustomEventAdapter(@NonNull Context context, @NonNull ArrayList<Event> items) {
        super(context, -1, items);
        this.context = context;
        this.values = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_event_row, parent, false);

        TextView eventName = rowView.findViewById(R.id.tvEventName);
        TextView eventDateTime = rowView.findViewById(R.id.tvEventDateTime);
        TextView eventPlaceName = rowView.findViewById(R.id.tvEventPlaceName);
        /*TextView eventEmail=rowView.findViewById(R.id.tvEventEmail);
        TextView eventCapacity=rowView.findViewById(R.id.tvEventcapacity);
        TextView eventBudget=rowView.findViewById(R.id.tvEventcapacity);
        TextView eventPhone=rowView.findViewById(R.id.tvEventPhone);
        TextView eventDiscription=rowView.findViewById(R.id.tvEventDiscription);

*/



        Event e = values.get(position);
        eventName.setText(e.name);
        eventDateTime.setText(e.datetime);
        eventPlaceName.setText(e.place);
       /* eventEmail.setText(e.email);
        eventCapacity.setText(e.capacity);
        eventBudget.setText(e.budget);
        eventPhone.setText(e.phone);
        eventDiscription.setText(e.description);*/

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e.name;
                String datetime = e.datetime;
                String place = e.place;
                String email=e.email;
                String capacity=e.capacity;
                String budget=e.budget;
                String phone=e.phone;
                String description=e.description;

                Bundle bundle = new Bundle();
                bundle.putString("nameKey",name);
                bundle.putString("datetimeKey",datetime);
                bundle.putString("placeKey",place);
                bundle.putString("emailKey",email);
                bundle.putString("capacityKey",capacity);
                bundle.putString("budgetKey",budget);
                bundle.putString("phoneKey",phone);
                bundle.putString("descriptionKey",description);
            ;

                Intent intent = new Intent(context, showdetails.class);
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });

        return rowView;
    }
}
