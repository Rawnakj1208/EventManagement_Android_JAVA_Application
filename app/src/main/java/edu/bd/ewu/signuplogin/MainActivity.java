package edu.bd.ewu.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText Name,Email,Phone,UserID,Password,ReEnterPassword;
    private TextView login,signText,account;
    private Button ExitBtn,GoBtn;
    private CheckBox box1, box2;
    SharedPreferences getSharedPreferences;
    SharedPreferences.Editor myEdit;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=(EditText) findViewById(R.id.Name);
        Email=(EditText) findViewById(R.id.Email);
        Phone=(EditText) findViewById(R.id.Phone);
        UserID=(EditText) findViewById(R.id.UserID);
        Password=(EditText) findViewById(R.id.Password);
        ReEnterPassword=(EditText) findViewById(R.id.ReEnterPassword);
        login=(TextView)findViewById(R.id.login);
        signText=(TextView)findViewById(R.id.signText);
        account=(TextView)findViewById(R.id.account);
        ExitBtn= (Button) findViewById(R.id.ExitBtn);
        GoBtn= (Button) findViewById(R.id.GoBtn);
        box1 = (CheckBox)  findViewById(R.id.checkbox1);
        box2 = (CheckBox)  findViewById(R.id.checkbox2);

       /* GoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=Name.getText().toString();
            }
        });*/

        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signText.setText("Login");
                Name.setVisibility(View.INVISIBLE);
                Email.setVisibility(View.INVISIBLE);
                Phone.setVisibility(View.INVISIBLE);
                ReEnterPassword.setVisibility(View.INVISIBLE);
                account.setText("Haven't Register yet?");
                login.setText("SignUp");
                ReEnterPassword.setVisibility(View.INVISIBLE);
            }
        });*/


        getSharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        myEdit = getSharedPreferences.edit();


        if(getSharedPreferences.contains("userId") && getSharedPreferences.contains("userPwd")){
            loginUi();
        }else userSignup();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUi();

            }

        });

        findViewById(R.id.ExitBtn).setOnClickListener(view -> finish());

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private void loginUi(){

        signText.setText("Login In");
        ReEnterPassword.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        Name.setVisibility(View.GONE);
        account.setVisibility(View.GONE);


        if (getSharedPreferences.contains("userId")) {
            if(getSharedPreferences.getBoolean("rememberUserId",true)){
                box1.setChecked(true);
                UserID.setText(getSharedPreferences.getString("userId", ""));
            }
        }
        if (getSharedPreferences.contains("userPwd")) {
            if(getSharedPreferences.getBoolean("rememberpass",true)){
                box2.setChecked(true);
                Password.setText(getSharedPreferences.getString("userPwd", ""));
            }
        }
        else{
            Toast.makeText(MainActivity.this, "No user found" , Toast.LENGTH_LONG).show();
        }

        GoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matchInfo="";

                boolean rememberUser = box1.isChecked();
                boolean rememberPass = box2.isChecked();

                if(getSharedPreferences.getString("userId","").equals(UserID.getText().toString())){
                    if(getSharedPreferences.getString("userPwd","").equals(Password.getText().toString())){

                        myEdit.putBoolean("rememberUserId", rememberUser);
                        myEdit.putBoolean("rememberpass", rememberPass);
                        myEdit.commit();

                        Intent i = new Intent(MainActivity.this,UpcomingEvents .class);
                        startActivity(i);
                        finish();
                    }else matchInfo = matchInfo+"Password ";
                }else matchInfo = matchInfo+"User ID ";

                if(!matchInfo.isEmpty()){
                    matchInfo = matchInfo+" does not match";
                    Toast.makeText(MainActivity.this, matchInfo, Toast.LENGTH_LONG).show();
                }else  Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();


            }
        });


    }

    private void userSignup() {

        GoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = Name.getText().toString();
                String userEmail = Email.getText().toString();
                String userPhone = Phone.getText().toString().trim();
                String userId = UserID.getText().toString().trim();
                String userPwd = Password.getText().toString().trim();
                String userRePwd = ReEnterPassword.getText().toString().trim();
                boolean rememberUser = box1.isChecked();
                boolean rememberPass = box2.isChecked();

                boolean error= false;
                if (Name.getText().toString().length()<2) {
                    Name.setError("Error in name field");
                    error = true;
                }
                else {
                    System.out.println("Name :" + Name.getText());
                }

                if (!isEmailValid(Email.getText().toString())) {
                    Email.setError("Error in name field");
                    error = true;
                }
                else {
                    System.out.println("Email :" + Email.getText());
                }
                if (Phone.getText().toString().length()<10) {
                    Name.setError("Error in Phone field");
                    error = true;
                }
                else {
                    System.out.println("Phone :" + Phone.getText());
                }
                if (Password.getText().toString().length()<5) {
                    Name.setError("Error in password field");
                    error = true;
                }
                else {

                }
                if(!error){

                    System.out.println("user Name: " + userName);
                    System.out.println("user Email: " + userEmail);
                    System.out.println("user Phone Number: " + userPhone);
                    System.out.println("user Id: " + userId);
                    System.out.println("user pwd: " + userPwd);

                    if (userPwd.equals(userRePwd)) {
                        myEdit.putString("userName", userName);
                        myEdit.putString("userEmail", userEmail);
                        myEdit.putString("userPhone", userPhone);
                        myEdit.putString("userId", userId);
                        myEdit.putString("userPwd", userPwd);
                        myEdit.putBoolean("rememberUserId", rememberUser);
                        myEdit.putBoolean("rememberpass", rememberPass);
                        myEdit.commit();

                        Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, UpcomingEvents.class));
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Confirm Password doesn't match.", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}