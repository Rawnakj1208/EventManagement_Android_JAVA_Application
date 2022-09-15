package edu.bd.ewu.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class EventInfoActivity extends AppCompatActivity {



        Button btn_cancel, btn_share, btn_save;
        TextView show_result;

        EditText name_id, place_id, des_id,  capacity_id, budget_id, email_id, phone_id, date_id;
        RadioButton indoor, outdoor, online;

        KeyValueDB kvd = new KeyValueDB(EventInfoActivity.this);


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_event_info);

            btn_cancel = findViewById(R.id.cancel_btn);
            btn_save = findViewById(R.id.save_btn);
            btn_share = findViewById(R.id.share_btn);

            show_result = findViewById(R.id.result_id);
            indoor = findViewById(R.id.btn_indoor);
            outdoor = findViewById(R.id.btn_outdoor);
            online = findViewById(R.id.btn_online);
            name_id = findViewById(R.id.name_id);
            place_id = findViewById(R.id.place_id);
            des_id = findViewById(R.id.description_id);
            capacity_id = findViewById(R.id.capacity_id);
            email_id = findViewById(R.id.email_id);
            phone_id = findViewById(R.id.phone_id);
            date_id = findViewById(R.id.date_id);
            budget_id = findViewById(R.id.budget_id);




            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = name_id.getText().toString();
                    String place = place_id.getText().toString();
                    String capacity = capacity_id.getText().toString();
                    String budget = budget_id.getText().toString();
                    String email = email_id.getText().toString();
                    String phone = phone_id.getText().toString();
                    String description = des_id.getText().toString();
                    String date = date_id.getText().toString();

                    String errorMsg="";

                    if(name.length()<3){
                        errorMsg += "Invalid name\n";
                    }
                    if(place.length()<6){
                        errorMsg += "Invalid place\n";
                    }
                    if(budget.length() ==0 || Integer.parseInt(budget) < 25){
                        errorMsg += "invalid budget\n";
                    }
                    if(phone.length()<4){
                        errorMsg += "invalid phone\n";
                    }
                    if(email.length()<6){
                        errorMsg += "invalid email\n";
                    }
                    if(capacity.length() ==0 || Integer.parseInt(capacity) < 10){
                        errorMsg += "invalid capacity\n";
                    }
                    if(description.length()< 5){
                        errorMsg += "invalid description\n";
                    }
                    if(date.length() <3) {
                        errorMsg += "invalid date\n";
                    }


                    if(errorMsg.length() == 0)
                    {
                        String key = name + "-" + System.currentTimeMillis();
                        String values = name + "," + place + "," + date + "," + capacity + "," + budget + "," + email + ","+ description;
                        showDialog("Do you want to save data?", "info", "Yes","no", key, values);
                    }
                    else
                    {
                        showDialog(errorMsg,"Error", "ok","back", null, null);
                    }
                }

            });

        }


        private void showDialog(String message, String title,String btn1, String btn2, String key, String value)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message);
            builder.setTitle(title);
            builder.setCancelable(false)
                    .setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            if (btn1.equals("Yes")) {

                                System.out.println(key);
                                kvd.updateValueByKey(key, value);
                                String[] keys = {"action", "id", "semester", "key", " event"};
                                String[] values = {"backup", "2019-1-60-134", "2022-2", key,value};
                                httpRequest(keys, values);

                            }
                            //Util.getInstance().deleteByKey(MainActivity.this,key);
                            dialog.cancel();
                            //loadData();
                            //adapter.notifyDataSetChanged();


                        }
                    })
                    .setNegativeButton(btn2, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        @SuppressLint("StaticFieldLeak")
        private void httpRequest(final String keys[], final String values[]){

            new AsyncTask<Void, Void, String>(){
                @Override
                protected String doInBackground(Void...param) {
                    try{
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        for(int i =0; i<keys.length;i++){
                            params.add(new BasicNameValuePair(keys[i],values[i]));
                        }
                        String data = JSONParser.getInstance().makeHttpRequest( "http://muthosoft.com/univ/cse489/index.php","POST",params);
                        return data;
                    }catch (Exception e){e.printStackTrace();

                    }
                    return null;
                }
                @Override
                protected void onPostExecute(String data) {
                    if (data != null) {
                        try {
                            System.out.println(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


            }.execute();
        }

        @Override
        protected void onResume() {
            super.onResume();
            String[] keys = {"action", "id", "semester"};
            String[] values = {"backup", "2019-1-60-134", "2022-2"};
            httpRequest(keys, values);
        }
    }