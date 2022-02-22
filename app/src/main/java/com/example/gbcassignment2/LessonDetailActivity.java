package com.example.gbcassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbcassignment2.databinding.ActivityLessonDetailBinding;

public class LessonDetailActivity extends AppCompatActivity {

    private ActivityLessonDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLessonDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        //Getting the list item position which is being passed with the intent
        int position = intent.getIntExtra("position",0);

        //Getting video details using the position from the LessonList class
        VideoMetaData selectedVideoMetaData = LessonList.getSingleton().getLectureList()[position];

        // getting out EditText view for the Lecture Notes
        EditText etLectureNotes = findViewById(R.id.lectureNotes);

        //populating the Lesson Details in various TextView's
        this.binding.videoTitle.setText(selectedVideoMetaData.getVideoTitle());
        this.binding.lengthDetail.setText(selectedVideoMetaData.getVideoDuration());
        this.binding.videoDescription.setText(selectedVideoMetaData.getVideoDescription());

        //Using the shared preference to store key value pair
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Button saveNotesButton = this.binding.saveNote;

        //Checking the Visibility of Completion Status(TextView)  & completionButton(Button)
        Button completionButton = this.binding.completionButton;
        TextView tvCompletionStatus = this.binding.completionStatus;
        //Check Completion Status of VideoMetaData(class) isCompleted Variable for setting visibility
        if(selectedVideoMetaData.isCompleted()){
            tvCompletionStatus.setVisibility(View.VISIBLE);
            completionButton.setVisibility(View.GONE);
        }

        //Setting if there are any saved notes for that lecture
        etLectureNotes.setText(sharedPref.getString("lesson"+position+"SavedNotes",""));

        //Adding dynamic listener for the save notes button
        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG","saving notes");
                //Displaying toast
                CharSequence text = "Saving Notes!";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(getApplicationContext(), text, duration).show();
                //Adding data in sharedPreference
                editor.putString("lesson"+position+"SavedNotes",etLectureNotes.getText().toString());
                editor.apply();
            }
        });

        //Adding Dynamic listener for the Mark Completed Button
        completionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG","Mark Completed Pressed");
                //Hide the Mark Completed button
                completionButton.setVisibility(view.GONE);
                //Show the CompletionStatus
                tvCompletionStatus.setVisibility(view.VISIBLE);
                //Updating the Lesson Completion Status
                selectedVideoMetaData.setCompleted(true);
                //updating the list view about the completion change
                LessonListActivity.updateListView();
            }
        });

        //Adding Dynamic listener for the Watch lesson button
        this.binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG","Watch Lesson button Pressed");
                gotoURL(selectedVideoMetaData.getVideoURL().toString());

            }
        });

    }

    public void gotoURL(String s)
    {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}