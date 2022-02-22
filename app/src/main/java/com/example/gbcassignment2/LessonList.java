package com.example.gbcassignment2;

import java.util.ArrayList;

public class LessonList {

  /*
    This is a singleton class that act as a common lesson list Database for the app
     prevent us from  passing data from one activity to another
  */


    private static LessonList lessonList;
    //To maintain the record weather the  sequential progression is enabled or not
    private static boolean isSequentialProgressionEnabled;

    //Defining an Array for video Lecture meta data (Data source for the LessonList View)
    private VideoMetaData[]  videoLectureList = {new VideoMetaData("What is Algorithmic Thinking, Peak Finding?",
            "In This lesson we will learn about some interesting things,like algorithms and" +
                    " complexity of algorithms",
            "https://youtu.be/HtSuA80QTyo","Length: 53:22 min"),
            new VideoMetaData("What are Models of Computation, Document Distance?",
            "This about, what actually is an algorithm? What is an algorithm allowed to do? " +
                    "And also deep philosophical questions like, what is time?",
            "https://youtu.be/Zc54gFhdpLA","Length: 48:51 min"),
            new VideoMetaData("What is Insertion Sort, Merge Sort?",
            " We'll be talking about specific sorting algorithms today I want to start by motivating why" +
                    " we're interested in sorting, which should be fairly easy Then I want to discuss a particular " +
                    "sorting algorithm that's called insertion sort. ",
            "https://youtu.be/Kg4bqzAqRBM","Length: 51:20 min"),
            new VideoMetaData("Heaps and Heap Sort?",
                    "One of the cutest little data structures that was ever invented is called the heap.And we're going to use the heap as an example" +
                            "implementation of a priority queue"+ "And we'll also use heaps to build a sorting algorithm," +
                            "called heap sort, that is very, very" + "different from either insertion sort or merge sort." +
                            "And it has some nice properties that neither insertions sort ",
                    "https://youtu.be/B7hVxCmfPtM","Length: 52:31 min")
    };

    private LessonList(){
    }

    public static LessonList getSingleton() {
        if(lessonList == null)
            lessonList = new LessonList();
        return lessonList;
    }


    public VideoMetaData[] getLectureList(){
        return videoLectureList;

    }

    public static void setSequentialProgressionEnabled(boolean sequentialProgressionEnabled) {
        isSequentialProgressionEnabled = sequentialProgressionEnabled;
    }

    public boolean isSequentialProgressionEnabled() {
        return isSequentialProgressionEnabled;
    }
}
