package com.example.gbcassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gbcassignment2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DEBUG";

    // properties
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Setting the App Title(Dynamically) for Login Screen
        this.binding.appTitle.setText(dbForApp.getAppTitle());

        //Using the shared preference to store key value pair
        SharedPreferences sharedPref = getSharedPreferences("Login_Preference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //checking if the valid username is stored in shared preference or not
        String savedPasswordForUser = sharedPref.getString(dbForApp.getDbForAppSingleton().getValidUserCredentialsList().get(0).getUserName(), null);
        /*
          Checking weather we need to skip the Login Screen or not By
          matching the credentials in the shared preference with the credentials ours credentialsList
          in the dbForApp(Singleton class)
        */

        if (sharedPref.getBoolean("rememberMeSwitchStatus", false) && savedPasswordForUser!=null ){
            //Taking user to (Lesson List Screen)
            if(savedPasswordForUser.equals(dbForApp.getDbForAppSingleton().
                    getValidUserCredentialsList().get(0).getPassword())){
                Log.d(TAG,"user has saved password");
                Intent intent = new Intent(getApplicationContext(), LessonListActivity.class);
                startActivity(intent);
            }
        } else {
            //Adding dynamic listener for the login button
            this.binding.loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "Login button clicked");

                    String Username = binding.etUsername.getText().toString();
                    String Password = binding.etPassword.getText().toString();
                    // checking if the username & password is null or not
                    if (Username != null && Password != null) {
                        // checking if the username & password is empty or not
                        if (!Username.trim().isEmpty() && !Password.trim().isEmpty()) {
                            // Adding data in sharedPreference (Username=key,Password=value)
                            editor.putString(Username, Password);
                            editor.apply();

                            String validityStatus =
                                    dbForApp.getDbForAppSingleton().checkCredentialValidity(new User(Username, Password));
                            if (validityStatus.equals("successfulLogin")) {
                                //Taking user to (Lesson List Screen) only if the login is successful
                                Intent intent = new Intent(getApplicationContext(), LessonListActivity.class);
                                //resetting the username & password
                                binding.etUsername.setText("");
                                binding.etPassword.setText("");
                                //calling finish() so that user cannot come back to login screen using the back button
                                startActivity(intent);
                            } else {
                                //Displaying the validityStatus in the toastBar
                                generateToastBar(validityStatus);
                            }
                        } else {
                            //Displaying toastBar message if Username OR Password is null
                            String text = "Provide Valid Username & Password";
                            generateToastBar(text);
                        }

                    } else {
                       //Displaying toastBar message if Username OR Password is null
                        String text = "Please Enter Username & Password";
                        generateToastBar(text);
                    }
                }
            });

           //Adding Change Listener for the Remember Me Switch
            this.binding.rememberMeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.d(TAG, "Remember Me Switch value changed to = " + b);
                   //Adding Remember Me Switch in sharedPreference
                    editor.putBoolean("rememberMeSwitchStatus", b);
                    editor.apply();
                }
            });
        }
    }

    public void generateToastBar(String message){
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(getApplicationContext(), message, duration).show();
    }

}