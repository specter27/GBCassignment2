package com.example.gbcassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.example.gbcassignment2.databinding.ActivityLessonListBinding;


public class LessonListActivity extends AppCompatActivity {

    private static LectureListAdapter lectureListAdapter;

    private ActivityLessonListBinding activityLessonListBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLessonListBinding = ActivityLessonListBinding.inflate(getLayoutInflater());
        setContentView(activityLessonListBinding.getRoot());

        //Setting the App Title(Dynamically) for Lesson List Screen
        this.activityLessonListBinding.textView.setText(dbForApp.getAppTitle());

        //Using the shared preference to store key value pair
        SharedPreferences sharedPref = getSharedPreferences("Login_Preference", Context.MODE_PRIVATE);


        //Specifying the Custom Adapter for ListView on the LessonListActivity
        lectureListAdapter = new LectureListAdapter(this,
                LessonList.getSingleton().getLectureList());

        //getting the ListView
        ListView videoListView = findViewById(R.id.lectureList);
        videoListView.setAdapter(lectureListAdapter);

        //Adding Change Listener for the Force Sequential Progression Switch
        activityLessonListBinding.sequentialProgressionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true){
                    Log.d("DEBUG","Force Sequential Progression Switch is set to TRUE");
                    //Setting the isSequentialProgressionEnabled to true in the LessonList( Singleton class )
                    LessonList.getSingleton().setSequentialProgressionEnabled(true);
                }else{
                    Log.d("DEBUG","Force Sequential Progression Switch is set to FALSE");
                    //Setting the isSequentialProgressionEnabled to false in the LessonList( Singleton class )
                    LessonList.getSingleton().setSequentialProgressionEnabled(false);
                }
                //Updating the lesson list view about the changes in case there are any
                updateListView();
            }
        });

        //Adding clickListener for the listView
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Navigating from Lesson List view to the lesson Detail view
                Log.d("DEBUG","Lesson list item at "+" position="+position+" clicked");
                Intent intent = new Intent(LessonListActivity.this, LessonDetailActivity.class);
                
                //we are passing the position of the list item to the LessonDetailActivity
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

        //Adding dynamic listener for the logout button
        activityLessonListBinding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG","Logout button pressed");
                // clearing the data using the clearLoginSharedPreference() in Login Screen Activity
                sharedPref.edit().clear().apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void updateListView(){
        //Updating the lesson list view about the changes in case there are any
        lectureListAdapter.notifyDataSetChanged();
    }
}