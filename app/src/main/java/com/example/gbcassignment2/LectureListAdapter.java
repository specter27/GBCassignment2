package com.example.gbcassignment2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.gbcassignment2.databinding.VideoListLayoutBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class LectureListAdapter extends ArrayAdapter<VideoMetaData> implements Serializable {

    //Specifying our custom adapter for our ListView of the LessonListActivity

    public LectureListAdapter(Context context, VideoMetaData[] videoList) {
        super(context, 0,videoList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // 1. Setup view binding for the custom row layouts
        VideoListLayoutBinding binding;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.video_list_layout,parent,false);
        }
        binding = VideoListLayoutBinding.bind(convertView);
        // 2. Get the current item that we are trying to populate in the ListView
        VideoMetaData currVideoMetaData = getItem(position);
        if(currVideoMetaData.isCompleted()){
            // Display the checkmark
            binding.tickMark.setVisibility(View.VISIBLE);
        }
        binding.lessonNumber.setText(Integer.toString((position+1)));
        binding.lessonTitle.setText(currVideoMetaData.getVideoTitle());
        binding.lessonDuration.setText(currVideoMetaData.getVideoDuration());

        // 3. Return the instance of the view
        return convertView;
    }

    /*
        The data items in the ListView will be enabled or disabled is determined by its
        adapter and all adapter are  child of BaseAdapter.
        BaseAdapter has two methods:
            - public boolean isEnabled(int position)
            so we will override this method with our custom logic
     */
    @Override
    public boolean isEnabled(int position) {
        boolean enabled = true;
        // Implementing the Force Sequential Progression Feature
        if(position > 0 && LessonList.getSingleton().isSequentialProgressionEnabled()){
            if(!LessonList.getSingleton().getLectureList()[position-1].isCompleted()){
                enabled = false;
            }
        }
        return enabled;

    }
}
